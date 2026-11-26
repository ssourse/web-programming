<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Лабораторная № 6 — Вход в библиотеку</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<main class="panel">
    <p class="eyebrow">ЛР № 6 · Аутентификация и авторизация</p>
    <h1>Защищённая библиотека</h1>
    <p>Введите имя читателя. При открытии списка книг Tomcat запросит
       логин и пароль пользователя с ролью <code>lab6reader</code>.</p>
    <form method="get" action="BookList.jsp">
        <label for="name">Имя читателя</label>
        <input id="name" name="name" type="text" value="Дарья" required maxlength="100">
        <button type="submit">Открыть список книг</button>
    </form>
    <p class="hint">Для лабораторной используйте локальный Tomcat.
       BASIC через обычный HTTP не защищает пароль; окончательную проверку
       проводите по HTTPS на порту 8443.</p>
</main>
</body>
</html>
