package it.unisannio.eshop.eshop.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document("Orders")
public class Order {

    @Id
    private String id;
    @Field(name = "libro")
    private Book libro;
    @Field(name = "data")
    private LocalDate data;

    public Order(Book libro) {
        this.libro = libro;
        this.data = LocalDate.now();
    }

//    public String getOrder_id() {
//        return order_id;
//    }
//
//    public void setOrder_id(String order_id) {
//        this.order_id = order_id;
//    }


    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
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
