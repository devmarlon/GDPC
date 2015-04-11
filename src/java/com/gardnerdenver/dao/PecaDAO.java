package com.gardnerdenver.dao;


import com.gardnerdenver.bean.UserItemFactoryBean;
import com.gardnerdenver.model.Peca;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

public class PecaDAO extends GenericDAO<Peca> {

    private static final long serialVersionUID = 1L;

    public PecaDAO() {
        super(UserItemFactoryBean.banco, Peca.class, false);
    }

    public PecaDAO(String banco) {
        super(banco, Peca.class, false);
    }

    public PecaDAO(String banco, boolean create) {
        super(banco, Peca.class, create);
    }

    public Peca findPecaByCode(String pecaCod) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("codigo", pecaCod);

        return super.findOneResult(Peca.FIND_PECA_BY_CODIGO, parameters);
    }

    public Peca findPecaByFab(int fab) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("fab", fab);

        return super.findOneResult(Peca.FIND_PECA_BY_FAB, parameters);
    }

    public void delete(Peca peca) {
        super.delete(peca.getPEC_ID());
    }
    
    public List<Peca> findLista() {
        return super.findListResult(Peca.FIND_ALL, null);
    }

    public List<Peca> findBusca(String descricao) {
        List<Peca> eqps = null;
        try {

            Query query = super.em.createQuery("SELECT e FROM Peca e WHERE E.codigo LIKE '%" + descricao + "%' OR e.descricao like '%" + descricao + "%'");
            eqps = query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + "\n" + e.getClass());
        }

        return eqps;
    }
}
