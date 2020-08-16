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
    private final int THREADS = 5;
    Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        List<Book> books;
        books = bookRepository.findAll();
        return books;
    }

    @Override
    public List<Book> getBookByAuthor(String author) {
        List<Book> books;
        books = bookRepository.findByAuthorName(author);
        return books;
    }

    @Override
    public int insertBooksIteratively(List<Book> books) {
        for (Book book :
                books) {
            bookRepository.save(book);
        }
        return books.size();
    }

    @Override
    public int insertBooks(List<Book> books) {
        bookRepository.saveAll(books);
        return books.size();
    }

    @Override
    public int insertBooksInParallel(List<Book> books) {
        books.parallelStream().forEach(book ->
                bookRepository.save(book));
        return books.size();
    }

    @Override
    public void insertBooksThread(List<Book> books) {
        MyThread[] threads;
        if (books == null) return;
        if (books.size() >= THREADS) {
            int i = 0;
            // Thread + 1 because we may encounter an extra partition for 26/5 = (5*5 + 1) in this case 1 book will be processed in the extra thread.
            threads = new MyThread[THREADS + 1];
            for (List<Book> list : ListUtils.partition(books, books.size() / THREADS)) {
                threads[i] = new MyThread(bookRepository, list);
                if (list != null) {
                    threads[i].start();
                    i++;
                }
            }
            for (MyThread thread : threads) {
                try {
                    if (thread != null)
                        thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            insertBooks(books);
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
            bookRepository.saveAll(books);
        }
    }
}