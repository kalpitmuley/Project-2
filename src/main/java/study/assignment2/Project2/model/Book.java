package study.assignment2.Project2.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "BOOK")
public class Book {

    @Field
    String name;

    @Field
    String authorName;

    @Field
    Integer cost;

    public Book(String name, String authorName, Integer cost) {
        this.name = name;
        this.authorName = authorName;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
