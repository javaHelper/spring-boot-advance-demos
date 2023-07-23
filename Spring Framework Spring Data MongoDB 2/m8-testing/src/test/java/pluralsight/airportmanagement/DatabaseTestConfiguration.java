package pluralsight.airportmanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import pluralsight.airportmanagement.db.GenericCascadeListener;

@org.springframework.boot.test.context.TestConfiguration
public class DatabaseTestConfiguration {
    @Bean
    GenericCascadeListener genericCascadeListener(MongoTemplate mongoTemplate){
        return new GenericCascadeListener(mongoTemplate);
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoClientDbFactory("mongodb://localhost:27017/db-tests");
    }

    @Bean
    MongoTemplate mongoTemplate(MongoDbFactory factory) {
        return new MongoTemplate(factory);
    }
}
