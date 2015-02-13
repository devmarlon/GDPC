package com.gardnerdenver.dao;

import com.gardnerdenver.bean.UserItemFactoryBean;

import com.gardnerdenver.model.Servico;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

public class ServicoDAO extends GenericDAO<Servico> {

    private static final long serialVersionUID = 1L;

    public ServicoDAO() {
        super(UserItemFactoryBean.banco, Servico.class, false);
    }

    public ServicoDAO(String banco, boolean create) {
        super(banco, Servico.class, create);
    }

    public Servico findServicoByFab(int fab) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fab", fab);

        return super.findOneResult(Servico.FIND_SERVICO_BY_FAB, parameters);
    }

//    public Servico findServicoByServId(int servId) {
//        Map<String, Object> parameters = new HashMap<String, Object>();
//        parameters.put("servId", servId);
//
//        return super.findOneResult(Servico.FIND_SERVICO_BY_ID, parameters);
//    }
//
//    public void delete(Parceiro parceiro) {
//        super.delete(parceiro.getPAR_ID(), Parceiro.class);
//    }
    public void delete(Servico servico) {
        super.delete(servico.getSRV_ID());
    }

    public List<Servico> findLista() {
        return super.findListResult(Servico.FIND_ALL, null);
    }

    public Servico findById(int id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("servId", id);
        return super.findOneResult(Servico.FIND_SERVICO_BY_ID, parameters);
    }

    public List<Servico> findBusca(String descricao) {
        List<Servico> eqps = null;
        try {
//            em = emf.createEntityManager();
//            em.getTransaction().begin();
//            super.beginTransaction();

            Query query = super.em.createQuery("SELECT e FROM Servico e WHERE e.SRV_DESCRICAO like '%" + descricao + "%'");
//            System.out.println("SELECT p FROM Parceiro p WHERE " + where + sql);
//                    + "ORDER BY p." + CAMPO + " " + ORDEM);
            eqps = query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + e.getCause() + "\n" + e.getClass());
        } finally {
//            em.close();
//            super.closeEntityManager();
        }

        return eqps;
    }
}
