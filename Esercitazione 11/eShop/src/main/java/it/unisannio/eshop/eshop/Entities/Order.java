package it.unisannio.eshop.eshop.Entities;

import org.springframework.data.mongodb.core.mapping.MongoId;

public class Order {

    @MongoId
    private String id;
    private String order_id;
    private Book libro;

    public Order(String id, Book libro) {
        this.order_id = id;
        this.libro = libro;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Book getLibro() {
        return libro;
    }

    public void setLibro(Book libro) {
        this.libro = libro;
    }
}
