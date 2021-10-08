package microservice.core.person.repository;

import microservice.core.person.beans.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {
}
