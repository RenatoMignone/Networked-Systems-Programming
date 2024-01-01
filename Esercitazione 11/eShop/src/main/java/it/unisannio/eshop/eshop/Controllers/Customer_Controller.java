package it.unisannio.eshop.eshop.Controllers;


import it.unisannio.eshop.eshop.Service.Customer_Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



//@CrossOrigin(origins = "**")
@RestController
@RequestMapping("eShop/Customer")
public class Customer_Controller {
    private final Customer_Service service;
    public Customer_Controller(Customer_Service customer_service){
        this.service = customer_service;
    }

    //metodo per ottenere una lista di tutti i libri disponibili
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return service.getAll();
    }
    //metodo per individuare i dettagli di un signolo libro dato il suo isbn
    @GetMapping("/{isbn}")
    public ResponseEntity<?> getDetails(@PathVariable String isbn){
        return service.getDetails(isbn);
    }
    //metodo per acquistare un libro e quindi creare un ordine
    @PostMapping("{isbn}")
    public ResponseEntity<?> buyBook(@PathVariable String isbn){
        return service.buyBook(isbn);
    }
    //metodo per restituire i dettagli di un determinato ordine
    @GetMapping("orders/{order_id}")
    public ResponseEntity<?> getOrderDetails(@PathVariable String order_id){
        return service.getOrderDetails(order_id);
    }
}
