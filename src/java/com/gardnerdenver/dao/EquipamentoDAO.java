package com.gardnerdenver.dao;

import com.gardnerdenver.bean.UserItemFactoryBean;
import com.gardnerdenver.model.Equipamento;
import com.gardnerdenver.model.Parceiro;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

public class EquipamentoDAO extends GenericDAO<Equipamento> {

    private static final long serialVersionUID = 1L;

    public EquipamentoDAO() {
        super(UserItemFactoryBean.banco, Equipamento.class, false);
    }

    public EquipamentoDAO(String banco, boolean create) {
        super(banco, Equipamento.class, create);
    }

    public void delete(Equipamento equipamento) {
        super.delete(equipamento.getEQP_ID());
    }

    public Equipamento findEqpBySN(String sn) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("sn", sn);

        return super.findOneResult(Equipamento.FIND_BY_SN, parameters);
    }

    public List<Equipamento> findBusca(String descricao, Parceiro par, int catId, String modelo, String serie, String fabricante) {
        String sql = "";
        String where = "";
        String desc = "";

        if (par.getPAR_ID() == 0) {
            sql += "";
        } else {
            sql += " AND e.parceiro.PAR_ID = " + par.getPAR_ID();
        }

        if (catId == 0) {
            sql += "";
        } else {
            sql += " AND e.EQP_CATID = " + catId;
        }

        List<Equipamento> eqps = null;
        try {
//            super.beginTransaction();
            super.createEntityManager();

            Query query = super.em.createQuery("SELECT e FROM Equipamento e WHERE e.EQP_FABRICANTE LIKE '%" + fabricante + "%' AND e.EQP_SERIE LIKE '%" + serie + "%' AND e.EQP_MODELO LIKE '%" + modelo + "%' AND e.EQP_DESCRICAO LIKE '%" + descricao + "%' " + sql);
//            System.out.println("SELECT p FROM Parceiro p WHERE " + where + sql);
//                    + "ORDER BY p." + CAMPO + " " + ORDEM);
            eqps = query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + e.getCause() + "\n" + e.getClass());
        } finally {
//            super.closeEntityManager();
            super.closeEntityManager();
        }

        return eqps;
    }

}
