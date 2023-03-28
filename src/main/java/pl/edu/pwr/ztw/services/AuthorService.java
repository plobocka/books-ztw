package pl.edu.pwr.ztw.services;

import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.model.Author;
import pl.edu.pwr.ztw.model.Book;
import pl.edu.pwr.ztw.repo.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    public Author addAuthor(Author author) {
        repo.authorsRepo.add(author);
        return author;
    }

    @Override
    public void deleteAuthor(int id) {
        List<Book> books= repo.booksRepo;
        int searchID = -1;
        for (Book book : books) {
            for (int i=0; i < book.getAuthors().size(); i++){
                if (book.getAuthors().get(i).getId() == id){
                    searchID = i;
                }

            }
            if (searchID != -1) {
                List<Author> as = new ArrayList<>(book.getAuthors());
                as.remove(searchID);
                book.setAuthors(as);
                searchID = -1;
            }
        }

        repo.authorsRepo.removeIf(a -> a.getId() == id);
    }

    @Override
    public Author updateAuthor(int id, Author updatedAuthor) {
        Author author = repo.authorsRepo.stream()
                .filter(a -> a.getId() == id)
                .findAny()
                .orElse(null);
        author.setFirstName(updatedAuthor.getFirstName());
        author.setLatsName(updatedAuthor.getLatsName());
        return author;
    }
}
