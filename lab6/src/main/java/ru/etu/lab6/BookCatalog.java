package ru.etu.lab6;

import java.util.Arrays;
import java.util.List;

/** Демонстрационный источник данных для JSP-приложения. */
public final class BookCatalog {
    private BookCatalog() { }
    /** @return список из трёх книг для отображения */
    public static List<Book> sampleBooks() {
        return Arrays.asList(
            new Book("Булгаков М. А.", "Мастер и Маргарита", true),
            new Book("Достоевский Ф. М.", "Преступление и наказание", true),
            new Book("Пелевин В. О.", "Чапаев и Пустота", false)
        );
    }
}
