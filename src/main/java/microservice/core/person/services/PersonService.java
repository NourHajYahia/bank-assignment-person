package microservice.core.person.services;


import microservice.core.person.beans.Person;
import microservice.core.person.exceptions.PersonSystemException;

import java.util.List;

public interface PersonService {

    /**
     * +
     * Adding person to database
     *
     * @param person object
     * @throws PersonSystemException if it does not exist
     */
    void addPerson(Person person) throws PersonSystemException;

    /**
     * Updating person in database
     *
     * @param person object
     * @throws PersonSystemException if it exists
     */
    void updatePerson(Person person) throws PersonSystemException;

    /**
     * Deleting person from database by its id
     *
     * @param personId person's id number
     * @throws PersonSystemException If id not found
     */
    void deletePerson(String personId) throws PersonSystemException;

    /**
     * Getting all database existing persons as a List
     *
     * @return List<Person>
     */
    List<Person> getAllPersons();

    /**
     * Getting single person from database
     *
     * @param personId person's id number
     * @return Person
     * @throws PersonSystemException if id not found
     */
    Person getSinglePerson(String personId) throws PersonSystemException;
}
