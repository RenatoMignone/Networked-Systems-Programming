package it.unisannio.eshop.eshop.Database;

import it.unisannio.eshop.eshop.Entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_Repository extends MongoRepository<User,String> {
    User findByEmailAndPassword(String email,String password);
}
