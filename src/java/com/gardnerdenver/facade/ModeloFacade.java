package com.gardnerdenver.facade;

import com.gardnerdenver.dao.ModeloDao;
import java.io.Serializable;
import java.util.List;

import com.gardnerdenver.model.Modelo;

public class ModeloFacade implements Serializable {

    private static final long serialVersionUID = 1L;

//    private ParceiroDAO pecaDAO;
    private ModeloDao dao;

    public ModeloFacade() {
        dao = new ModeloDao();
    }

    public ModeloFacade(String banco) {
        dao = new ModeloDao(banco, true);
    }

    public void create(Modelo m) {
        try {
            dao.beginTransaction();
            dao.save(m);
        } catch (Exception e) {
            dao.rollback();
            System.out.println(e.getMessage());
        } finally {
            dao.commitAndCloseTransaction();
        }

//        dao.beginTransaction();
//        peca.setMOD_ID(0);
//        dao.save(peca);
//        dao.commitAndCloseTransaction();
    }

    public void update(Modelo peca) {
        dao.beginTransaction();
        dao.update(peca);
        dao.commitAndCloseTransaction();
    }
//    public void deletePerson(Servico parceiro) {
//        pecaDAO.beginTransaction();
//        Servico persistedPersonWithIdOnly = pecaDAO.findReferenceOnly(parceiro.getPAR_ID());
//        pecaDAO.delete(persistedPersonWithIdOnly);
//        pecaDAO.commitAndCloseTransaction();
//
//    }

    public void delete(Modelo peca) {
        dao.beginTransaction();
        Modelo persistedServicoWithIdOnly = dao.findReferenceOnly(peca.getMOD_ID());
        dao.delete(persistedServicoWithIdOnly);
        dao.commitAndCloseTransaction();
    }

    public Modelo findModelo(int pecaId) {
        dao.beginTransaction();
        Modelo peca = dao.find(pecaId);
        dao.closeTransaction();
        return peca;
    }

    public Modelo findModeloByFab(int fab) {
        dao.beginTransaction();
        Modelo peca = dao.findModeloByFab(fab);
        dao.closeTransaction();
        return peca;
    }

    public List<Modelo> listAll() {
        dao.beginTransaction();
        List<Modelo> result = dao.findAll();
        dao.closeTransaction();
        return result;
    }

    public List<Modelo> listBusca(String d) {
        dao.beginTransaction();
        List<Modelo> result = dao.findBusca(d);
        dao.closeTransaction();
        return result;
    }

//    public List<Modelo> listByEqs(int eqsId) {
//        pecaDAO.beginTransaction();
//        List<Modelo> result = pecaDAO.findPecaByEqs(eqsId);
//        pecaDAO.commitAndCloseTransaction();
//
//        return result;
//    }
//    public Servico findPersonWithAllDogs(int personId) {
//        pecaDAO.beginTransaction();
////        Servico person = pecaDAO.findPersonWithAllDogs(personId);
//        pecaDAO.closeTransaction();
////        return person;
//    }
//    public void addServicoToEquipamento(int servId, int equipId, EquipamentoServico es) {
//        pecaDAO.beginTransaction();
//        equipamentoDAO.joinTransaction();
//        Equipamento equipamento = equipamentoDAO.find(equipId);
//        Servico servico = pecaDAO.find(servId);
////        EquipamentoServico es = new EquipamentoServico();
//        es.setEquipamento(equipamento);
//        es.setServico(servico);
//        equipamento.getServicos().add(es);
//        servico.getEquipamentos().add(es);
//        pecaDAO.commitAndCloseTransaction();
//    }
//    public void removeEquipamentoFromPerson(int equip_id, int par_id) {
//        pecaDAO.beginTransaction();
//        equipamentoDAO.joinTransaction();
//        Equipamento equipamento = equipamentoDAO.find(equip_id);
//        Servico parceiro = pecaDAO.find(par_id);
//        parceiro.getEquipamentos().remove(equipamento);
//        equipamento.setParceiro(null);
//        pecaDAO.commitAndCloseTransaction();
//    }
}
