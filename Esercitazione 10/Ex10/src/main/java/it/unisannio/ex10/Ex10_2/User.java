package it.unisannio.ex10.Ex10_2;

public class User {

    public User(int id,String firstName,String lastName){
        this.id = id;
        this.nome = firstName;
        this.cognome = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return nome;
    }

    public void setFirstName(String firstName) {
        this.nome = firstName;
    }

    public String getLastName() {
        return cognome;
    }

    public void setLastName(String lastName) {
        this.cognome = lastName;
    }

    int id;
    String nome;
    String cognome;

}
