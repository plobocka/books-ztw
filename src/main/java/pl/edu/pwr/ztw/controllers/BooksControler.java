package pl.edu.pwr.ztw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.ztw.exceptions.AuthorNotFoundException;
import pl.edu.pwr.ztw.exceptions.BookNotFoundException;
import pl.edu.pwr.ztw.model.Author;
import pl.edu.pwr.ztw.services.IBooksService;
import pl.edu.pwr.ztw.model.Book;

import java.util.List;

@RestController
public class BooksControler {
    @Autowired
    IBooksService booksService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity<Object> getBooks() {
        try {
            return new ResponseEntity<>(booksService.getBooks(), HttpStatus.OK);
        }
        catch(BookNotFoundException exception){
            return new ResponseEntity("Book not found", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getBook(@PathVariable("id") int id) {
        return new ResponseEntity<>(booksService.getBook(id), HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<Book> handlePostRequest(@RequestBody RequestBook book) {
        try {
            Book responseBook = booksService.addBook(
                    new Book(book.id, book.title, null, book.pages),
                    book.authors
            );
            return new ResponseEntity(responseBook, HttpStatus.CREATED);
        }
        catch (AuthorNotFoundException exception){
            return new ResponseEntity("Author not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Book> handlePutRequest(@RequestBody RequestBook book) {
        try {
            Book responseBook = booksService.updateBook(
                    book.id,
                    new Book(book.id, book.title, null, book.pages),
                    book.authors
            );
            return new ResponseEntity(responseBook, HttpStatus.CREATED);
        }
        catch (AuthorNotFoundException exception){
            return new ResponseEntity("Author not found", HttpStatus.NOT_FOUND);
        }
        catch(BookNotFoundException exception){
            return new ResponseEntity("Book not found", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBook(@PathVariable("id") int id) {
        try {
            booksService.deleteBook(id);
            return new ResponseEntity<>(booksService.getBook(id), HttpStatus.OK);
        }
        catch(BookNotFoundException exception){
            return new ResponseEntity("Book not found", HttpStatus.NOT_FOUND);
        }
    }

    private static class RequestBook{
        public int id;
        public String title;
        public List<Integer> authors;
        int pages;

        public RequestBook(int id, String title, List<Integer> authors, int pages) {
            this.id = id;
            this.title = title;
            this.authors = authors;
            this.pages = pages;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Integer> getAuthors() {
            return authors;
        }

        public void setAuthors(List<Integer> authors) {
            this.authors = authors;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }
    }
}
