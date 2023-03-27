package pl.edu.pwr.ztw.books;

import java.util.Collection;

public interface IBooksService {
    public abstract Collection<Book> getBooks();
    public abstract Book getBook(int id);
}
