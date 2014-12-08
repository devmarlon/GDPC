package com.gardnerdenver.facade;

import com.gardnerdenver.dao.FactoryCategoriaDao;
import com.gardnerdenver.model.FactoryCategoria;
import java.io.Serializable;
import java.util.List;

public class FactoryCategoriaFacade implements Serializable {

    private static final long serialVersionUID = 1L;

//    private ParceiroDAO pecaDAO;
    private FactoryCategoriaDao categoriaDao;

    public FactoryCategoriaFacade() {
//        if (pecaDAO == null) {
        categoriaDao = new FactoryCategoriaDao();
//        }

    }

    public void createPeca(FactoryCategoria categoria) {
        categoriaDao.beginTransaction();
        categoria.setCAT_ID(0);
        categoriaDao.save(categoria);
        categoriaDao.commitAndCloseTransaction();
    }

    public void updatePeca(FactoryCategoria peca) {
        categoriaDao.beginTransaction();
        categoriaDao.update(peca);
        categoriaDao.commitAndCloseTransaction();
    }
//    public void deletePerson(Servico parceiro) {
//        pecaDAO.beginTransaction();
//        Servico persistedPersonWithIdOnly = pecaDAO.findReferenceOnly(parceiro.getPAR_ID());
//        pecaDAO.delete(persistedPersonWithIdOnly);
//        pecaDAO.commitAndCloseTransaction();
//
//    }

    public void deletePeca(FactoryCategoria categoria) {
        categoriaDao.beginTransaction();
        FactoryCategoria persistedServicoWithIdOnly = categoriaDao.findReferenceOnly(categoria.getCAT_ID());
        categoriaDao.delete(persistedServicoWithIdOnly);
        categoriaDao.commitAndCloseTransaction();
    }

    public FactoryCategoria findCategoriaById(int catId) {
        categoriaDao.beginTransaction();
        FactoryCategoria cat = categoriaDao.find(catId);
        categoriaDao.closeTransaction();
        return cat;
    }

    public FactoryCategoria findPecaByCod(String catCod) {
        categoriaDao.beginTransaction();
        FactoryCategoria peca = categoriaDao.findCategoriaById(catCod);
        categoriaDao.closeTransaction();
        return peca;
    }

    public List<FactoryCategoria> listAll() {
        categoriaDao.beginTransaction();
        List<FactoryCategoria> result = categoriaDao.findAll();
        categoriaDao.closeTransaction();
        return result;
    }

    public List<FactoryCategoria> listBusca(String d) {
        categoriaDao.beginTransaction();
        List<FactoryCategoria> result = categoriaDao.findBusca(d);
        categoriaDao.closeTransaction();
        return result;
    }

//    public List<Peca> listByEqs(int eqsId) {
//        pecaDAO.beginTransaction();
//        List<Peca> result = pecaDAO.findPecaByEqs(eqsId);
//        pecaDAO.commitAndCloseTransaction();
//
//        return result;
//    }
//    public Servico findPersonWithAllDogs(int personId) {
//        pecaDAO.beginTransaction();
////        Servico person = pecaDAO.findPersonWithAllDogs(personId);
//        pecaDAO.closeTransaction();
////        return person;
//    }
//    public void addServicoToEquipamento(int servId, int equipId, EquipamentoServico es) {
//        pecaDAO.beginTransaction();
//        equipamentoDAO.joinTransaction();
//        Equipamento equipamento = equipamentoDAO.find(equipId);
//        Servico servico = pecaDAO.find(servId);
////        EquipamentoServico es = new EquipamentoServico();
//        es.setEquipamento(equipamento);
//        es.setServico(servico);
//        equipamento.getServicos().add(es);
//        servico.getEquipamentos().add(es);
//        pecaDAO.commitAndCloseTransaction();
//    }
//    public void removeEquipamentoFromPerson(int equip_id, int par_id) {
//        pecaDAO.beginTransaction();
//        equipamentoDAO.joinTransaction();
//        Equipamento equipamento = equipamentoDAO.find(equip_id);
//        Servico parceiro = pecaDAO.find(par_id);
//        parceiro.getEquipamentos().remove(equipamento);
//        equipamento.setParceiro(null);
//        pecaDAO.commitAndCloseTransaction();
//    }
}
