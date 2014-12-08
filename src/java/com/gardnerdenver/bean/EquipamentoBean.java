package com.gardnerdenver.bean;

import com.gardnerdenver.facade.EquipamentoFacade;
import com.gardnerdenver.facade.EquipamentoMedicaoFacade;
import com.gardnerdenver.facade.EquipamentoServicoFacade;
import com.gardnerdenver.facade.ModeloFacade;
import com.gardnerdenver.facade.PadraoManutencaoFacade;
import com.gardnerdenver.facade.ParceiroFacade;
import com.gardnerdenver.facade.PecaFacade;
import com.gardnerdenver.facade.ServicoFacade;
import com.gardnerdenver.model.Equipamento;
import com.gardnerdenver.model.EquipamentoMedicao;
import com.gardnerdenver.model.EquipamentoServico;
import com.gardnerdenver.model.Modelo;
import com.gardnerdenver.model.PadraoManutencao;
import com.gardnerdenver.model.PadraoManutencaoPeca;
import com.gardnerdenver.model.PadraoManutencaoServico;
import com.gardnerdenver.model.Parceiro;
import com.gardnerdenver.model.Peca;
import com.gardnerdenver.model.PecaEqs;
import com.gardnerdenver.model.Servico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@SessionScoped
@ManagedBean(name = "equipamentoBean")
public class EquipamentoBean extends AbstractMB implements Serializable {

    //objs
    private Equipamento equipamento;
    private Equipamento equipamentoCompare;
    private EquipamentoServico equipamentoServico;
    private PadraoManutencao padraoManutencao;
    //lists
    private List<Equipamento> equipamentos;
    private List<Equipamento> selectedEquipamentos;
    private List<Modelo> modelos;
    private List<Servico> listAllServicos;
    private List<Servico> selectedServicos;
    private List<Peca> listAllPecas;
    private List<Peca> selectedPecas;
    private List<PadraoManutencao> listaPdm;
    //datamodel
    private DataModel<Equipamento> dtmEquipamentos;
    private DataModel<EquipamentoServico> dataServicos;
    //facade
    private EquipamentoFacade equipamentoFacade;
    private EquipamentoMedicaoFacade eqmFacade;
    private EquipamentoServicoFacade equipamentoServicoFacade;
    private PadraoManutencaoFacade pdmFacade;
    private ParceiroFacade parceiroFacade;
    //string
    private String tipoAcao = "";
    //busca
    private int tipoLista = 0;
    private String nomeBusca = "";
    private Parceiro parceiroBusca;
    private int categoriaBusca = 0;
    private String modeloBusca = "";
    private String serieBusca = "";
    private String fabricanteBusca = "";
    //
    private int horimetroInicial = 0;
    private int columnsPartida = 0;

    public void showEquipamento() {
        equipamento = null;
        equipamentoCompare = null;
        tipoLista = 0;
        parceiroBusca = null;
        nomeBusca = "";
        categoriaBusca = 0;
        modeloBusca = "";
        serieBusca = "";
        fabricanteBusca = "";
        loadModelos();

        redirect("/pages/protected/distributor/equipamento.xhtml");
    }

    public void novo() {
        setTipoAcao("Inclusão de Equipamento");
        equipamento = new Equipamento();
        equipamento.setEquipamentoMedicao(new ArrayList());
        equipamento.setServicos(new ArrayList());

        equipamentoCompare = new Equipamento(equipamento);
        loadModelos();

        redirect("/pages/protected/distributor/equipamentoCadastro.xhtml");
    }

    public void alterar() {
        equipamento = dtmEquipamentos.getRowData();
        if (equipamento.isFab()) {
            displayInfoMessageToUser(equipamento.getCategoria().getCAT_NOME() + " não pode ser alterado");
            return;
        }
        equipamentoCompare = new Equipamento(equipamento);
        setTipoAcao(equipamento.getModelo().getMOD_NOME() + " - " + equipamento.getEQP_SERIE());
//        dataServicos = new ListDataModel<>(getEquipamentoServicoFacade().listByEqpId(getEquipamento().getEQP_ID()));
        equipamento.setServicos(getEquipamentoServicoFacade().listByEqpId(getEquipamento().getEQP_ID()));
        loadModelos();
        equipamentoServico = null;
        selectedServicos = null;
        selectedPecas = null;

        redirect("/pages/protected/distributor/equipamentoCadastro.xhtml");
    }

