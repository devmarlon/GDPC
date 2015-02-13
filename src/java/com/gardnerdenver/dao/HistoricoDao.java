package com.gardnerdenver.dao;

import com.gardnerdenver.bean.UserItemFactoryBean;
import java.util.HashMap;
import java.util.Map;

import com.gardnerdenver.model.Historico;
import java.util.List;

public class HistoricoDao extends GenericDAO<Historico> {

    private static final long serialVersionUID = 1L;

    public HistoricoDao() {
        super(UserItemFactoryBean.banco, Historico.class, false);
    }

    public List<Historico> findHistoricoByParceiro(int parId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("parId", parId);

        return super.findListResult(Historico.FIND_BY_PARCEIRO, parameters);
    }
    public List<Historico> findHistoricoByEquipamento(int eqpId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("eqpId", eqpId);

        return super.findListResult(Historico.FIND_BY_EQUIPAMENTO, parameters);
    }

    public void delete(Historico his) {
        super.delete(his.getHisId());
    }
}
