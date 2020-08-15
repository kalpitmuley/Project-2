package study.assignment2.Project2.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import study.assignment2.Project2.model.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, Long> {

    List<Book> findByAuthorName(String authorName);
}
