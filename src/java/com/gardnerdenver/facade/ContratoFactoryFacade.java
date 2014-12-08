package com.gardnerdenver.facade;

import java.io.Serializable;
import java.util.List;

import com.gardnerdenver.dao.ContratoFactoryDAO;
import com.gardnerdenver.model.FactoryContrato;

public class ContratoFactoryFacade implements Serializable {

    private static final long serialVersionUID = 1L;

    private ContratoFactoryDAO ctoFactoryDao = new ContratoFactoryDAO();

    public void createContratoFactory(FactoryContrato dog) {
        ctoFactoryDao.beginTransaction();
        ctoFactoryDao.save(dog);
        ctoFactoryDao.commitAndCloseTransaction();
    }

    public void updateUserFactory(FactoryContrato FacUs) {
        ctoFactoryDao.beginTransaction();
//		UserFactory persistedUserFactory = userFactoryDao.find(dog.getId());
//		persistedUserFactory.setAge(dog.getAge());
//		persistedUserFactory.setName(dog.getName());
        ctoFactoryDao.update(FacUs);
        ctoFactoryDao.commitAndCloseTransaction();
    }

    public FactoryContrato findUserFactory(int dogId) {
        ctoFactoryDao.beginTransaction();
        FactoryContrato dog = ctoFactoryDao.find(dogId);
        ctoFactoryDao.closeTransaction();
        return dog;
    }

    public List<FactoryContrato> listAll() {
        ctoFactoryDao.beginTransaction();
        List<FactoryContrato> result = ctoFactoryDao.findAll();
        ctoFactoryDao.closeTransaction();
        return result;
    }

    public List<FactoryContrato> listDist() {
        ctoFactoryDao.beginTransaction();
        List<FactoryContrato> result = ctoFactoryDao.findListDist();
        ctoFactoryDao.closeTransaction();
        return result;
    }

    public void deleteUserFactory(FactoryContrato dog) {
        ctoFactoryDao.beginTransaction();
        FactoryContrato persistedUserFactory = ctoFactoryDao.findReferenceOnly(dog.getCtoId());
        ctoFactoryDao.delete(persistedUserFactory);
        ctoFactoryDao.commitAndCloseTransaction();
    }
}
