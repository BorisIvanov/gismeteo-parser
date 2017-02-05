package gismeteo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface GismeteoRepository extends MongoRepository<WeatherItem, String> {
}
