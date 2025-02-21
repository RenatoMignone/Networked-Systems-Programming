package it.unisannio.clienttester11.Ex10_2;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;

@Produces("application/json")
@Consumes("application/json")
@Path("/Users")
public class User_Controller {
    private final HashMap<Integer, User> users = new HashMap<Integer,User>();


    //POST, crea un nuovo utente nella collezione
    @POST
    public Response createUser(User utente){
//        User utente = new User(id,nome,cognome);
        users.put(utente.getId(), utente);
        return Response.created(URI.create("Users/"+(users.size()-1))).build();
    }

    @Path("/{id}")
    @GET
    public User getUserById(@PathParam("id") int id){
        return users.get(id);
    }

    @Path("name/{nome}")
    @GET
    public User getUserByFistName(@PathParam("nome") String nome){
        for (User u : users.values()){
            if(u.getNome().equals(nome)){
                return u;
            }
            else
            {
                System.err.println("User not found");
                return null;
            }
        }
        return null;
    }

    @Path("id/{id}")
    @PUT
    public void updateUserById(@PathParam("id") int id, User user){
        users.put(id,user);
    }

    @Path("cognome/{cognome}")
    @GET
    public User getUserByLastName(@PathParam("cognome") String cognome){
        for (User u : users.values()){
            if(u.getCognome().equals(cognome)){
                return u;
            }
            else
            {
                System.err.println("User not found");
                return null;
            }
        }
        return null;
    }

    @DELETE
    @Path("delete/{id}")
    public void deleteUserById(@PathParam("id") int id){
        users.remove(id);
    }

    @Path("/all")
    @GET
    public Collection<User> getAllUsers(){
        return users.values();
    }
}
