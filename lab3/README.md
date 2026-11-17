# Лабораторная работа № 3 — Технология сервлетов

Губина Дарья Александровна, группа 4312. Дисциплина: Web-программирование.

## Суть задания

Создано веб-приложение «Список книг читателя». Стартовая HTML-страница
передаёт динамические параметры `name` и `read` через GET-запрос в
сервлет `BooksListServlet`. Сервлет извлекает параметр инициализации
`libraryTitle` из `WEB-INF/web.xml`, формирует HTML-таблицу книг и
поддерживает фильтры `all`, `yes` и `no`. `doPost` также поддерживается.

## Требования

- **JDK 8** (установленная пользователем Java 1.8.0_472 подходит)
- **Apache Maven** (в Eclipse выбрана внешняя версия)
- **Apache Tomcat 9** (используется Servlet API **javax.servlet** 4.0.1)

Не заменяйте `javax.servlet` на `jakarta.servlet`: последний несовместим с Tomcat 9.

## Сборка и документация

В каталоге `lab3`, где находится pom.xml:

```cmd
mvn clean install
mvn javadoc:javadoc
```

Maven создаёт `target/lab3.war`, Javadoc размещает в `target/site/apidocs`.

## Запуск

Самый простой вариант: остановить Tomcat в Eclipse, скопировать
`target/lab3.war` в `C:\tools\apache-tomcat-9.0.124\webapps`, затем
запустить **внешний** Tomcat через `bin\catalina.bat run`.

- Стартовая форма: `http://localhost:8081/lab3/`
- Сервлет: `http://localhost:8081/lab3/BooksList?name=Дарья&read=all`
- Фильтр прочитанных: `http://localhost:8081/lab3/BooksList?name=Дарья&read=yes`
- Фильтр непрочитанных: `http://localhost:8081/lab3/BooksList?name=Дарья&read=no`

Можно использовать `8080` вместо `8081`.

Если запускаете приложение прямо в Eclipse через Run As > Run on Server,
не нужно копировать WAR вручную в папку Tomcat: Eclipse использует свою
директорию публикации. Контекст приложения при таком запуске может
зависеть от настроек Eclipse — проверьте итоговый URL в браузере.

## Проверка состава архива

```cmd
jar tf target\lab3.war
```

Внутри должны быть `WEB-INF/classes/ru/etu/lab3/BooksListServlet.class`,
`WEB-INF/web.xml`, `index.html` и `resources/style.css`.

## Содержимое архива проекта

- `src/` — исходники Maven-проекта.
- `pom.xml` — настройки сборки и Javadoc.
- `dist/lab3.war` — **демонстрационный WAR, скомпилированный отдельно
  из исходников** (это не подтверждение `mvn install` на компьютере пользователя).
- `docs/javadoc/` — локально сгенерированный пример Javadoc.

Для сдачи лабораторной необходимо выполнить `mvn clean install` и
`mvn javadoc:javadoc` на своём компьютере и снять реальные скриншоты.

Для GitHub разместите папку проекта внутри общего репозитория
`ssourse/web-programming/lab3`.
