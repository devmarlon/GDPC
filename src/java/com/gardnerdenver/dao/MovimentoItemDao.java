package com.gardnerdenver.dao;

import com.gardnerdenver.bean.UserItemFactoryBean;
import static com.gardnerdenver.dao.GenericDAO.emf;
import com.gardnerdenver.model.MovimentoItem;
import com.gardnerdenver.util.Util;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

public class MovimentoItemDao<T> extends GenericDAO<MovimentoItem> implements Serializable {

    public MovimentoItemDao() {
        super(UserItemFactoryBean.banco, MovimentoItem.class, false);
    }

    public void delete(MovimentoItem mit) {
        super.delete(mit, MovimentoItem.class);
    }

    public List<MovimentoItem> findByMovId(int movId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("movId", movId);

        return super.findListResult(MovimentoItem.FIND_BY_MOV, parameters);
    }

    public MovimentoItem getItemByEqsID(int EQS) {
        List< MovimentoItem> lista = null;
        MovimentoItem item = new MovimentoItem();
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT m FROM MovimentoItem m WHERE m.equipamentoServico.ID_EQS = :EQS");
            query.setParameter("EQS", EQS);
            lista = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        if ((lista == null) || (lista.isEmpty())) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    public List< MovimentoItem> getLista(String mov_id) {
        List< MovimentoItem> lista = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT m FROM MovimentoItem m WHERE m.MOV_ID = " + mov_id);
            lista = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return lista;
    }

    public List< MovimentoItem> getLista() {
        List< MovimentoItem> lista = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT m FROM MovimentoItem m ");
            lista = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return lista;
    }

    public List< MovimentoItem> getListaToOF(String mov, String mit) {
        List< MovimentoItem> lista = null;
        String where = "";
        if (Util.isInt(mov)) {
            where = " AND m.movimentoItemPK.MOV_ID LIKE '" + mov + "' ";
        }
        if (Util.isInt(mit)) {
            where += " AND m.movimentoItemPK.MIT_ID LIKE '" + mit + "' ";
        }
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT m FROM MovimentoItem m WHERE m.movimentoItemPK.MOV_TIPO = 'PED' " + where + " ORDER BY m.movimentoItemPK.MOV_ID ASC, m.movimentoItemPK.MIT_ID ASC ");
            lista = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return lista;
    }

    public MovimentoItem getItemByID(int MIT_ID) {
        List< MovimentoItem> lista = null;
        MovimentoItem item = new MovimentoItem();
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT m FROM MovimentoItem m WHERE m.movimentoItemPK.MIT_ID = :MIT_ID");
            query.setParameter("MIT_ID", MIT_ID);
            lista = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        if ((lista == null) || (lista.isEmpty())) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    public MovimentoItem getItemByPRO_ID(int PRO_ID) {
        List< MovimentoItem> lista = null;
        MovimentoItem item = new MovimentoItem();
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT m FROM MovimentoItem m WHERE m.produto.PRO_ID = " + PRO_ID);
            lista = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        if ((lista == null) || (lista.isEmpty())) {
            return null;
        } else {
            return lista.get(0);
        }
    }

//    public List< MovimentoItem> getListaByMov(Movimento mov) {
//        List< MovimentoItem> lista = null;
//        try {
//            em = emf.createEntityManager();
//            em.getTransaction().begin();
//            Query query = em.createQuery("SELECT m FROM MovimentoItem m WHERE m.movimento.MOV_TIPO = '" + mov.getMOV_TIPO() + "' AND m.movimento.MOV_ID = "
//                    + mov.getMOV_ID() + " ORDER BY m.movimentoItemPK.MIT_ID");
//
//            lista = query.getResultList();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            em.close();
//        }
//        if (lista != null && !lista.isEmpty()) {
//            return lista;
//        } else {
//            return null;
//        }
//    }
    public List< MovimentoItem> getListaByMovIDTipo(int id, String tipo) {
        List< MovimentoItem> lista = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT m FROM MovimentoItem m WHERE m.movimento.MOV_TIPO = :tipo AND m.movimento.MOV_ID = :id ORDER BY m.movimentoItemPK.MIT_ID");
            query.setParameter("id", id);
            query.setParameter("tipo", tipo);
            lista = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        if (lista != null && !lista.isEmpty()) {
            return lista;
        } else {
            return null;
        }
    }

    public boolean deleteByMOV_ID(int MOV) {
        boolean certo = true;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM MovimentoItem mi WHERE  mi.movimento.MOV_ID = :movId");
            query.setParameter("movId", MOV);
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            certo = false;
            System.out.println("Erro:\n" + e.getMessage() + "\n" + e.getCause() + "\n" + e.getClass());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return certo;
    }

    public String getLastID(String TIPO, int ID) {

        if ("".equals(TIPO)) {
            return "";
        }

        Object mov = 0;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT MAX(m.MIT_ID) FROM MovimentoItem m WHERE m.MOV_TIPO = '" + TIPO + "' AND m.MOV_ID = " + ID);
            mov = query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + e.getCause() + "\n" + e.getClass());
        } finally {
            em.close();
        }
        if (mov != null) {
            return mov.toString();
        } else {
            return "0";
        }

    }
}
