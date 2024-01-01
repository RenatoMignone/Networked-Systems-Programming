package it.unisannio.eshop.eshop.Controllers;

import it.unisannio.eshop.eshop.Entities.Book;
import it.unisannio.eshop.eshop.Entities.UserEntity;
import it.unisannio.eshop.eshop.Service.Admin_service;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

//@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@RequestMapping("eShop/Admin")
public class Admin_Controller {
    private Admin_service service;

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
        return ResponseEntity.status(HttpStatus.OK).body("Book eliminated correctly");
    }
}
