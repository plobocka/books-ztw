package pl.edu.pwr.ztw.books;

import pl.edu.pwr.ztw.books.model.Author;
import pl.edu.pwr.ztw.books.model.Book;

import java.util.Collection;

public interface IBooksService {
    public abstract Collection<Book> getBooks();
    public abstract Collection<Author> getAuthors();
    public abstract Book getBook(int id);
    public abstract Author getAuthor(int id);
}
