package gismeteo.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collections;

@Configuration
@EnableMongoRepositories(basePackages = {"gismeteo.repositories"})
public class MongoConfig extends AbstractMongoConfiguration {

    @Override
    public String getDatabaseName() {
        return "el-linko";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(
                Collections.singletonList(new ServerAddress("ds143449.mlab.com", 43449)),
                Collections.singletonList(MongoCredential.createCredential("gismeteo", "el-linko", "gismeteo".toCharArray())));
    }

}
