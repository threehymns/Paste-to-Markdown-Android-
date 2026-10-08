package com.example

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration

class MarkdownSyntaxHighlighter(private val colorScheme: ColorScheme) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val annotatedString = buildAnnotatedString {
            append(text.text)
            
            val plainText = text.text
            
            // Blockquotes
            val blockquoteRegex = Regex("(?m)^>\\s+.*$")
            blockquoteRegex.findAll(plainText).forEach { match ->
                addStyle(SpanStyle(color = colorScheme.secondary), match.range.first, match.range.last + 1)
            }

            // Headers
            val headerRegex = Regex("(?m)^#{1,6}\\s+.*$")
            headerRegex.findAll(plainText).forEach { match ->
                addStyle(SpanStyle(fontWeight = FontWeight.Bold, color = colorScheme.primary), match.range.first, match.range.last + 1)
            }

            // Bold
            val boldRegex = Regex("\\*\\*.*?\\*\\*|__.*?__")
            boldRegex.findAll(plainText).forEach { match ->
                addStyle(SpanStyle(fontWeight = FontWeight.Bold), match.range.first, match.range.last + 1)
            }

            // Italic
            val italicRegex = Regex("(?<!\\*)\\*(?!\\*).*?(?<!\\*)\\*(?!\\*)|(?<!_)_(?!_).*?(?<!_)_(?!_)")
            italicRegex.findAll(plainText).forEach { match ->
                addStyle(SpanStyle(fontStyle = FontStyle.Italic), match.range.first, match.range.last + 1)
            }

            // Strikethrough
            val strikethroughRegex = Regex("~~.*?~~")
            strikethroughRegex.findAll(plainText).forEach { match ->
                addStyle(SpanStyle(textDecoration = TextDecoration.LineThrough), match.range.first, match.range.last + 1)
            }

            // Links
            val linkRegex = Regex("\\[.*?\\]\\(.*?\\)")
            linkRegex.findAll(plainText).forEach { match ->
                addStyle(SpanStyle(color = colorScheme.tertiary, textDecoration = TextDecoration.Underline), match.range.first, match.range.last + 1)
            }

            // Lists (Unordered & Ordered)
            val listRegex = Regex("(?m)^\\s*(?:[-+*]|\\d+\\.)\\s+.*$")
            listRegex.findAll(plainText).forEach { match ->
                addStyle(SpanStyle(color = colorScheme.primary), match.range.first, match.range.last + 1)
            }
            
            // Tables (simple markdown tables)
            val tableRegex = Regex("(?m)^\\|.*\\|$")
            tableRegex.findAll(plainText).forEach { match ->
                addStyle(SpanStyle(background = colorScheme.surfaceVariant.copy(alpha = 0.3f)), match.range.first, match.range.last + 1)
            }
            
            // Horizontal Rules
            val hrRegex = Regex("(?m)^\\s*(?:-{3,}|\\*{3,}|_{3,})\\s*$")
            hrRegex.findAll(plainText).forEach { match ->
                addStyle(SpanStyle(color = colorScheme.outline, fontWeight = FontWeight.Bold), match.range.first, match.range.last + 1)
            }

            // Inline Code
            val inlineCodeRegex = Regex("`[^`]+`")
            inlineCodeRegex.findAll(plainText).forEach { match ->
                addStyle(
                    SpanStyle(
                        background = colorScheme.surfaceVariant,
                        color = colorScheme.onSurfaceVariant,
                        fontFamily = FontFamily.Monospace
                    ), 
                    match.range.first, 
                    match.range.last + 1
                )
            }

            // Code Blocks
            val codeBlockRegex = Regex("```[\\s\\S]*?```")
            codeBlockRegex.findAll(plainText).forEach { match ->
                addStyle(
                    SpanStyle(
                        background = colorScheme.surfaceVariant,
                        color = colorScheme.onSurfaceVariant,
                        fontFamily = FontFamily.Monospace
                    ), 
                    match.range.first, 
                    match.range.last + 1
                )
            }
        }
        
        return TransformedText(annotatedString, OffsetMapping.Identity)
    }
}
