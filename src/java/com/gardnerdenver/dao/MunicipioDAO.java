package com.gardnerdenver.dao;

import com.gardnerdenver.model.Municipio;
import com.gardnerdenver.util.Util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MunicipioDAO extends GenericGdpcDAO<Municipio> {

    private static final long serialVersionUID = 1L;

    public MunicipioDAO() {
        super(Util.getFactoryDB(), Municipio.class);
    }

    public List<Municipio> findListMunicipioByUF(int ufId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ufId", ufId);

        return super.findListResult(Municipio.FIND_LIST_MUNICIPIO_BY_UF, parameters);
    }

}
