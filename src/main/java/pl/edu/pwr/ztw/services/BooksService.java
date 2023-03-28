package pl.edu.pwr.ztw.services;

import org.springframework.stereotype.Service;
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
    public Book getBook(int id) {
        return repo.booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public Book addBook(Book book, List<Integer> authorsIDs) {
        List<Author> authors = new ArrayList<>();
        for (int id : authorsIDs) {
            authors.add(repo.authorsRepo.stream()
                    .filter(a -> a.getId() == id)
                    .findAny()
                    .orElse(null));
        }
        book.setAuthors(authors);
        repo.booksRepo.add(book);
        return book;
    }

    @Override
    public void deleteBook(int id) {
        repo.booksRepo.removeIf(b -> b.getId() == id);
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
    public Book updateBook(int id, Book updatedBook, List<Integer> authorsIDs) {
        Book bookToUpdate = repo.booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);
        if (bookToUpdate != null) {
            bookToUpdate.setTitle(updatedBook.getTitle());
            bookToUpdate.setPages(updatedBook.getPages());
            if (authorsIDs != null) {
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
        return null;
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
