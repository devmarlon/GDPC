package com.gardnerdenver.dao;

import com.gardnerdenver.model.FactoryCategoria;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

public class FactoryCategoriaDao extends GenericGdpcDAO<FactoryCategoria> {

    private static final long serialVersionUID = 1L;

    public FactoryCategoriaDao() {
        super("gdpc", FactoryCategoria.class);
    }

    public FactoryCategoria findCategoriaById(String catId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("catId", catId);

        return super.findOneResult(FactoryCategoria.FIND_CATEGORIA_BY_CODIGO, parameters);
    }

    public void delete(FactoryCategoria cat) {
        super.delete(cat.getCAT_ID(), FactoryCategoria.class);
    }

    public List<FactoryCategoria> findBusca(String descricao) {
        List<FactoryCategoria> eqps = null;
        try {

            Query query = super.em.createQuery("SELECT e FROM FactoryCategoria e WHERE e.CAT_NOME LIKE '%" + descricao + "%'");
            eqps = query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + "\n" + e.getClass());
        }

        return eqps;
    }
}
