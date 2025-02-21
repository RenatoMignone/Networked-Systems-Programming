package it.unisannio.clienttester11.Ex10_3;
public class Order {
    private int order_id;
    private String isbn;

    public Order(int order_id, String isbn) {
        this.order_id = order_id;
        this.isbn = isbn;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
