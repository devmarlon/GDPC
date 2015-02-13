package com.gardnerdenver.dao;

import com.gardnerdenver.bean.UserItemFactoryBean;
import com.gardnerdenver.model.PecaEqs;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

public class PecaEqsDao extends GenericDAO<PecaEqs> {

    private static final long serialVersionUID = 1L;

    public PecaEqsDao() {
        super(UserItemFactoryBean.banco, PecaEqs.class, false);
    }

    public List<PecaEqs> findListByEqs(int eqsId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("eqsId", eqsId);

        return super.findListResult(PecaEqs.FIND_PQS_BY_EQS, parameters);
    }

    public void delete(PecaEqs peca) {
        super.delete(peca.getPES_ID());
    }

    public boolean deleteByID(int ID) {
        boolean result = true;
        try {
            beginTransaction();
            Query query = em.createQuery("DELETE FROM PecaEqs p WHERE p.PES_ID = :pesId");
            query.setParameter("pesId", ID);
            query.executeUpdate();
            commitAndCloseTransaction();
        } catch (Exception e) {
            result = false;
            System.out.println("Erro:\n" + e.getMessage() + "\n" + e.getCause() + "\n" + e.getClass());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return result;
    }
}
