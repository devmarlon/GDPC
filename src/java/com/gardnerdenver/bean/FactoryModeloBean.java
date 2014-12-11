package com.gardnerdenver.bean;

import com.gardnerdenver.facade.ContratoFactoryFacade;
import com.gardnerdenver.facade.FactoryModeloFacade;
import com.gardnerdenver.facade.ModeloFacade;
import com.gardnerdenver.facade.PecaFacade;
//import com.gardnerdenver.facade.ServicoFacade;
import com.gardnerdenver.model.Equipamento;
import com.gardnerdenver.model.FactoryContrato;
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
@ManagedBean(name = "factoryModeloBean")
public class FactoryModeloBean extends AbstractMB implements Serializable {

    private static final long serialVersionUID = 1L;

//    private Servico servico;
//    private Equipamento selectedEquipamento;
    private FactoryModelo factoryModelo;
    private FactoryModelo modeloCompare;

    private List<FactoryModelo> modelos;
    private List<FactoryModelo> modelosSelected;
    private List<Equipamento> equipamentos;
    private List<FactoryContrato> contratos;

    private DataModel<FactoryModelo> dtmFactoryModelos;

//    private ServicoFacade servicoFacade;
    private FactoryModeloFacade factoryModeloFacade;
    private ModeloFacade modeloFacade;
    private PecaFacade pecaFacade;
    //busca
    private int tipoLista = 0;
    private String nomeBusca = "";

    public void alterar() {
        factoryModelo = dtmFactoryModelos.getRowData();
        modeloCompare = new FactoryModelo(factoryModelo);

        redirect("/pages/protected/factory/modeloCadastro.xhtml");
    }

    public void novo() {
        factoryModelo = new FactoryModelo();
        modeloCompare = null;
        redirect("/pages/protected/factory/modeloCadastro.xhtml");
    }

    public void show() {
        tipoLista = 0;
        nomeBusca = "";
        factoryModelo = null;
        modeloCompare = null;
        redirect("/pages/protected/factory/modelo.xhtml");
    }

    public void buscar() {
        tipoLista = 1;
        getDtmFactoryModelos();
    }

