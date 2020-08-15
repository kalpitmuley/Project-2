package study.assignment2.Project2.service;

import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.assignment2.Project2.dao.BookRepository;
import study.assignment2.Project2.model.Book;

import java.util.List;

@Service("BookService")
public class BookServiceImpl implements BookService {

    // Number of threads to create
    private final int THREADS = 4;
    Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    @Autowired
    BookRepository bookRepository;

    @Override
    public Book getBookByName(String name) {
        Book book = bookRepository.findByName(name);
        logger.info("Returned book is: " + book);
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books;
        books = bookRepository.findAll();
        return books;
    }

    @Override
    public int insertBooks(List<Book> books) {
        bookRepository.insert(books);
        return books.size();
    }

    @Override
    public int insertBooksInParallel(List<Book> books) {
        books.parallelStream().forEach(book ->
                bookRepository.insert(book));
        return books.size();
    }

    @Override
    public void insertBooksThread(List<Book> books) {
        MyThread[] threads;
        int i = 0;
        threads = new MyThread[THREADS + 1];
        for (List<Book> list : ListUtils.partition(books, books.size() / THREADS)) {
            threads[i] = new MyThread(bookRepository, list);
            if (list != null) {
                threads[i].start();
            }
            i++;
        }
        for (MyThread thread : threads) {
            try {
                if (thread != null)
                    thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    /**
     * Inner static class for multithreading
     */
    private static class MyThread extends Thread {
        BookRepository bookRepository;
        List<Book> books;

        MyThread(BookRepository bookRepository, List<Book> books) {
            this.bookRepository = bookRepository;
            this.books = books;
        }

        /**
         * persist the Book entity
         */
        @Override
        public void run() {
            for (Book book : books)
                bookRepository.insert(book);
        }
    }
}