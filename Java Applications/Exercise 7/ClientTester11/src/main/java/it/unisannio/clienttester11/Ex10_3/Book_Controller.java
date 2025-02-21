package it.unisannio.clienttester11.Ex10_3;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Produces("application/json")
@Consumes("application/json")
@Path("/Books")
public class Book_Controller {
    private final HashMap<String,Book> books = new HashMap<>();
    private final HashMap<String,List<Order>> orders = new HashMap<>();

    public Book_Controller(){
        books.put("1000",new Book("1000","r","r"));
    }

    @POST
    public Response createBook(Book book){
        books.put(book.getIsbn(),book);
        URI uri = null;
        try{
            uri = new URI("Books/"+book.getIsbn());
        }catch(URISyntaxException ignored){}
        return Response.created(uri).build();
    }
    @GET
    public Collection<Book> getBookList(){
        return books.values();
    }
    @GET
    @Path("/{isbn}")
    public Book getBookDetails(@PathParam("isbn") String isbn){
        return books.get(isbn);
    }

    @DELETE
    @Path("/{isbn}")
    public void deleteBook(@PathParam("isbn") String isbn){
        books.remove(isbn);
    }

    @POST
    @Path("/{isbn}/orders")
    public void orderBook(@PathParam("isbn") String isbn){
        List<Order> ordini = orders.get(isbn);
        if(ordini == null){
            ordini = new ArrayList<Order>();
            orders.put(isbn,ordini);
        }
        ordini.add(new Order(ordini.size(),isbn));
        orders.put(isbn,ordini);
    }

    @GET
    @Path("{isbn}/orders/{order_id}")
    public Order getOrder(@PathParam("isbn") String isbn, @PathParam("order_id")int order_id){
        List<Order> ordini = orders.get(isbn);
        if (ordini != null) return ordini.get(order_id);
        return null;
    }

}
