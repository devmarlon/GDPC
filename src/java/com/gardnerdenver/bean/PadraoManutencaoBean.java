/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gardnerdenver.bean;

import com.gardnerdenver.facade.FactoryPecaFacade;
import com.gardnerdenver.facade.FactoryServicoFacade;
import com.gardnerdenver.facade.PadraoManutencaoFacade;
import com.gardnerdenver.model.FactoryPeca;
import com.gardnerdenver.model.FactoryServico;
import com.gardnerdenver.model.PadraoManutencao;
import com.gardnerdenver.model.PadraoManutencaoPeca;
import com.gardnerdenver.model.PadraoManutencaoServico;
import com.sun.faces.context.flash.ELFlash;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Marlon
 */
@ViewScoped
@ManagedBean
public class PadraoManutencaoBean extends AbstractMB implements Serializable {

    private static final String SELECTED_PDM = "selectedPdm";

    private PadraoManutencao padraoManutencaoCompare;
    private PadraoManutencao padraoManutencao;
    private PadraoManutencaoServico padraoManutencaoServico;
    private PadraoManutencaoPeca padraoManutencaoPeca;
    private PadraoManutencaoServico padraoManutencaoServicoDelete;
    private PadraoManutencaoPeca padraoManutencaoPecaDelete;

    private List<PadraoManutencao> listaPdm;
    private List<PadraoManutencao> listaPdmSelected;
    private List<PadraoManutencaoServico> listaPdsSelected;
    private List<FactoryServico> allFactoryServicos;
    private List<FactoryServico> selectedFactoryServicos;
    private List<FactoryPeca> allFactoryPecas;
    private List<FactoryPeca> selectedFactoryPecas;

    private int searchCode;

    //facade
    private PadraoManutencaoFacade pdmFacade;

    public String show() {

        return "/pages/protected/factory/padraoManutencao.xhtml";
    }

    public String novo() {
        setPadraoManutencao(new PadraoManutencao());
        ELFlash.getFlash().put(SELECTED_PDM, padraoManutencao);

        return "/pages/protected/factory/padraoManutencaoCadastro.xhtml";
    }

    public String alterar() {
        ELFlash.getFlash().put(SELECTED_PDM, padraoManutencao);

        return "/pages/protected/factory/padraoManutencaoCadastro.xhtml";
    }

    public void salvar() {
        if (padraoManutencao.getPDM_ID() == 0) {
            try {
                getPdmFacade().create(padraoManutencao);
                closeDialog();
                displayInfoMessageToUser("Created With Sucess");
                redirect(show());
            } catch (Exception e) {
                displayErrorMessageToUser("Ops, we could not create. Try again later");
                e.printStackTrace();
            }
        } else {
            try {
                getPdmFacade().update(padraoManutencao);
                closeDialog();
                displayInfoMessageToUser("Updated With Sucess");
                redirect(show());
            } catch (Exception e) {
                displayErrorMessageToUser("Ops, we could not create. Try again later");
                e.printStackTrace();
            }
        }
    }

    public void validaDelete() {
        if (listaPdmSelected.isEmpty()) {
            displayInfoMessageToUser("Selecione ao menos um Padrão");
        } else {
            RequestContext.getCurrentInstance().execute("padDeleteDialogWidget.show()");
        }
    }

    public void validaServicoSelecionado() {
        if (getPadraoManutencaoServico().getFactoryServico().getSRV_ID() == 0) {
            displayInfoMessageToUser("Selecione um serviço na tabela acima para adicionar peças a ele.");
        } else {
            RequestContext.getCurrentInstance().execute("selectPecaDialogWidget.show()");
        }
    }

    public void removePDS() {
        if (padraoManutencaoServicoDelete != null) {
            padraoManutencao.getPdmServico().remove(padraoManutencaoServicoDelete);
            if (padraoManutencaoServicoDelete.getPDS_ID() != 0) {
                try {
                    getPdmFacade().removePdsFromPDM(padraoManutencaoServicoDelete.getPDS_ID(), padraoManutencao.getPDM_ID());
                    closeDialog();
                    displayInfoMessageToUser("Removed With Sucess");

                } catch (Exception e) {
                    keepDialogOpen();
                    displayErrorMessageToUser("Ops, we could not create. Try again later");
                    e.printStackTrace();
                }
            }
//            padraoManutencao.getPdmServico().remove(padraoManutencaoServicoDelete);
        }
//        padraoManutencao = getPdmFacade().findPdm(padraoManutencao.getPDM_ID());
        padraoManutencaoServicoDelete = null;
    }

