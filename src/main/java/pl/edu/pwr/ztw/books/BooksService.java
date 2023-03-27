package pl.edu.pwr.ztw.books;

import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.model.Author;
import pl.edu.pwr.ztw.books.model.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BooksService implements IBooksService {
    private static List<Book> booksRepo = new ArrayList<>();
    private static List<Author> authorsRepo = new ArrayList<>();

    static {
        authorsRepo.add(new Author(1,"Henryk", "Sienkiewicz" ));
        authorsRepo.add(new Author(1,"Henryk", "Sienkiewicz" ));

        booksRepo.add(new Book(1, "W pustyni i w puszczy", authorsRepo.get(0), 300));
        booksRepo.add(new Book(2, "Pan Tadeusz", authorsRepo.get(1),200));
        booksRepo.add(new Book(3, "Dziady", authorsRepo.get(1), 300));
    }

    @Override
    public Collection<Book> getBooks() {
        return booksRepo;
    }

    @Override
    public Collection<Author> getAuthors() {
        return authorsRepo;
    }

    @Override
    public Book getBook(int id) {
        return booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public Author getAuthor(int id) {
        return authorsRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);
    }
}
