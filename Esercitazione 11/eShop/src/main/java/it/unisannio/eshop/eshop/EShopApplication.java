package it.unisannio.eshop.eshop;

import jakarta.ws.rs.ApplicationPath;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"it.unisannio.eshop.eshop.Controllers","it.unisannio.eshop.eshop.Service","it.unisannio.eshop.eshop.Database","it.unisannio.eshop.eshop.Entities"} )
@ApplicationPath("eShop")
public class EShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(EShopApplication.class, args);
    }

}
