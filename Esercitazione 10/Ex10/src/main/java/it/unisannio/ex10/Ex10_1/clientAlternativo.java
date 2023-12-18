package it.unisannio.ex10.Ex10_1;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class clientAlternativo {
    public static void main(String args[]){
        Client client = ClientBuilder.newClient();

        WebTarget endpoint = client.target("http://127.0.0.1:8080/");

        WebTarget resource = endpoint.path("/rest/Strings");
        String string = "daje";
        Response res = resource.request().post(Entity.entity(string, MediaType.TEXT_PLAIN));

        System.out.println("Created string " +string + "\n" +res.getLocation());
        System.out.println();

        resource = endpoint.path(res.getLocation().getPath());

        String b = resource.request().accept("text/plain").get(String.class);

        System.out.println(b);
    }
}
