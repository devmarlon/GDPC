package com.gardnerdenver.facade;

import java.io.Serializable;
import java.util.List;

import com.gardnerdenver.dao.EquipamentoMedicaoDAO;
import com.gardnerdenver.model.EquipamentoMedicao;

public class EquipamentoMedicaoFacade implements Serializable {

    private static final long serialVersionUID = 1L;

    private EquipamentoMedicaoDAO eqmDAO;

    public EquipamentoMedicaoFacade() {
        eqmDAO = new EquipamentoMedicaoDAO();
    }

    public void createEqm(EquipamentoMedicao eqm) {
        eqmDAO.beginTransaction();
        eqmDAO.save(eqm);
        eqmDAO.commitAndCloseTransaction();
    }

    public EquipamentoMedicao findEquipamento(int eqpId) {
        eqmDAO.createEntityManager();
        EquipamentoMedicao eqp = eqmDAO.find(eqpId);
        eqmDAO.closeEntityManager();
        return eqp;
    }

    public EquipamentoMedicao findLastUpdate(int eqpId) {
        eqmDAO.createEntityManager();
        EquipamentoMedicao eqm = eqmDAO.findLastUpdate(eqpId);
        eqmDAO.closeEntityManager();
        return eqm;
    }

    public List<EquipamentoMedicao> listAll() {
        eqmDAO.beginTransaction();
        List<EquipamentoMedicao> result = eqmDAO.findAll();
        eqmDAO.closeEntityManager();
        return result;
    }
    public List<EquipamentoMedicao> listByEqp(int eqpId) {
        eqmDAO.createEntityManager();
        List<EquipamentoMedicao> result = eqmDAO.findListByEqp(eqpId);
        eqmDAO.closeEntityManager();
        return result;
    }

    public void deleteEquipamento(EquipamentoMedicao eqp) {
        eqmDAO.beginTransaction();
        EquipamentoMedicao persistedEqp = eqmDAO.findReferenceOnly(eqp.getEQM_ID());
        eqmDAO.delete(persistedEqp);
        eqmDAO.commitAndCloseTransaction();
    }
}
