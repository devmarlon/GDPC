package com.gardnerdenver.bean;

import com.gardnerdenver.dao.EquipamentoServicoDAO;
import com.gardnerdenver.facade.EquipamentoFacade;
import com.gardnerdenver.facade.EquipamentoMedicaoFacade;
import com.gardnerdenver.facade.EquipamentoServicoFacade;
import com.gardnerdenver.facade.FactoryCategoriaFacade;
import com.gardnerdenver.facade.HistoricoFacade;
import com.gardnerdenver.facade.MunicipioFacade;
import com.gardnerdenver.facade.ParceiroFacade;
import com.gardnerdenver.facade.PecaEqsFacade;
import com.gardnerdenver.facade.PecaFacade;
import com.gardnerdenver.facade.ServicoFacade;
import com.gardnerdenver.model.Equipamento;
import com.gardnerdenver.model.EquipamentoMedicao;
import com.gardnerdenver.model.EquipamentoServico;
import com.gardnerdenver.model.Funcionario;
import com.gardnerdenver.model.Historico;
import com.gardnerdenver.model.Parceiro;
import com.gardnerdenver.model.Peca;
import com.gardnerdenver.model.PecaEqs;
import com.gardnerdenver.model.Servico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ViewScoped
//@SessionScoped
@ManagedBean(name = "indexBean")
public class IndexBean extends AbstractMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private EquipamentoServicoDAO equipamentoServicoDAO = new EquipamentoServicoDAO();

    private Equipamento equipamento;
    private EquipamentoMedicao equipamentoMedicao;
    private EquipamentoServico equipamentoServico;
    private EquipamentoServico equipamentoServicoAux;
    private EquipamentoServico equipamentoServicoDelete;
    private EquipamentoServico equipamentoServicoSelected;
    private Servico servicoSelected;
    private PecaEqs pecaDelete;
    private Parceiro parceiro;
    private Parceiro parceiroSelected;
    private Servico servico;
    private Peca peca;
    private Peca pecaAux;
    private PecaEqs pecaEqs;
    private PecaEqs pecaEqsAux;
    private PecaEqs pecaEqsSelected;
    private Peca fPart;
    private Historico historico;

    //facade
    private ParceiroFacade parceiroFacade;
    private ServicoFacade servicoFacade;
    private EquipamentoFacade equipamentoFacade;
    private EquipamentoServicoFacade equipamentoServicoFacade;
    private EquipamentoMedicaoFacade eqmFacade;
    private PecaFacade pecaFacade;
    private PecaEqsFacade pecaEqsFacade;
    private FactoryCategoriaFacade catFacade;
    private HistoricoFacade historicoFacade;
    //geral
    private int searchCode;
    private int searchCodeCli;
    private String lblBotaoServico = "Adicionar";
    private String icoBotaoServico = "ui-icon-plus";
    private String searchCodePart;
    private String focus = "pCodCliente";
    private String messageValidaManut = "";
    private boolean naLista = false;
    private boolean naListaPart = false;
    private boolean boolDlgPar = false;
    private boolean dlgServico = false;
//    private Date EQM_DATAATUALIZACAO;
    //lists
    private List<Parceiro> listaParceiros;
    private List<EquipamentoServico> listServicosHistorico;
    private List<EquipamentoServico> listServicos;
    private List<Historico> listHistorico;
    private List<Servico> servicos;
    private List<Peca> pecas;
    //datamodels
    private DataModel<Parceiro> dtmParceiros;
    private DataModel<Peca> dtmPecas;
