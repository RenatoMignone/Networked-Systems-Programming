package it.unisannio.eshop.eshop.Controllers;

import it.unisannio.eshop.eshop.Entities.Book;
import it.unisannio.eshop.eshop.Service.Admin_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

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
