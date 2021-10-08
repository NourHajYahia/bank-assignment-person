package microservice.core.person;


import microservice.core.person.beans.Address;
import microservice.core.person.beans.Gender;
import microservice.core.person.beans.Person;
import microservice.core.person.beans.State;
import microservice.core.person.controllers.PersonController;
import microservice.core.person.services.PersonService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @MockBean
    PersonService personService;

    @InjectMocks
    PersonController personController;

    @Autowired
    MockMvc mockMvc;


    @Test
    public void testGetAllPersons() throws Exception {
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
        List<Person> personList = new ArrayList<>();
        personList.add(person);

        //when
        Mockito.when(personService.getAllPersons()).thenReturn(personList);

        //then
        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.is("101")))
                .andExpect(jsonPath("$[0].name", Matchers.is("Nur")));

    }

}
