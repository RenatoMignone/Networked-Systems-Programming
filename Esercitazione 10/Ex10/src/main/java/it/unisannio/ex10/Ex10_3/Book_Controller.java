package it.unisannio.ex10.Ex10_3;

import jakarta.ws.rs.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Produces("application/json")
@Consumes("application/json")
@Path("/Books")
public class Book_Controller {
    private final HashMap<String,Book> books = new HashMap<>();
    private final List<Order> orders = new ArrayList<Order>();
    //    Books	getBookList() *
    //    void createBook(Book	book) *
    //    Book	getBookDetails(String	isbn) *
    //    void	deleteBook(String	isbn) *
    //    void orderBook(String	isbn) *
    //    Order	getOrder(String	isbn,	int	oid)
    @POST
    public void createBook(Book book){
        books.put(book.getIsbn(),book);
    }
    @GET
    @Path("/all")
    public Collection<Book> getBookList(){
        return books.values();
    }
    @GET
    @Path("details/{isbn}")
    public Book getBookDetails(@PathParam("isbn") String isbn){
        return books.get(isbn);
    }

    @DELETE
    @Path("/{isbn}")
    public void deleteBook(@PathParam("isbn") String isbn){
        books.remove(isbn);
    }

    @POST
    @Path("/order/{isbn}")
    public void orderBook(@PathParam("isbn") String isbn){
        orders.add(new Order(orders.size()+1,books.get(isbn)));
    }

    @GET
    @Path("order/{order_id}")
    public Order getOrder(@PathParam("order_id") String isbn,int order_id){
        return orders.get(order_id);
    }

}
