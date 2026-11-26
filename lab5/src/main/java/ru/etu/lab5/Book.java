package ru.etu.lab5;

/**
 * Модель одной книги домашней библиотеки.
 * Используется страницами JSP для вывода таблицы.
 */
public class Book {
    private final String author;
    private final String title;
    private final boolean read;

    /** Создаёт запись о книге.
     * @param author автор
     * @param title название
     * @param read прочитана ли книга
     */
    public Book(String author, String title, boolean read) {
        this.author = author;
        this.title = title;
        this.read = read;
    }

    /** @return автор книги */
    public String getAuthor() { return author; }
    /** @return название книги */
    public String getTitle() { return title; }
    /** @return true, если книга прочитана */
    public boolean isRead() { return read; }
}
