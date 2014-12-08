package com.gardnerdenver.facade;

import java.io.Serializable;
import java.util.List;

import com.gardnerdenver.dao.HistoricoDao;
import com.gardnerdenver.model.Historico;

public class HistoricoFacade implements Serializable {

    private static final long serialVersionUID = 1L;

    private HistoricoDao pdmDao = new HistoricoDao();

    public void create(Historico pdm) {
        pdmDao.begin();
        pdmDao.save(pdm);
        pdmDao.commitAndClose();
    }

    public void update(Historico pdm) {
        pdmDao.begin();
        pdmDao.update(pdm);
        pdmDao.commitAndClose();
    }

    public void delete(Historico pdm) {
        pdmDao.begin();
        Historico persistedPersonWithIdOnly = pdmDao.findReferenceOnly(pdm.getHisId());
        pdmDao.delete(persistedPersonWithIdOnly);
        pdmDao.commitAndClose();

    }

    public Historico find(int pdmId) {
        pdmDao.begin();
        Historico person = pdmDao.find(pdmId);
        pdmDao.close();
        return person;
    }

    public List<Historico> listByParceiro(int parId) {
        pdmDao.begin();
        List<Historico> result = pdmDao.findHistoricoByParceiro(parId);
        pdmDao.close();
        return result;
    }
    public List<Historico> listAll() {
        pdmDao.begin();
        List<Historico> result = pdmDao.findAll();
        pdmDao.close();
        return result;
    }

}
