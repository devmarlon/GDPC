package com.gardnerdenver.dao;

import java.util.HashMap;
import java.util.Map;

import com.gardnerdenver.model.FactoryUserItem;
import com.gardnerdenver.model.Role;
import java.util.List;

public class UserItemFactoryDAO extends GenericGdpcDAO<FactoryUserItem> {

    private static final long serialVersionUID = 1L;

    public UserItemFactoryDAO() {
        super("gdpc", FactoryUserItem.class);
    }

    public void delete(FactoryUserItem f) {
        super.delete(f.getUSI_ID(), FactoryUserItem.class);
    }

    public FactoryUserItem findUserByEmail(String email) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("USI_LOGIN", email);

        return super.findOneResult(FactoryUserItem.FIND_BY_EMAIL, parameters);
    }
    
     public List<FactoryUserItem> findListFabrica() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("rol", Role.FAB);

        return super.findListResult(FactoryUserItem.FIND_LIST_FABRICA, parameters);
    }
}
