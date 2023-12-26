//package it.unisannio.eshop.eshop.Database;
//
//import com.mongodb.MongoClientSettings;
//import com.mongodb.MongoCredential;
//import com.mongodb.ServerAddress;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import jakarta.inject.Qualifier;
//import org.springframework.boot.autoconfigure.mongo.MongoProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.mongodb.MongoDatabaseFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//import static java.util.Collections.singletonList;
//
//@Configuration
//@EnableConfigurationProperties
//@EnableMongoRepositories(basePackages = "it.unisannio.eshop.eshop.Database")
//public class Database_Configurator {
//    @Bean(name = "dbeShop")
//    @ConfigurationProperties(prefix = "db.eshop")
//    @Primary
//    public MongoProperties secondaryProperties(){
//        return new MongoProperties();
//    }
//
//    @Bean(name = "dbeShopMongoClient")
//    public MongoClient mongoClient(@Qualifier("dbeShop") MongoProperties mongoProperties){
//        MongoCredential credential = MongoCredential.createCredential(mongoProperties.getUsername(),mongoProperties.getAuthenticationDatabase(),mongoProperties.getPassword());
//        return MongoClients.create(MongoClientSettings.builder()
//                .applyToClusterSettings(builder -> builder.hosts(singletonList(new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort()))))
//                .credential(credential)
//                .build());
//    }
//
//    @Bean(name = "dbeShopFactory")
//    public MongoDatabaseFactory mongoDatabaseFactory(@Qualifier("dbEventiMongoClient") MongoClient mongoClient, @Qualifier("dbEventi") MongoProperties mongoProperties) {
//        return new SimpleMongoClientDatabaseFactory(mongoClient, mongoProperties.getDatabase());
//    }
//
//    @Bean(name = "dbeshopMongoTemplate")
//    public MongoTemplate mongoTemplate(@Qualifier("dbEventiFactory") MongoDatabaseFactory mongoDatabaseFactory) {
//        return new MongoTemplate(mongoDatabaseFactory);
//    }
//}
