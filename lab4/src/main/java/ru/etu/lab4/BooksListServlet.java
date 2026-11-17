package ru.etu.lab4;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Локализованный список книг читателя (лабораторная работа № 4).
 *
 * <p>Сервлет принимает параметры {@code lang}, {@code name}, {@code read},
 * выбирает {@link Locale}, загружает переводы через {@link ResourceBundle}
 * и возвращает HTML-таблицу на русском или английском языке.
 * Параметр инициализации {@code libraryTitle} содержит ключ ресурса.</p>
 *
 * @author Губина Дарья Александровна
 * @version 1.0
 */
public class BooksListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String BASE_NAME = "ru.etu.lab4.Book";
    private static final String[] STATES = {"yes", "no", "yes"};
    private String libraryTitleKey;

    /** Считывает имя ресурсного ключа из дескриптора web.xml. */
    @Override
    public void init() throws ServletException {
        super.init();
        String value = getInitParameter("libraryTitle");
        libraryTitleKey = value == null ? "library.title" : value;
    }

    /**
     * Формирует страницу с учётом запрошенной локали.
     *
     * @param request запрос; lang=ru|en, name=имя, read=all|yes|no
     * @param response ответ HTML в UTF-8
     * @throws IOException если ответ не удаётся записать
     */
    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String lang = "en".equals(request.getParameter("lang")) ? "en" : "ru";
        Locale locale = "en".equals(lang) ? Locale.US : new Locale("ru", "RU");
        ResourceBundle res = ResourceBundle.getBundle(BASE_NAME, locale);

        String name = request.getParameter("name");
        if (name == null || name.trim().isEmpty()) name = res.getString("reader.default");
        if (name.length() > 100) name = name.substring(0, 100);
        String read = request.getParameter("read");
        if (!"yes".equals(read) && !"no".equals(read)) read = "all";
        String context = request.getContextPath();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html lang='" + lang + "'><head><meta charset='UTF-8'>");
        out.println("<title>" + esc(res.getString("page.title")) + "</title>");
        out.println("<link rel='stylesheet' href='" + esc(context)
                + "/resources/style.css'></head><body><main class='container'>");
        out.println("<p class='eyebrow'>" + esc(res.getString(libraryTitleKey)) + "</p>");
        out.println("<h1>" + esc(res.getString("page.heading")) + "</h1>");
        out.println("<p>" + esc(res.getString("page.reader")) + ": <strong>"
                + esc(name) + "</strong></p>");
        out.println("<p>" + esc(res.getString("page.filter")) + ": "
                + esc(res.getString("filter." + read)) + "</p>");
        out.println("<p>" + esc(res.getString("page.switch")) + "</p>");
        out.println("<nav class='languages'>"
                + languageLink(context, "ru", lang, name, read, res.getString("page.ru"))
                + languageLink(context, "en", lang, name, read, res.getString("page.en"))
                + "</nav>");
        out.println("<table><thead><tr><th>" + esc(res.getString("table.author"))
                + "</th><th>" + esc(res.getString("table.title"))
                + "</th><th>" + esc(res.getString("table.read"))
                + "</th></tr></thead><tbody>");
        int count = 0;
        for (int i = 0; i < STATES.length; i++) {
            if (!"all".equals(read) && !read.equals(STATES[i])) continue;
            String prefix = "book." + (i + 1) + ".";
            out.println("<tr><td>" + esc(res.getString(prefix + "author"))
                    + "</td><td>" + esc(res.getString(prefix + "title"))
                    + "</td><td>" + esc(res.getString("table." + STATES[i]))
                    + "</td></tr>");
            count++;
        }
        out.println("</tbody></table><p>" + esc(res.getString("page.count"))
                + ": " + count + "</p>");
        out.println("<p><a href='" + esc(context) + "/'>"
                + esc(res.getString("page.back")) + "</a></p>");
        out.println("</main></body></html>");
    }

    /** Создаёт ссылку переключения языка, сохраняя параметры запроса. */
    private static String languageLink(String context, String target, String current,
                                       String name, String read, String label) throws IOException {
        String query = "?lang=" + target + "&amp;name="
                + URLEncoder.encode(name, "UTF-8") + "&amp;read=" + read;
        return "<a" + (target.equals(current) ? " class='active'" : "")
                + " href='" + esc(context) + "/BooksList" + query + "'>"
                + esc(label) + "</a>";
    }

    /** Защищает HTML-ответ от внедрения разметки через имя пользователя. */
    private static String esc(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    /** Обрабатывает HTTP GET. */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    /** Обрабатывает HTTP POST. */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }
}
