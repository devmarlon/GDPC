package com.gardnerdenver.dao;

import com.gardnerdenver.bean.UserItemFactoryBean;
import com.gardnerdenver.model.EquipamentoServico;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

public class EquipamentoServicoDAO extends GenericDAO<EquipamentoServico> {

    private static final long serialVersionUID = 1L;

    public EquipamentoServicoDAO() {
        super(UserItemFactoryBean.banco, EquipamentoServico.class, false);
    }

    public List<EquipamentoServico> findListByEqp(int eqpId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("eqpId", eqpId);

        return super.findListResult(EquipamentoServico.FIND_EQS_BY_EQP, parameters);
    }

    public List<EquipamentoServico> findListCarta() {
        Map<String, Object> parameters = new HashMap<>();

        return super.findListResult(EquipamentoServico.FIND_EQS_CARTA, parameters);
    }

    public List<EquipamentoServico> findListByEqp2(int eqpId) {
        super.beginTransaction();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("eqpId", eqpId);
        List<EquipamentoServico> list = super.findListResult(EquipamentoServico.FIND_EQS_BY_EQP, parameters);
        super.closeEntityManager();
//        return super.findListResult(EquipamentoServico.FIND_EQS_BY_EQP, parameters);
        return list;
    }

    public List<EquipamentoServico> findListByEqp3(int eqpId) {
        List<EquipamentoServico> list;
        super.beginTransaction();
        Query query = super.em.createNamedQuery(EquipamentoServico.FIND_EQS_BY_EQP);
        query.setParameter("eqpId", eqpId);
        list = query.getResultList();
        super.closeEntityManager();

        return list;
    }

    public List<EquipamentoServico> findListByEqpCarta(int eqpId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("eqpId", eqpId);

        return super.findListResult(EquipamentoServico.FIND_EQS_BY_EQP_CARTA, parameters);
    }

    public List<EquipamentoServico> findListHistByEqp(int eqpId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("eqpId", eqpId);

        return super.findListResult(EquipamentoServico.FIND_EQS_BY_EQP_HIST, parameters);
    }

    public List<EquipamentoServico> findListByServEqp(int srvId, int eqpId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("srvId", srvId);
        parameters.put("eqpId", eqpId);

        return super.findListResult(EquipamentoServico.FIND_EQS_BY_SERV_EQP, parameters);
    }

    public List<EquipamentoServico> findListRealizadoByServEqp(EquipamentoServico es) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("srvId", es.getServico().getSRV_ID());
        parameters.put("eqpId", es.getEquipamento().getEQP_ID());

        return super.findListResult(EquipamentoServico.FIND_EQS_ULT_BY_SERV_EQP, parameters);
    }

    public void delete(EquipamentoServico es) {
        super.delete(es.getID_EQS());
    }

}
