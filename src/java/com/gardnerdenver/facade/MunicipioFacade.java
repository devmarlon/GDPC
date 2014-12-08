package com.gardnerdenver.facade;

import java.io.Serializable;

import com.gardnerdenver.dao.MunicipioDAO;
import com.gardnerdenver.model.Municipio;
import java.util.List;

public class MunicipioFacade implements Serializable {

    private static final long serialVersionUID = 1L;

    private MunicipioDAO munDao;

    public MunicipioFacade() {
        munDao = new MunicipioDAO();
    }

    public Municipio findMunicipio(int munId) {
        munDao.beginTransaction();
        Municipio mun = munDao.find(munId);
        munDao.closeTransaction();
        return mun;
    }

    public List<Municipio> findListMunicipioByUF(int ufId) {
        munDao.beginTransaction();
        List<Municipio> result = munDao.findListMunicipioByUF(ufId);
        munDao.commitAndCloseTransaction();

        return result;
    }

//    public List<Configuracao> listAll() {
//        munDao.beginTransaction();
//        List<Configuracao> result = munDao.findAll();
//        munDao.closeTransaction();
//        return result;
//    }
//    public void deleteDog(Configuracao dog) {
//        configDao.beginTransaction();
//        Configuracao persistedDog = configDao.findReferenceOnly(dog.getId());
//        configDao.delete(persistedDog);
//        configDao.commitAndCloseTransaction();
//    }
}
