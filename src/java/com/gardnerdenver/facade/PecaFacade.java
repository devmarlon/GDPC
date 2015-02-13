package com.gardnerdenver.facade;

import com.gardnerdenver.dao.PecaDAO;
import java.io.Serializable;
import java.util.List;

import com.gardnerdenver.model.Equipamento;
import com.gardnerdenver.model.EquipamentoServico;
import com.gardnerdenver.model.Peca;
import com.gardnerdenver.model.Person;
import com.gardnerdenver.model.Servico;

public class PecaFacade implements Serializable {

    private static final long serialVersionUID = 1L;

//    private ParceiroDAO pecaDAO;
    private PecaDAO pecaDAO;

    public PecaFacade() {
        pecaDAO = new PecaDAO();
    }

    public PecaFacade(String banco) {
        pecaDAO = new PecaDAO(banco, true);
    }

    public void createPeca(Peca peca) {
        pecaDAO.beginTransaction();
        peca.setPEC_ID(0);
        pecaDAO.save(peca);
        pecaDAO.commitAndCloseTransaction();
    }

    public void updatePeca(Peca peca) {
        pecaDAO.beginTransaction();
        pecaDAO.update(peca);
        pecaDAO.commitAndCloseTransaction();
    }
//    public void deletePerson(Servico parceiro) {
//        pecaDAO.beginTransaction();
//        Servico persistedPersonWithIdOnly = pecaDAO.findReferenceOnly(parceiro.getPAR_ID());
//        pecaDAO.delete(persistedPersonWithIdOnly);
//        pecaDAO.commitAndCloseTransaction();
//
//    }

    public void deletePeca(Peca peca) {
        pecaDAO.beginTransaction();
        Peca persistedServicoWithIdOnly = pecaDAO.findReferenceOnly(peca.getPEC_ID());
        pecaDAO.delete(persistedServicoWithIdOnly);
        pecaDAO.commitAndCloseTransaction();
    }

    public Peca findPeca(int pecaId) {
        pecaDAO.createEntityManager();
        Peca peca = pecaDAO.find(pecaId);
        pecaDAO.closeEntityManager();
        return peca;
    }

    public Peca findPecaByCod(String pecaCod) {
        pecaDAO.createEntityManager();
        Peca peca = pecaDAO.findPecaByCode(pecaCod);
        pecaDAO.closeEntityManager();
        return peca;
    }

    public Peca findPecaByFab(int fab) {
        pecaDAO.createEntityManager();
        Peca peca = pecaDAO.findPecaByFab(fab);
        pecaDAO.closeEntityManager();
        return peca;
    }

    public List<Peca> listAll() {
        pecaDAO.createEntityManager();
        List<Peca> result = pecaDAO.findLista();
        pecaDAO.closeEntityManager();
        return result;
    }

    public List<Peca> listBusca(String d) {
        pecaDAO.createEntityManager();
        List<Peca> result = pecaDAO.findBusca(d);
        pecaDAO.closeEntityManager();
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
