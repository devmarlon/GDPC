package com.gardnerdenver.facade;

import com.gardnerdenver.dao.PecaEqsDao;
import java.io.Serializable;
import java.util.List;

import com.gardnerdenver.model.PecaEqs;

public class PecaEqsFacade implements Serializable {

    private static final long serialVersionUID = 1L;

//    private ParceiroDAO pecaDAO;
    private PecaEqsDao pecaEqsDao;

    public PecaEqsFacade() {
//        if (pecaDAO == null) {
        pecaEqsDao = new PecaEqsDao();
//        }

    }

    public void deletePecaServico(PecaEqs pqs) {
        pecaEqsDao.deleteByID(pqs.getPES_ID());
//        pecaEqsDao.beginTransaction();
//        PecaEqs persistedPqs = pecaEqsDao.findReferenceOnly(pqs.getPES_ID());
//        persistedPqs.setEqs(null);
//        persistedPqs.setPeca(null);
//        pecaEqsDao.delete(persistedPqs);
//        pecaEqsDao.commitAndCloseTransaction();
    }

    public void createPeca(PecaEqs peca) {
        pecaEqsDao.beginTransaction();
        peca.setPES_ID(0);
        pecaEqsDao.save(peca);
        pecaEqsDao.commitAndCloseTransaction();
    }

    public void updatePeca(PecaEqs pecaEqs) {
        pecaEqsDao.beginTransaction();
        PecaEqs persistedPeca = pecaEqsDao.find(pecaEqs.getPES_ID());
        persistedPeca.setQuantidade(pecaEqs.getQuantidade());
        persistedPeca.setPeca(pecaEqs.getPeca());
        persistedPeca.setEqs(pecaEqs.getEqs());
        pecaEqsDao.update(persistedPeca);
        pecaEqsDao.commitAndCloseTransaction();
    }
//    public void deletePerson(Servico parceiro) {
//        pecaDAO.beginTransaction();
//        Servico persistedPersonWithIdOnly = pecaDAO.findReferenceOnly(parceiro.getPAR_ID());
//        pecaDAO.delete(persistedPersonWithIdOnly);
//        pecaDAO.commitAndCloseTransaction();
//
//    }

    public PecaEqs findPecaEqs(int pecaId) {
        pecaEqsDao.createEntityManager();
        PecaEqs peca = pecaEqsDao.find(pecaId);
        pecaEqsDao.closeEntityManager();
        return peca;
    }

    public List<PecaEqs> findPecaEqsByEqs(int eqsId) {
        pecaEqsDao.createEntityManager();
        List<PecaEqs> peca = pecaEqsDao.findListByEqs(eqsId);
        pecaEqsDao.closeEntityManager();
        return peca;
    }

    public List<PecaEqs> listAll() {
        pecaEqsDao.createEntityManager();
        List<PecaEqs> result = pecaEqsDao.findAll();
        pecaEqsDao.closeEntityManager();

        return result;
    }

//    public List<PecaEqs> listByEqs(int eqsId) {
//        pecaEqsDao.beginTransaction();
////        List<PecaEqs> result = pecaEqsDao.
//        pecaEqsDao.commitAndCloseTransaction();
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
