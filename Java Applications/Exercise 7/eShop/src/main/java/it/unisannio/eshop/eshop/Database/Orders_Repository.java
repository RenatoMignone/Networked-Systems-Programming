package it.unisannio.eshop.eshop.Database;

import it.unisannio.eshop.eshop.Entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Orders_Repository extends MongoRepository<Order,String> {
    Order findOrderById(String order_id);
}
