package com.gardnerdenver.dao;


import com.gardnerdenver.bean.UserItemFactoryBean;
import com.gardnerdenver.model.Modelo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

public class ModeloDao extends GenericDAO<Modelo> {

    private static final long serialVersionUID = 1L;

    public ModeloDao() {
        super(UserItemFactoryBean.banco, Modelo.class, false);
    }

    public ModeloDao(String banco) {
        super(banco, Modelo.class, false);
    }

    public ModeloDao(String banco, boolean create) {
        super(banco, Modelo.class, create);
    }

//    public Modelo findPecaByCode(String pecaCod) {
//        Map<String, Object> parameters = new HashMap<String, Object>();
//        parameters.put("codigo", pecaCod);
//
//        return super.findOneResult(Modelo.FIND_PECA_BY_CODIGO, parameters);
//    }

    public Modelo findModeloByFab(int fab) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("fab", fab);

        return super.findOneResult(Modelo.FIND_MODELO_BY_FAB, parameters);
    }

    public void delete(Modelo peca) {
        super.delete(peca.getMOD_ID());
    }

    public List<Modelo> findBusca(String descricao) {
        List<Modelo> eqps = null;
        try {

            Query query = super.em.createQuery("SELECT e FROM Modelo e WHERE E.codigo LIKE '%" + descricao + "%' OR e.descricao like '%" + descricao + "%'");
            eqps = query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + "\n" + e.getClass());
        }

        return eqps;
    }
}
