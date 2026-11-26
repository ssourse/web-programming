package ru.etu.lab8.shared;

import java.io.Serializable;

/** Book record passed between browser and server by GWT-RPC. */
public class BookReader implements Serializable {
    private static final long serialVersionUID = 1L;
    private String author;
    private String title;
    private boolean read;

    /** Empty public constructor is required by GWT-RPC serialization. */
    public BookReader() { }

    /** Creates an entry containing the author, title and reading flag. */
    public BookReader(String author, String title, boolean read) {
        this.author = author;
        this.title = title;
        this.read = read;
    }

    /** @return the book author. */
    public String getAuthor() { return author; }
    /** @return the book title. */
    public String getTitle() { return title; }
    /** @return true when the reader has read the book. */
    public boolean isRead() { return read; }
    /** @param author new author. */
    public void setAuthor(String author) { this.author = author; }
    /** @param title new title. */
    public void setTitle(String title) { this.title = title; }
    /** @param read new reading status. */
    public void setRead(boolean read) { this.read = read; }
}
