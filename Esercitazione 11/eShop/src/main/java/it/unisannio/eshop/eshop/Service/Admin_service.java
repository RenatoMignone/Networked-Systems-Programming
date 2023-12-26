package it.unisannio.eshop.eshop.Service;

import it.unisannio.eshop.eshop.Database.Books_Repository;
import it.unisannio.eshop.eshop.Database.User_Repository;
import it.unisannio.eshop.eshop.Entities.Book;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class Admin_service {
    private final User_Repository user_repo;
    private final Books_Repository books_repo;
    @Autowired
    public Admin_service(User_Repository user_repo,Books_Repository books_repo){
        this.user_repo = user_repo;
        this.books_repo = books_repo;
    }

    public Response createBoook(Book book){
        books_repo.save(book);
        URI uri = null;
        try{
            uri = new URI("Admin/"+book.getIsbn());
        }catch(URISyntaxException ignored){}
        return Response.created(uri).build();
    }

    public void deleteBook(String isbn){
        Book book = books_repo.findByIsbn(isbn);
        books_repo.deleteById(book.getId());
    }
}
