package com.gardnerdenver.dao;

import com.gardnerdenver.model.FactoryPeca;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

public class FactoryPecaDao extends GenericGdpcDAO<FactoryPeca> {

    private static final long serialVersionUID = 1L;

    public FactoryPecaDao() {
        super("gdpc", FactoryPeca.class);
    }

    public FactoryPeca findPecaByCode(String pecaCod) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("codigo", pecaCod);

        return super.findOneResult(FactoryPeca.FIND_PECA_BY_CODIGO, parameters);
    }
    
    public List<FactoryPeca> findListaOrdenada() {
        return super.findListResult(FactoryPeca.FIND_ALL, null);
    }

    public void delete(FactoryPeca peca) {
        super.delete(peca.getPEC_ID(), FactoryPeca.class);
    }

    public List<FactoryPeca> findBusca(String descricao) {
        List<FactoryPeca> eqps = null;
        try {

            Query query = super.em.createQuery("SELECT e FROM FactoryPeca e WHERE E.codigo LIKE '%" + descricao + "%' OR e.descricao like '%" + descricao + "%' order by f.ativo, f.descricao");
            eqps = query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + "\n" + e.getClass());
        }

        return eqps;
    }
}
