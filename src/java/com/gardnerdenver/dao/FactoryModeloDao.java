package com.gardnerdenver.dao;

import com.gardnerdenver.model.FactoryModelo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

public class FactoryModeloDao extends GenericGdpcDAO<FactoryModelo> {

    private static final long serialVersionUID = 1L;

    public FactoryModeloDao() {
        super("gdpc", FactoryModelo.class);
    }

    public FactoryModelo findPecaByCode(String pecaCod) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("codigo", pecaCod);

        return super.findOneResult(FactoryModelo.FIND_CATEGORIA_BY_CODIGO, parameters);
    }

    public List<FactoryModelo> findAllAtivos() {
        return super.findListResult(FactoryModelo.FIND_LISTA_ATIVOS, null);
    }

    public void delete(FactoryModelo peca) {
        super.delete(peca.getMOD_ID());
    }

    public List<FactoryModelo> findBusca(String descricao) {
        List<FactoryModelo> eqps = null;
        try {

            Query query = super.em.createQuery("SELECT e FROM FactoryModelo e WHERE E.codigo LIKE '%" + descricao + "%' OR e.descricao like '%" + descricao + "%'");
            eqps = query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + "\n" + e.getClass());
        }

        return eqps;
    }
}
