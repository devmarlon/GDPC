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

    private final ParceiroDAO parceiroDAO;
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
        parceiroDAO.begin();
        parceiroDAO.save(parceiro);
        parceiroDAO.commitAndClose();
    }

    public void updateParceiro(Parceiro parceiro) {
        parceiroDAO.begin();
        parceiroDAO.update(parceiro);
        parceiroDAO.commitAndClose();
    }

    public void deletePerson(Parceiro parceiro) {
        parceiroDAO.begin();
        Parceiro persistedPersonWithIdOnly = parceiroDAO.findReferenceOnly(parceiro.getPAR_ID());
        parceiroDAO.delete(persistedPersonWithIdOnly);
        parceiroDAO.commitAndClose();

    }

    public Parceiro findParceiro(int parId) {
        parceiroDAO.begin();
        Parceiro person = parceiroDAO.find(parId);
        parceiroDAO.close();
        return person;
    }

    public List<Parceiro> listAll() {
        parceiroDAO.begin();
        List<Parceiro> result = parceiroDAO.findAll();
        parceiroDAO.close();

        return result;
    }

    public List<Parceiro> listParceiros() {
        parceiroDAO.begin();
        List<Parceiro> result = parceiroDAO.findParceiros();
        parceiroDAO.close();

        return result;
    }

    public List<Parceiro> listParceirosBusca(String NOME, String CPFCNPJ, String ENDERECO, String BAIRRO, String OUTRO, Estado ESTADO, Municipio CIDADE) {
        parceiroDAO.begin();
        List<Parceiro> result = parceiroDAO.findParceirosBusca(NOME, CPFCNPJ, ENDERECO, BAIRRO, OUTRO, ESTADO, CIDADE);
        parceiroDAO.close();

        return result;
    }

//    public Parceiro findPersonWithAllDogs(int personId) {
//        parceiroDAO.begin();
////        Parceiro person = parceiroDAO.findPersonWithAllDogs(personId);
//        parceiroDAO.close();
////        return person;
//    }
    public void addEquipamentoToPerson(int equip_id, int par_id) {
        parceiroDAO.begin();
        equipamentoDAO.joinTransaction();
        Equipamento equipamento = equipamentoDAO.find(equip_id);
        Parceiro parceiro = parceiroDAO.find(par_id);
        parceiro.getEquipamentos().add(equipamento);
        equipamento.setParceiro(parceiro);
        parceiroDAO.commitAndClose();
    }

    public void removeEquipamentoFromPerson(int equip_id, int par_id) {
        parceiroDAO.begin();
        equipamentoDAO.joinTransaction();
        Equipamento equipamento = equipamentoDAO.find(equip_id);
        Parceiro parceiro = parceiroDAO.find(par_id);
        parceiro.getEquipamentos().remove(equipamento);
        equipamento.setParceiro(null);
        parceiroDAO.commitAndClose();
    }
}
