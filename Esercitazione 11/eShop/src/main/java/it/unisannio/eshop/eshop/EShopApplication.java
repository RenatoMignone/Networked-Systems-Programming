package it.unisannio.eshop.eshop;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class EShopApplication{
    public static void main(String[] args) {
        SpringApplication.run(EShopApplication.class, args);
    }
}
