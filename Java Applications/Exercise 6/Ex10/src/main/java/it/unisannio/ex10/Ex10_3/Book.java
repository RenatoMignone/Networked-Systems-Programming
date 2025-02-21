package it.unisannio.ex10.Ex10_3;

public class Book {
    String isbn;
    String titolo;
    String autore;

    public Book(String isbn, String titolo, String autore) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.autore = autore;
    }

    public Book(){
    }

    public Book(String isbn, int id, String titolo, String autore) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.autore = autore;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }
}
