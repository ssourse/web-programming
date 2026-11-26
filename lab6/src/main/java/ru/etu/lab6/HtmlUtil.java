package ru.etu.lab6;

/** Утилита для экранирования значений перед вставкой в HTML. */
public final class HtmlUtil {
    private HtmlUtil() { }
    /** @param input исходный текст; @return HTML-безопасное представление */
    public static String escape(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
