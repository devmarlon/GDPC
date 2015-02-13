package com.gardnerdenver.facade;

import java.io.Serializable;
import java.util.List;

import com.gardnerdenver.dao.HistoricoDao;
import com.gardnerdenver.model.Historico;

public class HistoricoFacade implements Serializable {

    private static final long serialVersionUID = 1L;

    private HistoricoDao pdmDao = new HistoricoDao();

    public void create(Historico pdm) {
        pdmDao.beginTransaction();
        pdmDao.save(pdm);
        pdmDao.commitAndCloseTransaction();
    }

    public void update(Historico pdm) {
        pdmDao.beginTransaction();
        pdmDao.update(pdm);
        pdmDao.commitAndCloseTransaction();
    }

    public void delete(Historico pdm) {
        pdmDao.beginTransaction();
        Historico persistedPersonWithIdOnly = pdmDao.findReferenceOnly(pdm.getHisId());
        pdmDao.delete(persistedPersonWithIdOnly);
        pdmDao.commitAndCloseTransaction();

    }

    public Historico find(int pdmId) {
        pdmDao.beginTransaction();
        Historico person = pdmDao.find(pdmId);
        pdmDao.closeTransaction();
        return person;
    }

    public List<Historico> listByParceiro(int parId) {
        pdmDao.createEntityManager();
        List<Historico> result = pdmDao.findHistoricoByParceiro(parId);
        pdmDao.closeEntityManager();
        return result;
    }
    public List<Historico> listAll() {
        pdmDao.createEntityManager();
        List<Historico> result = pdmDao.findAll();
        pdmDao.closeEntityManager();
        return result;
    }

}
