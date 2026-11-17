# Лабораторная работа № 2. Web-программирование

**Тема:** Создание web-проекта с использованием системы сборки Maven.  
**Студент:** Губина Дарья Александровна, группа 4312.

## Назначение проекта

Простой web-проект с компоновкой `war`, соответствующей архетипу `maven-archetype-webapp` (v1.4). Главная страница `index.jsp` выводит `Hello world!`, сведения о работе и изображение `resources/cat.gif`. Приложение предназначено для Apache Tomcat 9 / Java 8.

## Сборка

Требуются JDK 8 и Apache Maven. Из каталога, содержащего `pom.xml`:

```cmd
mvn clean install
```

После успешной сборки Maven создаст `target/lab2.war`, а лог завершится `BUILD SUCCESS`.

## Развертывание

1. Остановить отдельный процесс Tomcat, если порт уже занят.
2. Скопировать `target/lab2.war` в `<TOMCAT_HOME>/webapps` и запустить Tomcat **или** в Eclipse выбрать проект -> Run As -> Run on Server -> Tomcat v9.0.
3. Проверить `http://localhost:8080/lab2/` либо `http://localhost:8081/lab2/` (если второй Connector настроен в server.xml).

**Важно:** при запуске Tomcat из Eclipse его каталог развертывания часто отличается от `<TOMCAT_HOME>/webapps`. Для Eclipse надежнее использовать *Run on Server*, а при ручном копировании WAR запускать отдельный экземпляр Tomcat.

## Проверка WAR

```cmd
jar tf target\lab2.war
```

Внутри WAR должны быть `index.jsp`, `WEB-INF/web.xml`, `resources/cat.gif`, `META-INF/MANIFEST.MF` и служебные Maven-метаданные.

## Структура

```
lab2/
├── pom.xml
├── src/main/webapp/
│   ├── index.jsp
│   ├── resources/cat.gif
│   └── WEB-INF/web.xml
├── dist/lab2.war           # демонстрационно упакованный архив
└── README.md
```

Файл `dist/lab2.war` подготовлен из исходников как иллюстрация содержимого WAR. Он **не является подтверждением выполнения** `mvn install` на компьютере студента; для сдачи лабораторной следует самостоятельно выполнить команду и сделать соответствующий скриншот.

## GitHub

Рекомендуемая папка общего репозитория: `web-programming/lab2`.
