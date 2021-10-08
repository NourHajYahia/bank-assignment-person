package microservice.core.person;


import microservice.core.person.beans.Address;
import microservice.core.person.beans.Gender;
import microservice.core.person.beans.Person;
import microservice.core.person.beans.State;
import microservice.core.person.repository.PersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    public void testAddGetDelete() {

        //given
        Person person = Person.builder().id("101")
                .name("Nur")
                .age(32)
                .height(1.58)
                .gender(Gender.MALE)
                .weight(70.0)
                .address(Address.builder().city("Tybe")
                        .containsAnimals(true)
                        .state(State.ISRAEL)
                        .zipcode("8008080")
                        .street("smaca").build())
                .build();

        personRepository.save(person);

        Iterable<Person> persons = personRepository.findAll();
        Assertions.assertThat(persons).extracting(Person::getName).containsOnly("Nur");

        personRepository.deleteAll();
        Assertions.assertThat(personRepository.findAll()).isEmpty();
    }
}
