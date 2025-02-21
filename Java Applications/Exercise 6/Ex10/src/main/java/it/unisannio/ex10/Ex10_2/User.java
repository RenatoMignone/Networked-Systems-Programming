package it.unisannio.ex10.Ex10_2;

public class User {

    public User(int id, String nome, String cognome, String username, String password, int age) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public User(String nome,String cognome,int id){
        this.nome = nome;
        this.cognome = cognome;
        this.id = id;
    }

    public User(){

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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }

    public void SetAll(User user){
        this.nome= user.getNome();
        this.cognome = user.getCognome();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.age = user.getAge();
    }

    private int id;
    private String nome;
    private String cognome;

    private String username;
    private String password;
    private int age;

}
