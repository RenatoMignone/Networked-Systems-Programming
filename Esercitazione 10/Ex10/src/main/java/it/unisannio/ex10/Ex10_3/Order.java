package it.unisannio.ex10.Ex10_3;

public class Order {
    int order_id;
    Book libro;

    public Order(int order_id, Book libro) {
        this.order_id = order_id;
        this.libro = libro;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Book getLibro() {
        return libro;
    }

    public void setLibro(Book libro) {
        this.libro = libro;
    }
}
