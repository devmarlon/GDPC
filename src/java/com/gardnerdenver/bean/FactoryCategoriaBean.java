package com.gardnerdenver.bean;

import com.gardnerdenver.facade.ContratoFactoryFacade;
import com.gardnerdenver.facade.FactoryCategoriaFacade;
//import com.gardnerdenver.facade.ServicoFacade;
import com.gardnerdenver.model.Equipamento;
import com.gardnerdenver.model.FactoryContrato;
import com.gardnerdenver.model.FactoryCategoria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.context.RequestContext;

@SessionScoped
@ManagedBean(name = "factoryCategoriaBean")
public class FactoryCategoriaBean extends AbstractMB implements Serializable {

    private static final long serialVersionUID = 1L;

//    private Servico servico;
    private Equipamento selectedEquipamento;
    private FactoryCategoria factoryCategoria;
    private FactoryCategoria categoriaCompare;

    private List<FactoryCategoria> categorias;
    private List<FactoryCategoria> categoriasSelected;
    private List<Equipamento> equipamentos;
    private List<FactoryContrato> contratos;

    private DataModel<FactoryCategoria> dtmFactoryCategorias;

//    private ServicoFacade servicoFacade;
    private FactoryCategoriaFacade factoryPecaFacade;
    //busca
    private int tipoLista = 0;
    private String nomeBusca = "";

    public void alterar() {
        factoryCategoria = dtmFactoryCategorias.getRowData();

        redirect("/pages/protected/factory/categoriaCadastro.xhtml");
    }

    public void novo() {
        factoryCategoria = new FactoryCategoria();
        categoriaCompare = null;
        redirect("/pages/protected/factory/categoriaCadastro.xhtml");
    }

    public void showFactoryCategoria() {
        tipoLista = 0;
        nomeBusca = "";
        factoryCategoria = null;
        categoriaCompare = null;
        redirect("/pages/protected/factory/categoria.xhtml");
    }

    public void buscar() {
        tipoLista = 1;
        getDtmFactoryCategorias();
    }

    public void salvar() {
        if (factoryCategoria.getCAT_ID() == 0) { //save
            try {
                getFactoryPecaFacade().createPeca(factoryCategoria);
                displayInfoMessageToUser("Categoria salva com sucesso.");
                showFactoryCategoria();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível salvar a Peça.");
            }
        } else { //update
            try {
                getFactoryPecaFacade().updatePeca(factoryCategoria);
                displayInfoMessageToUser("Peça salva com sucesso.");
                showFactoryCategoria();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível salvar a Peça.");
            }
        }
    }
    
    

    public void validaDelete() {
        if (categoriasSelected.isEmpty()) {
            displayInfoMessageToUser("Selecione ao menos uma peça.");
        } else {
            RequestContext.getCurrentInstance().execute("pecDeleteDialogWidget.show()");
        }
    }

    public void validaFechar() {
        if (factoryCategoria.equals(categoriaCompare)) {
            showFactoryCategoria();
        } else {
            RequestContext.getCurrentInstance().execute("catCloseDialogWidget.show()");
        }
    }

    public void deleteFactoryPeca() {
        for (FactoryCategoria pec : categoriasSelected) {
//            if (pec.isFab()) {
//                closeDialog();
//                displayInfoMessageToUser(pec.getDescricao() + " não pode ser removido");
//            } else {
            try {
                getFactoryPecaFacade().deletePeca(pec);
                closeDialog();
                displayInfoMessageToUser("Categoria removida com sucesso.");
//                loadDogs();
//                resetDog();
            } catch (Exception e) {
                displayErrorMessageToUser(pec.getCAT_NOME() + " não pode ser removida pois está em uso");
                keepDialogOpen();
                e.printStackTrace();
            }
        }
//        }
    }

    public Equipamento getSelectedEquipamento() {
        return selectedEquipamento;
    }

    public void setSelectedEquipamento(Equipamento selectedEquipamento) {
        this.selectedEquipamento = selectedEquipamento;
    }

    public List<FactoryCategoria> getFactoryPecas() {
        if (tipoLista == 0) {
            categorias = getFactoryPecaFacade().listAll();
        } else {
            categorias = getFactoryPecaFacade().listBusca(nomeBusca);
        }
        if (categorias == null) {
            categorias = new ArrayList<>();
        }
        return categorias;
    }

    public void setFactoryPecas(List<FactoryCategoria> pecas) {
        this.categorias = pecas;
    }

    public List<FactoryCategoria> complete(String name) {
        List<FactoryCategoria> queryResult = new ArrayList<>();

        if (categorias == null) {
            factoryPecaFacade = new FactoryCategoriaFacade();
            categorias = factoryPecaFacade.listAll();
        }

//        parceiros.removeAll(personWithDogs.getDogs());
        for (FactoryCategoria fP : categorias) {
            if (fP.getCAT_NOME().toLowerCase().contains(name.toLowerCase())) {
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

    public DataModel<FactoryCategoria> getDtmFactoryCategorias() {
        dtmFactoryCategorias = new ListDataModel(getFactoryPecas());
        return dtmFactoryCategorias;
    }

    public void setDtmFactoryCategorias(DataModel<FactoryCategoria> dtmFactoryCategorias) {
        this.dtmFactoryCategorias = dtmFactoryCategorias;
    }

    public FactoryCategoriaFacade getFactoryPecaFacade() {
        factoryPecaFacade = new FactoryCategoriaFacade();
        return factoryPecaFacade;
    }

    public void setFactoryPecaFacade(FactoryCategoriaFacade pecaFacade) {
        this.factoryPecaFacade = pecaFacade;
    }

    public FactoryCategoria getFactoryCategoria() {
        if (factoryCategoria == null) {
            factoryCategoria = new FactoryCategoria();
        }
        return factoryCategoria;
    }

    public void setFactoryCategoria(FactoryCategoria factoryCategoria) {
        this.factoryCategoria = factoryCategoria;
    }

    public FactoryCategoria getFactoryPecaCompare() {
        return categoriaCompare;
    }

    public void setFactoryPecaCompare(FactoryCategoria pecaCompare) {
        this.categoriaCompare = pecaCompare;
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

    public FactoryCategoria getCategoriaCompare() {
        return categoriaCompare;
    }

    public void setCategoriaCompare(FactoryCategoria categoriaCompare) {
        this.categoriaCompare = categoriaCompare;
    }

    public List<FactoryCategoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<FactoryCategoria> categorias) {
        this.categorias = categorias;
    }

    public List<FactoryCategoria> getCategoriasSelected() {
        return categoriasSelected;
    }

    public void setCategoriasSelected(List<FactoryCategoria> categoriasSelected) {
        this.categoriasSelected = categoriasSelected;
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

}
