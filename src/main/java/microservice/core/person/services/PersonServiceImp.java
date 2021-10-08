package microservice.core.person.services;


import microservice.core.person.beans.Person;
import microservice.core.person.exceptions.ErrMsg;
import microservice.core.person.exceptions.PersonSystemException;
import microservice.core.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImp implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * Adding person to database
     *
     * @param person object
     * @throws PersonSystemException if it does not exist
     */
    @Override
    public void addPerson(Person person) throws PersonSystemException {
        if (personRepository.existsById(person.getId())) {
            throw new PersonSystemException(ErrMsg.ALREADY_EXIST, "id");
        }
        personRepository.save(person);
    }

    /**
     * Updating person in database
     *
     * @param person object
     * @throws PersonSystemException if it does exist
     */
    @Override
    public void updatePerson(Person person) throws PersonSystemException {
        if (!personRepository.existsById(person.getId())) {
            throw new PersonSystemException(ErrMsg.NOT_FOUND, "id");
        }
        personRepository.save(person);
    }

    /**
     * Deleting person from database by its id
     *
     * @param personId person's id number
     * @throws PersonSystemException if id not found
     */
    @Override
    public void deletePerson(String personId) throws PersonSystemException {
        if (!personRepository.existsById(personId)) {
            throw new PersonSystemException(ErrMsg.NOT_FOUND, "id");
        }
        personRepository.deleteById(personId);
    }

    /**
     * Getting all database existing persons as a List
     *
     * @return List<Person>
     */
    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    /**
     * Getting single person from database
     *
     * @param personId person's id number
     * @return Person
     * @throws PersonSystemException if id not found
     */
    @Override
    public Person getSinglePerson(String personId) throws PersonSystemException {
        return personRepository.findById(personId)
                .orElseThrow(() -> new PersonSystemException(ErrMsg.NOT_FOUND, "id"));
    }
}