//    private DataModel<EquipamentoServico> dataServicos;
    private DataModel<EquipamentoServico> dataServicosHistorico;

    private DataModel<Servico> dtmServicos;
    //
    private boolean rdrDlgObs = false;

    public void createPeca() {
        if (getPecaEqsAux().getPES_ID() == 0) {
            getPecaEqsFacade().createPeca(pecaEqsAux);
            displayInfoMessageToUser("Criado com Sucesso");
        } else {
            getPecaEqsFacade().updatePeca(getPecaEqsAux());
            displayInfoMessageToUser("Alterado com Sucesso");
        }
        setPecaEqs(null);
//            equipamento = getEquipamentoFacade().findEquipamento(equipamento.getEQP_ID());
//            parceiro = getParceiroFacade().findParceiro(parceiro.getPAR_ID());
//        setEquipamentoServicoAux(getEquipamentoServicoFacade().findEquipamento(getEquipamentoServicoAux().getID_EQS()));
        setPecaEqsAux(null);
        setSearchCodePart("");
        loadEquip();
//        return null;
    }

    public void removerEqs() {
        try {
            getEquipamentoServicoFacade().deleteEquipamentoServicoAllHist(equipamentoServicoDelete);
            closeDialog();
            displayInfoMessageToUser("Deletado com Sucesso.");
            loadEquip();
            equipamentoServicoDelete = null;
        } catch (Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("Ops, we could not remove. Try again later");
            e.printStackTrace();
        }
    }

    public void removerPeca() {
        try {
            getPecaEqsFacade().deletePecaServico(pecaDelete);
            equipamentoServicoAux.setEquipamentosPecas(getPecaEqsFacade().findPecaEqsByEqs(equipamentoServicoAux.getID_EQS()));
            closeDialog();
            displayInfoMessageToUser("Removido com Sucesso.");
//            loadEquip();
            equipamentoServicoDelete = null;
        } catch (Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("Ops, we could not remove. Try again later");
            e.printStackTrace();
        }
    }

    public void confirmRemoverEqs() {
        equipamentoServicoDelete = equipamento.getDataServicos().getRowData();
    }

    public void createManutencao() {

        if (equipamentoServicoAux.getMANUTATUALRHORAS() <= 0) {
            displayInfoMessageToUser("Horimetro atual deve ser maior que 0 (Zero)");
            return;
        }

        if (!validaManutencaoB(equipamentoServicoAux.getMANUTATUAL(), equipamentoServicoAux.getMANUTATUALRHORAS(), equipamento.getEQP_ID())) {
            displayInfoMessageToUser(getMessageValidaManut());
            return;
        }

        if (getEquipamentoServicoAux().getMANUTATUAL().before(equipamento.getEQP_DATAPARTIDA())) {
            displayInfoMessageToUser("A data atual da manutenção não deve ser menor que a data da partida do equipamento. Selecione uma data posterior");
            return;
        }

        try {
            equipamentoServicoAux.setMANUTANTERIORHORAS(equipamentoServicoAux.getMANUTANTERIORHORAS_TEMP());
            equipamentoServicoAux.setMANUTANTERIOR(equipamentoServicoAux.getMANUTANTERIOR_TEMP());
            equipamentoServicoAux.setREALIZADO(true);
            List<PecaEqs> lstPecasTmp = equipamentoServicoAux.getEquipamentosPecas();
            equipamentoServicoAux.setEquipamentosPecas(new ArrayList<PecaEqs>());
            if (lstPecasTmp != null && !lstPecasTmp.isEmpty()) {
                for (PecaEqs peca1 : lstPecasTmp) {
                    equipamentoServicoAux.getEquipamentosPecas().add(peca1);
                }
            }

            equipamentoServicoAux.setID_EQS(0);
            List<PecaEqs> listPqsTemp = equipamentoServicoAux.getEquipamentosPecas();
            equipamentoServicoAux.setEquipamentosPecas(null);
//            for (PecaEqs pecaEqs1 : listPqsTemp) {
//                pecaEqs1.setEqs(equipamentoServicoAux);
//                listPqsTemp.add(pecaEqs1);
//            }
            getEquipamentoServicoFacade().addServEquip(equipamentoServicoAux);
            EquipamentoMedicao eqm = new EquipamentoMedicao();
            eqm.setEQM_DATAATUALIZACAO(equipamentoServicoAux.getMANUTATUAL());
            eqm.setEQM_HORASTOTAIS(equipamentoServicoAux.getMANUTATUALRHORAS());
            eqm.setEquipamento(equipamento);
            getEqmFacade().createEqm(eqm);

            for (PecaEqs p : listPqsTemp) {
                p.setEqs(equipamentoServicoAux);
                getPecaEqsFacade().createPeca(p);
            }
//            closeDialog();

            displayInfoMessageToUser("Criado com Sucesso");

//            loadPersons();
//            resetPerson();
        } catch (Exception e) {
//            keepDialogOpen();
            displayErrorMessageToUser("Não foi possivel criar manutenção");
            e.printStackTrace();
        } finally {
            equipamento = getEquipamentoFacade().findEquipamento(equipamento.getEQP_ID());
            equipamento.getEqpMedicao();
            parceiro = getParceiroFacade().findParceiro(parceiro.getPAR_ID());
        }
        loadEquip();
    }

    public boolean validaManutencaoB(Date dataManutAtual, int horimetroManutAtual, int eqpId) {
        List<EquipamentoMedicao> listaMedicao = getEqmFacade().listByEqp(eqpId);
        setMessageValidaManut("");
        for (EquipamentoMedicao em : listaMedicao) {

            if (dataManutAtual.compareTo(em.getEQM_DATAATUALIZACAO()) > 0) { //AFTER
                DateTime dtManut = new DateTime(dataManutAtual);
                DateTime dtMed = new DateTime(em.getEQM_DATAATUALIZACAO());
                int period = Days.daysBetween(dtMed, dtManut).getDays() + 1;
                int hrs = 24 * period;

                if (horimetroManutAtual < em.getEQM_HORASTOTAIS() && em.getEQM_HORASTOTAIS() > 0) {
                    setMessageValidaManut("Horimetro da manutenção atual deve ser maior que a medição de horas do equipamento na data.");
                    return false;
                } else if (horimetroManutAtual > (em.getEQM_HORASTOTAIS() + hrs) && em.getEQM_HORASTOTAIS() > 0) {
                    setMessageValidaManut("Horimetro da manutenção atual excede as horas da medição do equipamento.");
                    return false;
                }
            } else if (dataManutAtual.compareTo(em.getEQM_DATAATUALIZACAO()) < 0) { //BEFORE
                if (horimetroManutAtual > em.getEQM_HORASTOTAIS() && em.getEQM_HORASTOTAIS() > 0) {
                    setMessageValidaManut("Horimetro da manutenção atual deve ser menor que a medição de horas do equipamento na data.");
                    return false;
                }
            } else if (dataManutAtual.compareTo(em.getEQM_DATAATUALIZACAO()) == 0) { //EQUAL
                if (horimetroManutAtual - em.getEQM_HORASTOTAIS() >= 24
                        || horimetroManutAtual - em.getEQM_HORASTOTAIS() <= -24 && em.getEQM_HORASTOTAIS() > 0) {
                    setMessageValidaManut("Horimetro da manutenção atual excede as horas da medição do equipamento.");
                    return false;
                } else if (horimetroManutAtual == em.getEQM_HORASTOTAIS() && em.getEQM_HORASTOTAIS() > 0) {
                    setMessageValidaManut("Horimetro da manutenção atual é igual as horas da medição do equipamento.");
                    return false;
                }
            } else {
                setMessageValidaManut("Erro");
            }
        }

        return true;
    }

