package pl.edu.pwr.ztw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.ztw.services.IBooksService;
import pl.edu.pwr.ztw.model.Book;

import java.util.List;

@RestController
public class BooksControler {
    @Autowired
    IBooksService booksService;

    @RequestMapping(value = "/get/books", method = RequestMethod.GET)
    public ResponseEntity<Object> getBooks() {
        return new ResponseEntity<>(booksService.getBooks(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/book/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getBook(@PathVariable("id") int id) {
        return new ResponseEntity<>(booksService.getBook(id), HttpStatus.OK);
    }

    @PostMapping("/post/book")
    public ResponseEntity<Book> handleRequest(@RequestParam Book book, @RequestParam List<Integer> authors) {
        booksService.addBook(book, authors);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
}
