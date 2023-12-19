package it.unisannio.ex10.Ex10_3;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.ArrayList;
import java.util.List;

@Path("/Books")
public class Book_Controller {

    private final List<Book> books = new ArrayList<Book>();
    private final List<Order> orders = new ArrayList<Order>();
    //    Books	getBookList()
    //    void createBook(Book	book)
    //    Book	getBookDetails(String	isbn)
    //    void	deleteBook(String	isbn)
    //    void orderBook(String	isbn)
    //    Order	getOrder(String	isbn,	int	oid)
    @POST
    public void creaLibro(Book book){

    }

}
