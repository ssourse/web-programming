package ru.etu.lab5;

import java.util.Arrays;
import java.util.List;

/** Создаёт учебный набор книг, отображаемый на JSP-странице. */
public final class BookCatalog {
    private BookCatalog() { }

    /** @return неизменяемый по смыслу список учебных записей */
    public static List<Book> sampleBooks() {
        return Arrays.asList(
            new Book("Булгаков М. А.", "Мастер и Маргарита", true),
            new Book("Достоевский Ф. М.", "Преступление и наказание", true),
            new Book("Пелевин В. О.", "Чапаев и Пустота", false)
        );
    }
}
