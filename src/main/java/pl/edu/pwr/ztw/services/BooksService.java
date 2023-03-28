package pl.edu.pwr.ztw.services;

import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.exceptions.AuthorNotFoundException;
import pl.edu.pwr.ztw.exceptions.BookNotFoundException;
import pl.edu.pwr.ztw.model.Author;
import pl.edu.pwr.ztw.model.Book;
import pl.edu.pwr.ztw.repo.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BooksService implements IBooksService {
    private Repository repo = new Repository();

    @Override
    public Collection<Book> getBooks() {
        return repo.booksRepo;
    }

    @Override
    public Book getBook(int id) throws BookNotFoundException {
        try {
            return repo.booksRepo.stream()
                    .filter(b -> b.getId() == id)
                    .findAny()
                    .orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found"));
        } catch (BookNotFoundException e) {
            return null;
        }
    }

    @Override
    public Book addBook(Book book, List<Integer> authorsIDs) throws AuthorNotFoundException {
        try {
            List<Author> authors = new ArrayList<>();
            for (int id : authorsIDs) {
                authors.add(repo.authorsRepo.stream()
                        .filter(a -> a.getId() == id)
                        .findAny()
                        .orElseThrow(() -> new AuthorNotFoundException("Author with id " + id + " not found")));
            }
            book.setAuthors(authors);
            repo.booksRepo.add(book);
            return book;
        } catch (AuthorNotFoundException e) {
            return null;
        }
    }

    @Override
    public void deleteBook(int id) throws BookNotFoundException {
        boolean removed = repo.booksRepo.removeIf(b -> b.getId() == id);
        if (!removed) {
            throw new BookNotFoundException("Book with id " + id + " not found");
        }
    }

    @Override
    public void updateBookWithoutAuthors(int id, Book updatedBook) {
        Book book = repo.booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);
        book.setTitle(updatedBook.getTitle());
        book.setPages(updatedBook.getPages());
    }

    @Override
    public Book updateBook(int id, Book updatedBook, List<Integer> authorsIDs) throws BookNotFoundException, AuthorNotFoundException {
        try {
            Book bookToUpdate = repo.booksRepo.stream()
                    .filter(b -> b.getId() == id)
                    .findAny()
                    .orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found"));
            if (bookToUpdate != null) {
                bookToUpdate.setTitle(updatedBook.getTitle());
                bookToUpdate.setPages(updatedBook.getPages());
                if (authorsIDs != null) {
                    List<Author> authors = new ArrayList<>();
                    for (int authorID : authorsIDs) {
                        authors.add(repo.authorsRepo.stream()
                                .filter(a -> a.getId() == authorID)
                                .findAny()
                                .orElseThrow(() -> new AuthorNotFoundException("Author with id " + authorID + " not found")));
                    }
                    bookToUpdate.setAuthors(authors);
                }
            }
            return bookToUpdate;
        } catch (BookNotFoundException | AuthorNotFoundException e) {
            return null;
        }
    }

    @Override
    public void updateBookAuthors(int id, List<Integer> authorsIDs) {
        Book bookToUpdate = repo.booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);
        if (bookToUpdate != null) {
            List<Author> authors = new ArrayList<>();
            for (int authorID : authorsIDs) {
                authors.add(repo.authorsRepo.stream()
                        .filter(a -> a.getId() == authorID)
                        .findAny()
                        .orElse(null));
            }
            bookToUpdate.setAuthors(authors);
        }
    }

}
