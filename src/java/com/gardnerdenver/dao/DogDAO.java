package com.gardnerdenver.dao;


import com.gardnerdenver.model.Dog;

public class DogDAO extends GenericDAO<Dog> {

    private static final long serialVersionUID = 1L;

    public DogDAO() {
        super(null, Dog.class, false);
    }

    public void delete(Dog dog) {
        super.delete(dog.getId());
    }

}
