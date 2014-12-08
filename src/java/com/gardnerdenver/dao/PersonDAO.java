package com.gardnerdenver.dao;

import java.util.HashMap;
import java.util.Map;

import com.gardnerdenver.model.Person;

public class PersonDAO extends GenericDAO<Person> {

    private static final long serialVersionUID = 1L;

    public PersonDAO() {
        super(null, Person.class, false);
    }

    public Person findPersonWithAllDogs(int personId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("personId", personId);

        return super.findOneResult(Person.FIND_USER_BY_ID_WITH_DOGS, parameters);
    }

    public void delete(Person person) {
        super.delete(person.getId(), Person.class);
    }
}
