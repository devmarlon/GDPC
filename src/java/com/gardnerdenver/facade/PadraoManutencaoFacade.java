package com.gardnerdenver.facade;

import java.io.Serializable;
import java.util.List;

import com.gardnerdenver.dao.PadraoManutencaoDao;
import com.gardnerdenver.dao.PadraoManutencaoPecaDao;
import com.gardnerdenver.dao.PadraoManutencaoServicoDao;
import com.gardnerdenver.model.PadraoManutencao;
import com.gardnerdenver.model.PadraoManutencaoPeca;
import com.gardnerdenver.model.PadraoManutencaoServico;

public class PadraoManutencaoFacade implements Serializable {

    private static final long serialVersionUID = 1L;

    private PadraoManutencaoDao pdmDao = new PadraoManutencaoDao();
    private PadraoManutencaoServicoDao pdsDao = new PadraoManutencaoServicoDao();
    private PadraoManutencaoPecaDao pmpDao = new PadraoManutencaoPecaDao();

    public void create(PadraoManutencao pdm) {
        pdmDao.beginTransaction();
        pdmDao.save(pdm);
        pdmDao.commitAndCloseTransaction();
    }

    public void update(PadraoManutencao pdm) {
        pdmDao.beginTransaction();
        pdmDao.update(pdm);
        pdmDao.commitAndCloseTransaction();
    }

    public void delete(PadraoManutencao pdm) {
        pdmDao.beginTransaction();
        PadraoManutencao persistedPersonWithIdOnly = pdmDao.findReferenceOnly(pdm.getPDM_ID());
        pdmDao.delete(persistedPersonWithIdOnly);
        pdmDao.commitAndCloseTransaction();

    }

    public PadraoManutencao findPdm(int pdmId) {
        pdmDao.beginTransaction();
        PadraoManutencao person = pdmDao.find(pdmId);
        pdmDao.closeTransaction();
        return person;
    }

    public PadraoManutencao findPdmByModelo(int modeloId) {
        PadraoManutencao p = new PadraoManutencao();
        pdmDao.beginTransaction();
        List<PadraoManutencao> l = pdmDao.findPadraoByModelo(modeloId);
        if (l != null) {
            p = l.get(0);
        }
        pdmDao.closeTransaction();
        return p;
    }

    public PadraoManutencaoServico findPms(int pmsId) {
        pdsDao.beginTransaction();
        PadraoManutencaoServico padraoManutServ = pdsDao.find(pmsId);
        pdsDao.closeTransaction();
        return padraoManutServ;
    }

    public List<PadraoManutencao> listAll() {
        pdmDao.beginTransaction();
        List<PadraoManutencao> result = pdmDao.findAll();
        pdmDao.closeTransaction();
        return result;
    }

    public void removePdsFromPDM(int pdsId, int pdmId) {
        pdmDao.beginTransaction();
        pdsDao.joinTransaction();
        PadraoManutencaoServico pds = pdsDao.findReferenceOnly(pdsId);
        PadraoManutencao pdm = pdmDao.find(pdmId);
        pdm.getPdmServico().remove(pds);
        pds.setPdrManutencao(null);
//        pdsDao.update(pds);
        pdmDao.commitAndCloseTransaction();

        pdsDao.beginTransaction();
        pdsDao.update(pds);
        pdsDao.commitAndCloseTransaction();

        pdmDao.beginTransaction();
        pdmDao.update(pdm);
        pdmDao.commitAndCloseTransaction();

        pdsDao.beginTransaction();
        pdsDao.delete(pds);
        pdsDao.commitAndCloseTransaction();
    }

    public void removePmpFromPds(int pmpId, int pdsId) {
        pdsDao.beginTransaction();
        pmpDao.joinTransaction();
        PadraoManutencaoPeca pmp = pmpDao.findReferenceOnly(pmpId);
        PadraoManutencaoServico pds = pdsDao.find(pdsId);
        pds.getPdsPecas().remove(pmp);
        pmp.setPdmServico(null);
//        pdsDao.update(pds);
        pdsDao.commitAndCloseTransaction();

        pmpDao.beginTransaction();
        pmpDao.update(pmp);
        pmpDao.commitAndCloseTransaction();

        pdsDao.beginTransaction();
        pdsDao.update(pds);
        pdsDao.commitAndCloseTransaction();

        pdmDao.beginTransaction();
        pdmDao.update(pds.getPdrManutencao());
        pdmDao.commitAndCloseTransaction();

        pmpDao.beginTransaction();
        pmpDao.delete(pmp);
        pmpDao.commitAndCloseTransaction();
    }

}
