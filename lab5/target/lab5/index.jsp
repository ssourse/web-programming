<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head><meta charset="UTF-8"><title>Домашняя библиотека — ЛР 5</title>
<link rel="stylesheet" href="css/style.css"></head>
<body><main class="panel">
<p class="eyebrow">Web-программирование · Лабораторная № 5</p>
<h1>Домашняя библиотека</h1>
<p>Введите имя читателя, чтобы получить список книг.</p>
<form action="BookList.jsp" method="get">
<label for="name">Имя читателя</label>
<input id="name" name="name" type="text" maxlength="80" placeholder="Например, Дарья" required>
<button type="submit">Показать книги</button>
</form>
<p class="hint">Попробуйте также открыть BookList.jsp без параметра name, чтобы проверить страницу ошибки.</p>
</main></body></html>
