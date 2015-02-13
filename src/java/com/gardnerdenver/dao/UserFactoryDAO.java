package com.gardnerdenver.dao;

import com.gardnerdenver.model.Role;
import com.gardnerdenver.model.FactoryUser;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserFactoryDAO extends GenericGdpcDAO<FactoryUser> {

    private static final long serialVersionUID = 1L;

    public UserFactoryDAO() {
        super("gdpc", FactoryUser.class);
    }

    public void delete(FactoryUser userFactory) {
        super.delete(userFactory);
    }

    public List<FactoryUser> findListDist() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("rol", Role.DIS);

        return super.findListResult(FactoryUser.FIND_LIST, parameters);
    }

}
