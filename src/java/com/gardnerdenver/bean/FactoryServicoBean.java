package com.gardnerdenver.bean;

import com.gardnerdenver.facade.ContratoFactoryFacade;
import com.gardnerdenver.facade.FactoryServicoFacade;
import com.gardnerdenver.facade.ServicoFacade;
import com.gardnerdenver.model.Equipamento;
import com.gardnerdenver.model.FactoryContrato;
import com.gardnerdenver.model.FactoryServico;
import com.gardnerdenver.model.Servico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.context.RequestContext;

@SessionScoped
@ManagedBean(name = "factoryServicoBean")
public class FactoryServicoBean extends AbstractMB implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private FactoryServico servico;
    private FactoryServico servicoCompare;
    private Equipamento selectedEquipamento;
    
    private List<FactoryServico> servicos;
    private List<FactoryServico> servicosSelected;
//    private List<Equipamento> equipamentos;

    private List<FactoryContrato> contratos;

    //datamodel
    private DataModel<FactoryServico> dtmServicos;

    //
    private String tipoAcao = "";
    //facade
    private FactoryServicoFacade facServicoFacade;
    private ServicoFacade servicoFacade;
    //busca
    private int tipoLista = 0;
    private String nomeBusca = "";
    
    public void novo() {
        setTipoAcao("Inclusão de Cliente");
        servico = new FactoryServico();
        servicoCompare = new FactoryServico(servico);
        
        redirect("/pages/protected/factory/servicoCadastro.xhtml");
    }
    
    public void showServico() {
        servico = null;
        servicoCompare = null;
        setTipoLista(0);
        nomeBusca = "";
        
        redirect("/pages/protected/factory/servico.xhtml");
    }
    
    public void buscar() {
        tipoLista = 1;
        getDtmServicos();
    }
    
    public void alterar() {
        servico = dtmServicos.getRowData();
        
        servicoCompare = new FactoryServico(servico);
        redirect("/pages/protected/factory/servicoCadastro.xhtml");
    }
    
    public void salvar() {
        boolean result = false;
        servico.setAtivo(true);
        if (servico.getSRV_ID() == 0) { //save
            try {
                result = true;
                getFacServicoFacade().createServico(servico);
                displayInfoMessageToUser("Serviço salvo com sucesso.");
//                showServico();
            } catch (Exception e) {
                result = false;
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível salvar o serviço.");
            }
            if (result) {
                loadContratos();
                if (getContratos().size() > 0) {
                    for (FactoryContrato c : getContratos()) {
                        loadServicoFacade(c.getUsuId().getUSU_BANCO());
                        Servico s = new Servico(servico);
                        try {
                            getServicoFacade().createServico(s);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                showServico();
            }
        } else { //update
            try {
                getFacServicoFacade().updateServico(servico);
                displayInfoMessageToUser("Serviço salvo com sucesso.");
//                showServico();
                result = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível salvar o serviço.");
                result = false;
            }
            
            if (result) {
                loadContratos();
                if (getContratos().size() > 0) {
                    for (FactoryContrato c : getContratos()) {
                        loadServicoFacade(c.getUsuId().getUSU_BANCO());
                        Servico p = getServicoFacade().findServicoByFab(servico.getSRV_ID());
                        p.setSRV_DESCRICAO(servico.getSRV_DESCRICAO());
                        p.setSRV_FREQDIAS(servico.getSRV_FREQDIAS());
                        p.setSRV_FREQHORAS(servico.getSRV_FREQHORAS());
                        p.setAtivo(servico.getAtivo());
                        try {
                            getServicoFacade().updateServico(p);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                showServico();
            }
        }
    }
    
    public void validaDelete() {
        if (servicosSelected.isEmpty()) {
            displayInfoMessageToUser("Selecione ao menos um serviço.");
        } else {
            RequestContext.getCurrentInstance().execute("srvDeleteDialogWidget.show()");
        }
    }
    
    public void validaFechar() {
        if (servico.equals(servicoCompare)) {
            showServico();
        } else {
            RequestContext.getCurrentInstance().execute("srvCloseDialogWidget.show()");
        }
    }
    
    public void desativaServico() {
        for (FactoryServico servico1 : servicosSelected) {
            
            try {
                servico1.setAtivo(false);
                servico = servico1;
                salvar();

//                getFacServicoFacade().updateServico(servico1);
                closeDialog();
                displayInfoMessageToUser("Serviço desativado com sucesso.");
//                loadDogs();
//                resetDog();
            } catch (Exception e) {
                displayErrorMessageToUser(servico1.getSRV_DESCRICAO() + " não pode ser removido pois está em uso");
                keepDialogOpen();
                e.printStackTrace();
            }
        }
    }
    
    public void deleteServico() {
        for (FactoryServico servico1 : servicosSelected) {
            
            try {
                getFacServicoFacade().deleteServico(servico1);
                closeDialog();
                displayInfoMessageToUser("Serviço removido com sucesso.");
//                loadDogs();
//                resetDog();
            } catch (Exception e) {
                displayErrorMessageToUser(servico1.getSRV_DESCRICAO() + " não pode ser removido pois está em uso");
                keepDialogOpen();
                e.printStackTrace();
            }
        }
    }
    
    public List<FactoryServico> complete(String name) {
        List<FactoryServico> queryResult = new ArrayList<>();
        
        if (servicos == null) {
            facServicoFacade = new FactoryServicoFacade();
            servicos = facServicoFacade.listAll();
        }

//        parceiros.removeAll(personWithDogs.getDogs());
        for (FactoryServico serv : servicos) {
            if (serv.getSRV_DESCRICAO().toLowerCase().contains(name.toLowerCase())) {
                queryResult.add(serv);
            }
        }
        
        return queryResult;
    }
    
    public FactoryServico getParceiro() {
        return servico;
    }
    
    public void setParceiro(FactoryServico servico) {
        this.servico = servico;
    }
    
    public Equipamento getSelectedEquipamento() {
        return selectedEquipamento;
    }
    
    public void setSelectedEquipamento(Equipamento selectedEquipamento) {
        this.selectedEquipamento = selectedEquipamento;
    }
    
    public FactoryServico getServico() {
        return servico;
    }
    
    public void setServico(FactoryServico servico) {
        this.servico = servico;
    }
    
    public List<FactoryServico> getServicos() {
        if (tipoLista == 0) {
            servicos = getFacServicoFacade().listAll();
        } else {
            servicos = getFacServicoFacade().listBusca(nomeBusca);
        }
        return servicos;
    }
    
    public void setServicos(List<FactoryServico> servicos) {
        this.servicos = servicos;
    }
    
    public List<FactoryServico> getServicosSelected() {
        return servicosSelected;
    }
    
    public void setServicosSelected(List<FactoryServico> servicosSelected) {
        this.servicosSelected = servicosSelected;
    }
    
    public DataModel<FactoryServico> getDtmServicos() {
        servicosSelected = null;
        dtmServicos = new ListDataModel(getServicos());
        return dtmServicos;
    }
    
    public void setDtmServicos(DataModel<FactoryServico> dtmServicos) {
        this.dtmServicos = dtmServicos;
    }
    
    public FactoryServicoFacade getFacServicoFacade() {
        facServicoFacade = new FactoryServicoFacade();
        return facServicoFacade;
    }
    
    public void setFacServicoFacade(FactoryServicoFacade facServicoFacade) {
        this.facServicoFacade = facServicoFacade;
    }
    
    public FactoryServico getServicoCompare() {
        return servicoCompare;
    }
    
    public void setServicoCompare(FactoryServico servicoCompare) {
        this.servicoCompare = servicoCompare;
    }
    
    public void loadServicoFacade(String banco) {
        servicoFacade = new ServicoFacade(banco);
    }
    
    public ServicoFacade getServicoFacade() {
        return servicoFacade;
    }
    
    public void setServicoFacade(ServicoFacade servicoFacade) {
        this.servicoFacade = servicoFacade;
    }
    
    public String getTipoAcao() {
        return tipoAcao;
    }
    
    public void setTipoAcao(String tipoAcao) {
        this.tipoAcao = tipoAcao;
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
    
    private void loadContratos() {
        contratos = new ContratoFactoryFacade().listDist();
        if (getContratos() == null) {
            contratos = new ArrayList<>();
        }
    }
    
    public List<FactoryContrato> getContratos() {
        return contratos;
    }
    
    public void setContratos(List<FactoryContrato> contratos) {
        this.contratos = contratos;
    }
}
