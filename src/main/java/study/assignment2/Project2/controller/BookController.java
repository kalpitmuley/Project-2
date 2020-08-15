package study.assignment2.Project2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import study.assignment2.Project2.model.Book;
import study.assignment2.Project2.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/library")
public class BookController {

    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    /**
     * @return List of all the books in the DB
     */
    @GetMapping("/book/get/all")
    public List<Book> getAllBooks() {
        List<Book> bookList = bookService.getAllBooks();
        logger.info("List returned " + bookList.size() + " books");
        return bookList;
    }

    @GetMapping("/book/get/author")
    public List<Book> getBooksByAuthor(@RequestParam("q") String authorName) {
        List<Book> bookList = bookService.getBookByAuthor(authorName);
        logger.info("List returned " + bookList.size() + " books");
        return bookList;
    }

    /**
     * API to add multiple books to the DB iteratively
     *
     * @param books List of books to be added
     * @return number of books added to the DB
     */
    @PostMapping("/book/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addMultipleBooksIteratively(@RequestBody List<Book> books) {
        logger.info("Adding " + books.size() + " books");
        long start = System.currentTimeMillis();
        bookService.insertBooksIteratively(books);
        return books.size() + " Books Added to the DB & it took " + (System.currentTimeMillis() - start) + " time";
    }

    /**
     * API to add multiple books to the DB in batch
     *
     * @param books List of books to be added
     * @return number of books added to the DB
     */
    @PostMapping("/book/add/multiple")
    @ResponseStatus(HttpStatus.CREATED)
    public String addMultipleBooks(@RequestBody List<Book> books) {
        logger.info("Adding " + books.size() + " books");
        long start = System.currentTimeMillis();
        bookService.insertBooks(books);
        return books.size() + " Books Added to the DB & it took " + (System.currentTimeMillis() - start) + " time";
    }

    /**
     * API to add multiple books to the DB in parallel using parallelStream
     *
     * @param books List of books to be added
     * @return number of books added to the DB
     */
    @PostMapping("/book/add/multiple/parallel")
    @ResponseStatus(HttpStatus.CREATED)
    public String addMultipleBooksInParallel(@RequestBody List<Book> books) {
        logger.info("Adding " + books.size() + " books");
        long start = System.currentTimeMillis();
        bookService.insertBooksInParallel(books);
        return books.size() + " Books Added to the DB & it took " + (System.currentTimeMillis() - start) + " time";
    }

    /**
     * API to add multiple books to the DB in parallel
     *
     * @param books List of books to be added
     * @return number of books added to the DB
     */
    @PostMapping("/book/add/multiple/thread")
    @ResponseStatus(HttpStatus.CREATED)
    public String addMultipleBooksInThread(@RequestBody List<Book> books) {
        logger.info("Adding " + books.size() + " books");
        long start = System.currentTimeMillis();
        bookService.insertBooksThread(books);
        return books.size() + " Books Added to the DB & it took " + (System.currentTimeMillis() - start) + " time";
    }

    /**
     * API to delete all books from the DB
     *
     * @return Time taken to delete all the books
     */
    @DeleteMapping("/book/delete/all")
    @ResponseStatus(HttpStatus.CREATED)
    public String deleteAllBooks() {
        logger.info("deleting all books");
        long start = System.currentTimeMillis();
        bookService.deleteAll();
        return "All Books deleted from the DB & it took " + (System.currentTimeMillis() - start) + " time";
    }
}
