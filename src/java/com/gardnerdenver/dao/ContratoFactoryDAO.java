package com.gardnerdenver.dao;

import com.gardnerdenver.model.Role;
import com.gardnerdenver.model.FactoryContrato;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContratoFactoryDAO extends GenericGdpcDAO<FactoryContrato> {

    private static final long serialVersionUID = 1L;

    public ContratoFactoryDAO() {
        super("gdpc", FactoryContrato.class);
    }

    public void delete(FactoryContrato userFactory) {
        super.delete(userFactory);
    }

    public List<FactoryContrato> findListDist() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ctoStatus", true);

        return super.findListResult(FactoryContrato.FIND_LIST_STATUS_ATIVO, parameters);
    }

}
