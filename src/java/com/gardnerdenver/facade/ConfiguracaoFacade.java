package com.gardnerdenver.facade;

import com.gardnerdenver.bean.UserItemFactoryBean;
import java.io.Serializable;
import java.util.List;

import com.gardnerdenver.dao.ConfiguracaoDAO;
import com.gardnerdenver.model.Configuracao;

public class ConfiguracaoFacade implements Serializable {

    private static final long serialVersionUID = 1L;

    private ConfiguracaoDAO configDao;

    public ConfiguracaoFacade() {
        configDao = new ConfiguracaoDAO();
    }

    public ConfiguracaoFacade(String banco) {
        configDao = new ConfiguracaoDAO(banco);
    }

    public void createConfig(Configuracao cfg) {
        configDao.begin();
        configDao.save(cfg);
        configDao.commitAndClose();
    }

    public void updateConfig(Configuracao cfg) {
        configDao.begin();
        configDao.update(cfg);
        configDao.commitAndClose();
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
        configDao.begin();
        Configuracao cfg = configDao.find(cfgId);
        configDao.close();
        return cfg;
    }

    public List<Configuracao> listAll() {
        configDao.begin();
        List<Configuracao> result = configDao.findAll();
        configDao.close();
        return result;
    }

//    public void deleteDog(Configuracao dog) {
//        configDao.beginTransaction();
//        Configuracao persistedDog = configDao.findReferenceOnly(dog.getId());
//        configDao.delete(persistedDog);
//        configDao.commitAndCloseTransaction();
//    }
}
