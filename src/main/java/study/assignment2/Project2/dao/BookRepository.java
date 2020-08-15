package study.assignment2.Project2.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import study.assignment2.Project2.model.Book;

public interface BookRepository extends MongoRepository<Book, Long> {

    Book findByName(String name);
}
