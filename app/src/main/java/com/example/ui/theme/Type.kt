package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val GoogleSansFlexFont = GoogleFont("Google Sans Flex")
val RobotoFlexFont = GoogleFont("Roboto Flex")

val BrandFontFamily = FontFamily(
    Font(googleFont = GoogleSansFlexFont, fontProvider = provider),
    Font(googleFont = RobotoFlexFont, fontProvider = provider)
)

val PlainFontFamily = FontFamily(
    Font(googleFont = GoogleSansFlexFont, fontProvider = provider),
    Font(googleFont = RobotoFlexFont, fontProvider = provider)
)

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = BrandFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontFamily = BrandFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = BrandFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = BrandFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = BrandFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = BrandFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = PlainFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = PlainFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = PlainFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = PlainFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = PlainFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = PlainFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelLarge = TextStyle(
        fontFamily = PlainFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = PlainFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = PlainFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

val EmphasizedTypography = Typography(
    displayLarge = Typography.displayLarge.copy(fontWeight = FontWeight.Bold),
    displayMedium = Typography.displayMedium.copy(fontWeight = FontWeight.Bold),
    displaySmall = Typography.displaySmall.copy(fontWeight = FontWeight.Bold),
    headlineLarge = Typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
    headlineMedium = Typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
    headlineSmall = Typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
    titleLarge = Typography.titleLarge.copy(fontWeight = FontWeight.Bold),
    titleMedium = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
    titleSmall = Typography.titleSmall.copy(fontWeight = FontWeight.ExtraBold),
    bodyLarge = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
    bodyMedium = Typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
    bodySmall = Typography.bodySmall.copy(fontWeight = FontWeight.Bold),
    labelLarge = Typography.labelLarge.copy(fontWeight = FontWeight.ExtraBold),
    labelMedium = Typography.labelMedium.copy(fontWeight = FontWeight.ExtraBold),
    labelSmall = Typography.labelSmall.copy(fontWeight = FontWeight.ExtraBold)
)
