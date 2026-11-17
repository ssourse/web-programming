# Лабораторная работа №1. Web-программирование

Студент: Губина Дарья Александровна, группа 4312. Преподаватель: Павловский М. Г.

## Назначение

Первая работа по методическим указаниям проверяет установку Apache Tomcat 9, его настройку в Eclipse, подключение Maven и работу сервера на портах 8080 и 8081. Поскольку пользователь также запросил проект, приложен **дополнительный** минимальный Maven WAR-проект `lab1`. Он не подменяет обязательные пункты по настройке среды.

## Состав

- `pom.xml`: Maven-проект WAR (Servlet API 4.0 для Tomcat 9).
- `src/main/webapp/index.jsp`: страница для проверки работы приложения.
- `src/main/webapp/resources/cat.gif`: демонстрационное изображение из предоставленного образца.
- `src/main/webapp/WEB-INF/web.xml`: дескриптор приложения.
- `tomcat-conf/server.xml`: **учебный образец** полного XML с портами 8080 и 8081; не рекомендуется вслепую заменять им конфигурацию установленного сервера.
- `tomcat-conf/configure-tomcat.ps1`: меняет **существующий** `conf/server.xml` с резервным копированием.
- `target/lab1.war`: заранее упакованный рабочий WAR для запуска без Maven. Это упаковка исходников, а **не доказательство** запуска Maven install.

## Импорт в Eclipse

1. Установите JDK (например, 17), Apache Tomcat 9, Eclipse IDE for Enterprise Java and Web Developers и Apache Maven.
2. Eclipse → File → Import → Maven → Existing Maven Projects. Выберите папку `lab1` и закончите импорт.
3. Window → Preferences → Server → Runtime Environments → Add → Apache Tomcat v9.0 → укажите каталог установленного Tomcat.
4. Window → Show View → Servers → New → Server → Tomcat v9.0 Server.
5. Для сборки: правый клик по `lab1` → Run As → Maven install (для скачивания зависимостей нужен интернет).
6. Для запуска можно скопировать `target/lab1.war` в `<TOMCAT_HOME>/webapps` и перезапустить Tomcat; откройте http://localhost:8080/lab1/ или http://localhost:8081/lab1/.
7. Для изменения порта откройте `tomcat-conf/configure-tomcat.ps1`, запустите с параметром `-TomcatHome` и проверьте изменённый `<TOMCAT_HOME>/conf/server.xml` (сделана копия `.bak`).

**Важно:** Eclipse может запускать сервер со своей копией `Servers/Tomcat v9.0 Server at localhost-config/server.xml`. Если порт 8081 не открывается при запуске из Eclipse, добавьте второй `Connector` также в эту конфигурацию. Не запускайте два экземпляра Tomcat на одном порту одновременно.

## Требуемые реальные скриншоты

Смотрите отдельный файл «Инструкция_по_скриншотам_ЛР1.docx». Шаблон отчёта содержит места для вставки. Скриншоты не созданы искусственно: подтверждение работы именно вашей ОС и Eclipse необходимо получить локально.
