# Releasing

This repo has a GitHub Actions release pipeline (`.github/workflows/release.yml`).
It builds and signs the release APK and App Bundle, runs the unit tests, and
publishes a GitHub Release with a changelog and the build artifacts attached.

## Triggering a release

**Option A — from a tag** (recommended): tag a commit and push the tag. Any tag
starting with `v` triggers the pipeline, and the tag name becomes the version
(`v1.1.0` → `versionName=1.1.0`, `versionCode=1110`).

```bash
git tag v1.1.0
git push origin v1.1.0
```

**Option B — manually**: GitHub → **Actions** → *Release* → **Run workflow**.
Enter the version (e.g. `1.1.0`), optional Markdown release notes, and
draft/prerelease flags. A `v<version>` tag is created automatically at the
current HEAD of the selected branch.

Re-running a release for the same tag re-publishes it: the existing release is
deleted and rebuilt from scratch.

## Release signing (required for a distributable APK)

The pipeline signs the release build from GitHub secrets. Configure these in
**Settings → Secrets and variables → Actions**:

| Secret           | Required | Description                                          |
| ---------------- | -------- | ---------------------------------------------------- |
| `KEYSTORE_BASE64` | Yes     | Base64 encoding of your upload keystore file         |
| `STORE_PASSWORD`  | Yes      | Keystore password                                    |
| `KEY_PASSWORD`    | Yes      | Key password                                         |
| `KEY_ALIAS`       | No       | Key alias inside the keystore (default: `upload`)    |

> If the secrets are not configured, the pipeline still runs end-to-end but
> produces an **unsigned** `app-release-unsigned.apk` (with a warning) — handy
> for testing the pipeline before adding a keystore.

Create an upload keystore (a 10,000-day validity is required by Google Play):

```bash
keytool -genkeypair -v -storetype JKS -keystore my-upload-key.jks \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -storepass <store-password> -keypass <key-password> \
  -alias upload -dname "CN=<your name>, OU=<org unit>, O=<org>, L=<city>, ST=<state>, C=<country>"

# then add the base64 as the KEYSTORE_BASE64 secret
base64 -w0 my-upload-key.jks
```

The keystore is written to `my-upload-key.jks` in the CI workspace (git-ignored)
and never stored in the repo — only its base64 form lives in secrets.

## What the pipeline does

1. Resolves the version from the tag or workflow input and derives `versionCode`.
2. Restores/generates the debug keystore and prepares `.env` for the Secrets Gradle plugin.
3. Runs `assembleDebug testDebugUnitTest` as a release gate.
4. Builds `assembleRelease bundleRelease` with `-PversionName/-PversionCode`.
5. Verifies the APK's embedded certificate when signing is enabled.
6. Generates release notes (from your notes or an auto changelog since the previous tag).
7. Publishes a GitHub Release (with the tag) containing the signed APK and AAB.

## Notes

- **Play Store**: the release artifacts include `app-release.aab`, ready to
  upload to Play (e.g. internal track) via the Play Console or Fastlane Supply.
- **Versioning**: versions are supplied entirely by the tag or the workflow
  input — there is no in-repo version bump step. The Gradle defaults
  (`versionName=1.0`, `versionCode=1`) only apply to local builds.
- **CI vs Release**: `.github/workflows/android.yml` remains the everyday CI
  (debug build + tests on `main`); `release.yml` is only used for releases.
