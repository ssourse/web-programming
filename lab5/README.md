# Лабораторная работа № 5: JSP

Проект для дисциплины «Web-программирование», СПбГЭТУ «ЛЭТИ».
Java 8, Apache Maven, Apache Tomcat 9, Servlet 4.0 / JSP 2.3.

## Сборка
`mvn clean install`

## Развёртывание
Скопировать `target/lab5.war` в `C:/tools/apache-tomcat-9.0.124/webapps/` и перезапустить Tomcat.

- Форма: http://localhost:8080/lab5/
- Результат: http://localhost:8080/lab5/BookList.jsp?name=Дарья
- Ошибка: http://localhost:8080/lab5/BookList.jsp

## Javadoc
`mvn javadoc:javadoc`; затем открыть `target/site/apidocs/index.html`.

## Структура
`index.jsp` — форма ввода; `BookList.jsp` — обработка запроса; `ListData.jsp` — директива include и таблица; `ErrorManager.jsp` — обработчик ошибки. Классы `Book`, `BookCatalog`, `HtmlUtil` документируются Javadoc.

При запуске сборки Maven в Eclipse выберите **JDK 8 (Eclipse Adoptium)**, а не `C:/Program Files/Java/jre-1.8`.
