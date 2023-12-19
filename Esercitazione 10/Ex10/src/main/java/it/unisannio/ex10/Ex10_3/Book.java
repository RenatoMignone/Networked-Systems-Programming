package it.unisannio.ex10.Ex10_3;

public class Book {
    String isbn;
    int id;
    String nome;
    String autore;

    public Book(String isbn, int id) {
        this.isbn = isbn;
        this.id = id;
    }

    public Book(String isbn, int id, String nome, String autore) {
        this.isbn = isbn;
        this.id = id;
        this.nome = nome;
        this.autore = autore;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }
}
