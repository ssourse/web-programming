# Лабораторная № 6 — аутентификация и авторизация пользователей

**Среда:** Java 8 (JDK), Eclipse, Maven, Apache Tomcat 9.0.124.

## Задача

Продолжение ЛР № 5 («Домашняя библиотека»): JSP-приложение с защитой `BookList.jsp` с помощью **HTTP BASIC** и роли `lab6reader`; проверка работы через **HTTPS** по порту 8443 с локальным сертификатом.

## Сборка

```cmd
cd /d C:\web-programming\lab6
set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-8.0.472.8-hotspot"
mvn clean install
```

Архив: `target/lab6.war`. В ZIP также есть `demo-lab6.war` — проверочный архив, упакованный из скомпилированных Java-классов без Maven; для сдачи соберите свой `target/lab6.war` командой `mvn clean install`. Положить в `C:\tools\apache-tomcat-9.0.124\webapps` при остановленном Tomcat. Запустить через `catalina.bat run`.

## Настройки Tomcat

1. **Не заменяйте** рабочие `conf/server.xml` и `conf/tomcat-users.xml`. Сначала сделайте резервные копии.
2. В `conf/tomcat-users.xml` перед `</tomcat-users>` добавьте роль `lab6reader` и пользователя `reader6` с локальным тестовым паролем. Пример: `tomcat-config/tomcat-users.xml.example`.
3. Сертификат сделайте на своём ПК с помощью `keytool -genkeypair`; см. отдельную инструкцию DOCX. Хранилище и пароли никогда не загружайте на GitHub.
4. В рабочий `conf/server.xml` **внутрь** `<Service name="Catalina">` добавьте SSL-коннектор из `tomcat-config/ssl-connector.xml.snippet`, заменив пароль и проверив, что порт 8443 не дублируется.
5. Убедитесь, что у Tomcat в `server.xml` уже имеется `UserDatabaseRealm` (обычно присутствует по умолчанию).

## Адреса

- Открытая форма: `http://localhost:8080/lab6/`
- Проверка BASIC (только на локальном компьютере): `http://localhost:8080/lab6/BookList.jsp?name=Дарья`
- Проверка HTTPS и BASIC: `https://localhost:8443/lab6/BookList.jsp?name=Дарья`

При первом запросе защищённой страницы браузер запросит `reader6` и локальный пароль. В защищённой странице видны метод `BASIC`, роль и признак HTTPS. При самоподписанном сертификате браузер выдаст предупреждение; его можно принять **только для своего localhost**.

**Внимание:** HTTP BASIC передаёт учётные данные в Base64, это не шифрование. В реальной системе нужны HTTPS, надёжное управление секретами и паролями; пример предназначен для учебного локального Tomcat.

## Репозиторий

https://github.com/ssourse/web-programming/tree/main/lab6
