package pl.edu.pwr.ztw.books;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BooksService implements IBooksService {
    private static List<Book> booksRepo = new ArrayList<>();

    static {
        booksRepo.add(new Book(1, "W pustyni i w puszczy", "Henryk Sienkiewicz", 300));
        booksRepo.add(new Book(2, "Pan Tadeusz", "Adam Mickiewicz", 200));
        booksRepo.add(new Book(3, "Dziady", "Adam Mickiewicz", 300));
    }

    @Override
    public Collection<Book> getBooks() {
        return booksRepo;
    }

    @Override
    public Book getBook(int id) {
        return booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);
    }
}
