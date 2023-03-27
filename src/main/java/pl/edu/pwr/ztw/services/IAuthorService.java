package pl.edu.pwr.ztw.services;

import pl.edu.pwr.ztw.model.Author;
import pl.edu.pwr.ztw.model.Book;

import java.util.Collection;
import java.util.List;

public interface IAuthorService {
    public abstract Collection<Author> getAuthors();
    public abstract Author getAuthor(int id);
    public abstract void addAuthor(Author author);
}
