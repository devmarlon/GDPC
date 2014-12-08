package com.gardnerdenver.facade;

import java.io.Serializable;
import java.util.List;

import com.gardnerdenver.dao.FuncionarioDAO;
import com.gardnerdenver.model.Funcionario;

public class FuncionarioFacade implements Serializable {

    private static final long serialVersionUID = 1L;

    private final FuncionarioDAO funcDao;

    public FuncionarioFacade(String banco) {
        funcDao = new FuncionarioDAO(banco);
    }

    public FuncionarioFacade() {
        funcDao = new FuncionarioDAO();
    }

    public Funcionario isValidLogin(String email, String password) {
        funcDao.begin();
        Funcionario func = funcDao.findUserByEmail(email);

        if (func == null || !func.getFUN_SENHA().equals(password)) {
            return null;
        }

        return func;
    }

    public void createFuncionario(Funcionario funcionario) {
        funcDao.begin();
        funcDao.save(funcionario);
        funcDao.commitAndClose();
    }

    public void updateFuncionario(Funcionario funionario) {
        funcDao.begin();
//        Funcionario persistedDog = funcDao.find(dog.getId());
//        persistedDog.setAge(dog.getAge());
//        persistedDog.setName(dog.getName());
        funcDao.update(funionario);
        funcDao.commitAndClose();
    }

    public Funcionario findFuncionario(int funId) {
        funcDao.begin();
        Funcionario func = funcDao.find(funId);
        funcDao.close();
        return func;
    }

    public List<Funcionario> listAll() {
        funcDao.begin();
        List<Funcionario> result = funcDao.findAll();
        funcDao.close();
        return result;
    }

    public List<Funcionario> listVends() {
        funcDao.begin();
        List<Funcionario> result = funcDao.findVends();
        funcDao.close();
        return result;
    }

    public List<Funcionario> listTecs() {
        funcDao.begin();
        List<Funcionario> result = funcDao.findTecs();
        funcDao.close();
        return result;
    }

    public void deleteFuncionario(Funcionario funcionario) {
        funcDao.begin();
        Funcionario persistedFunc = funcDao.findReferenceOnly(funcionario.getFUN_ID());
        funcDao.delete(persistedFunc);
        funcDao.commitAndClose();
    }
}
