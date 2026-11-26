package ru.etu.lab5;

/** Безопасное экранирование текста при выводе пользовательских параметров в HTML. */
public final class HtmlUtil {
    private HtmlUtil() { }

    /**
     * Экранирует специальные символы HTML.
     * @param input строка пользователя
     * @return безопасная для текстового HTML строка
     */
    public static String escape(String input) {
        if (input == null) { return ""; }
        return input.replace("&", "&amp;").replace("<", "&lt;")
            .replace(">", "&gt;").replace(String.valueOf((char)34), "&quot;")
            .replace("'", "&#39;");
    }
}
