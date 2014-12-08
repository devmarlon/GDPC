package com.gardnerdenver.bean;

import com.gardnerdenver.facade.ServicoFacade;
import com.gardnerdenver.model.Equipamento;
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
@ManagedBean(name = "servicoBean")
public class ServicoBean extends AbstractMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Servico servico;
    private Servico servicoCompare;
    private Equipamento selectedEquipamento;

    private List<Servico> servicos;
    private List<Servico> servicosSelected;
    private List<Equipamento> equipamentos;

    //datamodel
    private DataModel<Servico> dtmServicos;

    //
    private String tipoAcao = "";
    //facade
    private ServicoFacade servicoFacade;
    //busca
    private int tipoLista = 0;
    private String nomeBusca = "";

    public void novo() {
        setTipoAcao("Inclusão de Cliente");
        servico = new Servico();
        servicoCompare = new Servico(servico);

        redirect("/pages/protected/distributor/servicoCadastro.xhtml");
    }

    public void showServico() {
        servico = null;
        servicoCompare = null;
        setTipoLista(0);
        nomeBusca = "";

        redirect("/pages/protected/distributor/servico.xhtml");
    }

    public void buscar() {
        tipoLista = 1;
        getDtmServicos();
    }

    public void alterar() {
        servico = dtmServicos.getRowData();
        if (servico.getFab() > 0) {
            displayInfoMessageToUser(servico.getSRV_DESCRICAO() + " não pode ser alterado");
            return;
        }
        servicoCompare = new Servico(servico);
        redirect("/pages/protected/distributor/servicoCadastro.xhtml");
    }

    public void salvar() {
        if (servico.getSRV_ID() == 0) { //save
            try {
                getServicoFacade().createServico(servico);
                displayInfoMessageToUser("Serviço salvo com sucesso.");
                showServico();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível salvar o serviço.");
            }
        } else { //update
            try {
                getServicoFacade().updateServico(servico);
                displayInfoMessageToUser("Serviço salvo com sucesso.");
                showServico();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível salvar o serviço.");
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

    public void deleteServico() {
        for (Servico servico1 : servicosSelected) {
            if (servico.getFab() > 0) {
                closeDialog();
                displayInfoMessageToUser(servico1.getSRV_DESCRICAO() + " não pode ser removido");
            } else {
                try {
                    getServicoFacade().deleteServico(servico1);
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
    }

    public List<Servico> complete(String name) {
        List<Servico> queryResult = new ArrayList<>();

        if (servicos == null) {
            servicoFacade = new ServicoFacade();
            servicos = servicoFacade.listAll();
        }

//        parceiros.removeAll(personWithDogs.getDogs());
        for (Servico serv : servicos) {
            if (serv.getSRV_DESCRICAO().toLowerCase().contains(name.toLowerCase())) {
                queryResult.add(serv);
            }
        }

        return queryResult;
    }

    public Servico getParceiro() {
        return servico;
    }

    public void setParceiro(Servico servico) {
        this.servico = servico;
    }

    public Equipamento getSelectedEquipamento() {
        return selectedEquipamento;
    }

    public void setSelectedEquipamento(Equipamento selectedEquipamento) {
        this.selectedEquipamento = selectedEquipamento;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public List<Servico> getServicos() {
        if (tipoLista == 0) {
            servicos = getServicoFacade().listAll();
        } else {
            servicos = getServicoFacade().listBusca(nomeBusca);
        }
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public List<Servico> getServicosSelected() {
        return servicosSelected;
    }

    public void setServicosSelected(List<Servico> servicosSelected) {
        this.servicosSelected = servicosSelected;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public DataModel<Servico> getDtmServicos() {
        dtmServicos = new ListDataModel(getServicos());
        return dtmServicos;
    }

    public void setDtmServicos(DataModel<Servico> dtmServicos) {
        this.dtmServicos = dtmServicos;
    }

    public ServicoFacade getServicoFacade() {
        if (servicoFacade == null) {
            servicoFacade = new ServicoFacade();
        }

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
}
