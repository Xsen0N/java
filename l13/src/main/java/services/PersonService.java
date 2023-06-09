package services;

import exception.RepositoryException;
import exception.ServiceException;
import models.Person;
import repository.PersonRepository;
import repository.RepositoryCreator;

import java.util.List;

public class PersonService {

    public List<Person> findAll() throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {

            PersonRepository personRepository = repositoryCreator.getPersonRepository();
            return personRepository.findAll();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Person person) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {

            PersonRepository personRepository = repositoryCreator.getPersonRepository();
            personRepository.save(person);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }
}
