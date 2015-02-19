package com.gardnerdenver.facade;

import com.gardnerdenver.dao.FuncionarioDAO;
import com.gardnerdenver.dao.UserItemFactoryDAO;
import com.gardnerdenver.model.FactoryUserItem;
import com.gardnerdenver.model.Funcionario;
import java.util.List;

public class UserItemFactoryFacade {

    private final UserItemFactoryDAO userDAO = new UserItemFactoryDAO();

    public void createUserItemFactory(FactoryUserItem user) {
        userDAO.beginTransaction();
        userDAO.save(user);
        userDAO.commitAndCloseTransaction();
    }

    public void updateUserItemFactory(FactoryUserItem user) {
        userDAO.beginTransaction();
        //userItem
        FuncionarioDAO funcDao = new FuncionarioDAO();
        FuncionarioFacade funcFacade = new FuncionarioFacade();
        FactoryUserItem userPersisted = userDAO.find(user.getUSI_ID());
        Funcionario persistedFuncionario = funcFacade.isValidLogin(userPersisted.getUSI_LOGIN(), userPersisted.getUSI_SENHA());

        userPersisted.setUSI_LOGIN(user.getUSI_LOGIN());
        userPersisted.setUSI_SENHA(user.getUSI_SENHA());
        user.setUserFactory(user.getUserFactory());
        userDAO.update(userPersisted);
        persistedFuncionario.setFUN_SENHA(user.getUSI_SENHA());
        funcFacade.updateFuncionario(persistedFuncionario);
        //Funcionario
        userDAO.commitAndCloseTransaction();
    }

    public FactoryUserItem findUsi(int usiId) {
        userDAO.beginTransaction();
        FactoryUserItem usi = userDAO.find(usiId);
        userDAO.closeTransaction();
        return usi;
    }

    public void deleteUserItemFactory(FactoryUserItem user) {
        userDAO.beginTransaction();
        FactoryUserItem persisted = userDAO.findReferenceOnly(user.getUSI_ID());
        userDAO.delete(persisted);
        userDAO.commitAndCloseTransaction();
    }

    public FactoryUserItem findByLogin(String login) {
        userDAO.beginTransaction();
        FactoryUserItem user = userDAO.findUserByEmail(login);
        userDAO.closeTransaction();

        return user;
    }

    public FactoryUserItem isValidLogin(String email, String password) {
        userDAO.beginTransaction();
        FactoryUserItem user = (FactoryUserItem) userDAO.findUserByEmail(email);
        userDAO.closeTransaction();

        if (user == null || !user.getUSI_SENHA().equals(password)) {
            return null;
        }

        return user;
    }
 public List<FactoryUserItem> listFabrica() {
        userDAO.beginTransaction();
        List<FactoryUserItem> result = userDAO.findListFabrica();
        userDAO.closeTransaction();
        return result;
    }
}
