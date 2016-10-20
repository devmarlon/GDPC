package com.gardnerdenver.dao;

import com.gardnerdenver.bean.UserItemFactoryBean;
import com.gardnerdenver.model.EquipamentoMedicao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EquipamentoMedicaoDAO extends GenericDAO<EquipamentoMedicao> {

    private static final long serialVersionUID = 1L;

    public EquipamentoMedicaoDAO() {
        super(UserItemFactoryBean.banco, EquipamentoMedicao.class, false);
    }

    public void delete(EquipamentoMedicao equipamentoMedicao) {
        super.delete(equipamentoMedicao.getEQM_ID());
    }

    public EquipamentoMedicao findLastUpdate(int eqpId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("eqpId", eqpId);

        return super.findOneResult(EquipamentoMedicao.FIND_LAST_UPDATE, parameters);
    }

    public List<EquipamentoMedicao> findListByEqp(int eqpId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("eqpId", eqpId);

        return super.findListResult(EquipamentoMedicao.FIND_LIST_BY_EQP, parameters);
    }

}
