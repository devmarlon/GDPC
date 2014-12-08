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
        eqmDAO.begin();
        eqmDAO.save(eqm);
        eqmDAO.commitAndClose();
    }

    public EquipamentoMedicao findEquipamento(int eqpId) {
        eqmDAO.begin();
        EquipamentoMedicao eqp = eqmDAO.find(eqpId);
        eqmDAO.close();
        return eqp;
    }

    public EquipamentoMedicao findLastUpdate(int eqpId) {
        eqmDAO.begin();
        EquipamentoMedicao eqm = eqmDAO.findLastUpdate(eqpId);
        eqmDAO.close();
        return eqm;
    }

    public List<EquipamentoMedicao> listAll() {
        eqmDAO.begin();
        List<EquipamentoMedicao> result = eqmDAO.findAll();
        eqmDAO.close();
        return result;
    }
    public List<EquipamentoMedicao> listByEqp(int eqpId) {
        eqmDAO.begin();
        List<EquipamentoMedicao> result = eqmDAO.findListByEqp(eqpId);
        eqmDAO.close();
        return result;
    }

    public void deleteEquipamento(EquipamentoMedicao eqp) {
        eqmDAO.begin();
        EquipamentoMedicao persistedEqp = eqmDAO.findReferenceOnly(eqp.getEQM_ID());
        eqmDAO.delete(persistedEqp);
        eqmDAO.commitAndClose();
    }
}
