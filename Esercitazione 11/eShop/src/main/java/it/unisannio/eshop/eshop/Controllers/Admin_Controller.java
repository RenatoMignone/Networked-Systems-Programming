package it.unisannio.eshop.eshop.Controllers;

import it.unisannio.eshop.eshop.Entities.Book;
import it.unisannio.eshop.eshop.Service.Admin_service;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Consumes("application/json")
@Produces("application/json")
@Path("/Admin")
public class Admin_Controller {
    private Admin_service service;

    @Autowired
    public Admin_Controller(Admin_service service){
        this.service = service;
    }

    @GET
    public String get(){
        return "ciao";
    }

    @POST
    public Response createBook(Book book){
        return service.createBoook(book);
    }

    @DELETE
    public void deleteBook(String isbn){
        service.deleteBook(isbn);
    }
}
