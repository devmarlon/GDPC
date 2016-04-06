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

    public static void main(String... args) {
        try {
            FactoryPecaDao anuDao = new FactoryPecaDao();
        } catch (Exception e) {
            System.out.println("Erro");
            e.printStackTrace();
        }
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
        super.delete(peca.getPEC_ID());
    }

    public List<FactoryPeca> findBusca(String descricao) {
        List<FactoryPeca> eqps = null;
        try {

            Query query = super.em.createQuery("SELECT e FROM FactoryPeca e WHERE e.codigo LIKE '%" + descricao + "%' OR e.descricao like '%" + descricao + "%' order by e.ativo, e.descricao");
            eqps = query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + "\n" + e.getClass());
        }

        return eqps;
    }
}
