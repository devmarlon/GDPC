package com.gardnerdenver.dao;

import com.gardnerdenver.bean.UserItemFactoryBean;
import com.gardnerdenver.model.FactoryServico;
import com.gardnerdenver.model.Servico;
import java.util.List;
import javax.persistence.Query;

public class FactoryServicoDao extends GenericGdpcDAO<FactoryServico> {

    private static final long serialVersionUID = 1L;

    public FactoryServicoDao() {
        super(UserItemFactoryBean.banco, FactoryServico.class);
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
    public void delete(FactoryServico servico) {
        super.delete(servico.getSRV_ID(), FactoryServico.class);
    }

    public List<FactoryServico> findLista() {
        return super.findListResult(FactoryServico.FIND_LISTA, null);
    }

    public List<FactoryServico> findBusca(String descricao) {
        List<FactoryServico> eqps = null;
        try {
//            em = emf.createEntityManager();
//            em.getTransaction().begin();
//            super.beginTransaction();

            Query query = super.em.createQuery("SELECT e FROM FactoryServico e WHERE e.SRV_DESCRICAO like '%" + descricao + "%' order by e.ativo, e.SRV_DESCRICAO");
//            System.out.println("SELECT p FROM Parceiro p WHERE " + where + sql);
//                    + "ORDER BY p." + CAMPO + " " + ORDEM);
            eqps = query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + e.getCause() + "\n" + e.getClass());
        } finally {
//            em.close();
//            super.closeTransaction();
        }

        return eqps;
    }
}
