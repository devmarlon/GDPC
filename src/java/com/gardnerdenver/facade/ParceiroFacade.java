package com.gardnerdenver.facade;

import java.io.Serializable;
import java.util.List;

import com.gardnerdenver.dao.ParceiroDAO;
import com.gardnerdenver.dao.EquipamentoDAO;
import com.gardnerdenver.model.Equipamento;
import com.gardnerdenver.model.Estado;
import com.gardnerdenver.model.Municipio;
import com.gardnerdenver.model.Parceiro;

public class ParceiroFacade implements Serializable {

    private static final long serialVersionUID = 1L;

    private ParceiroDAO parceiroDAO;
    private EquipamentoDAO equipamentoDAO;

    public ParceiroFacade() {
//        if (parceiroDAO == null) {
        parceiroDAO = new ParceiroDAO();
//        }
//        if (equipamentoDAO == null) {
        equipamentoDAO = new EquipamentoDAO();
//        }
    }

    public void createParceiro(Parceiro parceiro) {
        parceiroDAO.beginTransaction();
        parceiroDAO.save(parceiro);
        parceiroDAO.commitAndCloseTransaction();
    }

    public void updateParceiro(Parceiro parceiro) {
        parceiroDAO.beginTransaction();
        parceiroDAO.update(parceiro);
        parceiroDAO.commitAndCloseTransaction();
    }

    public void deletePerson(Parceiro parceiro) {
        parceiroDAO.beginTransaction();
        Parceiro persistedPersonWithIdOnly = parceiroDAO.findReferenceOnly(parceiro.getPAR_ID());
        parceiroDAO.delete(persistedPersonWithIdOnly);
        parceiroDAO.commitAndCloseTransaction();

    }

    public Parceiro findParceiro(int parId) {
        parceiroDAO.createEntityManager();
        Parceiro person = parceiroDAO.find(parId);
        parceiroDAO.closeEntityManager();
        return person;
    }

    public List<Parceiro> listAll() {
        parceiroDAO.createEntityManager();
        List<Parceiro> result = parceiroDAO.findAll();
        parceiroDAO.closeEntityManager();

        return result;
    }

    public List<Parceiro> listParceiros() {
        parceiroDAO.createEntityManager();
        List<Parceiro> result = parceiroDAO.findParceiros();
        parceiroDAO.closeEntityManager();

        return result;
    }

    public List<Parceiro> listParceirosBusca(String NOME, String CPFCNPJ, String ENDERECO, String BAIRRO, String OUTRO, Estado ESTADO, Municipio CIDADE) {
        parceiroDAO.createEntityManager();
        List<Parceiro> result = parceiroDAO.findParceirosBusca(NOME, CPFCNPJ, ENDERECO, BAIRRO, OUTRO, ESTADO, CIDADE);
        parceiroDAO.closeEntityManager();

        return result;
    }

//    public Parceiro findPersonWithAllDogs(int personId) {
//        parceiroDAO.beginTransaction();
////        Parceiro person = parceiroDAO.findPersonWithAllDogs(personId);
//        parceiroDAO.closeTransaction();
////        return person;
//    }
    public void addEquipamentoToPerson(int equip_id, int par_id) {
        parceiroDAO.beginTransaction();
        equipamentoDAO.joinTransaction();
        Equipamento equipamento = equipamentoDAO.find(equip_id);
        Parceiro parceiro = parceiroDAO.find(par_id);
        parceiro.getEquipamentos().add(equipamento);
        equipamento.setParceiro(parceiro);
        parceiroDAO.commitAndCloseTransaction();
    }

    public void removeEquipamentoFromPerson(int equip_id, int par_id) {
        parceiroDAO.beginTransaction();
        equipamentoDAO.joinTransaction();
        Equipamento equipamento = equipamentoDAO.find(equip_id);
        Parceiro parceiro = parceiroDAO.find(par_id);
        parceiro.getEquipamentos().remove(equipamento);
        equipamento.setParceiro(null);
        parceiroDAO.commitAndCloseTransaction();
    }
}
