package microservice.core.person;


import microservice.core.person.beans.Address;
import microservice.core.person.beans.Gender;
import microservice.core.person.beans.Person;
import microservice.core.person.beans.State;
import microservice.core.person.exceptions.PersonSystemException;
import microservice.core.person.repository.PersonRepository;
import microservice.core.person.services.PersonServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PersonServerTest {

    @InjectMocks
    PersonServiceImp personService;

    @Mock
    PersonRepository personRepository;

    @Test
    public void testGetAllPersons() {

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

        Person person1 = Person.builder().id("102")
                .name("June")
                .age(27)
                .height(1.55)
                .gender(Gender.FEMALE)
                .weight(50.0)
                .address(Address.builder().city("Tel Aviv")
                        .containsAnimals(true)
                        .state(State.ISRAEL)
                        .zipcode("404000")
                        .street("Moshe").build())
                .build();

        List<Person> personList = new ArrayList<>();
        personList.add(person);
        personList.add(person1);

        //when
        Mockito.when(personRepository.findAll()).thenReturn(personList);

        //then
        List<Person> persons = personService.getAllPersons();

        Assertions.assertEquals(2, persons.size());
        Mockito.verify(personRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testAddPerson() throws PersonSystemException {

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

        //when
        personService.addPerson(person);

        //then
        Mockito.verify(personRepository, Mockito.times(1)).save(person);
    }
}
