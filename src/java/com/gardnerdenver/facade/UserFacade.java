package com.gardnerdenver.facade;

import com.gardnerdenver.dao.UserDAO;
import com.gardnerdenver.model.User;

public class UserFacade {

    private final UserDAO userDAO = new UserDAO();

    public User isValidLogin(String email, String password) {
        userDAO.beginTransaction();
        User user = userDAO.findUserByEmail(email);

        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }

        return user;
    }
}
