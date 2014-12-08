package com.gardnerdenver.facade;

import java.io.Serializable;
import java.util.List;

import com.gardnerdenver.dao.DogDAO;
import com.gardnerdenver.dao.PersonDAO;
import com.gardnerdenver.model.Dog;
import com.gardnerdenver.model.Person;

public class PersonFacade implements Serializable {

    private static final long serialVersionUID = 1L;

    private PersonDAO personDAO = new PersonDAO();
    private DogDAO dogDAO = new DogDAO();

    public void createPerson(Person person) {
        personDAO.begin();
        personDAO.save(person);
        personDAO.commitAndClose();
    }

    public void updatePerson(Person person) {
        personDAO.begin();
        Person persistedPerson = personDAO.find(person.getId());
        persistedPerson.setName(person.getName());
        persistedPerson.setAge(person.getAge());
        personDAO.commitAndClose();
    }

    public void deletePerson(Person person) {
        personDAO.begin();
        Person persistedPersonWithIdOnly = personDAO.findReferenceOnly(person.getId());
        personDAO.delete(persistedPersonWithIdOnly);
        personDAO.commitAndClose();

    }

    public Person findPerson(int personId) {
        personDAO.begin();
        Person person = personDAO.find(personId);
        personDAO.close();
        return person;
    }

    public List<Person> listAll() {
        personDAO.begin();
        List<Person> result = personDAO.findAll();
        personDAO.close();

        return result;
    }

    public Person findPersonWithAllDogs(int personId) {
        personDAO.begin();
        Person person = personDAO.findPersonWithAllDogs(personId);
        personDAO.close();
        return person;
    }

    public void addDogToPerson(int dogId, int personId) {
        personDAO.begin();
        dogDAO.joinTransaction();
        Dog dog = dogDAO.find(dogId);
        Person person = personDAO.find(personId);
        person.getDogs().add(dog);
        dog.getPerson().add(person);
        personDAO.commitAndClose();
    }

    public void removeDogFromPerson(int dogId, int personId) {
        personDAO.begin();
        dogDAO.joinTransaction();
        Dog dog = dogDAO.find(dogId);
        Person person = personDAO.find(personId);
        person.getDogs().remove(dog);
        dog.getPerson().remove(person);
        personDAO.commitAndClose();
    }
}
