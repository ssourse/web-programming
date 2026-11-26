<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ page import="ru.etu.lab5.HtmlUtil" %>
<%
    String message = (String) request.getAttribute("errorMessage");
    if (message == null) { message = "Страница не найдена или возникла ошибка сервера."; }
%>
<!DOCTYPE html>
<html lang="ru"><head><meta charset="UTF-8"><title>Ошибка запроса</title>
<link rel="stylesheet" href="css/style.css"></head><body>
<main class="panel"><p class="eyebrow">Обработка ошибок · JSP</p>
<h1>Ошибка</h1><p><%= HtmlUtil.escape(message) %></p>
<p>Введите имя в форме или добавьте к адресу параметр <code>?name=Дарья</code>.</p>
<a class="back" href="index.jsp">← Вернуться к форме</a>
</main></body></html>
