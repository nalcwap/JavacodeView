package com.example.javacode;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.graphics.Color;
import android.graphics.Typeface;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashMap;
import java.util.Map;

public class JavaSyntaxHighlighter {

    private static final int CLASS_COLOR = Color.rgb(78, 201, 176);      // Teal
    private static final int METHOD_COLOR = Color.rgb(204, 120, 50);     // Orange
    private static final int MEMBER_COLOR = Color.rgb(152, 118, 170);    // Purple
    private static final int GETTER_COLOR = Color.rgb(106, 135, 89);     // Green
    private static final int KEYWORD_COLOR = Color.rgb(204, 120, 50);    // Orange
    private static final int TYPE_COLOR = Color.rgb(78, 201, 176);       // Teal
    private static final int STRING_COLOR = Color.rgb(106, 135, 89);     // Green
    private static final int NUMBER_COLOR = Color.rgb(104, 151, 187);    // Blue
    private static final int COMMENT_COLOR = Color.rgb(117, 113, 94);    // Gray
    private static final int ANNOTATION_COLOR = Color.rgb(184, 215, 163);// Light Green

    public SpannableString highlight(String code) {
        SpannableString spannable = new SpannableString(code);
        
        // ترتیب مهم است! اولویت با مواردی است که باید اول پردازش شوند
        highlightMultiLineComments(spannable);
        highlightSingleLineComments(spannable);
        highlightStrings(spannable);
        highlightAnnotations(spannable);
        highlightNumbers(spannable);
        highlightClasses(spannable);
        highlightGetters(spannable);
        highlightMembers(spannable);
        highlightKeywordsAndTypes(spannable);
        
        return spannable;
    }

    private void highlightClasses(SpannableString spannable) {
        // تشخیص کلاس‌ها (کلماتی که با حرف بزرگ شروع می‌شوند)
        Pattern pattern = Pattern.compile("\\b([A-Z][a-zA-Z0-9_]*)\\b");
        Matcher matcher = pattern.matcher(spannable);
        
        while (matcher.find()) {
            int start = matcher.start(1);
            int end = matcher.end(1);
            
            spannable.setSpan(
                new ForegroundColorSpan(CLASS_COLOR),
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );
            
            spannable.setSpan(
                new StyleSpan(Typeface.BOLD),
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );
        }
    }

    private void highlightGetters(SpannableString spannable) {
        // تشخیص متدهای getter
        Pattern pattern = Pattern.compile("\\b(get[A-Z][a-zA-Z0-9_]*)\\b");
        Matcher matcher = pattern.matcher(spannable);
        
        while (matcher.find()) {
            int start = matcher.start(1);
            int end = matcher.end(1);
            
            spannable.setSpan(
                new ForegroundColorSpan(GETTER_COLOR),
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );
        }
    }

    private void highlightMembers(SpannableString spannable) {
        // تشخیص اعضای کلاس (کلمات بعد از نقطه)
        Pattern pattern = Pattern.compile("\\.([a-zA-Z_][a-zA-Z0-9_]*)\\b");
        Matcher matcher = pattern.matcher(spannable);
        
        while (matcher.find()) {
            int start = matcher.start(1);
            int end = matcher.end(1);
            
            spannable.setSpan(
                new ForegroundColorSpan(MEMBER_COLOR),
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );
        }
    }

    private void highlightKeywordsAndTypes(SpannableString spannable) {
        String[] keywords = {
            "abstract", "assert", "boolean", "break", "byte", "case", "catch",
            "char", "class", "const", "continue", "default", "do", "double",
            "else", "enum", "extends", "final", "finally", "float", "for",
            "goto", "if", "implements", "import", "instanceof", "int",
            "interface", "long", "native", "new", "package", "private",
            "protected", "public", "return", "short", "static", "strictfp",
            "super", "switch", "synchronized", "this", "throw", "throws",
            "transient", "try", "void", "volatile", "while", "true", "false", "null"
        };
        
        for (String keyword : keywords) {
            highlightPattern(spannable, "\\b" + keyword + "\\b", KEYWORD_COLOR, Typeface.BOLD);
        }
        
        String[] types = {
            "boolean", "byte", "char", "double", "float", "int", "long", "short", "void"
        };
        
        for (String type : types) {
            highlightPattern(spannable, "\\b" + type + "\\b", TYPE_COLOR, Typeface.BOLD);
        }
    }

    private void highlightMultiLineComments(SpannableString spannable) {
        highlightPattern(spannable, "/\\*.*?\\*/", COMMENT_COLOR, Typeface.ITALIC);
    }

    private void highlightSingleLineComments(SpannableString spannable) {
        highlightPattern(spannable, "//.*?$", COMMENT_COLOR, Typeface.ITALIC);
    }

    private void highlightStrings(SpannableString spannable) {
        highlightPattern(spannable, "\".*?\"", STRING_COLOR, Typeface.NORMAL);
    }

    private void highlightAnnotations(SpannableString spannable) {
        highlightPattern(spannable, "@\\w+", ANNOTATION_COLOR, Typeface.BOLD);
    }

    private void highlightNumbers(SpannableString spannable) {
        highlightPattern(spannable, "\\b\\d+\\.?\\d*\\b", NUMBER_COLOR, Typeface.NORMAL);
    }

    private void highlightPattern(SpannableString spannable, String regex, int color, int style) {
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(spannable);
        
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            
            spannable.setSpan(
                new ForegroundColorSpan(color),
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );
            
            spannable.setSpan(
                new StyleSpan(style),
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );
        }
    }
}