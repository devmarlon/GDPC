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
        servicoDAO.createEntityManager();
        Servico b = servicoDAO.findServicoByFab(fab);
        servicoDAO.closeEntityManager();

        if (b == null) {
            b = new Servico();
        }
        return b;
    }

    public void createServico(Servico servico) {
        servicoDAO.beginTransaction();
        servicoDAO.save(servico);
        servicoDAO.commitAndCloseTransaction();
    }

    public void updateServico(Servico servico) {
        servicoDAO.beginTransaction();
        servicoDAO.update(servico);
        servicoDAO.commitAndCloseTransaction();
    }

    public void deleteServico(Servico serv) {
        servicoDAO.beginTransaction();
        Servico persistedServicoWithIdOnly = servicoDAO.findReferenceOnly(serv.getSRV_ID());
        servicoDAO.delete(persistedServicoWithIdOnly);
        servicoDAO.commitAndCloseTransaction();

    }

    public Servico findServico(int servId) {
        servicoDAO.beginTransaction();
        Servico servico = servicoDAO.findById(servId);
        servicoDAO.closeTransaction();
        return servico;
    }

    public List<Servico> listAll() {
        servicoDAO.beginTransaction();
        List<Servico> result = servicoDAO.findLista();
        servicoDAO.closeTransaction();

        return result;
    }

    public List<Servico> listBusca(String d) {
        servicoDAO.beginTransaction();
        List<Servico> result = servicoDAO.findBusca(d);
        servicoDAO.closeTransaction();
        return result;
    }

//    public Servico findPersonWithAllDogs(int personId) {
//        servicoDAO.beginTransaction();
////        Servico person = servicoDAO.findPersonWithAllDogs(personId);
//        servicoDAO.closeTransaction();
////        return person;
//    }
    public void addServicoToEquipamento(int servId, int equipId, EquipamentoServico es) {
        servicoDAO.beginTransaction();
        equipamentoDAO.joinTransaction();
        Equipamento equipamento = equipamentoDAO.find(equipId);
        Servico servico = servicoDAO.find(servId);
//        EquipamentoServico es = new EquipamentoServico();
        es.setEquipamento(equipamento);
        es.setServico(servico);
        equipamento.getServicos().add(es);
        servico.getEquipamentos().add(es);
        servicoDAO.commitAndCloseTransaction();
    }

//    public void removeEquipamentoFromPerson(int equip_id, int par_id) {
//        servicoDAO.beginTransaction();
//        equipamentoDAO.join();
//        Equipamento equipamento = equipamentoDAO.find(equip_id);
//        Servico parceiro = servicoDAO.find(par_id);
//        parceiro.getEquipamentos().remove(equipamento);
//        equipamento.setParceiro(null);
//        servicoDAO.commitAndCloseTransaction();
//    }
}
