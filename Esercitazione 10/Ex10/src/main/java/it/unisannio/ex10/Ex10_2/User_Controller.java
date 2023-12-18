package it.unisannio.ex10.Ex10_2;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import java.net.URI;
import java.util.HashMap;

@Path("/Users")
public class User_Controller {
    private HashMap<Integer,User> users = new HashMap<Integer,User>();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(@RequestBody User utente){
//        User utente = new User(id,nome,cognome);
        users.put(utente.getId(), utente);
        return Response.created(URI.create("Users/"+(users.size()-1))).build();
    }

    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("id") int id){
        return users.get(id);
    }

}
