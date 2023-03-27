package pl.edu.pwr.ztw.model;

import java.util.List;

public class Book {
    private int id;
    private String title;
    private List<Author> authors;
    int pages;

    public Book(int id, String title, List<Author> authors, int pages) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.pages = pages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
