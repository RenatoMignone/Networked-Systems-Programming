package it.unisannio.eshop.eshop.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document("Users")
public class UserEntity {
    @Id
    private String id;
    @Field(name="email")
    private String email;
    @Field(name="password")
    private String password;
    @Field(name= "role")
    private List<String> role;

    public UserEntity(String email, String password, List<String> role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}
