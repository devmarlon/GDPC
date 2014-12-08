package com.gardnerdenver.facade;

import com.gardnerdenver.dao.FactoryServicoDao;
import com.gardnerdenver.model.FactoryServico;
import java.io.Serializable;
import java.util.List;

public class FactoryServicoFacade implements Serializable {

    private static final long serialVersionUID = 1L;

//    private ParceiroDAO servicoDAO;
    private final FactoryServicoDao servicoDAO;
//    private final EquipamentoDAO equipamentoDAO;

    public FactoryServicoFacade() {
        servicoDAO = new FactoryServicoDao();
//        if (equipamentoDAO == null) {
//        equipamentoDAO = new EquipamentoDAO();
//        }
    }

    public void createServico(FactoryServico servico) {
        servicoDAO.beginTransaction();
        servicoDAO.save(servico);
        servicoDAO.commitAndCloseTransaction();
    }

    public void updateServico(FactoryServico servico) {
        servicoDAO.beginTransaction();
        servicoDAO.update(servico);
        servicoDAO.commitAndCloseTransaction();
    }

    public void deleteServico(FactoryServico serv) {
        servicoDAO.beginTransaction();
        FactoryServico persistedServicoWithIdOnly = servicoDAO.findReferenceOnly(serv.getSRV_ID());
        servicoDAO.delete(persistedServicoWithIdOnly);
        servicoDAO.commitAndCloseTransaction();

    }

    public FactoryServico findServico(int servId) {
        servicoDAO.beginTransaction();
        FactoryServico servico = servicoDAO.find(servId);
        servicoDAO.closeTransaction();
        return servico;
    }

    public List<FactoryServico> listAll() {
        servicoDAO.beginTransaction();
        List<FactoryServico> result = servicoDAO.findAll();
        servicoDAO.closeTransaction();

        return result;
    }

    public List<FactoryServico> listBusca(String d) {
        servicoDAO.beginTransaction();
        List<FactoryServico> result = servicoDAO.findBusca(d);
        servicoDAO.closeTransaction();
        return result;
    }

//    public FactoryServico findPersonWithAllDogs(int personId) {
//        servicoDAO.beginTransaction();
////        FactoryServico person = servicoDAO.findPersonWithAllDogs(personId);
//        servicoDAO.closeTransaction();
////        return person;
//    }
//    public void removeEquipamentoFromPerson(int equip_id, int par_id) {
//        servicoDAO.beginTransaction();
//        equipamentoDAO.joinTransaction();
//        Equipamento equipamento = equipamentoDAO.find(equip_id);
//        FactoryServico parceiro = servicoDAO.find(par_id);
//        parceiro.getEquipamentos().remove(equipamento);
//        equipamento.setParceiro(null);
//        servicoDAO.commitAndCloseTransaction();
//    }
}
