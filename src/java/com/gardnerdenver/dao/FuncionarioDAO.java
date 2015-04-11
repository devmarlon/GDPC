package com.gardnerdenver.dao;


import com.gardnerdenver.bean.UserItemFactoryBean;
import com.gardnerdenver.model.Funcionario;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FuncionarioDAO extends GenericDAO<Funcionario> {

    private static final long serialVersionUID = 1L;

    public FuncionarioDAO(String banco) {
        super(banco, Funcionario.class, true);
    }

    public FuncionarioDAO() {
        super(UserItemFactoryBean.banco, Funcionario.class, false);
    }

    public void delete(Funcionario funcionario) {
        super.delete(funcionario.getFUN_ID());
    }

    public Funcionario findUserByEmail(String email) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("FUN_LOGIN", email);

        return super.findOneResult(Funcionario.FIND_BY_FUNCLOGIN, parameters);
    }

    public List<Funcionario> findVends() {
        Map<String, Object> parameters = new HashMap<String, Object>();

        return super.findListResult(Funcionario.FIND_LIST_VEND, parameters);
    }

    public List<Funcionario> findTecs() {
        Map<String, Object> parameters = new HashMap<String, Object>();

        return super.findListResult(Funcionario.FIND_LIST_TEC, parameters);
    }

}
