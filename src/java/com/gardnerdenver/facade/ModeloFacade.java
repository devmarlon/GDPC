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
            dao.begin();
            dao.save(m);
        } catch (Exception e) {
            dao.rollback();
            System.out.println(e.getMessage());
        } finally {
            dao.commitAndClose();
        }

//        dao.begin();
//        peca.setMOD_ID(0);
//        dao.save(peca);
//        dao.commitAndClose();
    }

    public void update(Modelo peca) {
        dao.begin();
        dao.update(peca);
        dao.commitAndClose();
    }
//    public void deletePerson(Servico parceiro) {
//        pecaDAO.begin();
//        Servico persistedPersonWithIdOnly = pecaDAO.findReferenceOnly(parceiro.getPAR_ID());
//        pecaDAO.delete(persistedPersonWithIdOnly);
//        pecaDAO.commitAndClose();
//
//    }

    public void delete(Modelo peca) {
        dao.begin();
        Modelo persistedServicoWithIdOnly = dao.findReferenceOnly(peca.getMOD_ID());
        dao.delete(persistedServicoWithIdOnly);
        dao.commitAndClose();
    }

    public Modelo findModelo(int pecaId) {
        dao.begin();
        Modelo peca = dao.find(pecaId);
        dao.close();
        return peca;
    }

    public Modelo findModeloByFab(int fab) {
        dao.begin();
        Modelo peca = dao.findModeloByFab(fab);
        dao.close();
        return peca;
    }

    public List<Modelo> listAll() {
        dao.begin();
        List<Modelo> result = dao.findAll();
        dao.close();
        return result;
    }

    public List<Modelo> listBusca(String d) {
        dao.begin();
        List<Modelo> result = dao.findBusca(d);
        dao.close();
        return result;
    }

//    public List<Modelo> listByEqs(int eqsId) {
//        pecaDAO.begin();
//        List<Modelo> result = pecaDAO.findPecaByEqs(eqsId);
//        pecaDAO.commitAndClose();
//
//        return result;
//    }
//    public Servico findPersonWithAllDogs(int personId) {
//        pecaDAO.begin();
////        Servico person = pecaDAO.findPersonWithAllDogs(personId);
//        pecaDAO.close();
////        return person;
//    }
//    public void addServicoToEquipamento(int servId, int equipId, EquipamentoServico es) {
//        pecaDAO.begin();
//        equipamentoDAO.joinTransaction();
//        Equipamento equipamento = equipamentoDAO.find(equipId);
//        Servico servico = pecaDAO.find(servId);
////        EquipamentoServico es = new EquipamentoServico();
//        es.setEquipamento(equipamento);
//        es.setServico(servico);
//        equipamento.getServicos().add(es);
//        servico.getEquipamentos().add(es);
//        pecaDAO.commitAndClose();
//    }
//    public void removeEquipamentoFromPerson(int equip_id, int par_id) {
//        pecaDAO.begin();
//        equipamentoDAO.joinTransaction();
//        Equipamento equipamento = equipamentoDAO.find(equip_id);
//        Servico parceiro = pecaDAO.find(par_id);
//        parceiro.getEquipamentos().remove(equipamento);
//        equipamento.setParceiro(null);
//        pecaDAO.commitAndClose();
//    }
}
