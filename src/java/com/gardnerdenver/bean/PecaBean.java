package com.gardnerdenver.bean;

import com.gardnerdenver.facade.FactoryPecaFacade;
import com.gardnerdenver.facade.PecaFacade;
import com.gardnerdenver.facade.ServicoFacade;
import com.gardnerdenver.model.Equipamento;
import com.gardnerdenver.model.FactoryPeca;
import com.gardnerdenver.model.Peca;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.context.RequestContext;

@SessionScoped
@ManagedBean(name = "pecaBean")
public class PecaBean extends AbstractMB implements Serializable {

    private static final long serialVersionUID = 1L;

//    private Servico servico;
    private Equipamento selectedEquipamento;
    private Peca peca;
    private Peca pecaCompare;

    private List<Peca> pecas;
    private List<Peca> pecasSelected;
    private List<Equipamento> equipamentos;

    private DataModel<Peca> dtmPecas;

    private ServicoFacade servicoFacade;
    private PecaFacade pecaFacade;
    //busca
    private int tipoLista = 0;
    private String nomeBusca = "";

    public void alterar() {
        peca = dtmPecas.getRowData();
        pecaCompare = new Peca(peca);
        if (peca.getFab() > 0) {
            displayInfoMessageToUser(peca.getDescricao() + " não pode ser alterado");
            return;
        }
        redirect("/pages/protected/distributor/pecaCadastro.xhtml");
    }

    public void novo() {
        peca = new Peca();
        pecaCompare = new Peca(peca);
        redirect("/pages/protected/distributor/pecaCadastro.xhtml");
    }

    public void showPeca() {
        tipoLista = 0;
        nomeBusca = "";
        peca = null;
        pecaCompare = null;

        List<FactoryPeca> listFPeca = new FactoryPecaFacade().listAll();
        for (FactoryPeca fPeca : listFPeca) {
            Peca p = getPecaFacade().findPecaByFab(fPeca.getPEC_ID());
            if (p == null) {
                p = new Peca(fPeca);
                getPecaFacade().createPeca(p);
            } else {
                int id = p.getPEC_ID();
                p = new Peca(fPeca);
                p.setPEC_ID(id);
                getPecaFacade().updatePeca(p);
            }

        }

        redirect("/pages/protected/distributor/peca.xhtml");
    }

    public void buscar() {
        tipoLista = 1;
        getDtmPecas();
    }

    public void salvar() {
        if (peca.getPEC_ID() == 0) { //save
            try {
                getPecaFacade().createPeca(peca);
                displayInfoMessageToUser("Peça salva com sucesso.");
                showPeca();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível salvar a Peça.");
            }
        } else { //update
            try {
                getPecaFacade().updatePeca(peca);
                displayInfoMessageToUser("Peça salva com sucesso.");
                showPeca();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível salvar a Peça.");
            }
        }
    }

    public void validaDelete() {
        if (pecasSelected.isEmpty()) {
            displayInfoMessageToUser("Selecione ao menos uma peça.");
        } else {
            RequestContext.getCurrentInstance().execute("pecDeleteDialogWidget.show()");
        }
    }

    public void validaFechar() {
        if (peca.equals(pecaCompare)) {
            showPeca();
        } else {
            RequestContext.getCurrentInstance().execute("pecCloseDialogWidget.show()");
        }
    }

    public void deletePeca() {
        for (Peca pec : pecasSelected) {
            if (pec.getFab() > 0) {
                closeDialog();
                displayInfoMessageToUser(pec.getDescricao() + " não pode ser removido");
            } else {
                try {
                    getPecaFacade().deletePeca(pec);
                    closeDialog();
                    displayInfoMessageToUser("Peça removida com sucesso.");
                } catch (Exception e) {
                    displayErrorMessageToUser(pec.getDescricao() + " não pode ser removido pois está em uso");
                    keepDialogOpen();
                    e.printStackTrace();
                }
            }
        }
    }

    public Equipamento getSelectedEquipamento() {
        return selectedEquipamento;
    }

    public void setSelectedEquipamento(Equipamento selectedEquipamento) {
        this.selectedEquipamento = selectedEquipamento;
    }

    public List<Peca> getPecas() {
        if (tipoLista == 0) {
            pecas = getPecaFacade().listAll();
        } else {
            pecas = getPecaFacade().listBusca(nomeBusca);
        }
        return pecas;
    }

    public void setPecas(List<Peca> pecas) {
        this.pecas = pecas;
    }

    public List<Peca> complete(String name) {
        List<Peca> queryResult = new ArrayList<>();

        if (pecas == null) {
            pecaFacade = new PecaFacade();
            pecas = pecaFacade.listAll();
        }

//        parceiros.removeAll(personWithDogs.getDogs());
        for (Peca fP : pecas) {
            if (fP.getDescricao().toLowerCase().contains(name.toLowerCase())) {
                queryResult.add(fP);
            }
        }

        return queryResult;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public DataModel<Peca> getDtmPecas() {
        dtmPecas = new ListDataModel(getPecas());
        return dtmPecas;
    }

    public void setDtmPecas(DataModel<Peca> dtmPecas) {
        this.dtmPecas = dtmPecas;
    }

    public ServicoFacade getServicoFacade() {
        return servicoFacade;
    }

    public void setServicoFacade(ServicoFacade servicoFacade) {
        this.servicoFacade = servicoFacade;
    }

    public PecaFacade getPecaFacade() {
        pecaFacade = new PecaFacade();
        return pecaFacade;
    }

    public void setPecaFacade(PecaFacade pecaFacade) {
        this.pecaFacade = pecaFacade;
    }

    public Peca getPeca() {
        if (peca == null) {
            peca = new Peca();
        }
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public Peca getPecaCompare() {
        return pecaCompare;
    }

    public void setPecaCompare(Peca pecaCompare) {
        this.pecaCompare = pecaCompare;
    }

    public List<Peca> getPecasSelected() {
        return pecasSelected;
    }

    public void setPecasSelected(List<Peca> pecasSelected) {
        this.pecasSelected = pecasSelected;
    }

    public int getTipoLista() {
        return tipoLista;
    }

    public void setTipoLista(int tipoLista) {
        this.tipoLista = tipoLista;
    }

    public String getNomeBusca() {
        return nomeBusca;
    }

    public void setNomeBusca(String nomeBusca) {
        this.nomeBusca = nomeBusca;
    }

}
