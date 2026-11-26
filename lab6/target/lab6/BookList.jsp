<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List,ru.etu.lab6.Book,ru.etu.lab6.BookCatalog,ru.etu.lab6.HtmlUtil" %>
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
    String user = request.getRemoteUser();
    String safeUser = HtmlUtil.escape(user == null ? "не определён" : user);
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Список книг</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<main class="panel wide">
    <p class="eyebrow">JSP · BASIC · HTTPS</p>
    <h1>Список книг читателя <%= safeName %></h1>
    <p>Книг в библиотеке: <strong><%= books.size() %></strong></p>
    <div class="identity">
        <div>Пользователь: <strong><%= safeUser %></strong></div>
        <div>Метод аутентификации: <strong><%= request.getAuthType() %></strong></div>
        <div>Роль lab6reader: <strong><%= request.isUserInRole("lab6reader") ? "подтверждена" : "нет" %></strong></div>
        <div>Протокол: <strong><%= request.isSecure() ? "HTTPS" : "HTTP (только для проверки)" %></strong></div>
    </div>
    <jsp:include page="ListData.jsp" />
    <a class="back" href="index.jsp">← Вернуться к форме</a>
</main>
</body>
</html>
