package pl.edu.pwr.ztw.repo;

import pl.edu.pwr.ztw.model.Author;
import pl.edu.pwr.ztw.model.Book;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    public static List<Book> booksRepo = new ArrayList<>();
    public static List<Author> authorsRepo = new ArrayList<>();

    static {
        authorsRepo.add(new Author(1,"Henryk", "Sienkiewicz" ));
        authorsRepo.add(new Author(2,"Adam", "Mickiewicz" ));

        booksRepo.add(new Book(1, "W pustyni i w puszczy", List.of(authorsRepo.get(0)), 200));
        booksRepo.add(new Book(2, "Pan Tadeusz", List.of(authorsRepo.get(1)),200));
        booksRepo.add(new Book(3, "Dziady", List.of(authorsRepo.get(1)), 300));
    }
}
