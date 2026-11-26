package ru.etu.lab9;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервлет списка книг читателя.
 *
 * <p>Получает параметр инициализации {@code libraryTitle} из файла web.xml,
 * динамические параметры {@code name} и {@code read} из HTTP-запроса,
 * фильтрует книги по статусу прочтения и формирует HTML-ответ.</p>
 *
 * @author Губина Дарья Александровна
 * @version 1.0
 */
public class BooksListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Автор, название книги, статус прочтения (yes / no).
    private static final String[][] BOOKS = {
        {"Булгаков М. А.", "Мастер и Маргарита", "yes"},
        {"Пелевин В. О.", "Чапаев и Пустота", "no"},
        {"Ремарк Э. М.", "Три товарища", "yes"}
    };

    private String libraryTitle;

    /** Считывает параметр инициализации libraryTitle из web.xml. */
    @Override
    public void init() throws ServletException {
        super.init();
        String value = getInitParameter("libraryTitle");
        libraryTitle = value == null || value.trim().isEmpty()
                ? "Личная библиотека" : value.trim();
    }

    /**
     * Обрабатывает запрос и выводит HTML-таблицу книг.
     *
     * @param request входящий HTTP-запрос с параметрами name и read
     * @param response HTTP-ответ с HTML-страницей в кодировке UTF-8
     * @throws IOException при ошибках записи ответа
     */
    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        if (name == null || name.trim().isEmpty()) name = "без имени";
        if (name.length() > 100) name = name.substring(0, 100);

        String read = request.getParameter("read");
        if (!"yes".equals(read) && !"no".equals(read)) read = "all";
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String context = request.getContextPath();
        out.println("<!DOCTYPE html><html lang='ru'><head><meta charset='UTF-8'>");
        out.println("<title>Список книг</title><link rel='stylesheet' href='"
                + context + "/resources/style.css'></head><body><main class='container'>");
        out.println("<p class='eyebrow'>" + escapeHtml(libraryTitle) + "</p>");
        out.println("<h1>Список книг читателя " + escapeHtml(name) + "</h1>");
        out.println("<p>Фильтр: " + filterLabel(read) + "</p>");
        out.println("<table><thead><tr><th>Автор</th><th>Название книги</th>");
        out.println("<th>Прочитал</th></tr></thead><tbody>");
        int count = 0;
        for (String[] book : BOOKS) {
            if (!"all".equals(read) && !read.equals(book[2])) continue;
            out.println("<tr><td>" + escapeHtml(book[0]) + "</td><td>"
                    + escapeHtml(book[1]) + "</td><td>"
                    + ("yes".equals(book[2]) ? "Да" : "Нет") + "</td></tr>");
            count++;
        }
        out.println("</tbody></table><p>Найдено книг: " + count + "</p>");
        out.println("<p><a href='" + context + "/'>Вернуться к форме</a></p>");
        out.println("</main></body></html>");
    }

    /** @return человекочитаемая подпись выбранного фильтра */
    private static String filterLabel(String read) {
        if ("yes".equals(read)) return "прочитанные";
        if ("no".equals(read)) return "непрочитанные";
        return "все книги";
    }

    /** Экранирует специальные символы пользовательского текста для HTML. */
    private static String escapeHtml(String value) {
        return value.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    /** Обработка GET-запроса браузера. */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    /** Обработка POST-запроса формы. */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }
}
