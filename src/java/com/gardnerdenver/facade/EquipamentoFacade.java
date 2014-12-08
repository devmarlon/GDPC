package com.gardnerdenver.facade;

import java.io.Serializable;
import java.util.List;

import com.gardnerdenver.dao.EquipamentoDAO;
import com.gardnerdenver.model.Equipamento;
import com.gardnerdenver.model.Parceiro;

public class EquipamentoFacade implements Serializable {

    private static final long serialVersionUID = 1L;

    private final EquipamentoDAO equipamentoDAO;

    public EquipamentoFacade() {
        equipamentoDAO = new EquipamentoDAO();
    }

    public void createEquipamento(Equipamento eqp) {
        equipamentoDAO.begin();
        equipamentoDAO.save(eqp);
        equipamentoDAO.commitAndClose();
    }

    public boolean verificaSN(String sn) {
        equipamentoDAO.begin();
        Equipamento eqp = equipamentoDAO.findEqpBySN(sn);
        equipamentoDAO.close();

        return eqp != null;
    }

    public void updateEquipamento(Equipamento eqp) {
        equipamentoDAO.begin();
        equipamentoDAO.update(eqp);
        equipamentoDAO.commitAndClose();
    }

    public Equipamento findEquipamento(int eqpId) {
        equipamentoDAO.begin();
        Equipamento eqp = equipamentoDAO.find(eqpId);
        equipamentoDAO.close();
        return eqp;
    }

    public List<Equipamento> listBusca(String descricao, Parceiro par, int catId, String modelo, String serie, String fabricante) {
        equipamentoDAO.begin();
        List<Equipamento> result = equipamentoDAO.findBusca(descricao, par, catId, modelo, serie, fabricante);
        equipamentoDAO.close();
        return result;
    }

    public List<Equipamento> listAll() {
        equipamentoDAO.begin();
        List<Equipamento> result = equipamentoDAO.findAll();
        equipamentoDAO.close();
        return result;
    }

    public void deleteEquipamento(Equipamento eqp) {
        equipamentoDAO.begin();
        Equipamento persistedEqp = equipamentoDAO.findReferenceOnly(eqp.getEQP_ID());
        equipamentoDAO.delete(persistedEqp);
        equipamentoDAO.commitAndClose();
    }
}