//    public void listenerEquipamento() {
//        equipamento.ge
//    }
    public List<Parceiro> completeParceiros(String name) {
        List<Parceiro> queryResult = new ArrayList<>();

        if (listaParceiros == null) {
            if (parceiroFacade == null) {
                parceiroFacade = new ParceiroFacade();
            }
            listaParceiros = parceiroFacade.listParceiros();
        }

//        parceiros.removeAll(personWithDogs.getDogs());
//        if (name.isEmpty()) {
//            queryResult = parceiros;
//        } else {
        for (Parceiro parc : listaParceiros) {
            if (parc.getPAR_RAZAO().toLowerCase().contains(name.toLowerCase())) {
                queryResult.add(parc);
            } else if (name.isEmpty()) {
                queryResult.add(parc);
            }
        }
//        }

        return queryResult;
    }

    public void showDlgObs() {
        setRdrDlgObs(true);
    }

    public void showIndex() {
        setParceiro(null);
        setEquipamento(null);
        setListServicos(null);
        setEquipamentoServico(null);
        setEquipamentoServicoAux(null);
        setPecaAux(null);
        setSearchCodeCli(0);
        redirect("/pages/protected/distributor/index.xhtml");
    }

    public String atualizaEquipamento() {

        setFocus("eqp");
        try {
            getEquipamentoFacade().updateEquipamento(equipamento);
            displayInfoMessageToUser("Equipamento atualizado com sucesso!");
        } catch (Exception e) {
            displayErrorMessageToUser("Não foi possivel atualizar o equipamento!");
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }

        equipamento = getEquipamentoFacade().findEquipamento(equipamento.getEQP_ID());
//        equipamentoMedicao = equipamento.getEqpMedicao();
        loadEquip();

        return null;
    }

    public void salvaMedicao() {
        //        if (validaManutencaoB(equipamento.getEqpMedicao().getEQM_DATAATUALIZACAO(), equipamento.getEqpMedicao().getEQM_HORASTOTAIS(), equipamento.getEQP_ID())
//                && equipamentoMedicao.getEQM_DATAATUALIZACAO().after(equipamento.getEQP_DATAPARTIDA())) {
        if (equipamento.getEqpMedicao().getEQM_DATAATUALIZACAO().after(equipamento.getEQP_DATAPARTIDA())) {
            try {
                equipamento.getEqpMedicao().setEQM_ID(0);
                getEqmFacade().createEqm(equipamento.getEqpMedicao());
                displayInfoMessageToUser("Horímetro cadastrado com sucesso");
            } catch (Exception e) {
                displayErrorMessageToUser("Não foi possivel atualizar o equipamento!");
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
            }
        }
        equipamento = getEquipamentoFacade().findEquipamento(equipamento.getEQP_ID());
    }

    public void loadEquip() {
        if (equipamento != null && equipamento.getEQP_ID() != 0) {

//            dataServicos = new ListDataModel<>(getEquipamentoServicoFacade().listByEqpId(equipamento.getEQP_ID()));
            listServicos = getEquipamentoServicoFacade().listByEqpId(equipamento.getEQP_ID());
            //listServicos = equipamentoServicoDAO.findListByEqp3(equipamento.getEQP_ID());
            loadListServicosHistorico();
            getDataServicosHistorico();
            //dataServicosHistorico = new ListDataModel<>(getEquipamentoServicoFacade().listHistByEqpId(equipamento.getEQP_ID()));
            setSearchCode(0);
//            equipamentoMedicao = new EquipamentoMedicao(equipamento.getEqpMedicao());
//            EQM_DATAATUALIZACAO = new Date(equipamentoMedicao.getEQM_DATAATUALIZACAO().getTime());

        }
        setEquipamentoServicoAux(null);
        setEquipamentoServico(null);
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
            equipamentoServico = new EquipamentoServico(equipamento);
        }
        return equipamentoServico;
    }

    public void setEquipamentoServico(EquipamentoServico equipamentoServico) {
        this.equipamentoServico = equipamentoServico;
    }

    public EquipamentoMedicao getEquipamentoMedicao() {
        if (equipamentoMedicao == null) {
            equipamentoMedicao = new EquipamentoMedicao();
        }

        return equipamentoMedicao;
    }

    public void setEquipamentoMedicao(EquipamentoMedicao equipamentoMedicao) {
        this.equipamentoMedicao = equipamentoMedicao;
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

    public EquipamentoMedicaoFacade getEqmFacade() {
        if (eqmFacade == null) {
            eqmFacade = new EquipamentoMedicaoFacade();
        }
        return eqmFacade;
    }

    public void setEqmFacade(EquipamentoMedicaoFacade eqmFacade) {
        this.eqmFacade = eqmFacade;
    }

    public PecaFacade getPecaFacade() {
        if (pecaFacade == null) {
            pecaFacade = new PecaFacade();
        }

        return pecaFacade;
    }

    public void setPecaFacade(PecaFacade pecaFacade) {
        this.pecaFacade = pecaFacade;
    }

    public DataModel<Peca> getDtmPecas() {
        if (getEquipamento().getEQP_ID() != 0) {
            dtmPecas = new ListDataModel(getPecas());
        }

        return dtmPecas;
    }

    public void setDtmPecas(DataModel<Peca> dtmPecas) {
        this.dtmPecas = dtmPecas;
    }

    public List<Peca> getPecas() {
        if (pecas == null) {
            pecas = getPecaFacade().listAll();
        }

        return pecas;
    }

    public void setPecas(List<Peca> pecas) {
        this.pecas = pecas;
    }

    public void loadAllServicos() {
        getListServicos();
        RequestContext.getCurrentInstance().execute("manutServicesDialog.show()");
    }

    public void loadAllPecas() {
        if (getEquipamentoServico().getServico().getSRV_ID() != 0) {

            if (getPecas() != null) {
                List<Peca> p = new PecaFacade().listAll();
                for (Peca fs : p) {
                    for (PecaEqs es : getEquipamentoServico().getEquipamentosPecas()) {
                        if (Objects.equals(es.getPeca().getPEC_ID(), fs.getPEC_ID())) {
                            pecas.remove(fs);
                        }
                    }
                }
            }
            RequestContext.getCurrentInstance().execute("manutPecasDialog.show()");
        } else {
            displayInfoMessageToUser("Selecione um serviço na tabela para adicionar peças");
        }
    }

    public PecaEqsFacade getPecaEqsFacade() {
        if (pecaEqsFacade == null) {
            pecaEqsFacade = new PecaEqsFacade();
        }

        return pecaEqsFacade;
    }

    public void setPecaEqsFacade(PecaEqsFacade pecaEqsFacade) {
        this.pecaEqsFacade = pecaEqsFacade;
    }

    public FactoryCategoriaFacade getCatFacade() {
        if (catFacade == null) {
            catFacade = new FactoryCategoriaFacade();
        }
        return catFacade;
    }

    public void setCatFacade(FactoryCategoriaFacade catFacade) {
        this.catFacade = catFacade;
    }

    public HistoricoFacade getHistoricoFacade() {
        if (historicoFacade == null) {
            historicoFacade = new HistoricoFacade();
        }
        return historicoFacade;
    }

    public void setHistoricoFacade(HistoricoFacade historicoFacade) {
        this.historicoFacade = historicoFacade;
    }

    public void loadListaHistoricoByParceiro() {
        if (getParceiro().getPAR_ID() != 0) {
            listHistorico = getHistoricoFacade().listByParceiro(getParceiro().getPAR_ID());
            if (listHistorico != null && !listHistorico.isEmpty()) {
                historico = listHistorico.get(listHistorico.size() - 1);
            }
        }
    }

    public List<Historico> getListHistorico() {
        if (listHistorico == null) {
            loadListaHistoricoByParceiro();
        }
        return listHistorico;
    }

    public void setListHistorico(List<Historico> listHistorico) {
        this.listHistorico = listHistorico;
    }

    public Parceiro getParceiro() {
        if (parceiro == null) {
            parceiro = new Parceiro();
        }
        return parceiro;
    }

    public void setParceiro(Parceiro parceiro) {
        setSearchCodeCli(getParceiro().getPAR_ID());
        this.parceiro = parceiro;
    }

    public Parceiro getParceiroSelected() {
        return parceiroSelected;
    }

    public void setParceiroSelected(Parceiro parceiroSelected) {
        this.parceiroSelected = parceiroSelected;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
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

    public Peca getPecaAux() {
        if (pecaAux == null) {
            pecaAux = new Peca();
        }
        return pecaAux;
    }

    public void setPecaAux(Peca pecaAux) {
        this.pecaAux = pecaAux;
    }

    public EquipamentoServico getEquipamentoServicoDelete() {
        return equipamentoServicoDelete;
    }

    public void setEquipamentoServicoDelete(EquipamentoServico equipamentoServicoDelete) {
        this.equipamentoServicoDelete = equipamentoServicoDelete;
    }

    public EquipamentoServico getEquipamentoServicoSelected() {
        if (equipamentoServicoSelected == null) {
            equipamentoServicoSelected = new EquipamentoServico();
        }
        return equipamentoServicoSelected;
    }

    public void setEquipamentoServicoSelected(EquipamentoServico equipamentoServicoSelected) {
        this.equipamentoServicoSelected = equipamentoServicoSelected;
    }

    public Servico getServicoSelected() {
        if (servicoSelected == null) {
            servicoSelected = new Servico();
        }
        return servicoSelected;
    }

    public void setServicoSelected(Servico servicoSelected) {
        this.servicoSelected = servicoSelected;
    }

    public PecaEqs getPecaDelete() {
        return pecaDelete;
    }

    public void setPecaDelete(PecaEqs pecaDelete) {
        this.pecaDelete = pecaDelete;
    }

    public PecaEqs getPecaEqs() {
        if (pecaEqs == null) {
            pecaEqs = new PecaEqs();
        }
        return pecaEqs;
    }

    public void setPecaEqs(PecaEqs pecaEqs) {
        this.pecaEqs = pecaEqs;
    }

    public PecaEqs getPecaEqsAux() {
        if (pecaEqsAux == null) {
            pecaEqsAux = new PecaEqs();
        }
        return pecaEqsAux;
    }

    public void setPecaEqsAux(PecaEqs pecaEqsAux) {
        this.pecaEqsAux = pecaEqsAux;
    }

    public Peca getfPart() {
        return fPart;
    }

    public void setfPart(Peca fPart) {
        this.fPart = fPart;
    }

    public Historico getHistorico() {
        if (historico == null) {
            historico = new Historico();
        }
        
        return historico;
    }

    public void setHistorico(Historico historico) {
        this.historico = historico;
    }

    public void resetHistorico() {
        historico = new Historico();
        Funcionario f = (Funcionario) getRequest().getSession().getAttribute("func");
        historico.setFuncionario(f);
        historico.setParceiro(parceiro);
        if (getEquipamento().getEQP_ID() != 0) {
            historico.setEquipamento(equipamento);
        }
    }

    public ParceiroFacade getParceiroFacade() {
//        if (parceiroFacade == null) {
        parceiroFacade = new ParceiroFacade();
//        }

        return parceiroFacade;
    }

    public void setParceiroFacade(ParceiroFacade parceiroFacade) {
        this.parceiroFacade = parceiroFacade;
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

    public EquipamentoFacade getEquipamentoFacade() {
        if (equipamentoFacade == null) {
            equipamentoFacade = new EquipamentoFacade();
        }

        return equipamentoFacade;
    }

    public void setEquipamentoFacade(EquipamentoFacade equipamentoFacade) {
        this.equipamentoFacade = equipamentoFacade;
    }

    public void onRowSelectParc(SelectEvent event) {
//        parceiro = dtmParceiros.getRowData();

//        reset();
//        setaParceiro(event);
        System.out.println("parc = " + parceiro.getPAR_RAZAO());
    }

    public void onRowSelect(SelectEvent event) {
        setSearchCode(getEquipamentoServico().getServico().getSRV_ID());

        equipamentoServicoAux = equipamentoServico;
        equipamentoServicoAux.setMANUTANTERIORHORAS_TEMP(equipamentoServicoAux.getMANUTATUALRHORAS());
        equipamentoServicoAux.setMANUTANTERIOR_TEMP(equipamentoServicoAux.getMANUTATUAL());
        peca = new Peca();
        pecaAux = new Peca();
        pecaEqs = new PecaEqs();
        pecaEqsAux = new PecaEqs();

        setSearchCodePart("0");

        setLblBotaoServico("Confirmar");
        setIcoBotaoServico("ui-icon-check");

//        equipamentoServico.setPecas(getPecaFacade().listByEqs(equipamentoServico.getID()));
    }

    public void onRowSelectParts(SelectEvent event) {
        setSearchCodePart(String.valueOf(getPecaEqs().getPeca().getCodigo()));

        setPecaEqsAux(getPecaEqs());
    }

    public void onRowUnselect(UnselectEvent event) {
    }

    public EquipamentoServico getEquipamentoServicoAux() {
        if (equipamentoServicoAux == null) {
            equipamentoServicoAux = new EquipamentoServico(equipamento);
        }

        return equipamentoServicoAux;
    }

    public void setEquipamentoServicoAux(EquipamentoServico equipamentoServicoAux) {
        this.equipamentoServicoAux = equipamentoServicoAux;
    }

    public void showDlgService() {
        setDlgServico(true);
    }

    public void selectServico() {
        if (getServicoSelected().getSRV_ID() == 0) {
            displayInfoMessageToUser("Selecione um Serviço");
            keepDialogOpen();
            return;
        }
//        setEquipamentoServico(equipamentoServico);
        setSearchCode(getServicoSelected().getSRV_ID());
//        setaParceiro(null);
        setEquipamentoServicoAux(null);
        setaEquipamentoServico(null);
        closeDialog();
    }

    public void setaEquipamentoServico(SelectEvent event) {
        if (String.valueOf(getSearchCode()).isEmpty() || getSearchCode() == 0) {
            setEquipamentoServicoAux(null);
            setEquipamentoServico(null);
            return;
        }

        setEquipamentoServicoAux(null);

        if (event != null) {
            getEquipamentoServicoAux().setServico((Servico) event.getObject());
            setSearchCode(getEquipamentoServicoAux().getServico().getSRV_ID());
            for (EquipamentoServico es : getListServicos()) {
                setNaLista(false);
                if (es.getServico().getSRV_ID() == getSearchCode()) {
                    setNaLista(true);
                    break;
                }
            }
        } else {
            if (getSearchCode() != 0) {
                for (EquipamentoServico es : getListServicos()) {
                    setNaLista(false);
                    if (es.getServico().getSRV_ID() == getSearchCode()) {
                        setEquipamentoServicoAux(es);
                        setSearchCode(getEquipamentoServicoAux().getServico().getSRV_ID());
                        setNaLista(true);
                        break;
                    }
                }
                if (!naLista) {
                    getEquipamentoServicoAux().setServico(getServicoFacade().findServico(searchCode));
                    getEquipamentoServicoAux().setMANUTATUAL(equipamento.getEQP_DATAPARTIDA());
                    if (getEquipamentoServicoAux().getServico() == null) {
                        displayInfoMessageToUser("Não há nenhum serviço cadastrado com o código informado");
                        setSearchCode(0);
                        return;
                    }
                }
            }
        }
        if (!naLista) {
            setLblBotaoServico("Adicionar");
            setIcoBotaoServico("ui-icon-plus");
        } else {
            setLblBotaoServico("Confirmar");
            setIcoBotaoServico("ui-icon-check");
        }
        setEquipamentoServico(getEquipamentoServicoAux());
//        System.out.println("eq" + getEquipamentoServicoAux().toString());
    }

    public void selectParceiro(ActionEvent e) {
//        if (parceiroSelected == null) {
//            displayInfoMessageToUser("Selecione um cliente");
//            keepDialogOpen();
//            return;
//        }
//        setParceiro(parceiroSelected);
        setSearchCodeCli(getParceiro().getPAR_ID());
//        setaParceiro(null);
        setEquipamentoServico(null);
        setEquipamentoServicoAux(null);
        setaParceiro(null);
//        closeDialog();
    }

    public void setaParceiro(SelectEvent event) {
        if ((String.valueOf(searchCodeCli).isEmpty() || getSearchCodeCli() == 0) && (event == null)) {
            parceiro = null;
            return;
        }

        if (event != null) {
            setSearchCodeCli(getParceiro().getPAR_ID());
        } else {
            if (getSearchCodeCli() != 0) {
                parceiro = getParceiroFacade().findParceiro(getSearchCodeCli());
                if (parceiro == null) {
                    displayInfoMessageToUser("Não há nenhum cliente com o código informado");
                }
            } else {
                parceiro = null;
            }
        }

        MunicipioFacade munFacade = new MunicipioFacade();
        getParceiro().setMunicipio(munFacade.findMunicipio(getParceiro().getPAR_CIDADE()));

        if (getEquipamento().getEQP_ID() != 0) {
            equipamento = new Equipamento();
        }
        equipamentoServicoAux = null;
        setSearchCode(0);
        setSearchCodeCli(getParceiro().getPAR_ID());
        if (!parceiro.getEquipamentos().isEmpty()) {
            equipamento = getEquipamentoFacade().findEquipamento(parceiro.getEquipamentos().get(0).getEQP_ID());
        }
        loadEquip();
        if (parceiro.getPAR_ID() != 0) {
            loadListaHistoricoByParceiro();
        }
    }

    public void selectPeca() {
        if (pecaEqsSelected.getPeca() == null) {
            displayInfoMessageToUser("Selecione uma Peca");
            keepDialogOpen();
            return;
        }
//        setEquipamentoServico(equipamentoServico);
        setSearchCodePart(pecaEqsSelected.getPeca().getCodigo());
//        setaParceiro(null);
        setPecaEqsAux(null);
        setaPart(null);
        closeDialog();
    }

    public void setaPart(SelectEvent event) {
        if ((String.valueOf(searchCodePart).isEmpty() || getSearchCodePart().trim().isEmpty()) && (event == null)) {
            setPecaEqsAux(null);
            return;
        }

        setPecaEqsAux(null);

        if (event != null) {
            fPart = (Peca) event.getObject();
            getPecaEqsAux().setEqs(getEquipamentoServicoAux());
            getPecaEqsAux().setPeca(fPart);
            getPecaEqsAux().setQuantidade(1);
            setSearchCodePart(getPecaEqsAux().getPeca().getCodigo());
            setPecaEqs(getPecaEqsAux());
        } else {
            if (!getSearchCodePart().equalsIgnoreCase("0")) {
                for (PecaEqs pc : getEquipamentoServicoAux().getEquipamentosPecas()) {
                    setNaListaPart(false);
                    if (pc.getPeca().getCodigo().equalsIgnoreCase(getSearchCodePart())) {
                        setPecaEqsAux(pc);

                        setSearchCodePart(getPecaEqsAux().getPeca().getCodigo());
                        setNaListaPart(true);
                        break;
                    }
                }
                if (!naListaPart) {
                    Peca fPartT = getPecaFacade().findPecaByCod(searchCodePart);
                    if (fPartT == null) {
                        displayInfoMessageToUser("Não há nenhum peça cadastrada com o código informado");
                    }
                    getPecaEqsAux().setEqs(getEquipamentoServicoAux());
                    getPecaEqsAux().setPeca(fPartT);
                    getPecaEqsAux().setQuantidade(1);
//                    getPecaAux().setFactoryPart(fPartT);
//                    getPecaAux().setPart_id(fPartT.getId());
//                    getPecaAux().setEquipamentoservico(getEquipamentoServicoAux());
//                    getPecaAux().setQuantidade(1d);
                    setPecaAux(fPartT);
                }
            }
        }
        setPecaEqs(getPecaEqsAux());
    }

    public void createHistorico() {
        if (historico.getHIS_COMENTARIO().trim().isEmpty()) {
            keepDialogOpen();
            displayInfoMessageToUser("O texto de contato não pode ser vazio.");
            return;
        }

        try {
            getHistoricoFacade().create(historico);
            closeDialog();
            displayInfoMessageToUser("Salvo com sucesso!");
            loadListaHistoricoByParceiro();
            resetHistorico();
        } catch (Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("Ops, we could not create. Try again later");
            e.printStackTrace();
        }
    }

    public int getSearchCode() {
        return searchCode;
    }

    public void setSearchCode(int searchCode) {
        this.searchCode = searchCode;
    }

    public int getSearchCodeCli() {
        return searchCodeCli;
    }

    public void setSearchCodeCli(int searchCodeCli) {
        this.searchCodeCli = searchCodeCli;
    }

    public String getLblBotaoServico() {
        return lblBotaoServico;
    }

    public void setLblBotaoServico(String lblBotaoServico) {
        this.lblBotaoServico = lblBotaoServico;
    }

    public String getIcoBotaoServico() {
        return icoBotaoServico;
    }

    public void setIcoBotaoServico(String icoBotaoServico) {
        this.icoBotaoServico = icoBotaoServico;
    }

    public String getSearchCodePart() {
        return searchCodePart;
    }

    public void setSearchCodePart(String searchCodePart) {
        this.searchCodePart = searchCodePart;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public String getMessageValidaManut() {
        return messageValidaManut;
    }

    public void setMessageValidaManut(String messageValidaManut) {
        this.messageValidaManut = messageValidaManut;
    }

    public List<Parceiro> getListaParceiros() {
//        if (listaParceiros == null) {
//            if (parceiroFacade == null) {
//                parceiroFacade = new ParceiroFacade();
//            }
//            listaParceiros = parceiroFacade.listParceiros();
//        }
        return listaParceiros;
    }

    public void setListaParceiros(List<Parceiro> listaParceiros) {
        this.listaParceiros = listaParceiros;
    }

    public void loadDtmParceiros() {
        dtmParceiros = new ListDataModel<>(getParceiroFacade().listParceiros());
    }

    public DataModel<Parceiro> getDtmParceiros() {
        if (dtmParceiros == null) {
            loadDtmParceiros();
        }

        return dtmParceiros;
    }

    public void setDtmParceiros(DataModel<Parceiro> dtmParceiros) {
        this.dtmParceiros = dtmParceiros;
    }

    public boolean isRdrDlgObs() {
        return rdrDlgObs;
    }

    public void setRdrDlgObs(boolean rdrDlgObs) {
        this.rdrDlgObs = rdrDlgObs;
    }

//    public DataModel<EquipamentoServico> getDataServicos() {
////        dataServicos = new ListDataModel<>(getEquipamentoServicoFacade().listByEqpId(getEquipamento().getEQP_ID()));
//        return dataServicos;
//    }
//
//    public void setDataServicos(DataModel<EquipamentoServico> dataServicos) {
//        this.dataServicos = dataServicos;
//    }
    public DataModel<EquipamentoServico> getDataServicosHistorico() {
        if (dataServicosHistorico == null) {
            dataServicosHistorico = new ListDataModel<>(getListServicosHistorico());
        }
        return dataServicosHistorico;
    }

    public void setDataServicosHistorico(DataModel<EquipamentoServico> dataServicosHistorico) {
        this.dataServicosHistorico = dataServicosHistorico;
    }

    public DataModel<Servico> getDtmServicos() {
        if (getParceiro().getPAR_ID() != 0) {
            dtmServicos = new ListDataModel(getServicos());
        }

        return dtmServicos;
    }

    public void setDtmServicos(DataModel<Servico> dtmServicos) {
        this.dtmServicos = dtmServicos;
    }

    public List<Servico> getServicos() {
        if (servicos == null) {
            servicos = getServicoFacade().listAll();
        }

        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public List<EquipamentoServico> getListServicos() {
        if (listServicos == null) {
            listServicos = new ArrayList<>();
        }
        return listServicos;
    }

    public void setListServicos(List<EquipamentoServico> listServicos) {
        this.listServicos = listServicos;
    }

    public void loadListServicosHistorico() {
        if (getEquipamento().getEQP_ID() != 0) {
            listServicosHistorico = getEquipamentoServicoFacade().listHistByEqpId(equipamento.getEQP_ID());
            System.out.println("Tamanho Lista Historico = " + getListServicosHistorico().size());
        }
    }

    public List<EquipamentoServico> getListServicosHistorico() {

        if (listServicosHistorico == null) {
            listServicosHistorico = new ArrayList<>();
        }
        return listServicosHistorico;
    }

    public void setListServicosHistorico(List<EquipamentoServico> listServicosHistorico) {
        this.listServicosHistorico = listServicosHistorico;
    }

    public boolean isNaLista() {
        return naLista;
    }

    public void setNaLista(boolean naLista) {
        this.naLista = naLista;
    }

    public boolean isNaListaPart() {
        return naListaPart;
    }

    public void setNaListaPart(boolean naListaPart) {
        this.naListaPart = naListaPart;
    }

    public boolean isBoolDlgPar() {
        return boolDlgPar;
    }

    public void setBoolDlgPar(boolean boolDlgPar) {
        this.boolDlgPar = boolDlgPar;
    }

    public boolean isDlgServico() {
        return dlgServico;
    }

    public void setDlgServico(boolean dlgServico) {
        this.dlgServico = dlgServico;
    }

    public PecaEqs getPecaEqsSelected() {
        if (pecaEqsSelected == null) {
            pecaEqsSelected = new PecaEqs();
        }
        return pecaEqsSelected;
    }

    public void setPecaEqsSelected(PecaEqs pecaEqsSelected) {
        this.pecaEqsSelected = pecaEqsSelected;
    }

    public List<Peca> completePeca(String name) {
        List<Peca> queryResult = new ArrayList<>();

//        parceiros.removeAll(personWithDogs.getDogs());
        for (Peca fP : getPecas()) {
            if (fP.getDescricao().toLowerCase().contains(name.toLowerCase())) {
                queryResult.add(fP);
            }
        }

        return queryResult;
    }

    public List<Servico> completeServico(String name) {
        List<Servico> queryResult = new ArrayList<>();

        for (Servico serv : getServicos()) {
            if (serv.getSRV_DESCRICAO().toLowerCase().contains(name.toLowerCase())) {
                queryResult.add(serv);
            }
        }

        return queryResult;
    }
}