    public void salvar() {
        if (equipamento.getEQP_ID() == 0) {//insert

            try {

                if (getEquipamentoFacade().verificaSN(equipamento.getEQP_SERIE())) {
                    displayErrorMessageToUser("O Número de Série informado já está sendo usado por outro equipamento");
                } else {
                    getEquipamentoFacade().createEquipamento(equipamento);
                    EquipamentoMedicao eqm = new EquipamentoMedicao();
                    eqm.setEQM_DATAATUALIZACAO(equipamento.getEQP_DATAPARTIDA());
                    eqm.setEQM_HORASTOTAIS(horimetroInicial);
                    eqm.setEQM_HORASEMCARGA(0);
                    eqm.setEquipamento(equipamento);
                    getEqmFacade().createEqm(eqm);

                    displayInfoMessageToUser("Equipamento salvo com sucesso.");
                    showEquipamento();
                }
            } catch (Exception e) {
                displayErrorMessageToUser("Não foi possível salvar o Equipamento.");
                e.printStackTrace();
            }
        } else {//update
            try {
                getEquipamentoFacade().updateEquipamento(equipamento);
                displayInfoMessageToUser("Equipamento atualizado com sucesso.");
                showEquipamento();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível atualizar o Equipamento.");
            }
        }
    }

    public void aplicaPadraoManutencao() {

        for (PadraoManutencaoServico pms : getPadraoManutencao().getPdmServico()) {
            equipamentoServico = new EquipamentoServico(equipamento);
            Servico s = new ServicoFacade().findServicoByFab(pms.getFactoryServico().getSRV_ID());
            if (s != null) {

                equipamentoServico.setServico(s);
                equipamentoServico.setMANUTATUAL(equipamento.getEQP_DATAPARTIDA());
                equipamentoServico.setMANUTATUALRHORAS(equipamento.getEQP_HORIMETROINICIAL());
                equipamentoServico.setSRV_FREQUENCIADIAS(pms.getPMS_FREQDIAS());
                equipamentoServico.setSRV_FREQUENCIAHORAS(pms.getPMS_FREQHORAS());
                equipamentoServico.setREALIZADO(true);

                for (PadraoManutencaoPeca pmPeca : pms.getPdsPecas()) {
                    PecaEqs pecaEqs = new PecaEqs();
                    Peca p = new PecaFacade().findPecaByFab(pmPeca.getFactoryPeca().getPEC_ID());
                    if (p != null) {
                        pecaEqs.setPeca(p);
                        pecaEqs.setQuantidade(pmPeca.getQuantidade());
                        pecaEqs.setEqs(equipamentoServico);
                        equipamentoServico.getEquipamentosPecas().add(pecaEqs);
                    }
                }
                equipamento.getServicos().add(equipamentoServico);
                equipamentoServico = null;
            }
        }
        selectedServicos = null;
        displayInfoMessageToUser("INFO: selecionado padrao " + getPadraoManutencao().getFactoryModelo().getMOD_NOME());
    }

    public void carregaPadrao() {
        padraoManutencao = getPdmFacade().findPdmByModelo(equipamento.getModelo().getFab());
    }

    public void addServicoToEquipamento() {

        if (selectedServicos.isEmpty()) {
            keepDialogOpen();
            displayInfoMessageToUser("Selecione ao menos um serviço");
            return;
        }
        for (Servico srv : selectedServicos) {
            EquipamentoServico eqs = new EquipamentoServico(equipamento);
            eqs.setServico(srv);
            eqs.setMANUTATUAL(equipamento.getEQP_DATAPARTIDA());
            eqs.setMANUTATUALRHORAS(equipamento.getEQP_HORIMETROINICIAL());
            eqs.setSRV_FREQUENCIADIAS(srv.getSRV_FREQDIAS());
            eqs.setSRV_FREQUENCIAHORAS(srv.getSRV_FREQHORAS());
            eqs.setREALIZADO(true);
            equipamento.getServicos().add(eqs);
        }
        selectedServicos = null;
        closeDialog();
    }

    public void addPecaToServico() {
        if (selectedPecas.isEmpty()) {
            keepDialogOpen();
            displayInfoMessageToUser("Selecione ao menos uma Peça");
            return;
        }
        for (Peca peca : selectedPecas) {
            PecaEqs pqs = new PecaEqs();
            pqs.setEqs(equipamentoServico);
            pqs.setPeca(peca);
            pqs.setQuantidade(1);
            equipamentoServico.getEquipamentosPecas().add(pqs);
        }
        selectedPecas = null;
        closeDialog();
    }

    public void validaDelete() {
        if (selectedEquipamentos.isEmpty()) {
            displayInfoMessageToUser("Selecione ao menos um equipamento.");
        } else {
            RequestContext.getCurrentInstance().execute("eqpDeleteDialogWidget.show()");
        }
    }

    public void validaFechar() {
        if (equipamento.equals(equipamentoCompare)) {
            showEquipamento();
        } else {
            RequestContext.getCurrentInstance().execute("eqpCloseDialogWidget.show()");
        }
    }

