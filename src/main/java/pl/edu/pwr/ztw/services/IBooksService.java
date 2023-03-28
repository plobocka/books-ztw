package pl.edu.pwr.ztw.services;

import pl.edu.pwr.ztw.model.Book;

import java.util.Collection;
import java.util.List;

public interface IBooksService {
    public abstract Collection<Book> getBooks();
    public abstract Book getBook(int id);
    public abstract Book addBook(Book book, List<Integer> authorsIDs);
    public abstract void deleteBook(int id);
    public abstract Book updateBook(int id, Book updatedBook, List<Integer> authorsIDs);
    public abstract void updateBookWithoutAuthors(int id, Book updatedBook);
    public abstract void updateBookAuthors(int id, List<Integer> authorsIDs);
}
