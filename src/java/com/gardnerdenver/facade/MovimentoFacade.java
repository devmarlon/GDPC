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
        movimentoDao.begin();
        movimentoDao.save(mov);
        movimentoDao.commitAndClose();
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
        movimentoDao.begin();
        movimentoDao.update(mov);
        movimentoDao.commitAndClose();
    }
    
    public Movimento findMovimento(int movId) {
        movimentoDao.begin();
        Movimento eqp = movimentoDao.find(movId);
        movimentoDao.close();
        return eqp;
    }
    
    public List<Movimento> listAll() {
        movimentoDao.begin();
        List<Movimento> result = movimentoDao.findAll();
        movimentoDao.close();
        return result;
    }
    
    public List<MovimentoItem> listAllMitsByMov(int movId) {
        movimentoItemDao.begin();
        List<MovimentoItem> result = movimentoItemDao.findByMovId(movId);
        movimentoItemDao.close();
        return result;
    }
    
    public void deleteMovimento(Movimento eqp) {
        movimentoDao.begin();
        Movimento persistedEqp = movimentoDao.findReferenceOnly(eqp.getMOV_ID());
        movimentoDao.delete(persistedEqp);
        movimentoDao.commitAndClose();
    }
    
    public void deleteMovimentoItemByMov(Movimento mov) {
        movimentoItemDao.deleteByMOV_ID(mov.getMOV_ID());
    }
}
