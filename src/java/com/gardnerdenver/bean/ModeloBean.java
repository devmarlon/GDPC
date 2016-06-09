package com.gardnerdenver.bean;

import com.gardnerdenver.facade.FactoryModeloFacade;
import com.gardnerdenver.facade.ModeloFacade;
import com.gardnerdenver.facade.ServicoFacade;
import com.gardnerdenver.model.FactoryModelo;
import com.gardnerdenver.model.Modelo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.context.RequestContext;

@SessionScoped
@ManagedBean(name = "modeloBean")
public class ModeloBean extends AbstractMB implements Serializable {

    private static final long serialVersionUID = 1L;

//    private Servico servico;
    private Modelo modelo;
    private Modelo modeloCompare;

    private List<Modelo> modelos;
    private List<Modelo> modelosSelected;

    private DataModel<Modelo> dtmModelos;

    private ServicoFacade servicoFacade;
    private ModeloFacade modeloFacade;
    //busca
    private int tipoLista = 0;
    private String nomeBusca = "";

    public void alterar() {
        modelo = dtmModelos.getRowData();
        modeloCompare = new Modelo(modelo);
        if (modelo.getFab() > 0) {
            displayInfoMessageToUser(modelo.getMOD_NOME() + " não pode ser alterado");
            return;
        }
        redirect("/pages/protected/distributor/modeloCadastro.xhtml");
    }

    public void novo() {
        modelo = new Modelo();
        modeloCompare = new Modelo(modelo);
        redirect("/pages/protected/distributor/modeloCadastro.xhtml");
    }

    public void show() {
        tipoLista = 0;
        nomeBusca = "";
        modelo = null;
        modeloCompare = null;

        gerarModelos();

        redirect("/pages/protected/distributor/modelo.xhtml");
    }

    public void gerarModelos() {
        List<FactoryModelo> listMod = new FactoryModeloFacade().listAllAtivos();
        for (FactoryModelo fMod : listMod) {
            Modelo mod = getModeloFacade().findModeloByFab(fMod.getMOD_ID());
            if (mod == null) {
                mod = new Modelo(fMod);
                getModeloFacade().create(mod);
            } else {
                int id = mod.getMOD_ID();
                mod = new Modelo(fMod);
                mod.setMOD_ID(id);
                getModeloFacade().update(mod);
            }
        }
    }

    public void buscar() {
        tipoLista = 1;
        getDtmModelos();
    }

    public void salvar() {
        if (modelo.getMOD_ID() == 0) { //save
            try {
                getModeloFacade().create(modelo);
                displayInfoMessageToUser("Modelo salvo com sucesso.");
                show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível salvar o modelo.");
            }
        } else { //update
            try {
                getModeloFacade().update(modelo);
                displayInfoMessageToUser("Modelo salvo com sucesso.");
                show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível salvar o modelo.");
            }
        }
    }

    public void validaDelete() {
        if (modelosSelected.isEmpty()) {
            displayInfoMessageToUser("Selecione ao menos um modelo");
        } else {
            RequestContext.getCurrentInstance().execute("modDeleteDialogWidget.show()");
        }
    }

    public void validaFechar() {
        if (modelo.equals(modeloCompare)) {
            show();
        } else {
            RequestContext.getCurrentInstance().execute("modCloseDialogWidget.show()");
        }
    }

    public void delete() {
        for (Modelo mod : modelosSelected) {
            if (mod.getFab() > 0) {
                closeDialog();
                displayInfoMessageToUser(mod.getMOD_NOME() + " não pode ser removido");
            } else {
                try {
                    getModeloFacade().delete(mod);
                    closeDialog();
                    displayInfoMessageToUser("Modelo removido com sucesso.");
//                loadDogs();
//                resetDog();
                } catch (Exception e) {
                    displayErrorMessageToUser(mod.getMOD_NOME() + " não pode ser removido pois está em uso");
                    keepDialogOpen();
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Modelo> getModelos() {
        if (tipoLista == 0) {
            modelos = getModeloFacade().listAll();
        } else {
            modelos = getModeloFacade().listBusca(nomeBusca);
        }
        return modelos;
    }

    public void setModelos(List<Modelo> modelos) {
        this.modelos = modelos;
    }

    public List<Modelo> complete(String name) {
        List<Modelo> queryResult = new ArrayList<>();

        if (modelos == null) {
            modelos = getModeloFacade().listAll();
        }

//        parceiros.removeAll(personWithDogs.getDogs());
        for (Modelo fP : modelos) {
            if (fP.getMOD_NOME().toLowerCase().contains(name.toLowerCase())) {
                queryResult.add(fP);
            }
        }

        return queryResult;
    }

    public DataModel<Modelo> getDtmModelos() {
        dtmModelos = new ListDataModel(getModelos());
        return dtmModelos;
    }

    public void setDtmModelos(DataModel<Modelo> dtmModelos) {
        this.dtmModelos = dtmModelos;
    }

    public ServicoFacade getServicoFacade() {
        return servicoFacade;
    }

    public void setServicoFacade(ServicoFacade servicoFacade) {
        this.servicoFacade = servicoFacade;
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

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Modelo getModeloCompare() {
        return modeloCompare;
    }

    public void setModeloCompare(Modelo modeloCompare) {
        this.modeloCompare = modeloCompare;
    }

    public List<Modelo> getModelosSelected() {
        return modelosSelected;
    }

    public void setModelosSelected(List<Modelo> modelosSelected) {
        this.modelosSelected = modelosSelected;
    }

    public ModeloFacade getModeloFacade() {
        if (modeloFacade == null) {
            modeloFacade = new ModeloFacade();
        }
        return modeloFacade;
    }

    public void setModeloFacade(ModeloFacade modeloFacade) {
        this.modeloFacade = modeloFacade;
    }

}
