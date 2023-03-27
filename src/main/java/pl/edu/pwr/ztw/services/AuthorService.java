package pl.edu.pwr.ztw.services;

import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.model.Author;
import pl.edu.pwr.ztw.repo.Repository;

import java.util.Collection;

@Service
public class AuthorService implements IAuthorService {
    private Repository repo = new Repository();


    @Override
    public Collection<Author> getAuthors() {
        return repo.authorsRepo;
    }

    @Override
    public Author getAuthor(int id) {
        return repo.authorsRepo.stream()
                .filter(a -> a.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void addAuthor(Author author) {
        repo.authorsRepo.add(author);
    }
}
