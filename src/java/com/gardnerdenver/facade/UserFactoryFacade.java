package com.gardnerdenver.facade;

import java.io.Serializable;
import java.util.List;

import com.gardnerdenver.dao.UserFactoryDAO;
import com.gardnerdenver.model.FactoryUser;

public class UserFactoryFacade implements Serializable {

    private static final long serialVersionUID = 1L;

    private UserFactoryDAO userFactoryDao = new UserFactoryDAO();

    public void createUserFactory(FactoryUser dog) {
        userFactoryDao.beginTransaction();
        userFactoryDao.save(dog);
        userFactoryDao.commitAndCloseTransaction();
    }

    public void updateUserFactory(FactoryUser FacUs) {
        userFactoryDao.beginTransaction();
//		UserFactory persistedUserFactory = userFactoryDao.find(dog.getId());
//		persistedUserFactory.setAge(dog.getAge());
//		persistedUserFactory.setName(dog.getName());
        userFactoryDao.update(FacUs);
        userFactoryDao.commitAndCloseTransaction();
    }

    public FactoryUser findUserFactory(int dogId) {
        userFactoryDao.beginTransaction();
        FactoryUser dog = userFactoryDao.find(dogId);
        userFactoryDao.closeTransaction();
        return dog;
    }

    public List<FactoryUser> listAll() {
        userFactoryDao.beginTransaction();
        List<FactoryUser> result = userFactoryDao.findAll();
        userFactoryDao.closeTransaction();
        return result;
    }

    public List<FactoryUser> listDist() {
        userFactoryDao.beginTransaction();
        List<FactoryUser> result = userFactoryDao.findListDist();
        userFactoryDao.closeTransaction();
        return result;
    }

    public void deleteUserFactory(FactoryUser dog) {
        userFactoryDao.beginTransaction();
        FactoryUser persistedUserFactory = userFactoryDao.findReferenceOnly(dog.getUSU_ID());
        userFactoryDao.delete(persistedUserFactory);
        userFactoryDao.commitAndCloseTransaction();
    }
}
