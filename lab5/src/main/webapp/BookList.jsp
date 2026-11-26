<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List,ru.etu.lab5.Book,ru.etu.lab5.BookCatalog,ru.etu.lab5.HtmlUtil" %>
<%-- Главная JSP обработки запроса. Страница выводит данные и подключает таблицу. --%>
<%
    request.setCharacterEncoding("UTF-8");
    String name = request.getParameter("name");
    if (name == null || name.trim().length() == 0) {
        request.setAttribute("errorMessage", "Необходимо указать имя читателя.");
        request.getRequestDispatcher("/ErrorManager.jsp").forward(request, response);
        return;
    }
    String safeName = HtmlUtil.escape(name.trim());
    List<Book> books = BookCatalog.sampleBooks();
    request.setAttribute("books", books);
%>
<!DOCTYPE html>
<html lang="ru">
<head><meta charset="UTF-8"><title>Список книг</title>
<link rel="stylesheet" href="css/style.css"></head>
<body><main class="panel wide">
<p class="eyebrow">JSP · обработка GET-запроса</p>
<h1>Список книг читателя <%= safeName %></h1>
<p>Книг в библиотеке: <strong><%= books.size() %></strong></p>
<jsp:include page="ListData.jsp" />
<a class="back" href="index.jsp">← Вернуться к форме</a>
</main></body></html>
