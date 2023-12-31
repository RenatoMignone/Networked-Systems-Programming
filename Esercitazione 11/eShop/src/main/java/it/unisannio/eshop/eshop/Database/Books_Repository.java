package it.unisannio.eshop.eshop.Database;

import it.unisannio.eshop.eshop.Entities.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Books_Repository extends MongoRepository<Book,String> {
        Book findByIsbn(String idbn);
        Book getBookByIsbn(String isbn);
}