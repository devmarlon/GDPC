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
        equipamentoDAO.beginTransaction();
        equipamentoDAO.save(eqp);
        equipamentoDAO.commitAndCloseTransaction();
    }

    public boolean verificaSN(String sn) {
        equipamentoDAO.createEntityManager();
        Equipamento eqp = equipamentoDAO.findEqpBySN(sn);
        equipamentoDAO.closeEntityManager();

        return eqp != null;
    }

    public void updateEquipamento(Equipamento eqp) {
        equipamentoDAO.beginTransaction();
        equipamentoDAO.update(eqp);
        equipamentoDAO.commitAndCloseTransaction();
    }

    public Equipamento findEquipamento(int eqpId) {
        equipamentoDAO.createEntityManager();
        Equipamento eqp = equipamentoDAO.find(eqpId);
        equipamentoDAO.closeEntityManager();
        return eqp;
    }

    public List<Equipamento> listBusca(String descricao, Parceiro par, int catId, String modelo, String serie, String fabricante) {
        equipamentoDAO.createEntityManager();
        List<Equipamento> result = equipamentoDAO.findBusca(descricao, par, catId, modelo, serie, fabricante);
        equipamentoDAO.closeEntityManager();
        return result;
    }

    public List<Equipamento> listAll() {
        equipamentoDAO.createEntityManager();
        List<Equipamento> result = equipamentoDAO.findAll();
        equipamentoDAO.closeEntityManager();
        return result;
    }

    public List<Equipamento> listByParceiro(Parceiro p) {
        equipamentoDAO.createEntityManager();
        List<Equipamento> result = equipamentoDAO.findListByParceiro(p);
        equipamentoDAO.closeEntityManager();
        return result;
    }

    public void deleteEquipamento(Equipamento eqp) {
        equipamentoDAO.beginTransaction();
        Equipamento persistedEqp = equipamentoDAO.findReferenceOnly(eqp.getEQP_ID());
        equipamentoDAO.delete(persistedEqp);
        equipamentoDAO.commitAndCloseTransaction();
    }
}
