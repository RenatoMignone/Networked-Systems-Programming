package it.unisannio.eshop.eshop.Controllers;

import it.unisannio.eshop.eshop.Entities.Book;
import it.unisannio.eshop.eshop.Entities.UserEntity;
import it.unisannio.eshop.eshop.Service.Admin_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("eShop/Admin")
public class Admin_Controller {
    private Admin_service service;

        // questo metodo serve per effettuare un encoding sulla nostra password per andarla a crittare nel database
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//    @PostMapping("/new_user")
//    public void crea(@RequestBody UserEntity user){
//        user.setPassword(passwordEncoder().encode(user.getPassword()));
//        service.createUser(user);
//    }

    @Autowired
    public Admin_Controller(Admin_service service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book){
        return service.createBoook(book);
    }

    @DeleteMapping("{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn){
        service.deleteBook(isbn);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
