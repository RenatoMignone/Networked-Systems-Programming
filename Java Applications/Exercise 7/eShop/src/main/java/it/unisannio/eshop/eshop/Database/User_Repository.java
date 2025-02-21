package it.unisannio.eshop.eshop.Database;

import it.unisannio.eshop.eshop.Entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_Repository extends MongoRepository<UserEntity,String> {
    UserEntity findByEmailAndPassword(String email, String password);

    UserEntity findUserByEmail(String email);
}
