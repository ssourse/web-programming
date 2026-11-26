<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.etu.lab6.HtmlUtil" %>
<%
    Object error = request.getAttribute("errorMessage");
    String safeError = HtmlUtil.escape(error == null ? "Ошибка запроса." : error.toString());
%>
<!DOCTYPE html>
<html lang="ru"><head><meta charset="UTF-8"><title>Ошибка</title>
<link rel="stylesheet" href="css/style.css"></head><body>
<main class="panel"><p class="eyebrow">Ошибка обработки запроса</p>
<h1>Не удалось показать список книг</h1><p><%= safeError %></p>
<a class="back" href="index.jsp">Вернуться к форме</a></main></body></html>
