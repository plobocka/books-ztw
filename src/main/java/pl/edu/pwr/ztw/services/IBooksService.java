package pl.edu.pwr.ztw.services;

import pl.edu.pwr.ztw.exceptions.AuthorNotFoundException;
import pl.edu.pwr.ztw.exceptions.BookNotFoundException;
import pl.edu.pwr.ztw.model.Book;

import java.util.Collection;
import java.util.List;

public interface IBooksService {
    public abstract Collection<Book> getBooks();
    public abstract Book getBook(int id) throws BookNotFoundException;
    public abstract Book addBook(Book book, List<Integer> authorsIDs) throws AuthorNotFoundException;
    public abstract void deleteBook(int id) throws BookNotFoundException;
    public abstract Book updateBook(int id, Book updatedBook, List<Integer> authorsIDs) throws BookNotFoundException, AuthorNotFoundException;
    public abstract void updateBookWithoutAuthors(int id, Book updatedBook);
    public abstract void updateBookAuthors(int id, List<Integer> authorsIDs);
}
