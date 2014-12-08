package com.gardnerdenver.dao;

import com.gardnerdenver.bean.UserItemFactoryBean;
import com.gardnerdenver.model.Configuracao;
import java.util.HashMap;
import java.util.Map;

public class ConfiguracaoDAO extends GenericDAO<Configuracao> {

    private static final long serialVersionUID = 1L;

    public ConfiguracaoDAO() {
        super(UserItemFactoryBean.banco, Configuracao.class, false);
    }

    public ConfiguracaoDAO(String banco) {
        super(banco, Configuracao.class, true);
    }

    public Configuracao findUserByEmail(int cfg_id) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("cfg_id", cfg_id);

        return super.findOneResult(Configuracao.FIND_CONFIG, parameters);
    }

}
