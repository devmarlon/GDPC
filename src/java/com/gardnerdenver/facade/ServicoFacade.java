package com.gardnerdenver.facade;

import com.gardnerdenver.dao.EquipamentoDAO;
import com.gardnerdenver.dao.ServicoDAO;
import java.io.Serializable;
import java.util.List;

import com.gardnerdenver.model.Equipamento;
import com.gardnerdenver.model.EquipamentoServico;
import com.gardnerdenver.model.Servico;

public class ServicoFacade implements Serializable {

    private static final long serialVersionUID = 1L;

//    private ParceiroDAO servicoDAO;
    private final ServicoDAO servicoDAO;
    private final EquipamentoDAO equipamentoDAO;

    public ServicoFacade() {
        servicoDAO = new ServicoDAO();
        equipamentoDAO = new EquipamentoDAO();
    }

    public ServicoFacade(String banco) {
        servicoDAO = new ServicoDAO(banco, true);
        equipamentoDAO = null;
    }

    public Servico findServicoByFab(int fab) {
        servicoDAO.begin();
        Servico peca = servicoDAO.findServicoByFab(fab);
        servicoDAO.close();
        return peca;
    }

    public void createServico(Servico servico) {
        servicoDAO.begin();
        servicoDAO.save(servico);
        servicoDAO.commitAndClose();
    }

    public void updateServico(Servico servico) {
        servicoDAO.begin();
        servicoDAO.update(servico);
        servicoDAO.commitAndClose();
    }

    public void deleteServico(Servico serv) {
        servicoDAO.begin();
        Servico persistedServicoWithIdOnly = servicoDAO.findReferenceOnly(serv.getSRV_ID());
        servicoDAO.delete(persistedServicoWithIdOnly);
        servicoDAO.commitAndClose();

    }

    public Servico findServico(int servId) {
        servicoDAO.begin();
        Servico servico = servicoDAO.find(servId);
        servicoDAO.close();
        return servico;
    }

    public List<Servico> listAll() {
        servicoDAO.begin();
        List<Servico> result = servicoDAO.findAll();
        servicoDAO.close();

        return result;
    }

    public List<Servico> listBusca(String d) {
        servicoDAO.begin();
        List<Servico> result = servicoDAO.findBusca(d);
        servicoDAO.close();
        return result;
    }

//    public Servico findPersonWithAllDogs(int personId) {
//        servicoDAO.begin();
////        Servico person = servicoDAO.findPersonWithAllDogs(personId);
//        servicoDAO.close();
////        return person;
//    }
    public void addServicoToEquipamento(int servId, int equipId, EquipamentoServico es) {
        servicoDAO.begin();
        equipamentoDAO.joinTransaction();
        Equipamento equipamento = equipamentoDAO.find(equipId);
        Servico servico = servicoDAO.find(servId);
//        EquipamentoServico es = new EquipamentoServico();
        es.setEquipamento(equipamento);
        es.setServico(servico);
        equipamento.getServicos().add(es);
        servico.getEquipamentos().add(es);
        servicoDAO.commitAndClose();
    }

//    public void removeEquipamentoFromPerson(int equip_id, int par_id) {
//        servicoDAO.begin();
//        equipamentoDAO.join();
//        Equipamento equipamento = equipamentoDAO.find(equip_id);
//        Servico parceiro = servicoDAO.find(par_id);
//        parceiro.getEquipamentos().remove(equipamento);
//        equipamento.setParceiro(null);
//        servicoDAO.commitAndClose();
//    }
}
