package study.assignment2.Project2.service;

import study.assignment2.Project2.model.Book;

import java.util.List;

public interface BookService {

    Book getBookByName(String name);

    List<Book> getAllBooks();

    int insertBooksIteratively(List<Book> books);

    int insertBooks(List<Book> books);

    int insertBooksInParallel(List<Book> books);

    void insertBooksThread(List<Book> books);

    void deleteAll();
}
