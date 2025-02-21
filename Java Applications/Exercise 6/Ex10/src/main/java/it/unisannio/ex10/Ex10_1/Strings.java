package it.unisannio.ex10.Ex10_1;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("/Strings")
public class Strings{

    private List<String> stringhe = new ArrayList<String>();

    public Strings(){
        stringhe.add("Ciao");
        stringhe.add("JAX-RS");
    }
    @POST
    public Response createString(String s){
        stringhe.add(s);
        return Response.created(URI.create("strings/"+(stringhe.size()-1))).build();
    }

    @GET
    @Path("/{id}")
    public String getStringById(@PathParam("id") int id){
        return stringhe.get(id);
    }

}