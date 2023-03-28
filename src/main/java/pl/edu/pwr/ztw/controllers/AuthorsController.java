package pl.edu.pwr.ztw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.ztw.model.Author;
import pl.edu.pwr.ztw.model.Book;
import pl.edu.pwr.ztw.services.IAuthorService;
import pl.edu.pwr.ztw.services.IBooksService;

@RestController
public class AuthorsController {
    @Autowired
    IAuthorService authorService;

    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    public ResponseEntity<Object> getAuthors() {
        return new ResponseEntity<>(authorService.getAuthors(), HttpStatus.OK);
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAuthor(@PathVariable("id") int id) {
        try{
            return new ResponseEntity<>(authorService.getAuthor(id), HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity("Author not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/author")
    public ResponseEntity<Author> handleRequest(@RequestBody Author author) {
        Author responseAuthor = authorService.addAuthor(author);
        return new ResponseEntity(responseAuthor, HttpStatus.CREATED);
    }

    @PutMapping("/author")
    public ResponseEntity<Author> handlePutAuthorRequest(@RequestBody Author author) {
        try {
            Author responseAuthor = authorService.updateAuthor(author.getId(), author);
            return new ResponseEntity(responseAuthor, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity("Author not found", HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteAuthor(@PathVariable("id") int id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(authorService.getAuthor(id), HttpStatus.OK);
    }

}
