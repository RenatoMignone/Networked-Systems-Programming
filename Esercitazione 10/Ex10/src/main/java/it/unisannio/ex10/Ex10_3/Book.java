package it.unisannio.ex10.Ex10_3;

public class Book {
    String isbn;
    int id;

    public Book(String isbn, int id) {
        this.isbn = isbn;
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
