package com.gardnerdenver.facade;

import com.gardnerdenver.bean.UserItemFactoryBean;
import java.io.Serializable;
import java.util.List;

import com.gardnerdenver.dao.ConfiguracaoGdpcDAO;
import com.gardnerdenver.model.Configuracao;

public class ConfiguracaoGdpcFacade implements Serializable {

    private static final long serialVersionUID = 1L;

    private ConfiguracaoGdpcDAO configDao;

    public ConfiguracaoGdpcFacade() {
        configDao = new ConfiguracaoGdpcDAO();
    }

    public ConfiguracaoGdpcFacade(String banco) {
        configDao = new ConfiguracaoGdpcDAO(banco);
    }

    public void createConfig(Configuracao cfg) {
        configDao.beginTransaction();
        configDao.save(cfg);
        configDao.commitAndCloseTransaction();
    }

    public void updateConfig(Configuracao cfg) {
        configDao.beginTransaction();
        configDao.update(cfg);
        configDao.commitAndCloseTransaction();
    }

//    public void updateDog(Configuracao dog) {
//        configDao.beginTransaction();
//        Configuracao persistedDog = configDao.find(dog.getId());
//        persistedDog.setAge(dog.getAge());
//        persistedDog.setName(dog.getName());
//        configDao.update(persistedDog);
//        configDao.commitAndCloseTransaction();
//    }
    public Configuracao findConfig(int cfgId) {
        configDao.beginTransaction();
        Configuracao cfg = configDao.find(cfgId);
        configDao.closeTransaction();
        return cfg;
    }

    public List<Configuracao> listAll() {
        configDao.beginTransaction();
        List<Configuracao> result = configDao.findAll();
        configDao.closeTransaction();
        return result;
    }

//    public void deleteDog(Configuracao dog) {
//        configDao.beginTransaction();
//        Configuracao persistedDog = configDao.findReferenceOnly(dog.getId());
//        configDao.delete(persistedDog);
//        configDao.commitAndCloseTransaction();
//    }
}
