# Лабораторная № 8 — GWT / GWT-RPC

**Тема:** разработка web-приложения с использованием Google Web Toolkit.
**Среда:** Java 8 JDK, Maven, Eclipse, Apache Tomcat 9.

## Назначение

Выбор читателя (Иванов И. И., Петров П. П., Сидоров С. С.) и просмотр его книг в модальном окне. Клиент написан на Java и компилируется GWT в JavaScript. Данные поступают из `GreetingServiceImpl` по асинхронным RPC-вызовам. Клиентская, серверная и общая части разделены на `client`, `server` и `shared`.

## Сборка (Windows)

```cmd
set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-8.0.472.8-hotspot"
set "PATH=%JAVA_HOME%\bin;%PATH%"
cd /d C:\web-programming\lab8
mvn -v
mvn clean package
```

Сборка должна показать `BUILD SUCCESS`. GWT компилируется на фазе `prepare-package`, затем создаётся `target/lab8.war`. Начальная загрузка зависимостей GWT требует интернета. При нехватке памяти можно задать `MAVEN_OPTS=-Xmx2g`.

## Развёртывание

1. Остановить Tomcat, если он уже запущен в Eclipse или CMD.
2. Скопировать `target/lab8.war` в `C:\tools\apache-tomcat-9.0.124\webapps`.
3. Запустить `catalina.bat run` из `bin` Tomcat.
4. Открыть `http://localhost:8080/lab8/BookModule.html`.

## Документация

```cmd
mvn javadoc:javadoc
```

Открыть `target/site/apidocs/index.html` в браузере. Для отчёта нужны реальные скриншоты работающего приложения, Maven и Javadoc.

## Файлы

- `pom.xml` — Maven, GWT 2.9.0, WAR;
- `src/main/resources/ru/etu/lab8/BookModule.gwt.xml` — точка входа GWT;
- `src/main/java/ru/etu/lab8/client` — GWT UI + RPC интерфейсы;
- `src/main/java/ru/etu/lab8/server` — RPC servlet;
- `src/main/java/ru/etu/lab8/shared` — сериализуемая модель книги;
- `src/main/webapp/WEB-INF/web.xml` — маршрутизация RPC;
- `src/main/webapp/BookModule.html` — HTML контейнер GWT.

## Публикация

Загружать `lab8/src`, `lab8/pom.xml`, `lab8/README.md`, `lab8/.gitignore` в общий репозиторий `https://github.com/ssourse/web-programming/tree/main/lab8`. Результаты сборки `target`, большие WAR и временные файлы не нужны.

**Статус:** исходники и конфигурации подготовлены, XML проверен; реальная сборка через Maven/GWT и запуск в Tomcat должны быть выполнены локально и подтверждены собственными снимками.