    public void deleteEquipamento() {
        for (Equipamento equipamento1 : selectedEquipamentos) {
            if (equipamento1.isFab()) {
                closeDialog();
                displayInfoMessageToUser(equipamento1.getCategoria().getCAT_NOME() + " não pode ser removido");
            } else {
                try {
                    getEquipamentoFacade().deleteEquipamento(equipamento1);
                    closeDialog();
                    displayInfoMessageToUser("Equipamento removido com sucesso.");
//                loadDogs();
//                resetDog();
                } catch (Exception e) {
                    displayErrorMessageToUser(equipamento1.getModelo().getMOD_NOME() + " não pode ser removido pois está em uso");
                    keepDialogOpen();
                    e.printStackTrace();
                }
            }
        }
    }

    public void buscar() {
        tipoLista = 1;
        getDtmEquipamentos();
    }

    public void onRowSelect(SelectEvent event) {

//        equipamentoServico.setPecas(getPecaFacade().listByEqs(equipamentoServico.getID()));
    }

    public Equipamento getEquipamento() {
        if (equipamento == null) {
            equipamento = new Equipamento();
        }
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public EquipamentoServico getEquipamentoServico() {
        if (equipamentoServico == null) {
            equipamentoServico = new EquipamentoServico(getEquipamento());
        }
        return equipamentoServico;
    }

    public void setEquipamentoServico(EquipamentoServico equipamentoServico) {
        this.equipamentoServico = equipamentoServico;
    }

    public PadraoManutencao getPadraoManutencao() {
        if (padraoManutencao == null) {
            padraoManutencao = new PadraoManutencao();
        }
        return padraoManutencao;
    }

    public void setPadraoManutencao(PadraoManutencao padraoManutencao) {
        this.padraoManutencao = padraoManutencao;
    }

    public List<Equipamento> getEquipamentos() {
        if (tipoLista == 0) {
            equipamentos = getEquipamentoFacade().listAll();
        } else {
            equipamentos = getEquipamentoFacade().listBusca(nomeBusca, getParceiroBusca(), categoriaBusca, modeloBusca, serieBusca, fabricanteBusca);
        }
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public List<Equipamento> getSelectedEquipamentos() {
        return selectedEquipamentos;
    }

    public void setSelectedEquipamentos(List<Equipamento> selectedEquipamentos) {
        this.selectedEquipamentos = selectedEquipamentos;
    }

    public void loadModelos() {
        modelos = new ModeloFacade().listAll();
    }

    public List<Modelo> getModelos() {
        if (modelos == null) {
            modelos = new ArrayList<>();
        }
        return modelos;
    }

    public void setModelos(List<Modelo> modelos) {
        this.modelos = modelos;
    }

    public void loadAllServicos() {
        System.out.println("Carrega todos os serviços \n \n \n");
        listAllServicos = new ServicoFacade().listAll();
        if (listAllServicos != null) {
            List<Servico> t = new ServicoFacade().listAll();
            for (Servico fs : t) {
                for (EquipamentoServico es : equipamento.getServicos()) {
                    if (es.getServico().getSRV_ID() == fs.getSRV_ID()) {
                        listAllServicos.remove(fs);
                    }
                }
            }
        }
    }

    public List<Servico> getListAllServicos() {
        if (listAllServicos == null) {
            listAllServicos = new ArrayList<>();
        }
        
        return listAllServicos;
    }

    public void setListAllServicos(List<Servico> listAllServicos) {
        this.listAllServicos = listAllServicos;
    }

    public void loadAllPecas() {
        if (getEquipamentoServico().getServico().getSRV_ID() != 0) {

            listAllPecas = new PecaFacade().listAll();
            if (listAllPecas != null) {
                List<Peca> p = new PecaFacade().listAll();
                for (Peca fs : p) {
                    for (PecaEqs es : getEquipamentoServico().getEquipamentosPecas()) {
                        if (Objects.equals(es.getPeca().getPEC_ID(), fs.getPEC_ID())) {
                            listAllPecas.remove(fs);
                        }
                    }
                }
            }
            RequestContext.getCurrentInstance().execute("srvAddPecaDialogWidget.show()");
        } else {
            displayInfoMessageToUser("Selecione um serviço na tabela para adicionar peças");
        }

    }

    public List<Peca> getListAllPecas() {
        if (listAllPecas == null) {
            listAllPecas = new ArrayList<>();
        }
        return listAllPecas;
    }

    public void setListAllPecas(List<Peca> listAllPecas) {
        this.listAllPecas = listAllPecas;
    }

    public List<Peca> getSelectedPecas() {
        return selectedPecas;
    }

    public void setSelectedPecas(List<Peca> selectedPecas) {
        this.selectedPecas = selectedPecas;
    }

    public void loadListaPdm() {
        listaPdm = new PadraoManutencaoFacade().listAll();
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

    public List<Servico> getSelectedServicos() {
        return selectedServicos;
    }

    public void setSelectedServicos(List<Servico> selectedServicos) {
        this.selectedServicos = selectedServicos;
    }

    public DataModel<Equipamento> getDtmEquipamentos() {
        dtmEquipamentos = new ListDataModel<>(getEquipamentos());
        if (dtmEquipamentos == null) {
            dtmEquipamentos = new ArrayDataModel<>();
        }
        return dtmEquipamentos;
    }

    public void setDtmEquipamentos(DataModel<Equipamento> dtmEquipamentos) {
        this.dtmEquipamentos = dtmEquipamentos;
    }

    public EquipamentoFacade getEquipamentoFacade() {

        equipamentoFacade = new EquipamentoFacade();
        return equipamentoFacade;
    }

    public void setEquipamentoFacade(EquipamentoFacade equipamentoFacade) {
        this.equipamentoFacade = equipamentoFacade;
    }

    public String getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(String tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

    public String getNomeBusca() {
        return nomeBusca;
    }

    public void setNomeBusca(String nomeBusca) {
        this.nomeBusca = nomeBusca;
    }

    public EquipamentoMedicaoFacade getEqmFacade() {
        if (eqmFacade == null) {
            eqmFacade = new EquipamentoMedicaoFacade();
        }
        return eqmFacade;
    }

    public void setEqmFacade(EquipamentoMedicaoFacade eqmFacade) {
        this.eqmFacade = eqmFacade;
    }

    public EquipamentoServicoFacade getEquipamentoServicoFacade() {
        if (equipamentoServicoFacade == null) {
            equipamentoServicoFacade = new EquipamentoServicoFacade();
        }

        return equipamentoServicoFacade;
    }

    public void setEquipamentoServicoFacade(EquipamentoServicoFacade equipamentoServicoFacade) {
        this.equipamentoServicoFacade = equipamentoServicoFacade;
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

    public int getTipoLista() {
        return tipoLista;
    }

    public void setTipoLista(int tipoLista) {
        this.tipoLista = tipoLista;
    }

    public Parceiro getParceiroBusca() {
        if (parceiroBusca == null) {
            parceiroBusca = new Parceiro();
        }
        return parceiroBusca;
    }

    public ParceiroFacade getParceiroFacade() {
        if (parceiroFacade == null) {
            parceiroFacade = new ParceiroFacade();
        }
        
        return parceiroFacade;
    }

    public void setParceiroFacade(ParceiroFacade parceiroFacade) {
        this.parceiroFacade = parceiroFacade;
    }

    public List<Parceiro> completeParceiros(String name) {
        List<Parceiro> queryResult = new ArrayList<>();

        
        List<Parceiro> parceiros = getParceiroFacade().listParceiros();

        for (Parceiro parc : parceiros) {
            if (parc.getPAR_RAZAO().toLowerCase().contains(name.toLowerCase())) {
                queryResult.add(parc);
            } else if (name.isEmpty()) {
                queryResult.add(parc);
            }
        }

        return queryResult;
    }

    public void setParceiroBusca(Parceiro parceiroBusca) {
        this.parceiroBusca = parceiroBusca;
    }

    public int getCategoriaBusca() {
        return categoriaBusca;
    }

    public void setCategoriaBusca(int categoriaBusca) {
        this.categoriaBusca = categoriaBusca;
    }

    public String getModeloBusca() {
        return modeloBusca;
    }

    public void setModeloBusca(String modeloBusca) {
        this.modeloBusca = modeloBusca;
    }

    public String getSerieBusca() {
        return serieBusca;
    }

    public void setSerieBusca(String serieBusca) {
        this.serieBusca = serieBusca;
    }

    public String getFabricanteBusca() {
        return fabricanteBusca;
    }

    public void setFabricanteBusca(String fabricanteBusca) {
        this.fabricanteBusca = fabricanteBusca;
    }

    public DataModel<EquipamentoServico> getDataServicos() {
        return dataServicos;
    }

    public void setDataServicos(DataModel<EquipamentoServico> dataServicos) {
        this.dataServicos = dataServicos;
    }

    public Equipamento getEquipamentoCompare() {
        return equipamentoCompare;
    }

    public void setEquipamentoCompare(Equipamento equipamentoCompare) {
        this.equipamentoCompare = equipamentoCompare;
    }

    public int getHorimetroInicial() {
        return horimetroInicial;
    }

    public void setHorimetroInicial(int horimetroInicial) {
        this.horimetroInicial = horimetroInicial;
    }

    public int getColumnsPartida() {
        if (equipamento.getEQP_ID() == 0) {
            columnsPartida = 3;
        } else {
            columnsPartida = 2;
        }
        return columnsPartida;
    }

    public void setColumnsPartida(int columnsPartida) {
        this.columnsPartida = columnsPartida;
    }

}
