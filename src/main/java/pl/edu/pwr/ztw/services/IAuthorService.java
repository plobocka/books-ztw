package pl.edu.pwr.ztw.services;

import pl.edu.pwr.ztw.model.Author;

import java.util.Collection;

public interface IAuthorService {
    public abstract Collection<Author> getAuthors();
    public abstract Author getAuthor(int id);
    public abstract Author addAuthor(Author author);
    public abstract void deleteAuthor(int id);
    public abstract Author updateAuthor(int id, Author updatedAuthor);
}
