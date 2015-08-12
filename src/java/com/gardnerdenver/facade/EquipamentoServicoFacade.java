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
        equipamentoServicoDAO.beginTransaction();
        equipamentoServicoDAO.save(eqp);
        equipamentoServicoDAO.commitAndCloseTransaction();
    }

    public void updateEquipamentoServico(EquipamentoServico eqp) {
//        equipamentoServicoDAO = new EquipamentoServicoDAO();
        equipamentoServicoDAO.beginTransaction();
        equipamentoServicoDAO.update(eqp);
        equipamentoServicoDAO.commitAndCloseTransaction();
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
        equipamentoServicoDAO.beginTransaction();

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
        equipamentoServicoDAO.commitAndCloseTransaction();
    }

//	public void updateDog(EquipamentoServico dog) {
//		equipamentoServicoDAO.beginTransaction();
//		EquipamentoServico persistedDog = equipamentoServicoDAO.find(dog.getId());
//		persistedDog.setAge(dog.getAge());
//		persistedDog.setName(dog.getName());
//		equipamentoServicoDAO.update(persistedDog);
//		equipamentoServicoDAO.commitAndCloseTransaction();
//	}
    public EquipamentoServico findEquipamento(int eqpId) {
        equipamentoServicoDAO.createEntityManager();
        EquipamentoServico eqp = equipamentoServicoDAO.find(eqpId);
        equipamentoServicoDAO.closeEntityManager();
        return eqp;
    }

    public EquipamentoServico findUltimoRealizadoByEqs(EquipamentoServico eqpId) {
        EquipamentoServico eqp = new EquipamentoServico();

        equipamentoServicoDAO.createEntityManager();
        List<EquipamentoServico> lst = equipamentoServicoDAO.findListRealizadoByServEqp(eqpId);
        if (lst != null && !lst.isEmpty()) {
            eqp = lst.get(0);
        }
        equipamentoServicoDAO.closeEntityManager();
        return eqp;
    }

    public List<EquipamentoServico> listAll() {
        equipamentoServicoDAO.createEntityManager();
        List<EquipamentoServico> result = equipamentoServicoDAO.findAll();
        equipamentoServicoDAO.closeEntityManager();
        return result;
    }

    public List<EquipamentoServico> listByEqpId(int eqpId) {
        equipamentoServicoDAO.createEntityManager();
        List<EquipamentoServico> result = equipamentoServicoDAO.findListByEqp(eqpId);
        equipamentoServicoDAO.closeEntityManager();
        return result;
    }

    public List<EquipamentoServico> listCarta() {
        equipamentoServicoDAO.createEntityManager();
        List<EquipamentoServico> result = equipamentoServicoDAO.findListCarta();
        equipamentoServicoDAO.closeEntityManager();
        return result;
    }

    public List<EquipamentoServico> listByEqpIdCarta(int eqpId) {
        equipamentoServicoDAO.createEntityManager();
        List<EquipamentoServico> result = equipamentoServicoDAO.findListByEqpCarta(eqpId);
        equipamentoServicoDAO.closeEntityManager();
        return result;
    }

    public List<EquipamentoServico> listHistByEqpId(int eqpId) {
        equipamentoServicoDAO.createEntityManager();
        List<EquipamentoServico> result = equipamentoServicoDAO.findListHistByEqp(eqpId);
        equipamentoServicoDAO.closeEntityManager();
        return result;
    }

    public List<EquipamentoServico> listBySrvEqp(int srvId, int eqpId) {
        equipamentoServicoDAO.createEntityManager();
        List<EquipamentoServico> result = equipamentoServicoDAO.findListByServEqp(srvId, eqpId);
        equipamentoServicoDAO.closeEntityManager();
        return result;
    }

    public void deleteEquipamentoServico(EquipamentoServico eqp) {
        equipamentoServicoDAO.beginTransaction();
        EquipamentoServico persistedEqp = equipamentoServicoDAO.findReferenceOnly(eqp.getID_EQS());
        equipamentoServicoDAO.delete(persistedEqp);
        equipamentoServicoDAO.commitAndCloseTransaction();
    }

    public void deleteEquipamentoServicoAllHist(EquipamentoServico eqs) {
        MovimentoItem mit = mitDao.getItemByEqsID(eqs.getID_EQS());
        if (mit != null) {
            Movimento mov = movDao.getMovByTipoId(mit.getMovimento().getMOV_ID());
//            movDao.beginTransaction();
            movDao.delete2(mov);
//            movDao.commitAndCloseTransaction();
        }
//        eqp.getMovimentoItem().getC
        List<EquipamentoServico> lstAll = listBySrvEqp(eqs.getServico().getSRV_ID(), eqs.getEquipamento().getEQP_ID());
        for (EquipamentoServico persistedEqp : lstAll) {

            equipamentoServicoDAO.beginTransaction();
            equipamentoServicoDAO.delete(persistedEqp);
            equipamentoServicoDAO.commitAndCloseTransaction();
        }

    }
}
