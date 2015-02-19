package com.gardnerdenver.dao;

import java.util.HashMap;
import java.util.Map;

import com.gardnerdenver.model.FactoryUserItem;
import com.gardnerdenver.model.Role;
import java.util.List;
import javax.persistence.Query;

public class UserItemFactoryDAO extends GenericGdpcDAO<FactoryUserItem> {

    private static final long serialVersionUID = 1L;

    public UserItemFactoryDAO() {
        super("gdpc", FactoryUserItem.class);
    }

    public void delete(FactoryUserItem f) {
        super.delete(f.getUSI_ID());
    }

    public FactoryUserItem findUserByEmail(String email) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("USI_LOGIN", email);

        return (FactoryUserItem) super.findOneResult(FactoryUserItem.FIND_BY_EMAIL, parameters);
    }

    public FactoryUserItem findUserByLogin(String login) {
        List<FactoryUserItem> Funcionarios = null;

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("USI_LOGIN", login);

//        Funcionario fun = null;
        try {
            em = super.emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createNamedQuery(FactoryUserItem.FIND_BY_EMAIL);
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
            Funcionarios = query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro:\n" + e.getMessage() + "\n" + e.getCause() + "\n" + e.getClass());
        } finally {
            em.close();
        }

        if ((Funcionarios == null) || (Funcionarios.isEmpty())) {
            return null;
        }
        return Funcionarios.get(0);
    }

    public List<FactoryUserItem> findListFabrica() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("rol", Role.FAB);

        return super.findListResult(FactoryUserItem.FIND_LIST_FABRICA, parameters);
    }
}