    public void removePMP() {
        if (padraoManutencaoPecaDelete != null) {
            padraoManutencaoServico.getPdsPecas().remove(padraoManutencaoPecaDelete);
            if (padraoManutencaoPecaDelete.getPMP_ID() != 0) {
                try {
                    getPdmFacade().removePmpFromPds(padraoManutencaoPecaDelete.getPMP_ID(), padraoManutencaoServico.getPDS_ID());
                    closeDialog();
                    displayInfoMessageToUser("Removed With Sucess");

                } catch (Exception e) {
                    keepDialogOpen();
                    displayErrorMessageToUser("Ops, we could not create. Try again later");
                    e.printStackTrace();
                }
            }
        }
        padraoManutencao = getPdmFacade().findPdm(padraoManutencao.getPDM_ID());
        padraoManutencaoPecaDelete = null;
    }

    public void delete() {
        for (PadraoManutencao pdm : listaPdmSelected) {

            try {
                getPdmFacade().delete(pdm);
                closeDialog();
                displayInfoMessageToUser("Padrão de Manutenção removido com sucesso.");

//                loadDogs();
//                resetDog();
            } catch (Exception e) {
                displayErrorMessageToUser("Padrão de Manutenção do modelo " + pdm.getFactoryModelo().getMOD_NOME() + " não pode ser removido pois está em uso");
                keepDialogOpen();
                e.printStackTrace();
            }
            loadListaPdm();
        }
    }

    public void validaFechar() {
        if (padraoManutencao.equals(padraoManutencaoCompare)) {
            show();
        } else {
            RequestContext.getCurrentInstance().execute("pdmCloseDialogWidget.show()");
        }
    }

    public void addServicoToPms() {
        if (selectedFactoryServicos.isEmpty()) {
            keepDialogOpen();
            displayInfoMessageToUser("Selecione ao menos um serviço");
            return;
        }
        for (FactoryServico fs : selectedFactoryServicos) {
            padraoManutencaoServico = new PadraoManutencaoServico(fs);
            padraoManutencaoServico.setPdrManutencao(padraoManutencao);
            padraoManutencao.getPdmServico().add(padraoManutencaoServico);
        }
        int i = 1;
        for (PadraoManutencaoServico pds : padraoManutencao.getPdmServico()) {
            pds.setIdTemp(i);
            i++;
        }
        selectedFactoryServicos = new ArrayList<>();
        setAllFactoryServicos(null);
        setPadraoManutencaoServico(null);
        closeDialog();
    }

    public void addPecaToPMS() {
        if (selectedFactoryPecas.isEmpty()) {
            keepDialogOpen();
            displayInfoMessageToUser("Selecione ao menos uma Peça");
            return;
        }
        for (FactoryPeca fp : selectedFactoryPecas) {
            padraoManutencaoPeca = new PadraoManutencaoPeca(fp);
            padraoManutencaoPeca.setPdmServico(padraoManutencaoServico);
            padraoManutencaoPeca.setQuantidade(1);
            padraoManutencaoServico.getPdsPecas().add(padraoManutencaoPeca);
        }
        selectedFactoryPecas = new ArrayList<>();
        setAllFactoryPecas(null);
        setPadraoManutencaoPeca(null);
        closeDialog();
    }

    public List<FactoryServico> complete(String name) {
        List<FactoryServico> queryResult = new ArrayList<>();

        if (allFactoryServicos == null) {
            allFactoryServicos = new FactoryServicoFacade().listAll();
        }

        allFactoryServicos.removeAll(padraoManutencao.getPdmServico());

        for (FactoryServico fs : allFactoryServicos) {
            if (fs.getSRV_DESCRICAO().toLowerCase().contains(name.toLowerCase())) {
                queryResult.add(fs);
            }
        }

        return queryResult;
    }

    public PadraoManutencao getPadraoManutencao() {
        if (padraoManutencao == null) {
            padraoManutencao = (PadraoManutencao) ELFlash.getFlash().get(SELECTED_PDM);
            if (padraoManutencao == null) {
                setPadraoManutencao(new PadraoManutencao());
            } else if (padraoManutencao.getPDM_ID() == 0) {
                setPadraoManutencao(new PadraoManutencao());
            } else {
                padraoManutencao = getPdmFacade().findPdm(padraoManutencao.getPDM_ID());
            }
        }

        return padraoManutencao;
    }

    public void setPadraoManutencao(PadraoManutencao padraoManutencao) {
        this.padraoManutencao = padraoManutencao;
    }

    public void loadListaPdm() {
        listaPdm = getPdmFacade().listAll();
    }

    public List<PadraoManutencao> getListaPdm() {
        if (listaPdm == null) {
            loadListaPdm();
        }
        return listaPdm;
    }

    public void setListaPdm(List<PadraoManutencao> listaPdm) {
        this.listaPdm = listaPdm;
    }

    public PadraoManutencaoFacade getPdmFacade() {
        if (pdmFacade == null) {
            pdmFacade = new PadraoManutencaoFacade();
        }

        return pdmFacade;
    }

    public void setPdmFacade(PadraoManutencaoFacade pdmFacade) {
        this.pdmFacade = pdmFacade;
    }

