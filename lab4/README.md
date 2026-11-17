# Лабораторная работа № 4 — Интернационализация web-приложения

**Студент:** Губина Дарья Александровна, группа 4312.  
**Дисциплина:** Web-программирование, СПбГЭТУ «ЛЭТИ».

В основе — список книг читателя из лабораторной № 3. Сервлет `BooksListServlet`
получает `lang=ru` или `lang=en`, выбирает `Locale` и загружает строки через
`ResourceBundle` из `src/main/resources/ru/etu/lab4/Book*.properties`.

## Среда

- JDK 8 (важно: при Maven Build выбирать JDK, не JRE)
- Apache Maven 3.x
- Apache Tomcat 9.0.x
- Eclipse IDE for Enterprise Java and Web Developers

## Сборка и документация

```cmd
cd /d C:\web-programming\lab4
mvn clean install
mvn javadoc:javadoc
```

В результате `target/lab4.war` — развернуть в `C:\tools\apache-tomcat-9.0.124\webapps`.
Документация: `target/site/apidocs/index.html`.

## Проверка

- `http://localhost:8080/lab4/BooksList?lang=ru`
- `http://localhost:8080/lab4/BooksList?lang=en`
- `http://localhost:8080/lab4/` — форма с выбором языка и фильтра

Допустимы `name` (имя читателя), `read=all|yes|no` и `lang=ru|en`.
После переключения языка имя и фильтр сохраняются.

**Почему русский файл содержит `\uXXXX`:** Java 8 стандартно интерпретирует
`.properties` через ISO-8859-1; Unicode-последовательности гарантируют
корректную кириллицу. Значения читаются без дополнительной перекодировки.

Каталог `dist/` содержит предварительно собранный демонстрационный WAR,
а `docs/javadoc/` — независимо сгенерированную документацию. Они не заменяют
проверку `mvn clean install` и `mvn javadoc:javadoc` на своей машине.