    public void salvar() {
        boolean result = false;
        if (factoryModelo.getMOD_ID() == 0) { //save
            try {
                result = true;
                getFactoryModeloFacade().createModelo(factoryModelo);
                displayInfoMessageToUser("Modelo salvo com sucesso.");
//                showFactoryPeca();
            } catch (Exception e) {
                result = false;
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível salvar o modelo.");
            }
            if (result) {
                loadContratos();
                if (getContratos().size() > 0) {
                    for (FactoryContrato c : getContratos()) {
                        loadModeloFacade(c.getUsuId().getUSU_BANCO());
                        Modelo p = new Modelo(factoryModelo);
                        try {
                            getModeloFacade().create(p);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                show();
            }
        } else { //update
            try {
                result = true;
                getFactoryModeloFacade().updateModelo(factoryModelo);
                displayInfoMessageToUser("Modelo salvo com sucesso.");
//                showFactoryPeca();
            } catch (Exception e) {
                result = false;
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível salvar o modelo.");
            }
            if (result) {
                loadContratos();
                if (getContratos().size() > 0) {
                    for (FactoryContrato c : getContratos()) {
                        loadModeloFacade(c.getUsuId().getUSU_BANCO());
                        Modelo p = getModeloFacade().findModeloByFab(factoryModelo.getMOD_ID());
                        p.setMOD_NOME(factoryModelo.getMOD_NOME());
                        p.setAtivo(factoryModelo.getAtivo());
                        try {
                            getModeloFacade().update(p);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                show();
            }
        }
    }

    public void validaDelete() {
        if (modelosSelected.isEmpty()) {
            displayInfoMessageToUser("Selecione ao menos uma peça.");
        } else {
            RequestContext.getCurrentInstance().execute("pecDeleteDialogWidget.show()");
        }
    }

    public void validaFechar() {
        if (factoryModelo.equals(modeloCompare)) {
            show();
        } else {
            RequestContext.getCurrentInstance().execute("modeCloseDialogWidget.show()");
        }
    }

    public void loadPecaFacade(String banco) {
        pecaFacade = new PecaFacade(banco);
    }

    public void loadModeloFacade(String banco) {
        modeloFacade = new ModeloFacade(banco);
    }

    public void deleteFactoryPeca() {
        for (FactoryModelo pec : modelosSelected) {
//            if (pec.isFab()) {
//                closeDialog();
//                displayInfoMessageToUser(pec.getDescricao() + " não pode ser removido");
//            } else {
            try {
                getFactoryModeloFacade().deletePeca(pec);
                closeDialog();
                displayInfoMessageToUser("Serviço removido com sucesso.");
//                loadDogs();
//                resetDog();
            } catch (Exception e) {
                displayErrorMessageToUser(pec.getMOD_NOME() + " não pode ser removido pois está em uso");
                keepDialogOpen();
                e.printStackTrace();
            }
        }
//        }
    }

    public void desativaModelo() {
        for (FactoryModelo mod : modelosSelected) {

            try {
                mod.setAtivo(Boolean.FALSE);
                factoryModelo = mod;
                salvar();
//                getFactoryPecaFacade().updatePeca(pec);
                closeDialog();
                displayInfoMessageToUser("Modelo desativado com sucesso.");
            } catch (Exception e) {
                displayErrorMessageToUser(mod.getMOD_NOME() + " não pode ser removido pois está em uso");
                keepDialogOpen();
                e.printStackTrace();
            }
        }
    }

    public List<FactoryModelo> getFactoryPecas() {
        if (tipoLista == 0) {
            modelos = getFactoryModeloFacade().listAll();
        } else {
            modelos = getFactoryModeloFacade().listBusca(nomeBusca);
        }
        if (modelos == null) {
            modelos = new ArrayList<>();
        }
        return modelos;
    }

    public void setFactoryPecas(List<FactoryModelo> pecas) {
        this.modelos = pecas;
    }

    public List<FactoryModelo> complete(String name) {
        List<FactoryModelo> queryResult = new ArrayList<>();

        if (modelos == null) {
            factoryModeloFacade = new FactoryModeloFacade();
            modelos = factoryModeloFacade.listAll();
        }

//        parceiros.removeAll(personWithDogs.getDogs());
        for (FactoryModelo fP : modelos) {
            if (fP.getMOD_NOME().toLowerCase().contains(name.toLowerCase())) {
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

    public DataModel<FactoryModelo> getDtmFactoryModelos() {
        dtmFactoryModelos = new ListDataModel(getFactoryPecas());
        return dtmFactoryModelos;
    }

    public void setDtmFactoryModelos(DataModel<FactoryModelo> dtmFactoryModelos) {
        this.dtmFactoryModelos = dtmFactoryModelos;
    }

    public FactoryModeloFacade getFactoryModeloFacade() {
        factoryModeloFacade = new FactoryModeloFacade();
        return factoryModeloFacade;
    }

    public void setFactoryModeloFacade(FactoryModeloFacade pecaFacade) {
        this.factoryModeloFacade = pecaFacade;
    }

    public FactoryModelo getFactoryPecaCompare() {
        return modeloCompare;
    }

    public void setFactoryPecaCompare(FactoryModelo pecaCompare) {
        this.modeloCompare = pecaCompare;
    }

    public List<FactoryModelo> getFactoryPecasSelected() {
        return modelosSelected;
    }

    public void setFactoryPecasSelected(List<FactoryModelo> pecasSelected) {
        this.modelosSelected = pecasSelected;
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

    public FactoryModelo getPecaCompare() {
        return modeloCompare;
    }

    public void setPecaCompare(FactoryModelo pecaCompare) {
        this.modeloCompare = pecaCompare;
    }

    public void loadModelos() {
        modelos = getFactoryModeloFacade().listAll();
    }

    public List<FactoryModelo> getModelos() {
        if (modelos == null) {
            loadModelos();
        }
        return modelos;
    }

    public void setModelos(List<FactoryModelo> modelos) {
        this.modelos = modelos;
    }

    public List<FactoryModelo> getModelosSelected() {
        return modelosSelected;
    }

    public void setModelosSelected(List<FactoryModelo> modelosSelected) {
        this.modelosSelected = modelosSelected;
    }

    public List<FactoryContrato> getContratos() {
        return contratos;
    }

    private void loadContratos() {
        contratos = new ContratoFactoryFacade().listDist();
        if (getContratos() == null) {
            contratos = new ArrayList<>();
        }
    }

    public void setContratos(List<FactoryContrato> contratos) {
        this.contratos = contratos;
    }

    public PecaFacade getPecaFacade() {
        return pecaFacade;
    }

    public void setPecaFacade(PecaFacade pecaFacade) {
        this.pecaFacade = pecaFacade;
    }

    public ModeloFacade getModeloFacade() {
        return modeloFacade;
    }

    public void setModeloFacade(ModeloFacade modeloFacade) {
        this.modeloFacade = modeloFacade;
    }

    public FactoryModelo getFactoryModelo() {
        return factoryModelo;
    }

    public void setFactoryModelo(FactoryModelo factoryModelo) {
        this.factoryModelo = factoryModelo;
    }

    public FactoryModelo getModeloCompare() {
        return modeloCompare;
    }

    public void setModeloCompare(FactoryModelo modeloCompare) {
        this.modeloCompare = modeloCompare;
    }

}
