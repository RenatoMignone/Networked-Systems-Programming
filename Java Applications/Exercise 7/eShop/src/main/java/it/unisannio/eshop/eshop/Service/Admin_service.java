package it.unisannio.eshop.eshop.Service;

import it.unisannio.eshop.eshop.Database.Books_Repository;
import it.unisannio.eshop.eshop.Database.User_Repository;
import it.unisannio.eshop.eshop.Entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class Admin_service {
    private final User_Repository user_repo;
    private final Books_Repository books_repo;
    @Autowired
    public Admin_service(User_Repository user_repo,Books_Repository books_repo){
        this.user_repo = user_repo;
        this.books_repo = books_repo;
    }

    public ResponseEntity<?> createBoook(Book book){
        if(books_repo.findByIsbn(book.getIsbn())==null)
        {return ResponseEntity.status(HttpStatus.CREATED).body(books_repo.save(book));}
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("isbn already exists");
    }

    public void deleteBook(String isbn){
        Book book = books_repo.findByIsbn(isbn);
        books_repo.deleteById(book.getId());
    }

}
