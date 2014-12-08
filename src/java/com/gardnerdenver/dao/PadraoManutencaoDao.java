package com.gardnerdenver.dao;

import java.util.HashMap;
import java.util.Map;

import com.gardnerdenver.model.PadraoManutencao;
import java.util.List;

public class PadraoManutencaoDao extends GenericGdpcDAO<PadraoManutencao> {

    private static final long serialVersionUID = 1L;

    public PadraoManutencaoDao() {
        super(null, PadraoManutencao.class);
    }

    public List<PadraoManutencao> findPadraoByModelo(int modeloId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("modeloId", modeloId);
        
        return super.findListResult(PadraoManutencao.FIND_PADRAO_BY_MODELO, parameters);
    }
    public void delete(PadraoManutencao pdm) {
        super.delete(pdm.getPDM_ID(), PadraoManutencao.class);
    }
}
