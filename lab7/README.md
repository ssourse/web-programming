# Лабораторная работа № 7 — Cookie и HttpSession

Дисциплина: Web-программирование. СПбГЭТУ «ЛЭТИ».

По методическим указаниям: JSP-страница `index.jsp` запрашивает имя пользователя и цвет фона. Значения сохраняются в Cookie; число обращений и дата последнего обращения — в `HttpSession`. Страница `Result.jsp` отображает оба вида данных на цветном фоне.

## Среда

- Windows 11, JDK 8 (`javax.servlet` / Servlet 4.0), Apache Tomcat 9;
- Eclipse IDE for Enterprise Java and Web Developers;
- Apache Maven, упаковка WAR.

## Файлы

- `pom.xml` — Maven проект (готовит `lab7.war`);
- `src/main/webapp/index.jsp` — ввод и обработка, Cookie и счётчик сессии;
- `src/main/webapp/Result.jsp` — вывод Cookie и переменных сессии;
- `src/main/webapp/WEB-INF/web.xml` — стартовая страница и тайм-аут сессии 20 минут;
- `src/main/webapp/css/style.css` — четыре цвета страницы.

## Сборка и запуск

```cmd
set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-8.0.472.8-hotspot"
cd /d C:\web-programming\lab7
mvn clean install
```

Убедитесь в `BUILD SUCCESS`, остановите Tomcat, скопируйте `target\lab7.war` в `C:\tools\apache-tomcat-9.0.124\webapps`, затем запустите Tomcat:

```cmd
set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-8.0.472.8-hotspot"
cd /d C:\tools\apache-tomcat-9.0.124\bin
catalina.bat run
```

Адрес: http://localhost:8080/lab7/

## Проверка

1. Откройте форму; укажите имя `Дарья`, цвет «Голубой», отправьте.
2. Страница `Result.jsp` покажет Cookie `lab7.username`, `lab7.color`, счётчик и даты сессии.
3. В Chrome `F12 → Application → Storage → Cookies → http://localhost:8080` проверьте реальные Cookie. Значение имени в хранилище может быть URL-кодировано (`%D0...`).
4. Вернитесь к форме. Имя и цвет подставятся из Cookie; счётчик увеличится. Измените имя на `Анна`, цвет на «Зелёный» и повторите проверку.

Счётчик считает **запросы к index.jsp** (GET и POST), а не все запросы на сайт. Поэтому после первого открытия формы и первой отправки значения будет не меньше двух. `Result.jsp` только отображает сохранённые значения.

Cookie действуют 7 суток. Данные `HttpSession` сохраняются на сервере и исчезают по завершении сессии (20 мин неактивности). Идентификатор сессии `JSESSIONID` не стоит публиковать на скриншотах без маскировки.

## Публичный репозиторий

Размещайте `src/`, `pom.xml`, `README.md`, `.gitignore` в папке `lab7` общего репозитория. `target/` и `*.war` можно не загружать: приложение собирается командой Maven. Не публикуйте cookies сессионной авторизации или секреты.

Ссылка после загрузки: https://github.com/ssourse/web-programming/tree/main/lab7
