package com.gardnerdenver.facade;

import java.io.Serializable;
import java.util.List;

import com.gardnerdenver.dao.MovimentoDao;
import com.gardnerdenver.dao.MovimentoItemDao;
import com.gardnerdenver.model.Movimento;
import com.gardnerdenver.model.MovimentoItem;

public class MovimentoFacade implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final MovimentoDao movimentoDao = new MovimentoDao();
    private final MovimentoItemDao movimentoItemDao = new MovimentoItemDao();
    
    public void createMovimento(Movimento mov) {
//        int id = movimentoDao.nextId();
//        eqp.setMOV_ID(id + 1);
        int mitId = 1;
        for (MovimentoItem mit : mov.getListMovimentoItem()) {
            mit.setMIT_ID(mitId);
            mitId++;
        }
        movimentoDao.beginTransaction();
        movimentoDao.save(mov);
        movimentoDao.commitAndCloseTransaction();
    }
    
    public void updateMovimento(Movimento mov) {
//        int mitId = 1;
//        int mitIdOld = 0;
//        for (MovimentoItem mit : mov.getListMovimentoItem()) {
//            if (mit.getMIT_ID() == 0) {
//                mit.setMIT_ID(mitIdOld + 1);
//            }
//            mitIdOld = mit.getMIT_ID();
//            mit.getMovimento().setMOV_ID(mov.getMOV_ID());
//        }
        movimentoDao.beginTransaction();
        movimentoDao.update(mov);
        movimentoDao.commitAndCloseTransaction();
    }
    
    public Movimento findMovimento(int movId) {
        movimentoDao.beginTransaction();
        Movimento eqp = movimentoDao.find(movId);
        movimentoDao.closeTransaction();
        return eqp;
    }
    
    public List<Movimento> listAll() {
        movimentoDao.beginTransaction();
        List<Movimento> result = movimentoDao.findAll();
        movimentoDao.closeTransaction();
        return result;
    }
    
    public List<MovimentoItem> listAllMitsByMov(int movId) {
        movimentoItemDao.beginTransaction();
        List<MovimentoItem> result = movimentoItemDao.findByMovId(movId);
        movimentoItemDao.closeTransaction();
        return result;
    }
    
    public void deleteMovimento(Movimento eqp) {
        movimentoDao.beginTransaction();
        Movimento persistedEqp = movimentoDao.findReferenceOnly(eqp.getMOV_ID());
        movimentoDao.delete(persistedEqp);
        movimentoDao.commitAndCloseTransaction();
    }
    
    public void deleteMovimentoItemByMov(Movimento mov) {
        movimentoItemDao.deleteByMOV_ID(mov.getMOV_ID());
    }
}
