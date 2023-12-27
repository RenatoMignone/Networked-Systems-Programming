package it.unisannio.eshop.eshop.Service;

import it.unisannio.eshop.eshop.Database.Books_Repository;
import it.unisannio.eshop.eshop.Database.Orders_Repository;
import it.unisannio.eshop.eshop.Database.User_Repository;
import it.unisannio.eshop.eshop.Entities.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class Customer_Service {
    private final User_Repository user_repo;
    private final Books_Repository books_repo;
    private final Orders_Repository orders_repo;

    public Customer_Service(User_Repository user_repo, Books_Repository books_repo, Orders_Repository orders_repo) {
        this.user_repo = user_repo;
        this.books_repo = books_repo;
        this.orders_repo = orders_repo;
    }
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(books_repo.findAll());
    }
    public ResponseEntity<?> getDetails(String isbn){
        return ResponseEntity.status(HttpStatus.OK).body(books_repo.findByIsbn(isbn));
    }
    public ResponseEntity<?> buyBook(String isbn){
        return ResponseEntity.status(HttpStatus.CREATED).body(orders_repo.save(new Order(books_repo.findByIsbn(isbn))));
    }
    public ResponseEntity<?> getOrderDetails(String order_id){
        return ResponseEntity.status(HttpStatus.OK).body(orders_repo.findOrderById(order_id));
    }
}
