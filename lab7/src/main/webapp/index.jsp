<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date,java.net.URLEncoder,java.net.URLDecoder,javax.servlet.http.Cookie" %>
<%!
    // Защита HTML-атрибутов и текста от специальных символов.
    private String escapeHtml(String value) {
        if (value == null) return "";
        return value.replace("&", "&amp;").replace("<", "&lt;")
                    .replace(">", "&gt;").replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }

    // Разрешаем только четыре заранее определённых цвета.
    private boolean allowedColor(String color) {
        return "blue".equals(color) || "green".equals(color)
            || "pink".equals(color) || "sand".equals(color);
    }
%>
<%
    request.setCharacterEncoding("UTF-8");

    // 1. Извлекаем Cookie из входящего HTTP-запроса.
    String rememberedName = "";
    String rememberedColor = "blue";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("lab7.username".equals(cookie.getName())) {
                try {
                    rememberedName = URLDecoder.decode(cookie.getValue(), "UTF-8");
                } catch (IllegalArgumentException ex) {
                    rememberedName = "";
                }
            } else if ("lab7.color".equals(cookie.getName())) {
                rememberedColor = cookie.getValue();
            }
        }
    }
    if (!allowedColor(rememberedColor)) rememberedColor = "blue";

    // 2. В переменных HttpSession учитываем посещения именно index.jsp.
    Integer previousCount = (Integer) session.getAttribute("visitCount");
    int visitCount = (previousCount == null ? 0 : previousCount) + 1;
    Date previousVisit = (Date) session.getAttribute("lastVisit");
    Date now = new Date();
    session.setAttribute("visitCount", visitCount);
    session.setAttribute("previousVisit", previousVisit);
    session.setAttribute("lastVisit", now);

    // 3. При отправке формы устанавливаем Cookie и перенаправляем браузер.
    // После redirect браузер сделает НОВЫЙ HTTP-запрос с новыми Cookie.
    String error = "";
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String username = request.getParameter("username");
        String color = request.getParameter("color");
        username = username == null ? "" : username.trim();
        if (username.length() == 0) {
            error = "Введите имя пользователя.";
        } else if (username.length() > 60) {
            error = "Имя не должно быть длиннее 60 символов.";
        } else if (!allowedColor(color)) {
            error = "Выберите цвет из списка.";
        } else {
            Cookie userCookie = new Cookie("lab7.username", URLEncoder.encode(username, "UTF-8"));
            Cookie colorCookie = new Cookie("lab7.color", color);
            String appPath = request.getContextPath();
            userCookie.setPath(appPath);
            colorCookie.setPath(appPath);
            userCookie.setMaxAge(7 * 24 * 60 * 60); // Семь суток
            colorCookie.setMaxAge(7 * 24 * 60 * 60);
            userCookie.setHttpOnly(true);
            colorCookie.setHttpOnly(true);
            response.addCookie(userCookie);
            response.addCookie(colorCookie);
            response.sendRedirect(response.encodeRedirectURL(appPath + "/Result.jsp"));
            return;
        }
        rememberedName = username;
        if (allowedColor(color)) rememberedColor = color;
    }
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ЛР № 7 — ввод данных</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="form-screen">
    <main class="card">
        <p class="eyebrow">ЛАБОРАТОРНАЯ РАБОТА № 7 · JSP</p>
        <h1>Cookie и сеанс пользователя</h1>
        <p class="lead">Введите имя и выберите цвет. Значения сохранятся в Cookie,
            а число обращений и дата посещения — в HttpSession.</p>

        <% if (!error.isEmpty()) { %>
            <p class="error" role="alert"><%= escapeHtml(error) %></p>
        <% } %>

        <form method="post" action="index.jsp" accept-charset="UTF-8">
            <label for="username">Имя пользователя</label>
            <input id="username" type="text" name="username" maxlength="60"
                   value="<%= escapeHtml(rememberedName) %>" required>
            <label for="color">Цвет страницы результата</label>
            <select id="color" name="color">
                <option value="blue" <%= "blue".equals(rememberedColor) ? "selected" : "" %>>Голубой</option>
                <option value="green" <%= "green".equals(rememberedColor) ? "selected" : "" %>>Зелёный</option>
                <option value="pink" <%= "pink".equals(rememberedColor) ? "selected" : "" %>>Розовый</option>
                <option value="sand" <%= "sand".equals(rememberedColor) ? "selected" : "" %>>Песочный</option>
            </select>
            <button type="submit">Сохранить и показать результат</button>
        </form>
        <p class="hint">Обращений к index.jsp в текущем сеансе: <strong><%= visitCount %></strong></p>
        <p class="hint">Повторное открытие этой формы увеличивает счётчик.</p>
    </main>
</body>
</html>
