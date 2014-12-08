package com.gardnerdenver.facade;

import com.gardnerdenver.dao.FactoryPecaDao;
import com.gardnerdenver.model.FactoryPeca;
import java.io.Serializable;
import java.util.List;


public class FactoryPecaFacade implements Serializable {

    private static final long serialVersionUID = 1L;

//    private ParceiroDAO pecaDAO;
    private FactoryPecaDao pecaDAO;

    public FactoryPecaFacade() {
//        if (pecaDAO == null) {
        pecaDAO = new FactoryPecaDao();
//        }

    }

    public void createPeca(FactoryPeca peca) {
        pecaDAO.beginTransaction();
        peca.setPEC_ID(0);
        pecaDAO.save(peca);
        pecaDAO.commitAndCloseTransaction();
    }

    public void updatePeca(FactoryPeca peca) {
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

    public void deletePeca(FactoryPeca peca) {
        pecaDAO.beginTransaction();
        FactoryPeca persistedServicoWithIdOnly = pecaDAO.findReferenceOnly(peca.getPEC_ID());
        pecaDAO.delete(persistedServicoWithIdOnly);
        pecaDAO.commitAndCloseTransaction();
    }

    public FactoryPeca findPeca(int pecaId) {
        pecaDAO.beginTransaction();
        FactoryPeca peca = pecaDAO.find(pecaId);
        pecaDAO.closeTransaction();
        return peca;
    }

    public FactoryPeca findPecaByCod(String pecaCod) {
        pecaDAO.beginTransaction();
        FactoryPeca peca = pecaDAO.findPecaByCode(pecaCod);
        pecaDAO.closeTransaction();
        return peca;
    }

    public List<FactoryPeca> listAll() {
        pecaDAO.beginTransaction();
        List<FactoryPeca> result = pecaDAO.findAll();
        pecaDAO.closeTransaction();
        return result;
    }
    public List<FactoryPeca> listBusca(String d) {
        pecaDAO.beginTransaction();
        List<FactoryPeca> result = pecaDAO.findBusca(d);
        pecaDAO.closeTransaction();
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
