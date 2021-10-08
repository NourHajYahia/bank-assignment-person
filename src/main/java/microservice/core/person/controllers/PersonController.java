package microservice.core.person.controllers;


import microservice.core.person.beans.Person;
import microservice.core.person.exceptions.PersonSystemException;
import microservice.core.person.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("persons")
public class PersonController {

    @Autowired
    private PersonService personService;


    /**
     * Validates input and calls the service to perform the addition
     *
     * @param person object
     * @throws PersonSystemException if person already exist
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addPerson(@Valid @RequestBody Person person) throws PersonSystemException {
        personService.addPerson(person);
    }

    /**
     * Validates input and calls the service to perform the update
     *
     * @param person object
     * @throws PersonSystemException if person violate bean validations
     */
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePerson(@Valid @RequestBody Person person) throws PersonSystemException {
        personService.updatePerson(person);
    }

    /**
     * Calls the service to perform the deletion by its id
     *
     * @param id person's id number
     * @throws PersonSystemException if person not exist
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable String id) throws PersonSystemException {
        personService.deletePerson(id);
    }

    /**
     * Calls the service to get all persons
     *
     * @return List<Person>
     */
    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    /**
     * Calls the service to get single persons by its id
     *
     * @param id person's id number
     * @return Person
     * @throws PersonSystemException if person not exist
     */
    @GetMapping("/{id}")
    public Person getSinglePersons(@PathVariable String id) throws PersonSystemException {
        return personService.getSinglePerson(id);
    }
}
