package com.gardnerdenver.facade;

import com.gardnerdenver.dao.FactoryModeloDao;
import com.gardnerdenver.model.FactoryModelo;
import java.io.Serializable;
import java.util.List;

public class FactoryModeloFacade implements Serializable {

    private static final long serialVersionUID = 1L;

//    private ParceiroDAO pecaDAO;
    private FactoryModeloDao dao;

    public FactoryModeloFacade() {
//        if (pecaDAO == null) {
        dao = new FactoryModeloDao();
//        }

    }

    public void createModelo(FactoryModelo fModelo) {
        try {
            dao.beginTransaction();
            dao.save(fModelo);
        } catch (Exception e) {
            dao.rollback();
            System.out.println(e.getMessage());
        } finally {
            dao.commitAndCloseTransaction();
        }
//        dao.beginTransaction();
//        fModelo.setMOD_ID(0);
//        dao.save(fModelo);
//        dao.commitAndCloseTransaction();
    }

    public void updateModelo(FactoryModelo peca) {
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

    public void deletePeca(FactoryModelo peca) {
        dao.beginTransaction();
        FactoryModelo persistedServicoWithIdOnly = dao.findReferenceOnly(peca.getMOD_ID());
        dao.delete(persistedServicoWithIdOnly);
        dao.commitAndCloseTransaction();
    }

    public FactoryModelo findModelo(int pecaId) {
        dao.beginTransaction();
        FactoryModelo peca = dao.find(pecaId);
        dao.closeTransaction();
        return peca;
    }

    public FactoryModelo findPecaByCod(String pecaCod) {
        dao.beginTransaction();
        FactoryModelo peca = dao.findPecaByCode(pecaCod);
        dao.closeTransaction();
        return peca;
    }

    public List<FactoryModelo> listAll() {
        dao.beginTransaction();
        List<FactoryModelo> result = dao.findAll();
        dao.closeTransaction();
        return result;
    }
    public List<FactoryModelo> listAllAtivos() {
        dao.beginTransaction();
        List<FactoryModelo> result = dao.findAll();
        dao.closeTransaction();
        return result;
    }

    public List<FactoryModelo> listBusca(String d) {
        dao.beginTransaction();
        List<FactoryModelo> result = dao.findBusca(d);
        dao.closeTransaction();
        return result;
    }

//    public List<Peca> listByEqs(int eqsId) {
//        pecaDAO.beginTransaction();
//        List<Peca> result = pecaDAO.findPecaByEqs(eqsId);
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
