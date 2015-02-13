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
        funcDao.createEntityManager();
        Funcionario func = funcDao.findUserByEmail(email);
        funcDao.closeEntityManager();
        if (func == null || !func.getFUN_SENHA().equals(password)) {
            return null;
        }

        return func;
    }

    public void createFuncionario(Funcionario funcionario) {
        funcDao.beginTransaction();
        funcDao.save(funcionario);
        funcDao.commitAndCloseTransaction();
    }

    public void updateFuncionario(Funcionario funionario) {
        funcDao.beginTransaction();
//        Funcionario persistedDog = funcDao.find(dog.getId());
//        persistedDog.setAge(dog.getAge());
//        persistedDog.setName(dog.getName());
        funcDao.update(funionario);
        funcDao.commitAndCloseTransaction();
    }

    public Funcionario findFuncionario(int funId) {
        funcDao.beginTransaction();
        Funcionario func = funcDao.find(funId);
        funcDao.closeTransaction();
        return func;
    }

    public List<Funcionario> listAll() {
        funcDao.beginTransaction();
        List<Funcionario> result = funcDao.findAll();
        funcDao.closeTransaction();
        return result;
    }

    public List<Funcionario> listVends() {
        funcDao.beginTransaction();
        List<Funcionario> result = funcDao.findVends();
        funcDao.closeTransaction();
        return result;
    }

    public List<Funcionario> listTecs() {
        funcDao.beginTransaction();
        List<Funcionario> result = funcDao.findTecs();
        funcDao.closeTransaction();
        return result;
    }

    public void deleteFuncionario(Funcionario funcionario) {
        funcDao.beginTransaction();
        Funcionario persistedFunc = funcDao.findReferenceOnly(funcionario.getFUN_ID());
        funcDao.delete(persistedFunc);
        funcDao.commitAndCloseTransaction();
    }
}
