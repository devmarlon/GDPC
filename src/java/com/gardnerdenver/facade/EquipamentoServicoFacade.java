package com.gardnerdenver.facade;

import com.gardnerdenver.dao.EquipamentoDAO;
import com.gardnerdenver.dao.EquipamentoServicoDAO;
import com.gardnerdenver.dao.MovimentoDao;
import com.gardnerdenver.dao.MovimentoItemDao;
import com.gardnerdenver.dao.ServicoDAO;
import com.gardnerdenver.model.EquipamentoServico;
import com.gardnerdenver.model.Movimento;
import com.gardnerdenver.model.MovimentoItem;
import java.io.Serializable;
import java.util.List;

public class EquipamentoServicoFacade implements Serializable {

    private static final long serialVersionUID = 1L;

    private EquipamentoServicoDAO equipamentoServicoDAO;
    private MovimentoDao movDao;
    private MovimentoItemDao mitDao;

    public EquipamentoServicoFacade() {
//        if (servicoDAO == null) {
//            servicoDAO = new ServicoDAO();
//        }
//        if (equipamentoDAO == null) {
//            equipamentoDAO = new EquipamentoDAO();
//        }
//        if (equipamentoServicoDAO == null) {
        equipamentoServicoDAO = new EquipamentoServicoDAO();
        movDao = new MovimentoDao();
        mitDao = new MovimentoItemDao();
//        }
    }

    public void createEquipamentoServico(EquipamentoServico eqp) {
//        equipamentoServicoDAO = new EquipamentoServicoDAO();
        equipamentoServicoDAO.begin();
        equipamentoServicoDAO.save(eqp);
        equipamentoServicoDAO.commitAndClose();
//        equipamentoServicoDAO.insert(eqp);
    }

    public void updateEquipamentoServico(EquipamentoServico eqp) {
//        equipamentoServicoDAO = new EquipamentoServicoDAO();
        equipamentoServicoDAO.begin();
        equipamentoServicoDAO.update(eqp);
        equipamentoServicoDAO.commitAndClose();
//        equipamentoServicoDAO.insert(eqp);
    }

////    public void addDogToPerson(int dogId, int personId) {
    public void addServEquip(EquipamentoServico es) {
        EquipamentoServico eqs = new EquipamentoServico();
//        
//        eqs.setEquipamento(es.getEquipamento());
////        eqs.setEquipamentosPecas(es.getEquipamentosPecas());
//        eqs.setMANUTANTERIOR(es.getMANUTANTERIOR());
//        eqs.setMANUTANTERIORHORAS(es.getMANUTANTERIORHORAS());
//        eqs.setMANUTATUAL(es.getMANUTATUAL());
//        eqs.setMANUTATUALRHORAS(es.getMANUTATUALRHORAS());
//        eqs.setREALIZADO(es.isREALIZADO());
//        eqs.setSRV_FREQUENCIADIAS(es.getSRV_FREQUENCIADIAS());
//        eqs.setSRV_FREQUENCIAHORAS(es.getSRV_FREQUENCIAHORAS());
//        eqs.setServico(es.getServico());
//        es.setEquipamentosPecas(null);
//        es.setMovimentoItem(null);
        equipamentoServicoDAO.begin();

//        servicoDAO.joinTransaction();
//        equipamentoDAO.joinTransaction();
//        Servico serv = servicoDAO.find(servId);
//        Equipamento eqp = equipamentoDAO.find(equipId);
//        es.setID_EQS(0);
//        es.setEquipamento(eqp);
//        es.setServico(serv);
//        serv.getEquipamentos().add(es);
//        eqp.getServicos().add(es);
//        equipamentoServicoDAO.insert(es);
        equipamentoServicoDAO.save(es);
//		Person person = personDAO.find(personId);
//		person.getDogs().add(dog);
//		dog.getPerson().add(person);
        equipamentoServicoDAO.commitAndClose();
    }

//	public void updateDog(EquipamentoServico dog) {
//		equipamentoServicoDAO.begin();
//		EquipamentoServico persistedDog = equipamentoServicoDAO.find(dog.getId());
//		persistedDog.setAge(dog.getAge());
//		persistedDog.setName(dog.getName());
//		equipamentoServicoDAO.update(persistedDog);
//		equipamentoServicoDAO.commitAndClose();
//	}
    public EquipamentoServico findEquipamento(int eqpId) {
        equipamentoServicoDAO.begin();
        EquipamentoServico eqp = equipamentoServicoDAO.find(eqpId);
        equipamentoServicoDAO.close();
        return eqp;
    }

    public List<EquipamentoServico> listAll() {
        equipamentoServicoDAO.begin();
        List<EquipamentoServico> result = equipamentoServicoDAO.findAll();
        equipamentoServicoDAO.close();
        return result;
    }

    public List<EquipamentoServico> listByEqpId(int eqpId) {
        equipamentoServicoDAO.begin();
        List<EquipamentoServico> result = equipamentoServicoDAO.findListByEqp(eqpId);
        equipamentoServicoDAO.close();
        return result;
    }

    public List<EquipamentoServico> listCarta() {
        equipamentoServicoDAO.begin();
        List<EquipamentoServico> result = equipamentoServicoDAO.findListCarta();
        equipamentoServicoDAO.close();
        return result;
    }

    public List<EquipamentoServico> listByEqpIdCarta(int eqpId) {
        equipamentoServicoDAO.begin();
        List<EquipamentoServico> result = equipamentoServicoDAO.findListByEqpCarta(eqpId);
        equipamentoServicoDAO.close();
        return result;
    }

    public List<EquipamentoServico> listHistByEqpId(int eqpId) {
        equipamentoServicoDAO.begin();
        List<EquipamentoServico> result = equipamentoServicoDAO.findListHistByEqp(eqpId);
        equipamentoServicoDAO.close();
        return result;
    }

    public List<EquipamentoServico> listBySrvEqp(int srvId, int eqpId) {
        equipamentoServicoDAO.begin();
        List<EquipamentoServico> result = equipamentoServicoDAO.findListByServEqp(srvId, eqpId);
        equipamentoServicoDAO.close();
        return result;
    }

    public void deleteEquipamentoServico(EquipamentoServico eqp) {
        equipamentoServicoDAO.begin();
        EquipamentoServico persistedEqp = equipamentoServicoDAO.findReferenceOnly(eqp.getID_EQS());
        equipamentoServicoDAO.delete(persistedEqp);
        equipamentoServicoDAO.commitAndClose();
    }

    public void deleteEquipamentoServicoAllHist(EquipamentoServico eqs) {
        MovimentoItem mit = mitDao.getItemByEqsID(eqs.getID_EQS());
        if (mit != null) {
            Movimento mov = movDao.getMovByTipoId(mit.getMovimento().getMOV_ID());
//            movDao.begin();
            movDao.delete2(mov);
//            movDao.commitAndClose();
        }
//        eqp.getMovimentoItem().getC
        List<EquipamentoServico> lstAll = listBySrvEqp(eqs.getServico().getSRV_ID(), eqs.getEquipamento().getEQP_ID());
        for (EquipamentoServico persistedEqp : lstAll) {

            equipamentoServicoDAO.begin();
            equipamentoServicoDAO.delete(persistedEqp);
            equipamentoServicoDAO.commitAndClose();
        }

    }
}