    public List<PadraoManutencao> getListaPdmSelected() {
        return listaPdmSelected;
    }

    public void setListaPdmSelected(List<PadraoManutencao> listaPdmSelected) {
        this.listaPdmSelected = listaPdmSelected;
    }

    public List<PadraoManutencaoServico> getListaPdsSelected() {
        return listaPdsSelected;
    }

    public void setListaPdsSelected(List<PadraoManutencaoServico> listaPdsSelected) {
        this.listaPdsSelected = listaPdsSelected;
    }

    public void loadAllFactoryServicos() {
        allFactoryServicos = new FactoryServicoFacade().listAll();
    }

    public void loadAllFactoryPecas() {
        allFactoryPecas = new FactoryPecaFacade().listAll();
    }

    public List<FactoryServico> getAllFactoryServicos() {
        if (allFactoryServicos == null) {
            loadAllFactoryServicos();
        }
        List<FactoryServico> t = new FactoryServicoFacade().listAll();
        for (FactoryServico fs : t) {
            for (PadraoManutencaoServico pms : padraoManutencao.getPdmServico()) {
                if (pms.getFactoryServico().getSRV_ID() == fs.getSRV_ID()) {
                    allFactoryServicos.remove(fs);
                }
            }
        }
        return allFactoryServicos;
    }

    public void setAllFactoryServicos(List<FactoryServico> allFactoryServicos) {
        this.allFactoryServicos = allFactoryServicos;
    }

    public List<FactoryPeca> getAllFactoryPecas() {
        if (allFactoryPecas == null) {
            loadAllFactoryPecas();
        }
        List<FactoryPeca> t = new FactoryPecaFacade().listAll();
        for (FactoryPeca fs : t) {
            for (PadraoManutencaoPeca pms : padraoManutencaoServico.getPdsPecas()) {
                if (pms.getFactoryPeca().getPEC_ID() == fs.getPEC_ID()) {
                    allFactoryPecas.remove(fs);
                }
            }
        }
        return allFactoryPecas;
    }

    public void setAllFactoryPecas(List<FactoryPeca> allFactoryPecas) {
        this.allFactoryPecas = allFactoryPecas;
    }

    public List<FactoryPeca> getSelectedFactoryPecas() {
        if (selectedFactoryPecas == null) {
            selectedFactoryPecas = new ArrayList<>();
        }
        return selectedFactoryPecas;
    }

    public void setSelectedFactoryPecas(List<FactoryPeca> selectedFactoryPecas) {
        this.selectedFactoryPecas = selectedFactoryPecas;
    }

    public PadraoManutencao getPadraoManutencaoCompare() {
        return padraoManutencaoCompare;
    }

    public void setPadraoManutencaoCompare(PadraoManutencao padraoManutencaoCompare) {
        this.padraoManutencaoCompare = padraoManutencaoCompare;
    }

    public PadraoManutencaoServico getPadraoManutencaoServico() {
        if (padraoManutencaoServico == null) {
            padraoManutencaoServico = new PadraoManutencaoServico();
        }
        return padraoManutencaoServico;
    }

    public void setPadraoManutencaoServico(PadraoManutencaoServico padraoManutencaoServico) {
        this.padraoManutencaoServico = padraoManutencaoServico;
    }

    public PadraoManutencaoPeca getPadraoManutencaoPeca() {
        return padraoManutencaoPeca;
    }

    public void setPadraoManutencaoPeca(PadraoManutencaoPeca padraoManutencaoPeca) {
        this.padraoManutencaoPeca = padraoManutencaoPeca;
    }

    public PadraoManutencaoServico getPadraoManutencaoServicoDelete() {
        return padraoManutencaoServicoDelete;
    }

    public void setPadraoManutencaoServicoDelete(PadraoManutencaoServico padraoManutencaoServicoDelete) {
        this.padraoManutencaoServicoDelete = padraoManutencaoServicoDelete;
    }

    public PadraoManutencaoPeca getPadraoManutencaoPecaDelete() {
        return padraoManutencaoPecaDelete;
    }

    public void setPadraoManutencaoPecaDelete(PadraoManutencaoPeca padraoManutencaoPecaDelete) {
        this.padraoManutencaoPecaDelete = padraoManutencaoPecaDelete;
    }

    public int getSearchCode() {
        return searchCode;
    }

    public void setSearchCode(int searchCode) {
        this.searchCode = searchCode;
    }

    public List<FactoryServico> getSelectedFactoryServicos() {
        if (selectedFactoryServicos == null) {
            selectedFactoryServicos = new ArrayList<>();
        }
        return selectedFactoryServicos;
    }

    public void setSelectedFactoryServicos(List<FactoryServico> selectedFactoryServicos) {
        this.selectedFactoryServicos = selectedFactoryServicos;
    }
}
