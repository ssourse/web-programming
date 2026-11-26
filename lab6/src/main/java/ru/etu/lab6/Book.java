package ru.etu.lab6;

/** Книга в демонстрационном списке домашней библиотеки. */
public final class Book {
    private final String author;
    private final String title;
    private final boolean read;

    /** @param author автор; @param title название; @param read прочитана ли книга */
    public Book(String author, String title, boolean read) {
        this.author = author;
        this.title = title;
        this.read = read;
    }
    /** @return автор книги */
    public String getAuthor() { return author; }
    /** @return название книги */
    public String getTitle() { return title; }
    /** @return флаг прочтения */
    public boolean isRead() { return read; }
}
