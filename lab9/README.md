# Лабораторная работа № 9. Модульное тестирование web-приложения

**Студент:** Губина Дарья Александровна, группа 4312  
**Тема:** JUnit 4 + Mockito 3.11.2, модульные тесты сервлета из лабораторной № 3.

## Состав

- `src/main/java/ru/etu/lab9/BooksListServlet.java`: сервлет «Список книг» из лабораторной № 3, адаптированный под пакет lab9;
- `src/test/java/ru/etu/lab9/BooksListServletTest.java`: девять независимых модульных тестов;
- `pom.xml`: Servlet API 4, JUnit 4, Mockito 3.11.2 и Maven Surefire;
- `src/main/webapp`: демонстрационная форма и конфигурация развёртывания.

## Требования

Windows 11, JDK 8 (не JRE), Maven 3.x. Tomcat 9 **не нужен для запуска модульных тестов**.
Для просмотра приложения в браузере можно дополнительно развернуть `lab9.war` в Tomcat 9.

## Запуск тестов

```cmd
set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-8.0.472.8-hotspot"
set "PATH=%JAVA_HOME%\bin;%PATH%"
cd /d C:\web-programming\lab9
mvn clean test
```

Ожидаемый признак успеха: `BUILD SUCCESS` и сводка `Tests run: 9, Failures: 0, Errors: 0, Skipped: 0` при исправном окружении. Эти числа нужно подтвердить **фактическим запуском**, а не переносить в отчёт заранее.

Maven создаст реальные отчёты в `target/surefire-reports/`:

- `ru.etu.lab9.BooksListServletTest.txt`;
- `TEST-ru.etu.lab9.BooksListServletTest.xml`.

Для сборки WAR: `mvn clean install`. Он будет лежать в `target/lab9.war`.

## Что проверяется

Полный список книг, фильтры `yes` и `no`, неправильный фильтр, пустое имя, HTML-экранирование, POST-запрос, init-параметр и резервный заголовок. В тестах Mockito заменяет `HttpServletRequest`, `HttpServletResponse` и `ServletConfig`.

## GitHub

Добавить в общий репозиторий: `https://github.com/ssourse/web-programming/tree/main/lab9`.
Загружать `src/`, `pom.xml`, `README.md`, `.gitignore`. `target/` с сотнями файлов не загружать.

## Важно

При первом запуске Maven скачивает JUnit, Mockito и транзитивные библиотеки из интернета. Если Maven не может скачать зависимости, это проблема подключения или настроек Maven, а не обязательно ошибка тестов.
