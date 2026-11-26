<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.net.URLDecoder,java.text.SimpleDateFormat,java.util.Date,javax.servlet.http.Cookie" %>
<%!
    private String escapeHtml(String value) {
        if (value == null) return "";
        return value.replace("&", "&amp;").replace("<", "&lt;")
                    .replace(">", "&gt;").replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
    private String formatDate(Date date) {
        if (date == null) return "Первое обращение";
        return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date);
    }
%>
<%
    // Данные получаем из Cookie браузера, а не из URL или полей формы.
    String username = null;
    String selectedColor = "blue";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("lab7.username".equals(cookie.getName())) {
                try {
                    username = URLDecoder.decode(cookie.getValue(), "UTF-8");
                } catch (IllegalArgumentException ex) {
                    username = null;
                }
            } else if ("lab7.color".equals(cookie.getName())) {
                selectedColor = cookie.getValue();
            }
        }
    }
    if (!("blue".equals(selectedColor) || "green".equals(selectedColor)
            || "pink".equals(selectedColor) || "sand".equals(selectedColor))) {
        selectedColor = "blue";
    }

    // Состояние сессии было обновлено на странице index.jsp.
    Integer visitCount = (Integer) session.getAttribute("visitCount");
    Date lastVisit = (Date) session.getAttribute("lastVisit");
    Date previousVisit = (Date) session.getAttribute("previousVisit");
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ЛР № 7 — Cookie и HttpSession</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="theme-<%= selectedColor %>">
<main class="card result-card">
    <p class="eyebrow">ЛАБОРАТОРНАЯ РАБОТА № 7 · РЕЗУЛЬТАТ</p>
    <h1>Сохранённые данные</h1>
    <% if (username == null || username.length() == 0 || visitCount == null) { %>
        <p class="error">Данные ещё не заполнены. Сначала откройте форму.</p>
    <% } else { %>
        <p class="lead">Здравствуйте, <strong><%= escapeHtml(username) %></strong>!</p>
        <h2>Данные Cookie (хранятся в браузере)</h2>
        <table>
            <tr><th>Имя Cookie</th><th>Расшифрованное значение</th></tr>
            <tr><td><code>lab7.username</code></td><td><%= escapeHtml(username) %></td></tr>
            <tr><td><code>lab7.color</code></td><td><%= escapeHtml(selectedColor) %></td></tr>
        </table>
        <h2>Переменные HttpSession (хранятся на сервере)</h2>
        <table>
            <tr><th>Переменная сессии</th><th>Значение</th></tr>
            <tr><td><code>visitCount</code></td><td><%= visitCount %></td></tr>
            <tr><td><code>lastVisit</code></td><td><%= escapeHtml(formatDate(lastVisit)) %></td></tr>
            <tr><td><code>previousVisit</code></td><td><%= escapeHtml(formatDate(previousVisit)) %></td></tr>
        </table>
        <p class="hint">Счётчик изменяется при открытии или отправке формы index.jsp.
            Перезагрузка только этой страницы не увеличивает его.</p>
    <% } %>
    <a class="back" href="index.jsp">← Вернуться к форме</a>
</main>
</body>
</html>
