package com.gardnerdenver.facade;

import java.io.Serializable;
import java.util.List;

import com.gardnerdenver.dao.DogDAO;
import com.gardnerdenver.model.Dog;

public class DogFacade implements Serializable {

    private static final long serialVersionUID = 1L;

    private DogDAO dogDAO = new DogDAO();

    public void createDog(Dog dog) {
        dogDAO.begin();
        dogDAO.save(dog);
        dogDAO.commitAndClose();
    }

    public void updateDog(Dog dog) {
        dogDAO.begin();
        Dog persistedDog = dogDAO.find(dog.getId());
        persistedDog.setAge(dog.getAge());
        persistedDog.setName(dog.getName());
        dogDAO.update(persistedDog);
        dogDAO.commitAndClose();
    }

    public Dog findDog(int dogId) {
        dogDAO.begin();
        Dog dog = dogDAO.find(dogId);
        dogDAO.close();
        return dog;
    }

    public List<Dog> listAll() {
        dogDAO.begin();
        List<Dog> result = dogDAO.findAll();
        dogDAO.close();
        return result;
    }

    public void deleteDog(Dog dog) {
        dogDAO.begin();
        Dog persistedDog = dogDAO.findReferenceOnly(dog.getId());
        dogDAO.delete(persistedDog);
        dogDAO.commitAndClose();
    }
}
