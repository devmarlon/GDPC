package com.gardnerdenver.bean;

//import com.varkon.entity.Caixa;
import com.gardnerdenver.dao.MovimentoDao;
import com.gardnerdenver.dao.MovimentoItemDao;
import com.gardnerdenver.facade.EquipamentoFacade;
import com.gardnerdenver.facade.EquipamentoServicoFacade;
import com.gardnerdenver.facade.MovimentoFacade;
import com.gardnerdenver.model.Configuracao;
import com.gardnerdenver.model.Equipamento;
import com.gardnerdenver.model.EquipamentoServico;
import com.gardnerdenver.model.Funcionario;
import com.gardnerdenver.model.Generico;
import com.gardnerdenver.model.StatusOS;
import com.gardnerdenver.model.Movimento;
import com.gardnerdenver.model.MovimentoItem;
import com.gardnerdenver.model.Parceiro;
import com.gardnerdenver.model.PecaEqs;
import com.gardnerdenver.util.Util;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "ordemServicoBean")
@SessionScoped
public class OrdemServicoBean extends AbstractMB implements Serializable {

    //OBJ
    private Movimento movimento = new Movimento();
    private MovimentoItem movimentoItem = new MovimentoItem();
//    private Equipamento equipamento = new Equipamento();
//    private Titulo titulo = new Titulo();
//    private Caixa caixa = new Caixa();
//    private Produto produto = new Produto();
    private Parceiro parceiro = new Parceiro();
//    private Kardex kardex = new Kardex();
    private Funcionario funcionario = new Funcionario();
//    private Fatura fatura = new Fatura();
//    private PlanoConta planoConta = new PlanoConta();
    private Configuracao configuracao;
//    Agenda agenda = new Agenda();
    //Array
//    private Movimento[] selectedMov = null;
//    private Titulo[] selectedTitulo = null;
//    private CondicaoPagamentoMovimento[] cpMov = null;
//    private List<CondicaoPagamentoMovimento> cpMov = new ArrayList<>();
//    private CondicaoPagamentoMovimento cpMovObj = new CondicaoPagamentoMovimento();
    //BEANS
    private GenericoBean genericoBean = new GenericoBean();
//    private DocCondPagamentoBean dcPagBean = new DocCondPagamentoBean();
    //DAOS
//    private RelatorioDao relDao = new RelatorioDao();
//    private FaturaDao fatDao = new FaturaDao();
//    private KardexDao kdxDao = new KardexDao();
//    private CaixaDao caixaDao = new CaixaDao();
//    private TituloDao tituloDao = new TituloDao();
//    private CentroCustoDao ccDao = new CentroCustoDao();
    private MovimentoDao movDao = new MovimentoDao();
//    private ProdutoDao produtoDao = new ProdutoDao();
//    private CondicaoPagamentoDao condPagDao = new CondicaoPagamentoDao();
    private MovimentoItemDao movItemDao = new MovimentoItemDao();
//    private ContaBancariaDao cbDao = new ContaBancariaDao();
//    private NotaFiscalDao nfeDao = new NotaFiscalDao();
//    private NotaFiscalServicoDao nfseDao = new NotaFiscalServicoDao();
//    private AgendaDao agendaDao = new AgendaDao();
//    private ListaPrecoDao lpcDao = new ListaPrecoDao();
//    private ListaPrecoItemDao lpiDao = new ListaPrecoItemDao();
//    private NotaFiscalMovimentoDao nfMovDao = new NotaFiscalMovimentoDao();
//    private NotaFiscalServicoMovimentoDao nfsMovDao = new NotaFiscalServicoMovimentoDao();
    //DATAMODELS
    private DataModel<Movimento> dmMov = new ArrayDataModel<>();
    private DataModel<MovimentoItem> dmMovItem = new ArrayDataModel<>();
//    private DataModel<ListaPreco> dmLPC = new ArrayDataModel<>();
//    private DataModel<Produto> dmProd = new ArrayDataModel<>();
    //LISTAS
//    private List<ContaBancaria> listCBBoleto;
//    private List<Titulo> listBoleto = new ArrayList<>();
    private List<Movimento> listMov = new ArrayList<>();
    private List<Movimento> movimentoSelected = new ArrayList<>();
    private List<MovimentoItem> listMovimentoItem = new ArrayList<>();
    private List<MovimentoItem> listMovimentoItemServico = new ArrayList<>();
    private List<MovimentoItem> listMovimentoItemPeca = new ArrayList<>();
    private List<StatusOS> listStOrdServ = new ArrayList<>();
    private List<EquipamentoServico> listServicos;
    private List<PecaEqs> listPecasEqs;
//    private List<ContaBancaria> listCB = new ArrayList<>();
    private List<Integer> listQtdeParc = new ArrayList<>();
    private List<Generico> listGen = new ArrayList<>();
    //SELECTEDS
    private MovimentoItem movItemSelected = new MovimentoItem();
//    private CondicaoPagamento condPagSelected;
//    private ContaBancaria cbSelected = new ContaBancaria();
//    private ContaBancaria contaBancaria;
    private StatusOS statusSelected = null;
    private Generico tipoDataSelected = null;
    private StatusOS entSelected = null;
////    private CondicaoPagamento tipoDocCPSelected = null;
//    private ListaPreco selectedLp = new ListaPreco();
//    private Produto selectedProduto = null;
    //String
    private String emailParceiro = "";
    private String emailObs = "";
    private String razaoParceiro = "";
    private String caminhoBoleto = "";
    private String tipoMovimento = "ODS";
    private String tipoAcao = "";
    private String pesqCod = "";
    private String condPagDoc = "";
    private String condPagTipoInterv = "";
    private String labelJuroDesc = "Juros";
    private String condPagInterv = "";
    private String campo = "MOV_EMISSAO";
    private String ordem = "DESC";
    private String filtroBusca = "";
    private String cliCodBusca = "";
    private String textCancel = "";
    private String messageExc = "";
    private String ID = "";
    private String cmpFocus = "osCliente";
    private String alert = "";
    private String patternAgenda = "dd/MM/yyyy HH:mm";
    private String msgExclusao = "";
    private String emailParceiroAux = "";
    //DATA
    private Date dataini = null;
    private Date datafim = null;
    //double / int
    private int qtdeparc = 0;
    private int tipoLista = 0;
    private int qtdeSelect = 0;
    private int qtdeList = 0;
    private double totalLIst = 0;
    private double valorJurosDesc = 0;
    private double totalSelect = 0;
    private double valorEntFat = 0;
    private double juroDesc = 0;
    private double taxaFixa = 0;
    private double totalFinal = 0;
    private double jurosValor = 0;
    //BOOLEANS
    private boolean boletoBool = false;
    private boolean emailBool = false;
//    private boolean agendaBool = false;
    private boolean estIgual = true;
    private boolean cancelBool = false;
    private boolean delBool = false;
    private boolean faturasDelBool = false;
    private boolean vincContaBool = false;
    private boolean ODS = false;
    private boolean nfeVisBool = false;
    private boolean finalBool = false;
    private boolean cancBool = false;
//    sprivate boolean incFaturaBool = false;
//    private boolean stEntBool = false;
    private boolean stRepBool = false;
    private boolean showFatBool = false;
    private boolean showRep = false;
    private boolean tipoEntBool = false;
    private boolean showExclusao = false;
    private boolean newFatBool = false;
    private boolean criaFatBool = false;
    private boolean aVista = false;
    private boolean condPagBool = false;
    private boolean selectEnt = false;
    private boolean rdrBtExcluir = false;
    private boolean definirHorario = false;
    private boolean rdrLabelListaPreco = false;
    private boolean rdrPnCondPag = false;
    private boolean rdrDlgFinNF = false;
    private boolean visReabPed = false;
    private boolean incFaturaResumoBool = false;
    //SDF
    SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yyyy ", new Locale("pt", "br"));
    //OUTROS
    private HttpSession session;
    private ServletContext sc;
    private JasperPrint jp;
    private FacesContext facesContext = FacesContext.getCurrentInstance();
    //
//    private MovimentoHistorico movHis = new MovimentoHistorico();
//    private DataModel<MovimentoHistorico> dmHist = new ArrayDataModel<>();
//    private List<MovimentoHistorico> listPh = new ArrayList<>();
    //
    private byte[] conteudoPdf;
    //
//    private CondicaoPagamento condPagNovo;
    private double parcial = 0;

    @ManagedProperty(value = FuncionarioBean.INJECTION_NAME)
    private FuncionarioBean funcionarioBean;
//    private Equipamento equipamento;
    private EquipamentoServicoFacade equipamentoServicoFacade;
    private MovimentoFacade movimentoFacade;

    public OrdemServicoBean() {
    }

    public void loadEquip() {

//        movimento.getEquipamento().setServicos(getEquipamentoServicoFacade().listByEqpId(movimento.getEquipamento().getEQP_ID()));
//        movimento.setListMovimentoItem(new ArrayList<MovimentoItem>());
//
//        for (EquipamentoServico eqs : movimento.getEquipamento().getServicos()) {
//            if ((eqs.getMANUTPROXIMA().before(movimento.getMOV_EMISSAO())) || (eqs.getMANUTPROXIMAHORAS().before(movimento.getMOV_EMISSAO()))) {
//                movimento.getListMovimentoItem().add(new MovimentoItem(eqs));
//            }
//        }
    }

    public void listenerEquipamento() {
        movimento.setListMovimentoItem(new ArrayList<>());
        Equipamento eqpTemp = new EquipamentoFacade().findEquipamento(movimento.getEquipamento().getEQP_ID());

        if (movimento.getMOV_ID() != 0) {

//            for (MovimentoItem mit : movimento.getListMovimentoItem()) {
            getMovimentoFacade().deleteMovimentoItemByMov(movimento);
//            }
//            movimento = getMovimentoFacade().findMovimento(movimento.getMOV_ID());
//            List<MovimentoItem> temp = getMovimentoFacade().listAllMitsByMov(movimento.getMOV_ID());
//            for (MovimentoItem m : temp) {
//                movimento.getListMovimentoItem().remove(m);
//            }

        }

        if (movimento.getEquipamento().getEQP_CATID() == 1) {
            listServicos = getEquipamentoServicoFacade().listByEqpId(movimento.getEquipamento().getEQP_ID());
            setListMovimentoItemServico(new ArrayList<>());
            for (EquipamentoServico eqs : getListServicos()) {
                movimentoItem = new MovimentoItem();
                movimentoItem.setMovimento(movimento);
                movimentoItem.setPeca(null);
                movimentoItem.setServico(eqs.getServico());
                getListMovimentoItemServico().add(movimentoItem);
                setListMovimentoItemPeca(new ArrayList<>());
                for (PecaEqs pqs : eqs.getEquipamentosPecas()) {
                    movimentoItem = new MovimentoItem();
                    movimentoItem.setMovimento(movimento);
                    movimentoItem.setPeca(pqs.getPeca());
                    movimentoItem.setServico(null);
                    getListMovimentoItemPeca().add(movimentoItem);
                }
            }

        }
        movimento.setEquipamento(eqpTemp);
    }

    public void imprimir(ActionEvent ae) {
        String bt = ae.getComponent().getId();
        String link = "/gdpc";
        String name;
//        if (Util.localhost) {
//            link = "/gdpc";
//        }
        FacesContext context = FacesContext.getCurrentInstance();
//        String status = movimento.getMOV_STATUSSTR();

        switch (bt) {
            case "btImp":
//                if (status.equals("Entregue")) {
//                    link = link + "/RelatorioServlet?nome=movimentoOdsEntregue&mov_id=" + movimento.getMOV_ID();
//                } else {
                if (movimento.getEquipamento().getEQP_CATID() == 1) {
                    name = "rvt";
                } else {
                    name = "movimentoOds";
                }
                link = link + "/RelatorioServlet?nome=" + name + "&movId=" + movimento.getMOV_ID();
//                }
                break;
            case "btImpB":
//                if (status.equals("Entregue")) {
//                    link = link + "/RelatorioServlet?nome=movimentoOdsBlankEntregue&mov_id=" + movimento.getMOV_ID();
//                } else {
                link = link + "/RelatorioServlet?nome=movimentoOdsBlank&movId=" + movimento.getMOV_ID();
//                }
                break;
        }
        try {
            context.getExternalContext().redirect(link);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            context.responseComplete();
        }
    }

    public byte[] getConteudoPdf() {
        return conteudoPdf;
    }

    public void setConteudoPdf(byte[] conteudoPdf) {
        this.conteudoPdf = conteudoPdf;
    }

    //METODOS
    public String verificaUF(SelectEvent event) {
        configuracao = Util.getConfiguracao();
        if (event != null) {
            double tot = 0;
            double totDesc = 0;
            double res = 0;
            movimento.setParceiro((Parceiro) event.getObject());

            movimento.setFuncionario(movimento.getParceiro().getFuncionario());
            movimento.setMOV_CONTATO(movimento.getParceiro().getPAR_CONTATO());
            movimento.setMOV_TELEFONE(movimento.getParceiro().getPAR_TELEFONE1());
            movimento.setMOV_CELULAR(movimento.getParceiro().getPAR_CELULAR());
            movimento.setMOV_EMAIL(movimento.getParceiro().getPAR_EMAIL());
//            movimento.setListMovimentoItem(new ArrayList<MovimentoItem>());

            if (configuracao.getEST_IDEMP() == movimento.getParceiro().getPAR_UF()) {
                estIgual = true;
            } else {
                estIgual = false;
            }
//            if (listMovimentoItem != null || !listMovimentoItem.isEmpty()) {
//                for (MovimentoItem movItemTab : listMovimentoItem) {
//                    tot = movItemTab.getMIT_QTDE() * movItemTab.getMIT_VALORUNIT();
//                    totDesc = tot - movItemTab.getMIT_VALORDESC();
//                    if (estIgual) {
//                        res = totDesc / 100;
////                        movItemTab.setMIT_VALORIPI(res * movItemTab.getProduto().getPRO_D_IPI_ALIQ());
//                        totDesc = totDesc + movItemTab.getMIT_VALORIPI();
//                        movItemTab.setMIT_VALORTOT(totDesc);
//                    } else {
//                        res = tot / 100;
////                        movItemTab.setMIT_VALORIPI(res * movItemTab.getProduto().getPRO_F_IPI_ALIQ());
//                        tot = tot + movItemTab.getMIT_VALORIPI();
//                        movItemTab.setMIT_VALORTOT(tot);
//                    }
//                }
////                calcTot();
//            }
        }
        return "ordemservicoregistro";
    }

    public void addItem() {
//        if (movimento.getEquipamento().getEQP_ID() == 0) {
//            displayInfoMessageToUser("Selecione um equipamento do cliente.");
//            return;
//        }
        movimentoItem = new MovimentoItem();
        movimentoItem.setMovimento(movimento);
        movimento.getListMovimentoItem().add(movimentoItem);
    }

//    public void setaContaBanc(Titulo titulo) {
//        int i = 0;
//        ContaBancaria cb = new ContaBancaria();
//        for (Titulo tit : selectedTitulo) {
//            if (selectedTitulo[i].getTIT_VENCIMENTO() == titulo.getTIT_VENCIMENTO()) {
//                cb = selectedTitulo[i].getContabancaria();
//                break;
//            }
//            i++;
//        }
//        for (int j = i; j < selectedTitulo.length; j++) {
//            selectedTitulo[j].setContabancaria(cb);
//        }
//    }
//    public void setaDocCondPag(Titulo titulo) {
//        int i = 0;
//        CondicaoPagamento dc = null;
//        for (Titulo tit : selectedTitulo) {
//            if (selectedTitulo[i].getTIT_VENCIMENTO() == titulo.getTIT_VENCIMENTO()) {
//                dc = selectedTitulo[i].getDocCondPag();
//                break;
//            }
//            i++;
//        }
//        for (int j = i; j < selectedTitulo.length; j++) {
//            selectedTitulo[j].setDocCondPag(dc);
//        }
//    }
//
//    public void setaValor(Titulo titulo) {
//        int i = 0;
//        double valor = 0;
//        double parc = 0;
//        double tot = 0;
//        double corr = 0;
//        int qt = 0;
//        for (Titulo t : selectedTitulo) {
//            valor = valor + selectedTitulo[i].getTIT_VALOR();
//            if (selectedTitulo[i].getTIT_OBSERVACAO().equals(titulo.getTIT_OBSERVACAO())) {
//                i++;
//                break;
//            }
//            i++;
//        }
//        qt = selectedTitulo.length - (i);
//        tot = totalFinal - valor;
//        parc = tot / qt;
//        parc = Util.convertDoubleMoeda(parc);
//        corr = Util.corrigirParc(parc, tot, 0, qt, false);
//        for (int j = i; j < selectedTitulo.length; j++) {
//            selectedTitulo[j].setTIT_VALOR(parc + corr);
//            corr = 0;
//        }
//    }
    public void setaVariavelEmail() {
        emailParceiroAux = getEmailParceiro();
    }
//
//    public void newFatura() {
//        setIncFaturaBool(true);
//
////        condPagSelected = new CondicaoPagamento();
////        cbSelected = new ContaBancaria();
////        selectedTitulo = null;
////        tipoDataSelected = null;
////        entSelected = null;
////        tipoDocCPSelected = null;
////        setStEntBool(false);
////        setStRepBool(false);
////        setShowFatBool(false);
////        setSemEntBool(true);
////        setTipoEntBool(true);
////        setCriaFatBool(true);
////        valorEntFat = 0;
////        listGen = genericoBean.getListaSN();
////        for (StatusOS g : listGen) {
////            if (g.getGEN_ID() == 100) {
////                entSelected = g;
////                break;
////            }
////        }
////        if (movimento.getCondicaoPagamento() != null) {
////            condPagSelected = movimento.getCondicaoPagamento();
////        } else {
////            for (CondicaoPagamento cp : listCondPag) {
////                if (cp.getCPT_ID() == 1) {
////                    condPagSelected = cp;
////                    break;
////                }
////            }
////        }
////        calcTotByCondPag();
////        return "ordemservicofat";
//    }

//    public void salvarFatura() {
//
//        if (cpMov == null || cpMov.isEmpty()) {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar!", "Não existem faturas a serem salvas!!");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            return;
//        } else {
//            geraFaturasFin();
//        }
//
//        double valorFat = 0;
//        for (CondicaoPagamentoMovimento cp : cpMov) {
//            valorFat += cp.getCPM_VALOR();
//        }
//        if (valorFat > totalFinal) {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Valor a faturar excede o valor total da venda");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            return;
//        }
////        if (condPagSelected == null || condPagSelected.getCPT_ID() == 0) {
////            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar!", "Selecione uma Condição de Pagamento!");
////            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
////            return;
////        }
//        if (selectedTitulo == null || selectedTitulo.length == 0) {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar!", "Não existem faturas a serem salvas!!");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            return;
//        } else {
//            String doc = "OS " + String.valueOf(movimento.getMOV_ID());
//            fatura = new Fatura("REC", doc, new Date(), parceiro, getFuncionario(), movimento, movimento.getPlanoConta());
//            if (fatDao.insert(fatura)) {
////                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro salvo com sucesso!", "");
////                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            } else {
//                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível gravar fatura", "");
//                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//                return;
//            }
//        }
//
//        for (int i = 0; i < selectedTitulo.length; i++) {
//            selectedTitulo[i].setFatura(fatura);
//            if (tituloDao.insert(selectedTitulo[i])) {
//                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro salvo com sucesso!", "");
//                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            } else {
//                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível gravar registro", selectedTitulo[i].getTIT_OBSERVACAO());
//                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//                return;
//            }
//            if (entSelected.getGEN_ID() == 100 || aVista) {//COM ENTRADA
////                selectedTitulo[0].setCXA_EMISSAO(selectedTitulo[0].getTIT_PAGAMENTO());
////                selectedTitulo[0].setTIT_OBSERVACAO("Rec. " + selectedTitulo[0].getTIT_OBSERVACAO());
////                selectedTitulo[0].setTIT_VALORPAGO(selectedTitulo[0].getTIT_VALORPAGO());
////                selectedTitulo[0].setTitulo(selectedTitulo[0]);
////                selectedTitulo[0].setContabancaria(selectedTitulo[0].getContabancaria());
//                selectedTitulo[0].setPlanoconta(movimento.getPlanoConta());
//
//                if (caixaDao.update(selectedTitulo[0])) {
////                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Caixa salvo com sucesso!", "");
////                    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//                } else {
//                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível gravar caixa", "");
//                    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//                    return;
//                }
//            }
//        }
//        fatura();
//        incFaturaBool = false;
////        return "pedidofatura";
////        return "";
//    }
//    public String fatura() {
//        setShowExclusao(false);
//        setBoletoBool(false);
//
//        listTitulo = tituloDao.getObjByMOV(movimento.getMOV_ID(), movimento.getMOV_TIPO());
//        if (listTitulo == null || listTitulo.isEmpty()) {
//            newFatBool = true;
//            criaFatBool = false;
//        } else {
//            newFatBool = false;
//            criaFatBool = false;
//
//        }
//
//        ID = String.valueOf(movimento.getMOV_ID());
////        return "pedidofatura";
//        return "";
//    }
    public String finOSRegistro() {
        movimento.setMOV_FINALIZACAO(new Date());
//        cbSelected = new ContaBancaria();
//        selectedTitulo = null;
        statusSelected = null;
        tipoDataSelected = null;
        entSelected = null;
//        tipoDocCPSelected = null;
//        setIncFaturaBool(false);
//        setStEntBool(false);
        setStRepBool(false);
        setShowFatBool(false);
        setTipoEntBool(true);
        valorEntFat = 0;

//        if (movimento.getCondicaoPagamento() != null) {
//            condPagSelected = movimento.getCondicaoPagamento();
//        } else {
//            for (CondicaoPagamento cp : getListCondPag()) {
//                if (cp.getCPT_ID() == 1) {
//                    condPagSelected = cp;
//                    break;
//                }
//            }
//        }
//        calcTotByCondPag();
//        atualizaStFinal();
        return "ordemservicofinalizacao";
    }

    public void finalizarOds() {
        movimento.setMOV_FINALIZACAO(new Date());
//        cbSelected = new ContaBancaria();
//        selectedTitulo = null;
        statusSelected = null;
        tipoDataSelected = null;
        entSelected = null;
//        tipoDocCPSelected = null;
//        setIncFaturaBool(false);
//        setStEntBool(false);
        setStRepBool(false);
        setShowFatBool(false);
        setTipoEntBool(true);
        valorEntFat = 0;
//        if (movimento.getCondicaoPagamento() != null) {
//            condPagSelected = movimento.getCondicaoPagamento();
//        } else {
//            for (CondicaoPagamento cp : getListCondPag()) {
//                if (cp.getCPT_ID() == 1) {
//                    condPagSelected = cp;
//                    break;
//                }
//            }
//        }
//        calcTotByCondPag();
//        cpMov = new ArrayList<>();
        redirect("/pages/protected/distributor/ordemservicoFinalizacao.xhtml");
    }

    public String fechaBoleto() {
        setBoletoBool(false);
        return "ordemservicofat";
    }

//    public String abrirGeraBoleto() {
//        configuracao = Util.getConfiguracao();
////        listBoleto = new ArrayList<>();
//
//        if (titulo.getContabancaria() == null) {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Gerar Boleto!", "Nenhuma Conta Bancária configurada para gerar Boleto!");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            return "erro";
//        }
//
//        boolean quitado = true;
//        for (Titulo tit : listTitulo) {
//            quitado = false;
//            listBoleto.add(tit);
//            emailParceiro = tit.getFatura().getMovimento().getMOV_EMAIL();
//            razaoParceiro = tit.getFatura().getParceiro().getPAR_RAZAO();
//        }
//        if (quitado) {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Gerar Boleto!", "Não existe nenhum título em aberto para emissão de boleto");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            return "erro";
//        }
//        emailObs = configuracao.getTEXTO_EMAIL();
//        setBoletoBool(true);
//        return "ordemservicofat";
//    }
//    public boolean geraBoleto() throws SQLException {
//        configuracao = Util.getConfiguracao();
//        listBoleto = new ArrayList<>();
//        List<Boleto> boletos = new ArrayList<>();
//        for (Titulo tit : listTitulo) {
//            if (tit.getTIT_STATUS() == 0) {
//                listBoleto.add(tit);
//                contaBancaria = titulo.getContabancaria();
//
//                Boleto boleto = Util.geraBoleto(tit, contaBancaria);
//                if (boleto == null) {
//                    return false;
//                }
//                boletos.add(boleto);
//
//                /* * GERANDO O BOLETO BANCÁRIO.* */
//                try {
//                    String nomeBoleto = Util.getCaminhoAlternativo() + configuracao.getEMP_IDENTIFICADOR() + "/boleto/Boleto Ordem de Servico 00" + tit.getFatura().getMovimento().getMOV_ID() + ".pdf";
//                    caminhoBoleto = nomeBoleto;
//                    //BoletoViewer boletoViewer = new BoletoViewer(boleto, MeuPrimeiroBoleto.class.getResource("/resource/pdf/BoletoTemplateSemSacadorAvalista.pdf"));
//                    //File arquivoPdf = BoletoViewer.groupInOnePDF(boletos, nomeBoleto);
//
//                    String template = MeuPrimeiroBoleto.class.getResource("/resource/pdf/BoletoTemplateSemSacadorAvalista.pdf").getPath();
//                    File arquivoPdf = BoletoViewer.groupInOnePdfWithTemplate(boletos, nomeBoleto, template);
//                    conteudoPdf = new byte[(int) arquivoPdf.length()];
//                    InputStream is = new FileInputStream(arquivoPdf);
//                    is.read(conteudoPdf);
//                    is.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return true;
//    }
//    public String visualizarPdf() throws SQLException {
//        if (geraBoleto()) {
//            FacesContext fc = FacesContext.getCurrentInstance();
//            HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
//            response.reset();
//            response.setContentType("application/pdf");
//            response.setContentLength(conteudoPdf.length);
//            response.setHeader("Content-disposition", "inline; filename=boleto.pdf");
//            try {
//                response.getOutputStream().write(conteudoPdf);
//                response.getOutputStream().flush();
//                response.getOutputStream().close();
//                fc.responseComplete();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            setBoletoBool(false);
//        }
//        return "ordemservicofat";
//    }
    //envia Boleto por email
//    public String enviarBoleto() throws MessagingException, SQLException {
//        if (geraBoleto()) {
//            configuracao = Util.getConfiguracao();
//            String retorno = "erro";
//            String email = "";
//            String senha = "";
//            String usuario = "";
//            String smtp = "";
//            String porta = "";
//            int seguranca = 0;
//            String emailProprio = configuracao.getEMP_EMAILPROPRIO();
//            if (emailProprio.equals("1")) {
//                email = configuracao.getEMP_EMAILENVIO();
//                senha = configuracao.getEMP_SENHAEMAIL();
//                usuario = configuracao.getEMP_USUARIO();
//                smtp = configuracao.getEMP_SMTP();
//                porta = configuracao.getEMP_PORTA();
//                seguranca = configuracao.getEMP_SEGURANCA();
//            } else {
//                email = "atendimentogestorweb@gmail.com";
//                senha = "qwert4321";
//                usuario = configuracao.getEMP_FANTASIA();
//                smtp = "smtp.gmail.com";
//                porta = "587";
//                seguranca = 1;
//            }
//            final String emailFinal = email;
//            final String senhaFinal = senha;
//            Properties props = new Properties();
//            props.put("mail.transport.protocol", "smtp");//define protocolo de envio como SMTP
//            props.put("mail.smtp.starttls.enable", "true");
//            props.put("mail.smtp.host", smtp);
//            props.put("mail.smtp.auth", true);//ativa autenticacao
//            props.put("mail.smtp.user", email);//usuario ou seja, a conta que esta enviando o email
//            props.put("mail.debug", true);
//            props.put("mail.smtp.port", porta);//porta
//            props.put("mail.smtp.socketFactory.port", porta);//mesma porta para o socket
//            props.put("mail.smtp.ssl.trust", smtp);
//            //  |  Automático "1"  |  SSL "2"  |  TLS 3  |
//            if (seguranca == 2) {
//                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//            }
//            props.put("mail.smtp.socketFactory.fallback", false);
//            Authenticator autenticador = new Authenticator() {
//                @Override
//                public PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(emailFinal, senhaFinal);
//                }
//            };
//            try {
//                Session session = Session.getInstance(props, autenticador);
//                session.setDebug(false);
//                Message msg = new MimeMessage(session);
//                msg.setFrom(new InternetAddress(email));//email que enviará
//                msg.setRecipient(Message.RecipientType.TO, new InternetAddress(emailParceiro));//email que receberá
//                String assunto = "Boleto(s) - " + configuracao.getEMP_RAZAO();
//                msg.setSubject(assunto);
//                String textoEmail
//                        = "<p style='font-family: Arial'>"
//                        + "<small style='color:#999999'>*** Esse é um e-mail automático. Não é necessário respondê-lo ***</small>"
//                        + "<br/><br/><br/>Prezado(a),<br/>você está recebendo um e-mail de:<br/><br/><strong style='font-size:20px'>"
//                        + configuracao.getEMP_RAZAO()
//                        + "</strong><br/>" + configuracao.getEMP_EMAIL() + "<br/>"
//                        + configuracao.getEMP_FONE() + "<br/><br/><br/><br/>"
//                        + emailObs.replace("\n", "<br/>") + "<br/><br/><br/><br/>"
//                        + "<a href='http://www.gestorweb.com.br' alt='Sistema de Gestão Online'><img src='http://gestorweb.com.br/images/gw-email.png' /></a></p>"
//                        + "</p>";
//                MimeBodyPart mbp1 = new MimeBodyPart();
//                mbp1.setContent(textoEmail, "text/html; charset=utf-8");
//                MimeBodyPart mbp2 = new MimeBodyPart();
//                mbp2.setDataHandler(new DataHandler(new ByteArrayDataSource(msg.getContentType(), "application/x-any")));
//                mbp2.setDisposition(Part.ATTACHMENT);
//                mbp2.attachFile(caminhoBoleto);
//                Multipart mp = new MimeMultipart();
//                mp.addBodyPart(mbp1);
//                mp.addBodyPart(mbp2);
//                msg.setContent(mp);
//                msg.setSentDate(new Date());
//                if (!configuracao.getEMP_EMAILRESPOSTABO().equalsIgnoreCase("")) {
//                    msg.setReplyTo(new InternetAddress[]{new InternetAddress(usuario + "<" + configuracao.getEMP_EMAILRESPOSTABO() + ">")});
//                } else if (emailProprio.equals("0")) {
//                    msg.setReplyTo(new InternetAddress[]{new InternetAddress(usuario + "<" + configuracao.getEMP_EMAIL() + ">")});
//                }
//                msg.saveChanges();
//                Transport.send(msg);
//                FacesContext context = FacesContext.getCurrentInstance();
//                context.addMessage(null, new FacesMessage("Boleto Enviado com sucesso!", " "));
//                retorno = "";//retornar nada
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            setBoletoBool(false);
//        }
//        return "";
//    }
//    public void calcTotByCondPag() {
//        listQtdeParc = new ArrayList<>();
//        listDocCondPag = condPagDao.getLista();
//        qtdeparc = 0;
//        valorEntFat = 0;
//
//        if (condPagSelected != null) {
//
//            if (condPagSelected.getCPT_ID() != 0) {
//                rdrPnCondPag = true;
//            }
//
//            qtdeparc = condPagSelected.getCPT_QTDE_PARCELAS();
//            if (qtdeparc > 0) {
//                for (int i = 1; i <= qtdeparc; i++) {
//                    listQtdeParc.add(i);
//                }
//            } else {
//                listQtdeParc.add(0);
//            }
//
//            listGen = genericoBean.getListaSN();
//            for (StatusOS g : listGen) {
//                if (g.getGEN_ID() == (condPagSelected.getCPT_ENTRADA() + 100)) {
//                    entSelected = g;
//                    break;
//                }
//            }
//            if (entSelected.getGEN_ID() == 100) {
//                valorEntFat = movimento.getMOV_TOTAL() / (qtdeparc + 1);
//                selectEnt = true;
//            } else {
//                if (condPagSelected.getCPT_ID() == 1) {
//                    selectEnt = false;
//                } else {
//                    selectEnt = true;
//                }
//                valorEntFat = 0;
//            }
//            juroDesc = Math.abs(condPagSelected.getCPT_JUROS_DESC());
//            taxaFixa = condPagSelected.getCPT_TAXA();
//
//            for (ContaBancaria cb : getListCB()) {
//                if (cb.getCBC_ID() == 1) {
//                    cbSelected = cb;
//                }
//            }
//            for (CondicaoPagamento dcp : listDocCondPag) {
//                if (dcp.getCPT_ID() == condPagSelected.getCPT_DOCUMENTO()) {
//                    tipoDocCPSelected = dcp;
//                }
//            }
//
//        } else {
//            rdrPnCondPag = false;
//            listQtdeParc.add(0);
//            juroDesc = 0;
//            jurosValor = 0;
//            taxaFixa = 0;
//            qtdeparc = 0;
//            cbSelected = null;
//            tipoDocCPSelected = null;
//            for (StatusOS g : listGen) {
//                if (g.getGEN_ID() == 101) {
//                    entSelected = g;
//                }
//            }
//        }
//        calcValJuros();
//
//    }
//
//    public void calcValJuros() {
//        double prestacao = 0;
//        double jr = 0;
//        double valPrest = 0;
//        double aux = 0;
//        jurosValor = 0;
//        if (juroDesc > 0 && qtdeparc > 0) {
//            valPrest = movimento.getMOV_TOTAL() - valorEntFat;
//            jr = (1 + (juroDesc / 100));
//
//            jr = Math.pow(jr, -qtdeparc);
//
//            jr = 1 - jr;
//            jr = jr / (juroDesc / 100);
//
//            prestacao = valPrest / jr;
//
//            jurosValor = (prestacao * qtdeparc) - valPrest;
//            jurosValor = Util.convertDoubleMoeda(jurosValor);
//        }
//        double valorFaturar = movimento.getMOV_TOTAL();
//        if (!listTitulo.isEmpty()) {
//            for (Titulo t : listTitulo) {
//                valorFaturar -= t.getTIT_VALOR();
//            }
//        }
////        totalFinal = movimento.getMOV_TOTAL() + taxaFixa + jurosValor;
//        totalFinal = valorFaturar + taxaFixa + jurosValor;
//        totalFinal = Util.convertDoubleMoeda(totalFinal);
//    }
//    public String gerarFaturaOS() {
//        setShowFatBool(true);
//        String idDoc = String.valueOf(movimento.getMOV_ID());
//        parceiro = movimento.getParceiro();
//        aVista = false;
//        String entOrVista = "";
//        int p = 0;  // contador for
//        int foo = 0;
//        int idx = 1;
//        double valorParc = 0;
//        double correcao = 0;
//        if (condPagSelected.getCPT_ID() == 1) {
//            aVista = true;
//            entOrVista = ", À Vista.";
//        } else {
//            aVista = false;
//            entOrVista = ", Entrada.";
//        }
//
//        if (entSelected.getGEN_ID() == 100 || aVista) { //se tiver entrada
//            foo = qtdeparc + 1;
//            selectedTitulo = new Titulo[foo];
//            if (aVista) {
//                valorEntFat = totalFinal;
//            } else {
//                valorParc = (totalFinal - valorEntFat) / qtdeparc;
//                valorParc = Util.convertDoubleMoeda(valorParc);
//                correcao = Util.corrigirParc(valorParc, totalFinal, valorEntFat, qtdeparc, true);
//            }
//            p = 1;
//            selectedTitulo[0] = new Titulo();
//            selectedTitulo[0].setTIT_STATUS(0);
////            selectedTitulo[0].setTIT_PAGAMENTO(new Date());
//            selectedTitulo[0].setTIT_VALOR(valorEntFat);
//            selectedTitulo[0].setTIT_TIPO("REC");
////            selectedTitulo[0].setTIT_VALORPAGO(valorEntFat);
//            selectedTitulo[0].setTIT_OBSERVACAO("OS " + idDoc + entOrVista);
//            selectedTitulo[0].setTIT_VENCIMENTO(movimento.getMOV_FINALIZACAO());
//            selectedTitulo[0].setContabancaria(movimento.getCondicaoPagamento().getConta());
//            selectedTitulo[0].setPlanoconta(movimento.getPlanoConta());
//            selectedTitulo[0].setDocCondPag(tipoDocCPSelected);
//            selectedTitulo[0].setTIT_ENTRADA(true);
//        } else { //sem entrada
//            selectedTitulo = new Titulo[qtdeparc];
//            valorParc = totalFinal / qtdeparc;
//            valorParc = Util.convertDoubleMoeda(valorParc);
//            correcao = Util.corrigirParc(valorParc, totalFinal, 0, qtdeparc, false);
//            p = 0;
//        }
//        for (int i = p; i < selectedTitulo.length; i++) { //seta parcelas
//            selectedTitulo[i] = new Titulo();
//            selectedTitulo[i].setTIT_VALOR(valorParc + correcao);
//            selectedTitulo[i].setTIT_OBSERVACAO("OS " + idDoc + ", parcela " + idx + "/" + qtdeparc);
//            selectedTitulo[i].setContabancaria(movimento.getCondicaoPagamento().getConta());
//            selectedTitulo[i].setDocCondPag(tipoDocCPSelected);
//            //Calendar c = Calendar.getInstance();
//            //c.add(Calendar.MONTH, i);
//            //selectedTitulo[i].setTIT_VENCIMENTO(c.getTime());
//            Date dataVencTemp = new Date(movimento.getMOV_FINALIZACAO().getTime());
//            if (condPagSelected.getCPT_TIPO_INTERVALO() == 0) {       //DIAS
//                dataVencTemp.setDate(dataVencTemp.getDate() + (condPagSelected.getCPT_QTDE_INTERVALO() * idx));
//            } else {
//                Calendar c = Calendar.getInstance();
//                //if (i > 0) {
//                //    c.setTime(selectedTitulo[i - 1].getTIT_VENCIMENTO());
//                //} else {
//                c.setTime(movimento.getMOV_FINALIZACAO());
//                //}
//                c.add(Calendar.MONTH, (i + 1));      // adiciona o dia de vencimento da próxima conta
//                dataVencTemp = c.getTime();
//            }
//            selectedTitulo[i].setTIT_VENCIMENTO(dataVencTemp);
//            selectedTitulo[i].setTIT_ENTRADA(false);
//            correcao = 0;
//            idx++;
//        }
//        if (criaFatBool) {
//            return "ordemservicofat";
//        }
//        return "ordemservicofinalizacao";
//    }
    public void salvarFinalizacao() {
//        movimento.setMOV_STATUS(8);
        movimento.setFINALIZADO(Boolean.TRUE);

        if (movimento.getMOV_FINALIZACAO() == null || movimento.getMOV_FINALIZACAO().equals("")) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar!", "Selecione a data de Finalização!");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return;
        }
        getMovimentoFacade().updateMovimento(movimento);

        showOS();
    }

    //    public void geraFaturasFin() {
    //
    //        listQtdeParc = new ArrayList<>();
    //        String parcs[] = null;
    //        String tipoParcela = "";
    //        listDocCondPag = condPagDao.getLista();
    //        qtdeparc = 0;
    //        valorEntFat = 0;
    //        condPagNovo = new CondicaoPagamento();
    //        condPagNovo.setCPT_TIPO_INTERVALO(1);
    //
    //        ///////////////////////////
    //        if (movimento.getMOV_FORMAPGTO() != null && !movimento.getMOV_FORMAPGTO().trim().isEmpty()) {
    //            movimento.setMOV_FORMAPGTO(movimento.getMOV_FORMAPGTO().trim());
    //            String aux = movimento.getMOV_FORMAPGTO();
    //            int i = 0;
    //            if (movimento.getMOV_FORMAPGTO().startsWith("0 ")) {
    //                condPagNovo.setCPT_ENTRADA(0);
    //                aux = movimento.getMOV_FORMAPGTO().replaceFirst("0 ", "");
    //            } else {
    //                if (movimento.getMOV_FORMAPGTO().startsWith("0+")) {
    //                    condPagNovo.setCPT_ENTRADA(0);
    //                    aux = movimento.getMOV_FORMAPGTO().replaceFirst("0+", "");
    //                } else {
    //                    condPagNovo.setCPT_ENTRADA(1);
    //                }
    //            }
    //
    //            tipoParcela = "m"; //soma meses
    //            if (aux.startsWith("+") && aux.endsWith("x")) {
    //                String s;
    //                s = aux.substring(1);
    //                condPagNovo.setCPT_QTDE_PARCELAS(Integer.valueOf(s.replace("x", "")));
    //            } else if (!aux.startsWith("+") && aux.endsWith("x")) {
    //                String s;
    //                s = aux;
    //                condPagNovo.setCPT_QTDE_PARCELAS(Integer.valueOf(s.replace("x", "")));
    //            } else {
    //                tipoParcela = "d"; //soma dias
    //                if (!aux.equals("0")) {
    //                    parcs = aux.split(" ");
    //
    //                    condPagNovo.setCPT_QTDE_PARCELAS(parcs.length);
    //                } else {
    //                    condPagNovo.setCPT_QTDE_PARCELAS(Integer.valueOf(aux));
    //                    condPagNovo.setCPT_ID(1);
    //                }
    //            }
    //
    //        } else {
    //            System.out.println("nulo ou vazio");
    //        }
    //
    //        ///////
    //        rdrPnCondPag = true;
    //
    //        qtdeparc = condPagNovo.getCPT_QTDE_PARCELAS();
    ////        if (qtdeparc > 0) {
    ////            for (int i = 1; i <= qtdeparc; i++) {
    ////                listQtdeParc.add(i);
    ////            }
    ////        } else {
    ////            listQtdeParc.add(0);
    ////        }
    //
    //        listGen = genericoBean.getListaSN();
    //        for (StatusOS g : listGen) {
    //            if (g.getGEN_ID() == (condPagNovo.getCPT_ENTRADA() + 100)) {
    //                entSelected = g;
    //                break;
    //            }
    //        }
    //        if (entSelected.getGEN_ID() == 100) {
    //            valorEntFat = movimento.getMOV_TOTAL() / (qtdeparc + 1);
    //            selectEnt = true;
    //        } else {
    //            if (condPagNovo.getCPT_ID() == 1) {
    //                selectEnt = false;
    //            } else {
    //                selectEnt = true;
    //            }
    //            valorEntFat = 0;
    //        }
    //
    //        calcValJuros();
    //        //gerar faturas
    //
    //        setShowFatBool(true);
    //        String idDoc = String.valueOf(movimento.getMOV_ID());
    //        parceiro = movimento.getParceiro();
    //        aVista = false;
    //        int p = 0; // contador for
    //        int foo = 0;
    //        int idx = 1;
    //        int idz = 0;
    //        double valorParc = 0;
    //        double correcao = 0;
    //        String entOrVista = "";
    //        String sinal = "";
    //        String tipo = movimento.getMOV_TIPO();
    //
    //        if (condPagNovo.getCPT_ID() == 1) {
    //            aVista = true;
    //            entOrVista = ", À Vista.";
    //        } else {
    //            aVista = false;
    //            entOrVista = ", Entrada.";
    //        }
    ////
    //        if (entSelected.getGEN_ID() == 100 || aVista) { //se tiver entrada
    //            foo = qtdeparc + 1;
    //            selectedTitulo = new Titulo[foo];
    //            if (aVista) {
    //                valorEntFat = totalFinal;
    //            } else {
    //                valorParc = (totalFinal - valorEntFat) / qtdeparc;
    //                valorParc = Util.convertDoubleMoeda(valorParc);
    //                correcao = Util.corrigirParc(valorParc, totalFinal, valorEntFat, qtdeparc, true);
    //            }
    //            p = 1;
    ////            cpMov[0] = new CondicaoPagamentoMovimento();
    ////            cpMov[0].setCPM_DATA(new Date());
    ////            cpMov[0].setCPM_DIAS("0");
    ////            cpMov[0].setCPM_VALOR(valorEntFat);
    //
    //            selectedTitulo[0] = new Titulo();
    //            selectedTitulo[0].setTIT_STATUS(0);
    ////            selectedTitulo[0].setTIT_TIPO("TMP");
    //            selectedTitulo[0].setTIT_TIPO("REC");
    ////            selectedTitulo[0].setTIT_PAGAMENTO(new Date());
    ////            selectedTitulo[0].setTIT_VALOR(valorEntFat);
    ////            selectedTitulo[0].setTIT_VALORPAGO(valorEntFat);
    //            selectedTitulo[0].setTIT_VALOR(cpMov.get(0).getCPM_VALOR());
    ////            selectedTitulo[0].setTIT_VALORPAGO(cpMov[0].getCPM_VALOR());
    //            selectedTitulo[0].setTIT_OBSERVACAO("OS " + idDoc + entOrVista);
    //            selectedTitulo[0].setTIT_VENCIMENTO(new Date());
    //            selectedTitulo[0].setContabancaria(cpMov.get(0).getContaBancaria());
    //            selectedTitulo[0].setDocCondPag(tipoDocCPSelected);
    //            selectedTitulo[0].setTIT_ENTRADA(true);
    //        } else { //sem entrada
    ////            cpMov = new CondicaoPagamentoMovimento[qtdeparc];
    //            selectedTitulo = new Titulo[qtdeparc];
    //            valorParc = totalFinal / qtdeparc;
    //            valorParc = Util.convertDoubleMoeda(valorParc);
    //            correcao = Util.corrigirParc(valorParc, totalFinal, 0, qtdeparc, false);
    //            p = 0;
    //        }
    //
    //        for (int i = p; i < cpMov.size(); i++) { //seta parcelas
    ////            cpMov[i] = new CondicaoPagamentoMovimento();
    ////
    ////
    ////            cpMov[i].setCPM_VALOR(valorParc + correcao);
    //
    //            selectedTitulo[i] = new Titulo();
    //            selectedTitulo[i].setTIT_TIPO("REC");
    ////            selectedTitulo[i].setTIT_VALOR(valorParc + correcao);
    //            selectedTitulo[i].setTIT_VALOR(cpMov.get(i).getCPM_VALOR());
    //            selectedTitulo[i].setTIT_OBSERVACAO("OS " + idDoc + ", parcela " + idx + "/" + qtdeparc);
    //            selectedTitulo[i].setTIT_DOC(idDoc + "/" + idx);//Nº do Documento para Gerar Boleto
    //            selectedTitulo[i].setContabancaria(cpMov.get(i).getContaBancaria());
    //            selectedTitulo[i].setDocCondPag(tipoDocCPSelected);
    //            selectedTitulo[i].setPlanoconta(movimento.getPlanoConta());
    //
    ////            Date dataVencTemp = new Date(movimento.getMOV_FINALIZACAO().getTime());
    //////            Date dataVencTemp = new Date();
    ////            Calendar c = Util.dateToCalendar(dataVencTemp);
    ////            if (tipoParcela.equals("d")) {
    ////                c.add(Calendar.DAY_OF_MONTH, +Integer.valueOf(parcs[idz]));
    ////            }
    ////            if (tipoParcela.equals("m")) {
    ////                c.add(Calendar.MONTH, +idx);
    ////            }
    ////            dataVencTemp = c.getTime();
    ////            cpMov[i].setCPM_DATA(dataVencTemp);
    ////            selectedTitulo[i].setTIT_VENCIMENTO(dataVencTemp);
    //            selectedTitulo[i].setTIT_VENCIMENTO(cpMov.get(i).getCPM_DATA());
    //            selectedTitulo[i].setTIT_ENTRADA(false);
    //            correcao = 0;
    //            idx++;
    //            idz++;
    //        }
    //
    //    }
    //    public String showNfes() {
    //
    ////        listNfe = nfeDao.getListaByPed(movimento.getMOV_TIPO(), movimento.getMOV_ID());
    //        listNfeMov = nfMovDao.getListaByPed(movimento.getMOV_TIPO(), movimento.getMOV_ID());
    //        listNfe = new ArrayList<>();
    //        for (NotaFiscalMovimento nfm : listNfeMov) {
    //            NotaFiscalPK nfPK = new NotaFiscalPK();
    //            nfPK.setNFF_ID(nfm.getNFF_ID());
    //            nfPK.setNFF_TIPO(nfm.getNFF_TIPO());
    //            nfPK.setNFF_SEQ(nfm.getNFF_SEQ());
    //            nfPK.setNFF_SERIE(nfm.getNFF_SERIE());
    //            nfPK.setPAR_ID(nfm.getPAR_ID());
    //            listNfe.add(nfeDao.getObjByIdPk(nfPK));
    //        }
    //
    ////        listNfse = nfseDao.getListaByPed(movimento.getMOV_TIPO(), movimento.getMOV_ID());
    //        listNfsMov = nfsMovDao.getListaByPed(movimento.getMOV_TIPO(), movimento.getMOV_ID());
    //        listNfse = new ArrayList<>();
    //        for (NotaFiscalServicoMovimento nfsm : listNfsMov) {
    //            NotaFiscalServicoPK nfsPK = new NotaFiscalServicoPK();
    //            nfsPK.setNFS_AMBIENTE(nfsm.getNFS_AMBIENTE());
    //            nfsPK.setNFS_RPSNUM(nfsm.getNFS_RPSNUM());
    //            nfsPK.setNFS_RPSSERIE(nfsm.getNFS_RPSSERIE());
    //            nfsPK.setNFS_RPSTIPO(nfsm.getNFS_RPSTIPO());
    //            nfsPK.setNFS_SEQ(nfsm.getNFS_SEQ());
    //            listNfse.add(nfseDao.getObjByIdPk(nfsPK));
    //        }
    //
    //        ID = String.valueOf(movimento.getMOV_ID());
    //        return "ordemserviconfe";
    //    }
    public void setHaServMov(boolean a) {
        //
    }

    public void setHaProdMov(boolean a) {
        //
    }

//    public boolean getHaServMov() {
//        for (MovimentoItem mitTemp : listMovimentoItem) {
//            if (mitTemp.getProduto().getPRO_TITEM() == 9) {
//                return true;
//            }
//        }
//        return false;
//    }
//    public boolean getHaProdMov() {
//        for (MovimentoItem mitTemp : listMovimentoItem) {
//            if (mitTemp.getProduto().getPRO_TITEM() != 9) {
//                return true;
//            }
//        }
//        return false;
//    }
//    public String novaNfe() {
//
//        boolean haProd = false;
//        for (MovimentoItem mitTemp : listMovimentoItem) {
//            if (mitTemp.getProduto().getPRO_TITEM() != 9) {
//                haProd = true;
//                break;
//            }
//        }
//
//        if (haProd) {
//            Util.gravarCookie("movToNfe", movimento.getMOV_ID() + "");
//            Util.gravarCookie("movTipoToNfe", movimento.getMOV_TIPO());
//            FacesContext context = FacesContext.getCurrentInstance();
//            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//            request.getSession().setAttribute("mov", movimento);
//            request.getSession().setAttribute("movis", selectedMov);
//            return "notafiscal";
//        } else {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Não há produtos, apenas serviços!");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            return "erro";
//        }
//    }
//    public String novaNfse() {
//        boolean haServ = false;
//        for (MovimentoItem mitTemp : listMovimentoItem) {
//            if (mitTemp.getProduto().getPRO_TITEM() == 9) {
//                haServ = true;
//                break;
//            }
//        }
//        if (haServ) {
//            Util.gravarCookie("movToNfse", movimento.getMOV_ID() + "");
//            Util.gravarCookie("movTipoToNfse", movimento.getMOV_TIPO());
//            FacesContext context = FacesContext.getCurrentInstance();
//            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//            request.getSession().setAttribute("mov", movimento);
//            request.getSession().setAttribute("movis", selectedMov);
//            return "notafiscalservico";
//        } else {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Não há serviços, apenas produtos!");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            return "erro";
//        }
//    }
//    public String exclusaoFatura() {
//        messageExc = "Deseja excluir todas as faturas?";
//        for (Titulo c : listTitulo) {
//            listCaixa = new ArrayList<>();
////            if (c.getTIT_STATUS() == 1) {
////                listCaixa = caixaDao.getListaByIDconta(c.getTIT_ID());
////                if (listCaixa != null) {
////                    messageExc = "As faturas que já foram quitadas terão seu registro no caixa também cancelados."
////                            + " Deseja excluir todas as faturas?";
////                    break;
////                }
////            }
//        }
//        setShowExclusao(true);
//        return "";
//    }
//    public String excluirFatura() {
//        for (Titulo c : listTitulo) {
//            listCaixa = new ArrayList<>();
//            if (c.getTIT_STATUS() == 1) {
//                listCaixa = caixaDao.getListaByIDconta(c.getTIT_ID());
//                if (listCaixa != null) {
//                    for (Titulo cx : listCaixa) {
//                        if (caixaDao.delete(cx)) {
//                            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Caixa excluido com sucesso!", "");
//                            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//                        } else {
//                            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível excluir caixa", "");
//                            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//                            return "erro";
//                        }
//                    }
//                }
//            }
//        }
//
//        for (Titulo c : listTitulo) {
//            if (tituloDao.delete(c)) {
//                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Conta excluida com sucesso!", "");
//                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            } else {
//                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível excluir conta", "");
//                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//                return "erro";
//            }
//        }
//        fatura = new Fatura();
//        fatura = listTitulo.get(0).getFatura();
//        fatDao.delete(listTitulo.get(0).getFatura());
//
//        faturaOds();
//        return "ordemservicofat";
//    }
//    public void atualizaStFinal() {
//        if (statusSelected != null) {
//            if (statusSelected.getGEN_ID() == 167) {
////                setIncFaturaBool(false);
////                setStEntBool(true);
//                setStRepBool(false);
//                setShowFatBool(false);
//            } else if (statusSelected.getGEN_ID() == 168) {
////                setIncFaturaBool(false);
////                setStEntBool(false);
//                setStRepBool(true);
//                setShowFatBool(false);
//            }
//        } else {
////            setIncFaturaBool(false);
////            setStEntBool(false);
//            setStRepBool(false);
//            setShowFatBool(false);
//        }
//    }
    public String novaNfeOds() {
        return "ordemservico";
    }

//    public String faturaOds() {
//        setShowExclusao(false);
//        listTitulo = tituloDao.getObjByMOV(movimento.getMOV_ID(), movimento.getMOV_TIPO());
//        if (listTitulo == null || listTitulo.isEmpty()) {
//            newFatBool = true;
//            criaFatBool = false;
//        } else {
//            newFatBool = false;
//            criaFatBool = false;
//        }
//        ID = String.valueOf(movimento.getMOV_ID());
//
//        return "ordemservicofat";
//    }
    public String imprimirOS() {
        return "ordemservicoprint";
    }

//    public String imprimir() {
//        String link = "";
////        if (Util.localhost) {
//            link = "/gdpc";
////        }
//        FacesContext context = FacesContext.getCurrentInstance();
//        String status = movimento.getMOV_STATUSSTR();
//        if (status.equals("Entregue")) {
//            link = link + "/RelatorioServlet?id=" + relDao.getDataBase() + "&nome=movimentoOdsEntregue&mov_id=" + movimento.getMOV_ID() + "&mov_tipo=" + movimento.getMOV_TIPO();
//        } else {
//            link = link + "/RelatorioServlet?id=" + relDao.getDataBase() + "&nome=movimentoOds&mov_id=" + movimento.getMOV_ID() + "&mov_tipo=" + movimento.getMOV_TIPO();
//        }
//        try {
//            context.getExternalContext().redirect(link);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            context.responseComplete();
//        }
//        return "erro";
//    }
    public String enviaEmail() {
        configuracao = Util.getConfiguracao();
        emailParceiro = movimento.getMOV_EMAIL();
        emailObs = configuracao.getTEXTO_EMAIL();
        setEmailBool(true);
        return "ordemservicoresumo";
    }

//    public String enviarEmail() throws MessagingException {
//        configuracao = Util.getConfiguracao();
//
//        String retorno = "erro";
//
//        String email = "";
//        String senha = "";
//
//        String usuario = "";
//        String smtp = "";
//        String porta = "";
//        int seguranca = 0;
//
//        String emailProprio = configuracao.getEMP_EMAILPROPRIO();
//        if (emailProprio.equals("1")) {
//            email = configuracao.getEMP_EMAILENVIO();
//            senha = configuracao.getEMP_SENHAEMAIL();
//            usuario = configuracao.getEMP_USUARIO();
//            smtp = configuracao.getEMP_SMTP();
//            porta = configuracao.getEMP_PORTA();
//            seguranca = configuracao.getEMP_SEGURANCA();
//        } else {
//            email = "atendimentogestorweb@gmail.com";
//            senha = "qwert4321";
//            usuario = configuracao.getEMP_FANTASIA();
//            smtp = "smtp.gmail.com";
//            porta = "587";
//            seguranca = 1;
//        }
//
//        final String emailFinal = email;
//        final String senhaFinal = senha;
//
//        Properties props = new Properties();
//        props.put("mail.transport.protocol", "smtp");//define protocolo de envio como SMTP
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", smtp);
//        props.put("mail.smtp.auth", true);//ativa autenticacao
//        props.put("mail.smtp.user", email);//usuario ou seja, a conta que esta enviando o email
//        props.put("mail.debug", true);
//        props.put("mail.smtp.port", porta);//porta
//        props.put("mail.smtp.socketFactory.port", porta);//mesma porta para o socket
//        props.put("mail.smtp.ssl.trust", smtp);
//        //  |  Automático "1"  |  SSL "2"  |  TLS 3  |
//        if (seguranca == 2) {
//            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        }
//        props.put("mail.smtp.socketFactory.fallback", false);
//
//        Authenticator autenticador = new Authenticator() {
//            @Override
//            public PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(emailFinal, senhaFinal);
//            }
//        };
//
//        try {
//            Session session = Session.getInstance(props, autenticador);
//            session.setDebug(false);
//            Message msg = new MimeMessage(session);
//            msg.setFrom(new InternetAddress(email));//email que enviará
//            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(emailParceiro));//email que receberá
//            String assunto = "Ordem de Servico " + movimento.getMOV_ID() + " - " + configuracao.getEMP_RAZAO();
//            msg.setSubject(assunto);
//
//            String textoEmail
//                    = "<p style='font-family: Arial'>"
//                    + "<small style='color:#999999'>*** Esse é um e-mail automático. Não é necessário respondê-lo ***</small>"
//                    + "<br/><br/><br/>Prezado(a),<br/>você está recebendo um e-mail de:<br/><br/><strong style='font-size:20px'>"
//                    + configuracao.getEMP_RAZAO()
//                    + "</strong><br/>" + configuracao.getEMP_EMAIL() + "<br/>"
//                    + configuracao.getEMP_FONE() + "<br/><br/><br/><br/>"
//                    + emailObs.replace("\n", "<br/>") + "<br/><br/><br/><br/>"
//                    + "<a href='http://www.gestorweb.com.br' alt='Sistema de Gestão Online'><img src='http://gestorweb.com.br/images/gw-email.png' /></a></p>"
//                    + "</p>";
//
//            MimeBodyPart mbp1 = new MimeBodyPart();
//            mbp1.setContent(textoEmail, "text/html; charset=utf-8");
//
//            MimeBodyPart mbp2 = new MimeBodyPart();
//            mbp2.setDataHandler(new DataHandler(new ByteArrayDataSource(msg.getContentType(), "application/x-any")));
//            mbp2.setDisposition(Part.ATTACHMENT);
//
//            // anexo //
//            String mov_id = String.valueOf(movimento.getMOV_ID());
//            String mov_tipo = movimento.getMOV_TIPO();
//            String caminho
//                    //                    = Util.getCaminhoAlternativo()
//                    = Util.getCaminho()
//                    + configuracao.getEMP_IDENTIFICADOR()
//                    + "/movimento/" + mov_tipo + "/"
//                    + "Ordem de Servico " + mov_id + ".pdf";
//
//            Connection conn;
//            RelatorioDao relDao = null;
//            if (configuracao.getEMP_IDENTIFICADOR().startsWith("x_")) {
//                relDao = new RelatorioDao(configuracao.getEMP_IDENTIFICADOR(), "varkon", "qwert1234");
//            } else {
//                relDao = new RelatorioDao("x_" + configuracao.getEMP_IDENTIFICADOR(), "varkon", "qwert1234");
//            }
//            conn = relDao.getConexao();
//
//            Class myClass = RelatorioServlet.class;
//            String jasperPath = myClass.getResource("RelatorioServlet.class").toString();
//            jasperPath = jasperPath.replace("file:/", "");
//
//            if (movimento.getMOV_STATUSSTR()
//                    .equals("Entregue")) {
//                jasperPath = jasperPath.replace("build/web/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "web/jaspers/movimentoEntregueRes.jasper");
//                jasperPath = jasperPath.replace("home/munaiaco/appservers/glassfish-3x/domains/domain1/applications/GestorWeb/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "/home/munaiaco/appservers/glassfish-3x/domains/domain1/applications/GestorWeb/jaspers/movimentoOdsEntregue.jasper");
//                jasperPath = jasperPath.replace("home/gestorwe/appservers/glassfish-3x/domains/domain1/applications/GestorWeb/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "/home/gestorwe/appservers/glassfish-3x/domains/domain1/applications/GestorWeb/jaspers/movimentoOdsEntregue.jasper");
//                jasperPath = jasperPath.replace("opt/glassfish3/glassfish/domains/domain1/applications/GestorWeb/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "/opt/glassfish3/glassfish/domains/domain1/applications/GestorWeb/jaspers/movimentoOdsEntregue.jasper");
//                jasperPath = jasperPath.replace("var/www/clients/client1/web1/web/glassfish/domains/domain1/applications/GestorWeb/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "/var/www/clients/client1/web1/web/glassfish/domains/domain1/applications/GestorWeb/jaspers/movimentoOdsEntregue.jasper");
//            } else {
//                jasperPath = jasperPath.replace("build/web/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "web/jaspers/movimentoRes.jasper");
//                jasperPath = jasperPath.replace("home/munaiaco/appservers/glassfish-3x/domains/domain1/applications/GestorWeb/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "/home/munaiaco/appservers/glassfish-3x/domains/domain1/applications/GestorWeb/jaspers/movimentoOds.jasper");
//                jasperPath = jasperPath.replace("home/gestorwe/appservers/glassfish-3x/domains/domain1/applications/GestorWeb/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "/home/gestorwe/appservers/glassfish-3x/domains/domain1/applications/GestorWeb/jaspers/movimentoOds.jasper");
//                jasperPath = jasperPath.replace("opt/glassfish3/glassfish/domains/domain1/applications/GestorWeb/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "/opt/glassfish3/glassfish/domains/domain1/applications/GestorWeb/jaspers/movimentoOds.jasper");
//                jasperPath = jasperPath.replace("var/www/clients/client1/web1/web/glassfish/domains/domain1/applications/GestorWeb/WEB-INF/classes/com/varkon/servlet/RelatorioServlet.class", "/var/www/clients/client1/web1/web/glassfish/domains/domain1/applications/GestorWeb/jaspers/movimentoOds.jasper");
//            }
//
//            HashMap parametros = new HashMap();
//            parametros.put("MOV_ID", mov_id);
//            parametros.put("MOV_TIPO", mov_tipo);
//            jp = JasperFillManager.fillReport(jasperPath, parametros, conn);
//
//            JasperExportManager.exportReportToPdfFile(jp, caminho);//indicar o caminho do arquivo.....
//
//            mbp2.attachFile(caminho);
//
//            Multipart mp = new MimeMultipart();
//            mp.addBodyPart(mbp1);
//            mp.addBodyPart(mbp2);
//
//            msg.setContent(mp);
//
//            msg.setSentDate(new Date());
//
//            if (!configuracao.getEMP_EMAILRESPOSTAOS().equalsIgnoreCase("")) {
//                msg.setReplyTo(new InternetAddress[]{new InternetAddress(usuario + "<" + configuracao.getEMP_EMAILRESPOSTAOS() + ">")});
//            } else if (emailProprio.equals("0")) {
//                msg.setReplyTo(new InternetAddress[]{new InternetAddress(usuario + "<" + configuracao.getEMP_EMAIL() + ">")});
//            }
//            msg.saveChanges();
//            Transport.send(msg);
//            FacesContext context = FacesContext.getCurrentInstance();
//            context.addMessage(null, new FacesMessage("Email Enviado com sucesso!", " "));
//
//            retorno = "";//retornar nada
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        setEmailBool(false);
//        return retorno;
//    }
//    public void exclusaoVerificaFaturas(ActionEvent ae) {
//        
//        boolean temFatura = false;
//        if (selectedMov == null || selectedMov.length == 0) {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir!", "Não há OS selecionada!!");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            return;
//        }
//        for (int i = 0; i < selectedMov.length; i++) {
//            listTitulo = tituloDao.getObjByMOV(selectedMov[i].getMOV_ID(), selectedMov[i].getMOV_TIPO());
//            if (listTitulo != null) {
//                if (!listTitulo.isEmpty()) {
//                    temFatura = true;
//                    break;
//                }
//            }
//        }
//        if (temFatura) {
//            setFaturasDelBool(true);
////            return "ordemservico";
//        } else {
//            exclusaoOS(ae);
//        }
//        redirect("ordemservico.xhtml");
//
//    }
    public void reabertura(ActionEvent ae) {
        msgExclusao = "";
//        listTitulo = tituloDao.getObjByMOV(movimento.getMOV_ID(), movimento.getMOV_TIPO());
//        if (listTitulo != null && !listTitulo.isEmpty()) {
//            for (Titulo selTit : listTitulo) {
//                if (selTit.getTIT_STATUS() == 1) {
//                    msgExclusao = "Esta OS possui faturas quitadas que serão excluidas, alterando o saldo do caixa.";
//                    break;
//                }
//            }
//        }

        setVisReabPed(true);
    }

    public void excluirOS() {

//        for (int i = 0; i < selectedMov.length; i++) {
//            if (!movDao.cancelDeleteMov(true, selectedMov[i])) {
//                return;
//            }
//        }
        setDelBool(false);
//        return "ordemservico";
    }

    public void validaDelete() {
        if (movimentoSelected.isEmpty()) {
            displayInfoMessageToUser("Selecione ao menos uma Ordem de Serviço.");
        } else {
            RequestContext.getCurrentInstance().execute("osDeleteDialogWidget.show()");
        }
    }

    public void deleteMovimento() {
        for (Movimento mov : movimentoSelected) {
            try {
                getMovimentoFacade().deleteMovimento(mov);
                closeDialog();
                displayInfoMessageToUser("Order de Serviço removida com sucesso.");
//                loadDogs();
//                resetDog();
            } catch (Exception e) {
                displayErrorMessageToUser(mov.getMOV_ID() + " não pode ser removido pois está em uso");
                keepDialogOpen();
                e.printStackTrace();
            }
        }
    }

    public void reabrirOS(ActionEvent ae) {
//        cancelarOS();
//        movDao.cancelDeleteMov(false, movimento);
        movimento.setMOV_FINALIZACAO(null);
        movimento.setMOV_STATUS(0);
        movimento.setMOV_REPROVADO(null);
        movDao.update(movimento);
        resumoOS();
        setVisReabPed(false);
//        return "ordemservicoresumo";
    }

    public void cancelaReabrOs(ActionEvent ae) {
        setVisReabPed(false);
    }

    public String cancelamentoVerificaFaturas() {
        boolean temFatura = false;
        if (movimento.getMOV_STATUS() == 9) { // status cancelado
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "OS já cancelada!", "Verifique sua seleção!");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return "erro";
        }
//        listTitulo = tituloDao.getObjByMOV(movimento.getMOV_ID(), movimento.getMOV_TIPO());
//        if (listTitulo != null) {
//            if (!listTitulo.isEmpty()) {
//                temFatura = true;
//            }
//        }
        if (temFatura) {
            setFaturasDelBool(true);
            return "ordemservicoResumo";
        } else {
            cancelamentoOS();
        }
        return "ordemservicoResumo";
    }

    public String cancelamentoOS() {

//        if (movimento.getMOV_STATUS() == 9) { // status cancelado
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "OS já cancelada!", "Verifique sua seleção!");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            return "erro";
//        }
        textCancel = "Deseja realmente cancelar a OS?";
        setVincContaBool(false);
        setCancelBool(true);
        setFaturasDelBool(false);
        return "ordemservicoresumo";
    }

    public String cancelarOS() {
        if (movimento.getMOV_STATUS() != 9) {
            movimento.setMOV_FINALIZACAO(new Date());
            movimento.setMOV_STATUS(9); // 160 + 9 = 169 (cancelado) -> genericobean
//            if (!movDao.cancelDeleteMov(false, movimento)) {
//                return "erro";
//            }
        }
        showOS();
        return "ordemservicoresumo";
    }

    public String sairCancelamento() {
        setFaturasDelBool(false);
        setCancelBool(false);
        setVincContaBool(false);
        return "ordemservicoresumo";
    }

    public void atualizaFiltro() {
        filtroBusca = "";

        if (!"".equals(cliCodBusca) && !" ".equals(cliCodBusca)) {
            filtroBusca = filtroBusca + "Código/Cliente '" + cliCodBusca + "'. ";
        }
        if (statusSelected != null) {
            filtroBusca = filtroBusca + "Status '" + statusSelected.getSOS_NOME() + "'. ";
        }
        if (tipoDataSelected != null) {
            filtroBusca = filtroBusca + "Data '" + tipoDataSelected.getGEN_NOME() + "'. ";
        }
        if (dataini != null && datafim != null) {
            filtroBusca = filtroBusca + " de " + sdf.format(dataini) + " até " + sdf.format(datafim) + "'. ";
        } else {
            if (dataini != null) {
                filtroBusca = filtroBusca + " a partir " + sdf.format(dataini) + "'.";
            }
            if (datafim != null) {
                filtroBusca = filtroBusca + " até " + sdf.format(datafim) + "'. ";
            }
        }

    }

    public String buscar() {
        tipoLista = 1;
        getDmMov();
        atualizaFiltro();
        return "ordemservico";
    }

    public void showOS() {
        tipoMovimento = "ODS";
        setCancelBool(false);
        setEmailBool(false);
//        setAgendaBool(false);
        setDelBool(false);
        setFaturasDelBool(false);
        cliCodBusca = "";
        dataini = null;
        datafim = null;
        tipoDataSelected = null;
        listGen = genericoBean.getListaTipoDataODS();
//        listBuscaOS = genericoBean.getListStODSBusca();
        /*
         for (StatusOS g : listBuscaOS) {
         if (g.getGEN_ID() == 170) {
         statusSelected = g;
         }
         }
         */
        statusSelected = null;
        getDmMov();
        atualizaFiltro();
        ascCod();
//        return "ordemservico";
        redirect("/pages/protected/distributor/ordemservico.xhtml");
    }

    /**
     *
     * @param ae
     */
    public void novo(ActionEvent ae) {
//        if (!new UsuarioLogadoBean().getFuncionarioPermissao(206).isPER_NEWALTER()) {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro.", "Você não possui permissão para executar esta operação.");
//            addMessage(message);
//            return;
//        }

        configuracao = Util.getConfiguracao();
        tipoAcao = "Inclusão de";
        setCmpFocus("osCliente");
        ID = "";
        movimento = new Movimento();
        movimento.setFINALIZADO(Boolean.FALSE);
//        titulo = new Titulo();
//        titulo.setDocCondPag(new CentroCusto(1, "Geral"));
        statusSelected = null;
//        listStOrdServ = genericoBean.getListStOrdServ();
//        for (StatusOS g : listStOrdServ) {
//            if (g.getGEN_ID() == 160) {
//                statusSelected = g;
//            }
//        }
        listMovimentoItem = new ArrayList<>();
        condPagDoc = "";
        condPagTipoInterv = "";
        labelJuroDesc = "Juros/Desconto";
        condPagInterv = "";
//        atualizaCondPag();
        Date data = new Date();
        data.setDate(data.getDate() + configuracao.getMOV_OSPREVISAO());
        movimento.setMOV_PREVISAO(data);
//        movimento.setMOV_TIPO("ODS");
        setRdrPnCondPag(false);
        setRdrDlgFinNF(false);
//        selectedTitulo = null;
//        filteredProdutos = getListProd();
        redirect("/pages/protected/distributor/ordemservicoCadastro.xhtml");
    }

    public void duplicar(ActionEvent a) {
//        if (!new UsuarioLogadoBean().getFuncionarioPermissao(206).isPER_NEWALTER()) {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro.", "Você não possui permissão para executar esta operação.");
//            addMessage(message);
//            return;
//        }

//        if (selectedMov.length == 0) {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Duplicar!", "Selecione ao menos um movimento!");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            return;
//        } else if (selectedMov.length > 1) {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Duplicar!", "Selecione apenas um movimento!");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            return;
//        }
//        movimento = selectedMov[0];
        configuracao = Util.getConfiguracao();
        tipoAcao = "Duplicação de";
        setCmpFocus("osCliente");
        ID = "";
        movimento = new Movimento();
//        titulo = new Titulo();
//        titulo.setDocCondPag(new CentroCusto(1, "Geral"));
        statusSelected = null;
//        listStOrdServ = genericoBean.getListStOrdServ();
//        for (StatusOS g : listStOrdServ) {
//            if (g.getGEN_ID() == 160) {
//                statusSelected = g;
//            }
//        }
//        listMovimentoItem = movItemDao.getListaByMov(movimento);
        movimento.setMOV_ID(0);
        condPagDoc = "";
        condPagTipoInterv = "";
        labelJuroDesc = "Juros/Desconto";
        condPagInterv = "";
//        atualizaCondPag();
        Date data = new Date();
        movimento.setMOV_EMISSAO(data);
        data.setDate(data.getDate() + configuracao.getMOV_OSPREVISAO());
        movimento.setMOV_PREVISAO(data);
        setRdrPnCondPag(false);
        setRdrDlgFinNF(false);
//        selectedTitulo = null;
//        filteredProdutos = getListProd();
        redirect("ordemservicoregistro.xhtml");

    }

    public String cancelamento() {
        return "ordemservico";
    }

    public void pesqMov() {
        if (dmMov != null) {
            movimento = dmMov.getRowData();
        }
        if (movimento != null && movimento.getMOV_ID() > 0) {
//            listStOrdServ = genericoBean.getListStODSBusca();
//            for (StatusOS g : listStOrdServ) {
//                if (g.getGEN_ID() == (movimento.getMOV_STATUS() + 160)) {//lista
//                    statusSelected = g;
//                    break;
//                }
//            }
//            atualizaCondPag();
//            listMovimentoItem = movItemDao.getListaByMov(movimento);
            dmMovItem = new ListDataModel<>(getListMovimentoItem());
        }

    }

    public void alterarOds() {
        tipoAcao = "Alteração da";
        dmMov = null;
//        pesqMov();
        if (movimento == null && movimento.getMOV_ID() == 0) {
            return;
        }

        if (movimento.getEquipamento().getEQP_CATID() == 1) {
            movimento.setListMovimentoItem(new ArrayList<>());
            for (MovimentoItem mi : getListMovimentoItemServico()) {
                movimento.getListMovimentoItem().add(mi);
            }
            for (MovimentoItem mi : getListMovimentoItemPeca()) {
                movimento.getListMovimentoItem().add(mi);
            }
        }

        ID = String.valueOf(movimento.getMOV_ID());
//        listStOrdServ = genericoBean.getListStOrdServ();
//        return "ordemservicoCadastro";
        redirect("/pages/protected/distributor/ordemservicoCadastro.xhtml");
    }

    public void resumoOS() {
        int status = 0;
//        agenda = new Agenda();
        setCmpFocus("osCliente");
        pesqMov();
        if (movimento == null && movimento.getMOV_ID() == 0) {
            return;
        }
        status = movimento.getMOV_STATUS() + 160;
        setParcial(0);
        verificaBt(status);
//        showNfes();
        ID = String.valueOf(movimento.getMOV_ID());
        dmMov = null;

        if (movimento.getEquipamento().getEQP_CATID() == 1) {
            setListMovimentoItemPeca(null);
            setListMovimentoItemServico(null);
            for (MovimentoItem mi : movimento.getListMovimentoItem()) {
                if (mi.getPeca() != null && mi.getServico() == null) {
                    getListMovimentoItemPeca().add(mi);
                }
                if (mi.getServico() != null && mi.getPeca() == null) {
                    getListMovimentoItemServico().add(mi);
                }
            }
        }

//        equipamento = movimento.getEquipamento();
//        faturaOds();
//        incFaturaBool = false;
        redirect("/pages/protected/distributor/ordemservicoResumo.xhtml");
//        redirect("/pages/protected/distributor/ordemservicoCadastro.xhtml");
    }

    public String sairFatura() {
        verificaBt((movimento.getMOV_STATUS() + 160));
        return "ordemservicoresumo";
    }

    public void verificaBt(Integer status) {
        if (status == 167) {
            setNfeVisBool(true);
        } else {
            setNfeVisBool(false);
        }
        if (status != 167
                && status != 168
                && status != 169) {
            setFinalBool(true);
        } else {
            setFinalBool(false);
        }
        if (status == 169) {
            setShowRep(true);
        } else {
            setShowRep(false);
        }
    }
//
//    public String salvarFinalizarOds() {
//        String retorno = "erro";
//
//        if (salvarOds().equalsIgnoreCase("ordemservicoresumo")) {
//            finOSRegistro();
////            if (cpMov == null) {
////                setIncFaturaBool(false);
////            } else {
////                setIncFaturaBool(true);
////            }
//            salvarFinalizacao();
//            retorno = "ordemservicoresumo";
//        }
//        return retorno;
//    }

    public void finalizaAoSalvar() {
        // entregue e com faturas
//        if (condPagSelected == null || condPagSelected.getCPT_ID() == 0) {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar!", "Selecione uma Condição de Pagamento!");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//        }
//        if (selectedTitulo == null || selectedTitulo.length == 0) {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar!", "Não existem faturas a serem salvas!!");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//        } else {
//            String doc = "OS " + String.valueOf(movimento.getMOV_ID());
//            fatura = new Fatura("REC", doc, new Date(), parceiro, getFuncionario(), movimento, getPlanoConta());
//            if (fatDao.insert(fatura)) {
//                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro salvo com sucesso!", "");
//                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            } else {
//                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível gravar fatura", "");
//                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            }
//        }
//
//        for (int i = 0; i < selectedTitulo.length; i++) {
//            selectedTitulo[i].setFatura(fatura);
//            selectedTitulo[i].setCentrocusto(movimento.getCentroCustoTmp());
//            if (tituloDao.insert(selectedTitulo[i])) {
//                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro salvo com sucesso!", "");
//                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            } else {
//                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível gravar registro", selectedTitulo[i].getTIT_OBSERVACAO());
//                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//                return;
//            }
//
//            if (entSelected.getGEN_ID() == 100 || aVista) {
////                        selectedTitulo[0].setCXA_EMISSAO(selectedTitulo[0].getTIT_PAGAMENTO());
//                selectedTitulo[0].setTIT_OBSERVACAO("Rec. " + selectedTitulo[0].getTIT_OBSERVACAO());
//                selectedTitulo[0].setTIT_VALORPAGO(selectedTitulo[0].getTIT_VALORPAGO());
////                    selectedTitulo[0].setTitulo(selectedTitulo[0]);
//                selectedTitulo[0].setContabancaria(selectedTitulo[0].getContabancaria());
//                selectedTitulo[0].setPlanoconta(getPlanoConta());
//                selectedTitulo[0].setCentrocusto(movimento.getCentroCustoTmp());
//
//                if (caixaDao.update(selectedTitulo[0])) {
//                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Caixa salvo com sucesso!", "");
//                    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//                } else {
//                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível gravar caixa", "");
//                    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//                    return;
//                }
//            }
//        }
//        for (MovimentoItem mi : listMovimentoItem) {
//            if ("S".equals(mi.getProduto().getPRO_CONTESTOQUE())) {
//                mi.setMIT_KDXTIPO(9);
//                mi.setMIT_KDXINDEX(-1);
//            } else {
//                mi.setMIT_KDXTIPO(0);
//            }
//        }

//        movimento.setMOV_STATUS(statusSelected.getSOS_ID());
//        salvar();
    }

    public void salvarOds(ActionEvent ae) {
        String resultado = "erro";
        int statusID = 0;

//        if (movimento.getParceiro() == null || movimento.getParceiro().getPAR_ID() <= 0) {
//            movimento.setParceiro(new ParceiroDao().getObjByNome("Consumidor Final"));
//        }
        if (movimento.getMOV_EMISSAO() == null) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar!", "Sem data de emissão!");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            resultado = "erro";
        }

        if (movimento.getMOV_PREVISAO() == null) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar!", "Sem data de previsão!");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            resultado = "erro";
        }

        if (movimento.getMOV_PREVISAO().before(movimento.getMOV_EMISSAO())) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar!", "Data de Previsão menor que a de Emissão!");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            resultado = "erro";
        }

        salvar();
    }

    public void salvar() {

        if (movimento.getEquipamento().getEQP_CATID() == 1) {
            movimento.setListMovimentoItem(new ArrayList<>());
            for (MovimentoItem mi : getListMovimentoItemServico()) {
                movimento.getListMovimentoItem().add(mi);
            }
            for (MovimentoItem mi : getListMovimentoItemPeca()) {
                movimento.getListMovimentoItem().add(mi);
            }
        }

        movimento.setListMovimentoItem(new ArrayList<>(movimento.getListMovimentoItem()));
//        EquipamentoServico es = movimento.getEquipamentoServico();
        movimento.getEquipamentoServico().setMovimento(movimento);
        movimento.getEquipamentoServico().setMANUTATUAL(movimento.getMOV_EMISSAO());
        movimento.setEquipamentoServico(movimento.getEquipamentoServico());
        movimento.getEquipamentoServico().setEquipamento(movimento.getEquipamento());
        movimento.getEquipamentoServico().setOS(true);
        movimento.getEquipamentoServico().setREALIZADO(true);
        movimento.setMOV_CONTATO(movimento.getParceiro().getPAR_CONTATO());

        if (movimento.getMOV_ID() == 0) {
            try {
                getMovimentoFacade().createMovimento(movimento);
                displayInfoMessageToUser("OS salva com sucesso.");
                showOS();
//                retorno = "parceiro";
            } catch (Exception e) {
                System.out.println(e.getMessage());
                displayErrorMessageToUser("Não foi possível salvar a OS.");
            }

        } else {
            try {
                getMovimentoFacade().updateMovimento(movimento);
                displayInfoMessageToUser("OS atualizada com sucesso.");
                showOS();
//                retorno = "parceiro";
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                displayErrorMessageToUser("Não foi possível atualizar a OS.");
            }
        }
    }

//    public String salvarFinalizarEmitirNFOds() {
//        String retorno = "erro";
//        if (salvarFinalizarOds().equalsIgnoreCase("ordemservicoresumo")) {
//            if (getHaProdMov() && getHaServMov()) {
////            tem produtos e serviços
//                rdrDlgFinNF = true;
////                retorno = "ordemservicoregistro";
//            } else if (getHaProdMov() && !getHaServMov()) {
////            tem apenas produtos
//                retorno = novaNfe();
//            } else if (!getHaProdMov() && getHaServMov()) {
////            tem apenas serviços
//                retorno = novaNfse();
//            }
//        }
//        return retorno;
//    }
//    public void inserirAgenda(ActionEvent actionEvent) {
//
//        agenda = movDao.buscarAgendaMovimento(movimento.getMOV_ID(), movimento.getMOV_TIPO());
//        setRdrBtExcluir(true);
//
//        if (agenda.getId() == 0) {
//            setRdrBtExcluir(false);
//            agenda.setDataInicio(movimento.getMOV_PREVISAO());
//            agenda.setDataFim(movimento.getMOV_PREVISAO());
//            agenda.setDescricao(movimento.getMOV_DEFEITO());
//            agenda.setDiaTodo(true);
//            agenda.setMOV_ID(movimento.getMOV_ID());
//            agenda.setMOV_TIPO(movimento.getMOV_TIPO());
//            agenda.setTitulo("Ordem de Serviço " + movimento.getMOV_ID());
//        } else {
//            setDefinirHorario(!agenda.isDiaTodo());
//        }
////        setAgendaBool(true);
////        return "ordemservicoresumo";
//    }
//    public void salvarAgenda() {
////        String retorno = "erro";
//
//        if (agenda.getTitulo().isEmpty()) {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:", "O agendamento deve possuir um Título");
//            addMessage(message);
////            retorno = "erro";
//        } else {
//            agenda.setDiaTodo(!definirHorario);
//            if (agenda.getId() == 0) {
//                if (agendaDao.insert(agenda)) {
//                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Movimento agendado com sucesso");
//                    addMessage(message);
////            agenda = new Agenda();
////                    setAgendaBool(false);
////                    retorno = "ordemservicoresumo";
//                }
//            } else {
//                if (agendaDao.update(agenda)) {
//                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Agendamento atualizado com sucesso");
//                    addMessage(message);
////            agenda = new Agenda();
////                    setAgendaBool(false);
////                    retorno = "ordemservicoresumo";
//                }
//            }
//        }
////        return retorno;
//    }
//    public void excluirAgenda() {
//        setRdrBtExcluir(false);
//        if (agendaDao.delete(agenda)) {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento Excluído com Sucesso", "");
//            addMessage(message);
//        }
////        setAgendaBool(false);
//    }
//    private boolean salvar() {
//        boolean retorno = false;
//        int i = 1;
//
//        if (movimento.getMOV_ID() == 0) {
//
//            int lastMOV_ID = Integer.valueOf(movDao.getLastID(movimento.getMOV_TIPO()));
//            if (lastMOV_ID >= 0) {
//                movimento.setMOV_ID(lastMOV_ID + 1);
//            }
//            if (movDao.insert(movimento)) {
//                retorno = true;
//                //adicionar aqui o movimentoItem
//                for (MovimentoItem ite : listMovimentoItem) {
//                    ite.getMovimentoItemPK().setMIT_ID(i);
//                    ite.setMovimento(movimento);
//                    if (!movItemDao.insert(ite)) {
//                        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possivel gravar o item", "");
//                        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//                    }
//                    if (ite.getMIT_KDXTIPO() > 0) {
//
//                        kardex = new Kardex(ite.getMIT_KDXTIPO(), ite.getMIT_KDXINDEX(), ite.getProduto(), movimento.getFuncionario(),
//                                ite.getMIT_QTDE(), ite.getMIT_VALORUNIT(), movimento.getParceiro(), ite, new Date());
//                        kdxDao.insert(kardex);
//                    }
//                    i++;
//                }
//
//                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro incluido com sucesso ", "");
//                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            } else {
//                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possivel gravar Orçamento", "");
//                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            }
//        } else {
//            if (movDao.update(movimento)) {
//                retorno = true;
//                //alterar aqui o movimentoItem
//                kdxDao.deleteByMOV(movimento);
//                movItemDao.deleteByMOV_ID(movimento.getMOV_ID(), movimento.getMOV_TIPO());
//                for (MovimentoItem ite : listMovimentoItem) {
//                    ite.getMovimentoItemPK().setMIT_ID(i);
//                    ite.setMovimento(movimento);
//                    if (!movItemDao.insert(ite)) {
//                        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possivel gravar o item", "");
//                        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//                    }
//
//                    if (ite.getMIT_KDXTIPO() > 0) {
//                        kardex = new Kardex(ite.getMIT_KDXTIPO(), ite.getMIT_KDXINDEX(), ite.getProduto(), getFuncionario(),
//                                ite.getMIT_QTDE(), ite.getMIT_VALORUNIT(), movimento.getParceiro(), ite, new Date());
//                        kdxDao.insert(kardex);
//                    }
//                    i++;
//                }
//
//                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro alterado com sucesso", "");
//                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            } else {
//                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao alterar produto", "");
//                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            }
//        }
//        return retorno;
//    }
//    public void atualizaCondPag() {
//        labelJuroDesc = "Juros";
//        condPagInterv = "";
//        valorJurosDesc = 0;
//        condPagDoc = "";
//        if (movimento.getCondicaoPagamento() != null && movimento.getCondicaoPagamento().getCPT_ID() > 1) {
//            listGen = genericoBean.getLista();
//            for (StatusOS gen : listGen) {
//                if (gen.getGEN_ID() == (movimento.getCondicaoPagamento().getCPT_TIPO_INTERVALO() + 120)) {
//                    setCondPagTipoInterv(gen.getGEN_NOME());
//                }
//            }
//            listDocCondPag = ccDao.getLista();
//            for (CondicaoPagamento dcp : listDocCondPag) {
//                if (dcp.getCPT_ID() == movimento.getCondicaoPagamento().getCPT_DOCUMENTO()) {
//                    setCondPagDoc(dcp.getCPT_NOME());
//                }
//            }
//            if (movimento.getCondicaoPagamento().getCPT_JUROS_DESC() > 0) {
//                valorJurosDesc = movimento.getCondicaoPagamento().getCPT_JUROS_DESC();
//            } else if (movimento.getCondicaoPagamento().getCPT_JUROS_DESC() < 0) {
//                valorJurosDesc = Math.abs(movimento.getCondicaoPagamento().getCPT_JUROS_DESC());
//            }
//            condPagInterv = movimento.getCondicaoPagamento().getCPT_QTDE_INTERVALO() + " - " + condPagTipoInterv;
//            condPagBool = true;
//        } else {
//            condPagBool = false;
//        }
//
//    }
//    public void AddMitLista(ActionEvent event) {
//        setaMIT(null, selectedProduto);
//    }
//    public String setaMIT(SelectEvent event, Produto prod) {
//        if (event != null) {
//            movimentoItem.setProduto((Produto) event.getObject());
//        } else if (prod != null) {
//            movimentoItem.setProduto(prod);
//        } else {
//            movimentoItem.setProduto(null);
//        }
//        String codigo = "";
//        if (movimentoItem.getProduto() != null && movimentoItem.getProduto().getPRO_ID() > 0) {
//            movimentoItem = new MovimentoItem(movimentoItem.getProduto());
//            movimentoItem.setMIT_VALORCUSTO(movimentoItem.getProduto().getPRO_VALORCUSTO());
//            //
//            if (selectedLp.getLPC_TIPO() == 0) {
//                movimentoItem.setMIT_VALORUNIT(movimentoItem.getProduto().getPRO_VALORVENDA());
//            } else {
//                movimentoItem.setMIT_VALORUNIT(calculaMitValUnitByListaPreco(movimentoItem.getProduto()));
//            }
//            //
//            movimentoItem.setMIT_OBSNFE(movimentoItem.getProduto().getPRO_OBSNFE());
//            pesqCod = String.valueOf(movimentoItem.getProduto().getPRO_ID());
//        } else if (!"0".equals(pesqCod) && !"".equals(pesqCod) && !" ".equals(pesqCod)) {
//            movimentoItem = new MovimentoItem();
//            codigo = getPesqCod();
//            produto = produtoDao.getProdByCod(codigo);
//            if (produto != null) {
//                movimentoItem = new MovimentoItem(produto);
//                movimentoItem.setMIT_VALORCUSTO(movimentoItem.getProduto().getPRO_VALORCUSTO());
//                //
//                if (selectedLp.getLPC_TIPO() == 0) {
//                    movimentoItem.setMIT_VALORUNIT(movimentoItem.getProduto().getPRO_VALORVENDA());
//                } else {
//                    movimentoItem.setMIT_VALORUNIT(calculaMitValUnitByListaPreco(movimentoItem.getProduto()));
//                }
//                //
//                movimentoItem.setMIT_OBSNFE(produto.getPRO_OBSNFE());
//            } else {
//                setCmpFocus(":fmordemservico:tabVPO:orcPro_id");
//                movimentoItem = new MovimentoItem();
//                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Produto não existe!", "Codigo digitado: " + codigo);
//                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//                return "erro";
//            }
//        } else {
//            movimentoItem.setMIT_VALORUNIT(0);
//            movimentoItem.setMIT_DESCONTO(0);
//            movimentoItem.setMIT_VALORCUSTO(0);
//        }
//        setCmpFocus("");
//        if (movimentoItem.getProduto() != null) {
//            setCmpFocus(":fmordemservico:tabVPO:proQuant");
//        }
//        return "erro";
//    }
//    private double calculaMitValUnitByListaPreco(Produto prod) {
//        double valunit = 0;
//        List<ListaPrecoItem> lstListaPrecoItem = lpiDao.getListaItemByListaPreco(selectedLp.getLPC_ID());
//        for (ListaPrecoItem listaPrecoItem : lstListaPrecoItem) {
//            if (listaPrecoItem.getPRODUTO().getPRO_ID() == prod.getPRO_ID()) {
//                switch (selectedLp.getLPC_TIPO()) {
//                    case 0:
//                        valunit = listaPrecoItem.getLPI_PRECO();
//                        break;
//                    case 1:
//                        valunit = listaPrecoItem.getLPI_PRECO();
//                        break;
//                    case 2:
////                        setTIPOSTR("Acréscimo (%)");
//                        valunit = (listaPrecoItem.getPRODUTO().getPRO_VALORVENDA() + ((listaPrecoItem.getPRODUTO().getPRO_VALORVENDA() * selectedLp.getLPC_VALOR()) / 100));
//                        break;
//                    case 3:
////                        setTIPOSTR("Desconto (%)");
//                        valunit = (listaPrecoItem.getPRODUTO().getPRO_VALORVENDA() - ((listaPrecoItem.getPRODUTO().getPRO_VALORVENDA() * selectedLp.getLPC_VALOR()) / 100));
//                        break;
//                }
//            }
//        }
//        return valunit;
//    }
//    public double calcTotByDescPorc(double descPorc) {
//        double tot = 0;
//        double descVal = 0;
//        double unitario = 0;
//        
//        if (descPorc >= 0 && descPorc <= 99.99) {
//            descVal = (descPorc / 100) * movimentoItem.getMIT_VALORUNIT();
//            unitario = movimentoItem.getMIT_VALORUNIT() - descVal;
//        } else {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no desconto!", "O desconto deve ser maior que 0% e menor 99.99%!");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//        }
//        unitario = Util.convertDoubleMoeda(unitario);
//        tot = unitario * movimentoItem.getMIT_QTDE();
//        
//        movimentoItem.setMIT_VALORUNIT(unitario);
//        
//        return Util.convertDoubleMoeda(tot);
//    }
//    
//    public String addMIT() {
//        double res = 0;
//        double vVenda = 0;
//        double qt = 0;
//        
////        qt = movimentoItem.getMIT_QTDE();
//        movimentoItem.setMIT_VALORTOT(calcTotByDescPorc(movimentoItem.getMIT_DESCONTO()));
////        movimentoItem.setMIT_VALORCUSTO(movimentoItem.getProduto().getPRO_VALORCUSTO() * qt);
//
////        if (movimentoItem.getProduto() == null || movimentoItem.getProduto().getPRO_ID() == 0) {
////            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao adicionar produto!", "Nenhum produto selecionado!");
////            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
////            return "erro";
////        }
////        if (movimento.getFuncionario() != null) {
////            vVenda = (movimentoItem.getProduto().getPRO_VALORVENDA() * movimento.getFuncionario().getFUN_DESCONTO_ITEM());
////            vVenda = vVenda / 100;
////            vVenda = (movimentoItem.getProduto().getPRO_VALORVENDA() - vVenda);
////        }
////        if (movimentoItem.getMIT_VALORUNIT() < movimentoItem.getProduto().getPRO_VALORVENDAMIN() || movimentoItem.getMIT_VALORUNIT() < vVenda) {
////            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valores incorretos!", "Valor Unitário menor que o mínimo de venda!");
////            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
////            return "erro";
////        }
////        for (MovimentoItem movItem : getListMovimentoItem()) {
////            if (movItem.getProduto().getPRO_ID() == movimentoItem.getProduto().getPRO_ID()) {
////                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item já adicionado!", "Remova o item da lista e adicione novamente");
////                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
////                return "erro";
////            }
////        }
////        if (estIgual) {
////            res = movimentoItem.getMIT_VALORTOT() / 100;
////            if (movimentoItem.getProduto().getPRO_D_IPI_MOD() == 201) {
////                movimentoItem.setMIT_VALORIPI(res * movimentoItem.getProduto().getPRO_D_IPI_ALIQ());
////            } else if (movimentoItem.getProduto().getPRO_D_IPI_MOD() == 202) {
////                movimentoItem.setMIT_VALORIPI(qt * movimentoItem.getProduto().getPRO_D_IPI_VAL_UNIT());
////            }
////        } else {
////            res = movimentoItem.getMIT_VALORTOT() / 100;
////            if (movimentoItem.getProduto().getPRO_D_IPI_MOD() == 201) {
////                movimentoItem.setMIT_VALORIPI(res * movimentoItem.getProduto().getPRO_F_IPI_ALIQ());
////            } else if (movimentoItem.getProduto().getPRO_D_IPI_MOD() == 202) {
////                movimentoItem.setMIT_VALORIPI(qt * movimentoItem.getProduto().getPRO_F_IPI_VAL_UNIT());
////            }
////        }
//        listMovimentoItem.add(movimentoItem);
//        
//        setCmpFocus(":fmordemservico:tabVPO:orcPro_id");
////        calcTot();
//        setPesqCod("");
//        movimentoItem = new MovimentoItem();
//        movItemSelected = null;
//        return "erro";
//    }
//    public String removerMIT() {
//        MovimentoItem movDel = dmMovItem.getRowData();
//        int indice = 0;
////        for (MovimentoItem itemAux : listMovimentoItem) {
////            if (itemAux.getProduto().getPRO_ID() == movDel.getProduto().getPRO_ID()) {
////                listMovimentoItem.remove(indice);
////                break;
////            }
////            indice++;
////        }
////        calcTot();
//        movItemSelected = null;
//        return "erro";
//    }
//    public void calcTot() {
//        movimento.setMOV_TOTALPRO(0);
//        movimento.setMOV_TOTAL(0);
//        movimento.setMOV_TOTALSERV(0);
//        movimento.setMOV_TOTALIPI(0);
////        for (MovimentoItem movItem : listMovimentoItem) {
////            if (movItem.getProduto().getPRO_TITEM() == 9) {
////                movimento.setMOV_TOTALSERV(movimento.getMOV_TOTALSERV() + movItem.getMIT_VALORTOT());
////            } else {
////                movimento.setMOV_TOTALPRO(movimento.getMOV_TOTALPRO() + movItem.getMIT_VALORTOT());
////            }
////            movimento.setMOV_TOTALIPI(movimento.getMOV_TOTALIPI() + movItem.getMIT_VALORIPI());
////        }
//        movimento.setMOV_TOTAL(movimento.getMOV_TOTALPRO() + movimento.getMOV_TOTALSERV() + movimento.getMOV_TOTALIPI() + movimento.getMOV_TOTALSEGURO()
//                + movimento.getMOV_TOTALOUTRAS() + movimento.getMOV_TOTALFRETE() - movimento.getMOV_TOTALDESC());
//    }
    public String submit() {
//        getQtdeSelect();
//        getTotalSelect();
        return "ordemservico";
    }

    //GET / SET
    public Movimento getMovimento() {
        return movimento;
    }

    public MovimentoItem getMovimentoItem() {
        return movimentoItem;
    }

//    public Caixa getCaixa() {
//        return caixa;
//    }
//
//    public void setCaixa(Caixa caixa) {
//        this.caixa = caixa;
//    }
    public void setMovimentoItem(MovimentoItem movimentoItem) {
        this.movimentoItem = movimentoItem;
    }

    public Parceiro getParceiro() {
        return parceiro;
    }

    public void setParceiro(Parceiro parceiro) {
        this.parceiro = parceiro;
    }

    public Funcionario getFuncionario() {
        funcionario = new Funcionario();
//        funcionario = new FuncionarioLogadoBean().getUser();
//        funcionario = funcionarioBean.getFuncionarioLogado();
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getEmailParceiroAux() {
        return emailParceiroAux;
    }

    public void setEmailParceiroAux(String emailParceiroAux) {
        this.emailParceiroAux = emailParceiroAux;
    }

    public FuncionarioBean getFuncionarioBean() {
        return funcionarioBean;
    }

    public void setFuncionarioBean(FuncionarioBean funcionarioBean) {
        this.funcionarioBean = funcionarioBean;
    }

//  
//
//    public PlanoConta getPlanoConta() {
//        configuracao = Util.getConfiguracao();
//        List<PlanoConta> listPC = new PlanoContaDao().getLista();
//        for (PlanoConta pc : listPC) {
//            if (pc.getPLC_ID() == configuracao.getPlanocontaOS().getPLC_ID()) {
//                if (pc.getPLC_ID() != 0) {
//                    planoConta = pc;
//                    break;
//                } else {
//                    planoConta = null;
//                }
//            }
//        }
//        return planoConta;
//    }
//
//    public void setPlanoConta(PlanoConta planoConta) {
//        this.planoConta = planoConta;
//    }
//    public Titulo[] getSelectedTitulo() {
//        return selectedTitulo;
//    }
//
//    public void setSelectedTitulo(Titulo[] selectedTitulo) {
//        this.selectedTitulo = selectedTitulo;
//    }
    public GenericoBean getGenericoBean() {
        return genericoBean;
    }

    public void setGenericoBean(GenericoBean genericoBean) {
        this.genericoBean = genericoBean;
    }

//    public DocCondPagamentoBean getDcPagBean() {
//        return dcPagBean;
//    }
//
//    public void setDcPagBean(DocCondPagamentoBean dcPagBean) {
//        this.dcPagBean = dcPagBean;
//    }
//
//    public FaturaDao getFatDao() {
//        return fatDao;
//    }
//
//    public void setFatDao(FaturaDao fatDao) {
//        this.fatDao = fatDao;
//    }
//
//    public KardexDao getKdxDao() {
//        return kdxDao;
//    }
//
//    public void setKdxDao(KardexDao kdxDao) {
//        this.kdxDao = kdxDao;
//    }
//
//    public TituloDao getTituloDao() {
//        return tituloDao;
//    }
//    public void setTituloDao(TituloDao tituloDao) {
//        this.tituloDao = tituloDao;
//    }
//
//    public CaixaDao getCaixaDao() {
//        return caixaDao;
//    }
//
//    public void setCaixaDao(CaixaDao caixaDao) {
//        this.caixaDao = caixaDao;
//    }
    public void setMovimento(Movimento movimento) {
        this.movimento = movimento;
    }

    public MovimentoDao getMovDao() {
        return movDao;
    }

    public void setMovDao(MovimentoDao movDao) {
        this.movDao = movDao;
    }
//
//    public ProdutoDao getProdutoDao() {
//        return produtoDao;
//    }

//    public void setProdutoDao(ProdutoDao produtoDao) {
//        this.produtoDao = produtoDao;
//    }
//    public CondicaoPagamentoDao getCondPagDao() {
//        return condPagDao;
//    }
//    public void setCondPagDao(CondicaoPagamentoDao condPagDao) {
//        this.condPagDao = condPagDao;
//    }
    public MovimentoItemDao getMovItemDao() {
        return movItemDao;
    }

    public void setMovItemDao(MovimentoItemDao movItemDao) {
        this.movItemDao = movItemDao;
    }

//    public ContaBancariaDao getCbDao() {
//        return cbDao;
//    }
//
//    public void setCbDao(ContaBancariaDao cbDao) {
//        this.cbDao = cbDao;
//    }
    public DataModel<Movimento> getDmMov() {
        dmMov = new ListDataModel<>(getListMov());
        return dmMov;
    }

    public void setDmMov(DataModel<Movimento> dmMov) {
        this.dmMov = dmMov;
    }

    public DataModel<MovimentoItem> getDmMovItem() {
        dmMovItem = new ListDataModel<>(getListMovimentoItem());
        return dmMovItem;
    }

    public void setDmMovItem(DataModel<MovimentoItem> dmMovItem) {
        this.dmMovItem = dmMovItem;
    }

//    public List<CondicaoPagamento> getListDocCondPag() {
//        return listDocCondPag;
//    }
    public List<Movimento> getListMov() {
        listMov = new ArrayList<>();
        if (tipoLista == 1) {
            listMov = movDao.getListaBuscaODS(campo, ordem, tipoMovimento, cliCodBusca, statusSelected, tipoDataSelected, dataini, datafim);
        } else {
            listMov = movDao.getListaBuscaODS(campo, ordem, tipoMovimento, "", statusSelected, null, null, null);
        }
        return listMov;
    }

    public void setListMov(List<Movimento> listMov) {
        this.listMov = listMov;
    }

    public List<Movimento> getMovimentoSelected() {
        return movimentoSelected;
    }

    public void setMovimentoSelected(List<Movimento> movimentoSelected) {
        this.movimentoSelected = movimentoSelected;
    }

//    public List<ContaBancaria> getListCBBoleto() {
//        listCBBoleto = new ArrayList<>();
//        listCBBoleto = cbDao.getListaBoleto();
//        return listCBBoleto;
//    }
//
//    public void setListCBBoleto(List<ContaBancaria> listCBBoleto) {
//        this.listCBBoleto = listCBBoleto;
//    }
//
//    public List<Titulo> getListBoleto() {
//        return listBoleto;
//    }
//
//    public void setListBoleto(List<Titulo> listBoleto) {
//        this.listBoleto = listBoleto;
//    }
//    public List<Titulo> getListTitulo() {
//        return listTitulo;
//    }
//
//    public void setListTitulo(List<Titulo> listTitulo) {
//        this.listTitulo = listTitulo;
//    }
//
//    public List<Titulo> getListCaixa() {
//        return listCaixa;
//    }
//
//    public void setListCaixa(List<Titulo> listCaixa) {
//        this.listCaixa = listCaixa;
//    }
//
//    public void setListDocCondPag(List<CondicaoPagamento> listDocCondPag) {
//        this.listDocCondPag = listDocCondPag;
//    }
//
//    public List<CondicaoPagamento> getListCondPag() {
//        listCondPag = condPagDao.getLista();
//        if (listCondPag == null) {
//            listCondPag = new ArrayList<>();
//        }
//        return listCondPag;
//    }
//
//    public void setListCondPag(List<CondicaoPagamento> listCondPag) {
//        this.listCondPag = listCondPag;
//    }
    public List<MovimentoItem> getListMovimentoItem() {
        if (listMovimentoItem == null) {
            listMovimentoItem = new ArrayList<>();
        }
        return listMovimentoItem;
    }

    public void setListMovimentoItem(List<MovimentoItem> listMovimentoItem) {
        this.listMovimentoItem = listMovimentoItem;
    }

    public List<MovimentoItem> getListMovimentoItemServico() {
        if (listMovimentoItemServico == null) {
            listMovimentoItemServico = new ArrayList();
        }
        return listMovimentoItemServico;
    }

    public void setListMovimentoItemServico(List<MovimentoItem> listMovimentoItemServico) {
        this.listMovimentoItemServico = listMovimentoItemServico;
    }

    public List<MovimentoItem> getListMovimentoItemPeca() {
        if (listMovimentoItemPeca == null) {
            listMovimentoItemPeca = new ArrayList();
        }
        return listMovimentoItemPeca;
    }

    public void setListMovimentoItemPeca(List<MovimentoItem> listMovimentoItemPeca) {
        this.listMovimentoItemPeca = listMovimentoItemPeca;
    }

    public List<StatusOS> getListStOrdServ() {
        return listStOrdServ;
    }

    public void setListStOrdServ(List<StatusOS> listStOrdServ) {
        this.listStOrdServ = listStOrdServ;
    }

//    public List<ContaBancaria> getListCB() {
//        listCB = new ArrayList<>();
//        listCB = new ContaBancariaDao<>().getLista();
//        return listCB;
//    }
//
//    public void setListCB(List<ContaBancaria> listCB) {
//        this.listCB = listCB;
//    }
    public List<Integer> getListQtdeParc() {
        return listQtdeParc;
    }

    public void setListQtdeParc(List<Integer> listQtdeParc) {
        this.listQtdeParc = listQtdeParc;
    }

    public MovimentoItem getMovItemSelected() {
        return movItemSelected;
    }

    public void setMovItemSelected(MovimentoItem movItemSelected) {
        this.movItemSelected = movItemSelected;
    }
//
//    public CondicaoPagamento getCondPagSelected() {
//        return condPagSelected;
//    }
//
//    public void setCondPagSelected(CondicaoPagamento condPagSelected) {
//        this.condPagSelected = condPagSelected;
//    }
//
//    public ContaBancaria getCbSelected() {
//        return cbSelected;
//    }
//
//    public void setCbSelected(ContaBancaria cbSelected) {
//        this.cbSelected = cbSelected;
//    }
//
//    public ContaBancaria getContaBancaria() {
//        return contaBancaria;
//    }
//
//    public void setContaBancaria(ContaBancaria contaBancaria) {
//        this.contaBancaria = contaBancaria;
//    }

    public StatusOS getStatusSelected() {
        return statusSelected;
    }

    public void setStatusSelected(StatusOS statusSelected) {
        this.statusSelected = statusSelected;
    }

    public StatusOS getEntSelected() {
        return entSelected;
    }

    public void setEntSelected(StatusOS entSelected) {
        this.entSelected = entSelected;
    }

//    public CondicaoPagamento getTipoDocCPSelected() {
//        return tipoDocCPSelected;
//    }
//
//    public void setTipoDocCPSelected(CondicaoPagamento tipoDocCPSelected) {
//        this.tipoDocCPSelected = tipoDocCPSelected;
//    }
    public String getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(String tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public String getEmailParceiro() {
        return emailParceiro;
    }

    public void setEmailParceiro(String emailParceiro) {
        this.emailParceiro = emailParceiro;
    }

    public String getEmailObs() {
        return emailObs;
    }

    public void setEmailObs(String emailObs) {
        this.emailObs = emailObs;
    }

    public String getRazaoParceiro() {
        return razaoParceiro;
    }

    public void setRazaoParceiro(String razaoParceiro) {
        this.razaoParceiro = razaoParceiro;
    }

    public String getCaminhoBoleto() {
        return caminhoBoleto;
    }

    public void setCaminhoBoleto(String caminhoBoleto) {
        this.caminhoBoleto = caminhoBoleto;
    }

    public String getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(String tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

    public String getPesqCod() {
        return pesqCod;
    }

    public void setPesqCod(String pesqCod) {
        this.pesqCod = pesqCod;
    }

    public String getCondPagDoc() {
        return condPagDoc;
    }

    public void setCondPagDoc(String condPagDoc) {
        this.condPagDoc = condPagDoc;
    }

    public String getCondPagTipoInterv() {
        return condPagTipoInterv;
    }

    public void setCondPagTipoInterv(String condPagTipoInterv) {
        this.condPagTipoInterv = condPagTipoInterv;
    }

    public String getLabelJuroDesc() {
        return labelJuroDesc;
    }

    public void setLabelJuroDesc(String labelJuroDesc) {
        this.labelJuroDesc = labelJuroDesc;
    }

    public String getCondPagInterv() {
        return condPagInterv;
    }

    public void setCondPagInterv(String condPagInterv) {
        this.condPagInterv = condPagInterv;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public String getFiltroBusca() {
        return filtroBusca;
    }

    public void setFiltroBusca(String filtroBusca) {
        this.filtroBusca = filtroBusca;
    }

    public String getCliCodBusca() {
        return cliCodBusca;
    }

    public void setCliCodBusca(String cliCodBusca) {
        this.cliCodBusca = cliCodBusca;
    }

    public String getTextCancel() {
        return textCancel;
    }

    public void setTextCancel(String textCancel) {
        this.textCancel = textCancel;
    }

    public String getMessageExc() {
        return messageExc;
    }

    public void setMessageExc(String messageExc) {
        this.messageExc = messageExc;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Date getDataini() {
        return dataini;
    }

    public void setDataini(Date dataini) {
        this.dataini = dataini;
    }

    public Date getDatafim() {
        return datafim;
    }

    public void setDatafim(Date datafim) {
        this.datafim = datafim;
    }

    public int getTipoLista() {
        return tipoLista;
    }

    public void setTipoLista(int tipoLista) {
        this.tipoLista = tipoLista;
    }

    public int getQtdeparc() {
        return qtdeparc;
    }

    public void setQtdeparc(int qtdeparc) {
        this.qtdeparc = qtdeparc;
    }

    public double getValorJurosDesc() {
        return valorJurosDesc;
    }

    public void setValorJurosDesc(double valorJurosDesc) {
        this.valorJurosDesc = valorJurosDesc;
    }

    public double getValorEntFat() {
        return valorEntFat;
    }

    public void setValorEntFat(double valorEntFat) {
        this.valorEntFat = valorEntFat;
    }

    public double getJuroDesc() {
        return juroDesc;
    }

    public void setJuroDesc(double juroDesc) {
        this.juroDesc = juroDesc;
    }

    public double getTaxaFixa() {

        return taxaFixa;
    }

    public void setTaxaFixa(double taxaFixa) {
        this.taxaFixa = taxaFixa;
    }

    public double getTotalFinal() {
        return totalFinal;
    }

    public void setTotalFinal(double totalFinal) {
        this.totalFinal = totalFinal;
    }

    public double getJurosValor() {
        return jurosValor;
    }

    public void setJurosValor(double jurosValor) {
        this.jurosValor = jurosValor;
    }

    public double getTotalSelect() {
        totalSelect = 0;
        if (!getMovimentoSelected().isEmpty()) {
            for (Movimento mov : getMovimentoSelected()) {
                totalSelect += mov.getMOV_TOTAL();
            }
        }
        return totalSelect;
    }

    public int getQtdeSelect() {
        qtdeSelect = 0;
        if (!getMovimentoSelected().isEmpty()) {
            qtdeSelect = getMovimentoSelected().size();
        }
        return qtdeSelect;
    }

    public void setQtdeSelect(int qtdeSelect) {
        this.qtdeSelect = qtdeSelect;
    }

    public void setTotalSelect(double totalSelect) {
        this.totalSelect = totalSelect;
    }

    public double getTotalList() {
        double totalList = 0;
        for (Movimento mov : getListMov()) {
            totalList += mov.getMOV_TOTAL();
        }
        return totalList;
    }

    public int getQtdeList() {
        int qtdeList = 0;
        for (Movimento mov : getListMov()) {
            qtdeList += 1;
        }
        return qtdeList;
    }

    public void setQtdeList(int qtdeList) {
        this.qtdeList = qtdeList;
    }

    public void setTotalLIst(double totalLIst) {
        this.totalLIst = totalLIst;
    }

    public boolean isEstIgual() {
        return estIgual;
    }

    public void setEstIgual(boolean estIgual) {
        this.estIgual = estIgual;
    }

    public boolean isBoletoBool() {
        return boletoBool;
    }

    public void setBoletoBool(boolean boletoBool) {
        this.boletoBool = boletoBool;
    }

    public boolean isEmailBool() {
        return emailBool;
    }

    public void setEmailBool(boolean emailBool) {
        this.emailBool = emailBool;
    }

//    public boolean isAgendaBool() {
//        return agendaBool;
//    }
//
//    public void setAgendaBool(boolean agendaBool) {
//        this.agendaBool = agendaBool;
//    }
    public Configuracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(Configuracao configuracao) {
        this.configuracao = configuracao;
    }

//    public Agenda getAgenda() {
//        return agenda;
//    }
//
//    public void setAgenda(Agenda agenda) {
//        this.agenda = agenda;
//    }
//
//    public RelatorioDao getRelDao() {
//        return relDao;
//    }
//
//    public void setRelDao(RelatorioDao relDao) {
//        this.relDao = relDao;
//    }
//
//    public CentroCustoDao getCcDao() {
//        return ccDao;
//    }
//    public void setCcDao(CentroCustoDao ccDao) {
//        this.ccDao = ccDao;
//    }
//
//    public NotaFiscalDao getNfeDao() {
//        return nfeDao;
//    }
//    public void setNfeDao(NotaFiscalDao nfeDao) {
//        this.nfeDao = nfeDao;
//    }
//
//    public NotaFiscalServicoDao getNfseDao() {
//        return nfseDao;
//    }
//
//    public void setNfseDao(NotaFiscalServicoDao nfseDao) {
//        this.nfseDao = nfseDao;
//    }
//
//    public AgendaDao getAgendaDao() {
//        return agendaDao;
//    }
//
//    public void setAgendaDao(AgendaDao agendaDao) {
//        this.agendaDao = agendaDao;
//    }
    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public ServletContext getSc() {
        return sc;
    }

    public void setSc(ServletContext sc) {
        this.sc = sc;
    }

    public JasperPrint getJp() {
        return jp;
    }

    public void setJp(JasperPrint jp) {
        this.jp = jp;
    }

    public FacesContext getFacesContext() {
        return facesContext;
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    public boolean isCancelBool() {
        return cancelBool;
    }

    public boolean isVincContaBool() {
        return vincContaBool;
    }

    public void setVincContaBool(boolean vincContaBool) {
        this.vincContaBool = vincContaBool;
    }

    public void setCancelBool(boolean cancelBool) {
        this.cancelBool = cancelBool;
    }

    public boolean isODS() {
        return ODS;
    }

    public void setODS(boolean ODS) {
        this.ODS = ODS;
    }

    public boolean isNfeVisBool() {
        return nfeVisBool;
    }

    public void setNfeVisBool(boolean nfeVisBool) {
        this.nfeVisBool = nfeVisBool;
    }

    public boolean isFinalBool() {
        return finalBool;
    }

    public void setFinalBool(boolean finalBool) {
        this.finalBool = finalBool;
    }

//    public boolean isIncFaturaBool() {
//        if (incFaturaBool == false) {
//            selectedTitulo = null;
//            setShowFatBool(false);
//            if (movimento.getCondicaoPagamento() != null) {
//                condPagSelected = movimento.getCondicaoPagamento();
//            } else {
//                for (CondicaoPagamento cp : listCondPag) {
//                    if (cp.getCPT_ID() == 1) {
//                        condPagSelected = cp;
//                        break;
//                    }
//                }
//            }
//            calcTotByCondPag();
//        }
//        return incFaturaBool;
//    }
//    public void setIncFaturaBool(boolean incFaturaBool) {
//        this.incFaturaBool = incFaturaBool;
//    }
//    public boolean isStEntBool() {
//        double x = 0;
//        for (Titulo t : listTitulo) {
//            x += t.getTIT_VALOR();
//        }
//        if (x >= movimento.getMOV_TOTAL()) {
//            stEntBool = false;
//        }
//        return stEntBool;
//    }
//    public void setStEntBool(boolean stEntBool) {
//        this.stEntBool = stEntBool;
//    }
    public boolean isStRepBool() {
        return stRepBool;
    }

    public void setStRepBool(boolean stRepBool) {
        this.stRepBool = stRepBool;
    }

    public boolean isShowFatBool() {
        return showFatBool;
    }

    public void setShowFatBool(boolean showFatBool) {
        this.showFatBool = showFatBool;
    }

    public boolean isShowRep() {
        if (movimento != null) {
            verificaBt(movimento.getMOV_STATUS() + 160);
        }
        return showRep;
    }

    public void setShowRep(boolean showRep) {
        this.showRep = showRep;
    }

    public boolean isTipoEntBool() {
        return tipoEntBool;
    }

    public void setTipoEntBool(boolean tipoEntBool) {
        this.tipoEntBool = tipoEntBool;
    }

    public boolean isShowExclusao() {
        return showExclusao;
    }

    public void setShowExclusao(boolean showExclusao) {
        this.showExclusao = showExclusao;
    }

    public boolean isNewFatBool() {
        return newFatBool;
    }

    public void setNewFatBool(boolean newFatBool) {
        this.newFatBool = newFatBool;
    }

    public boolean isCriaFatBool() {
        return criaFatBool;
    }

    public void setCriaFatBool(boolean criaFatBool) {
        this.criaFatBool = criaFatBool;
    }

    public boolean isaVista() {
        return aVista;
    }

    public void setaVista(boolean aVista) {
        this.aVista = aVista;
    }

    public boolean isCondPagBool() {
        return condPagBool;
    }

    public void setCondPagBool(boolean condPagBool) {
        this.condPagBool = condPagBool;
    }

    public boolean isSelectEnt() {
        return selectEnt;
    }

    public void setSelectEnt(boolean selectEnt) {
        this.selectEnt = selectEnt;
    }

    public boolean isRdrBtExcluir() {
        return rdrBtExcluir;
    }

    public void setRdrBtExcluir(boolean rdrBtExcluir) {
        this.rdrBtExcluir = rdrBtExcluir;
    }

    public boolean isDefinirHorario() {
        return definirHorario;
    }

    public void setDefinirHorario(boolean definirHorario) {
        this.definirHorario = definirHorario;
    }

    ////ORDENAÇÕES DA CONSULTA
    public void descCod() {
        campo = "MOV_ID";
        ordem = " DESC ";
    }

    public void ascCod() {
        campo = "MOV_ID";
        ordem = " ";
    }

    public void descEmi() {
        campo = "MOV_EMISSAO";
        ordem = " DESC ";
    }

    public void ascEmi() {
        campo = "MOV_EMISSAO";
        ordem = " ";
    }

    public void descPrev() {
        campo = "MOV_PREVISAO";
        ordem = " DESC ";
    }

    public void ascPrev() {
        campo = "MOV_PREVISAO";
        ordem = " ";
    }

    public void descParc() {
        campo = "parceiro.PAR_RAZAO";
        ordem = " DESC ";
    }

    public void ascParc() {
        campo = "parceiro.PAR_RAZAO";
        ordem = " ";
    }

    public void descFunc() {
        campo = "funcionario.FUN_NOME";
        ordem = " DESC ";
    }

    public void ascFunc() {
        campo = "funcionario.FUN_NOME";
        ordem = " ";
    }

    public void descFin() {
        campo = "MOV_FINALIZACAO";
        ordem = " DESC ";
    }

    public void ascFin() {
        campo = "MOV_FINALIZACAO";
        ordem = " ";
    }

    public void descVal() {
        campo = "MOV_TOTAL";
        ordem = " DESC ";
    }

    public void ascVal() {
        campo = "MOV_TOTAL";
        ordem = " ";
    }

    public void descSt() {
        campo = "MOV_STATUS";
        ordem = " DESC ";
    }

    public void ascSt() {
        campo = "MOV_STATUS";
        ordem = " ";
    }

    public boolean isDelBool() {
        return delBool;
    }

    public void setDelBool(boolean delBool) {
        this.delBool = delBool;
    }

//    public ListaPrecoDao getLpcDao() {
//        return lpcDao;
//    }
//
//    public void setLpcDao(ListaPrecoDao lpcDao) {
//        this.lpcDao = lpcDao;
//    }
//
//    public ListaPrecoItemDao getLpiDao() {
//        return lpiDao;
//    }
//
//    public void setLpiDao(ListaPrecoItemDao lpiDao) {
//        this.lpiDao = lpiDao;
//    }
    public boolean isFaturasDelBool() {
        return faturasDelBool;
    }

    public void setFaturasDelBool(boolean faturasDelBool) {
        this.faturasDelBool = faturasDelBool;
    }

    public boolean isCancBool() {
        cancBool = false;
        if (movimento != null && movimento.getMOV_STATUS() != 9) {
            cancBool = true;
        }
        return cancBool;
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

    public List<PecaEqs> getListPecasEqs() {
        if (listPecasEqs == null) {
            listPecasEqs = new ArrayList<>();
        }
        return listPecasEqs;
    }

    public void setListPecasEqs(List<PecaEqs> listPecasEqs) {
        this.listPecasEqs = listPecasEqs;
    }

    public void setCancBool(boolean cancBool) {
        this.cancBool = cancBool;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String getAlert() {
        String alertTemp = alert;
        alert = "";
        return alertTemp;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getPatternAgenda() {
        if (definirHorario) {
            patternAgenda = "dd/MM/yyyy HH:mm";
        } else {
            patternAgenda = "dd/MM/yyyy";
        }
        return patternAgenda;
    }

    public void setPatternAgenda(String patternAgenda) {
        this.patternAgenda = patternAgenda;
    }

//    public List<NotaFiscal> getListNfe() {
//        return listNfe;
//    }
//
//    public void setListNfes(List<NotaFiscal> listNfe) {
//        this.listNfe = listNfe;
//    }
//
//    public DataModel<NotaFiscal> getDmNfe() {
//        dmNfe = new ListDataModel<>(getListNfe());
//        return dmNfe;
//    }
//    public void setDmNfe(DataModel<NotaFiscal> dmNfe) {
//        this.dmNfe = dmNfe;
//    }
//
//    public List<NotaFiscalServico> getListNfse() {
//        return listNfse;
//    }
//
//    public void setListNfse(List<NotaFiscalServico> listNfse) {
//        this.listNfse = listNfse;
//    }
//    public DataModel<NotaFiscalServico> getDmNfse() {
//        dmNfse = new ListDataModel<>(getListNfse());
//        return dmNfse;
//    }
//
//    public void setDmNfse(DataModel<NotaFiscalServico> dmNfse) {
//        this.dmNfse = dmNfse;
//    }
//
//    public MovimentoHistorico getMovHis() {
//        return movHis;
//    }
//
//    public void setMovHis(MovimentoHistorico movHis) {
//        this.movHis = movHis;
//    }
//    public DataModel<MovimentoHistorico> getDmHist() {
//        dmHist = new ListDataModel<>(getListPh());
//        return dmHist;
//    }
//
//    public void setDmHist(DataModel<MovimentoHistorico> dmHist) {
//        this.dmHist = dmHist;
//    }
//    public List<MovimentoHistorico> getListPh() {
//        listPh = movDao.getListaHistoricoMovimento(movimento.getMOV_TIPO(), movimento.getMOV_ID());
//        return listPh;
//    }
//
//    public void setListPh(List<MovimentoHistorico> listPh) {
//        this.listPh = listPh;
//    }
//
//    public String historico() throws SQLException {
//        carregaComentario();
//        return "ordemservicohistorico";
//    }
//    public void inserirComentario() throws SQLException {
//        movHis.setHIS_COMENTARIO(movHis.getHIS_COMENTARIO().trim());
//        if (movHis.getHIS_COMENTARIO().equals("")) {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não é possível salvar", "Insira um comentário");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//        } else {
//            if (movDao.insert(movHis)) {
//                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro salvo com sucesso!", "");
//                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            } else {
//                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível gravar registro", "");
//                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            }
//        }
//        carregaComentario();
//    }
//    public void excluirComentario() {
//        MovimentoHistorico movHisDel = dmHist.getRowData();
//        if (movDao.delete(movHisDel)) {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro removido com sucesso!", "");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//        } else {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível remover registro", "");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//        }
//    }
//
//    public void carregaComentario() throws SQLException {
//        movHis = new MovimentoHistorico(movimento.getMOV_TIPO(), movimento.getMOV_ID());
//        movHis.setHIS_EMISSAO(new Date());
//        movHis.setHIS_FUN(new UsuarioLogadoBean().getUsuarioLogado());
//    }
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

//    public ListaPreco getSelectedLp() {
//        return selectedLp;
//    }
//
//    public void setSelectedLp(ListaPreco selectedLp) {
//        this.selectedLp = selectedLp;
//    }
//    public Produto getSelectedProduto() {
//        return selectedProduto;
//    }
//
//    public void setSelectedProduto(Produto selectedProduto) {
//        this.selectedProduto = selectedProduto;
//    }
//    public boolean isRdrLabelListaPreco() {
//        if (getDmLPC().getRowCount() > 1) {
//            setRdrLabelListaPreco(true);
//        } else {
//            setRdrLabelListaPreco(false);
//        }
//
//        return rdrLabelListaPreco;
//    }
//    public DataModel<ListaPreco> getDmLPC() {
//        dmLPC = new ListDataModel<>(getListLPC());
//        return dmLPC;
//    }
//
//    public void setDmLPC(DataModel<ListaPreco> dmLPC) {
//        this.dmLPC = dmLPC;
//    }
//
//    public List<ListaPreco> getListLPC() {
//        setListLPC(lpcDao.getTodasListas());
//        return listLPC;
//    }
//    public void setListLPC(List<ListaPreco> listLPC) {
//        this.listLPC = listLPC;
//    }
//
//    public DataModel<Produto> getDmProd() {
//        dmProd = new ListDataModel<>(getListProd());
//        return dmProd;
//    }
//    public void setDmProd(DataModel<Produto> dmProd) {
//        this.dmProd = dmProd;
//    }
//
//    public List<Produto> getListProd() {
//        setListProd(produtoDao.getLista());
//        return listProd;
//    }
//    public void setListProd(List<Produto> listProd) {
//        this.listProd = listProd;
//    }
//    public List<Produto> getFilteredProdutos() {
//        return filteredProdutos;
//    }
//
//    public void setFilteredProdutos(List<Produto> filteredProdutos) {
//        this.filteredProdutos = filteredProdutos;
//    }
    public double getTotalLIst() {
        return totalLIst;
    }

//    public void setListNfe(List<NotaFiscal> listNfe) {
//        this.listNfe = listNfe;
//    }
    public void setRdrLabelListaPreco(boolean rdrLabelListaPreco) {
        this.rdrLabelListaPreco = rdrLabelListaPreco;
    }

    public boolean isRdrPnCondPag() {
        return rdrPnCondPag;
    }

    public void setRdrPnCondPag(boolean rdrPnCondPag) {
        this.rdrPnCondPag = rdrPnCondPag;
    }

    public boolean isRdrDlgFinNF() {
        return rdrDlgFinNF;
    }

    public void setRdrDlgFinNF(boolean rdrDlgFinNF) {
        this.rdrDlgFinNF = rdrDlgFinNF;
    }
//
//    public NotaFiscalMovimentoDao getNfMovDao() {
//        return nfMovDao;
//    }
//
//    public void setNfMovDao(NotaFiscalMovimentoDao nfMovDao) {
//        this.nfMovDao = nfMovDao;
//    }
//
//    public NotaFiscalServicoMovimentoDao getNfsMovDao() {
//        return nfsMovDao;
//    }

//    public void setNfsMovDao(NotaFiscalServicoMovimentoDao nfsMovDao) {
//        this.nfsMovDao = nfsMovDao;
//    }
//
//    public List<NotaFiscalMovimento> getListNfeMov() {
//        return listNfeMov;
//    }
//
//    public void setListNfeMov(List<NotaFiscalMovimento> listNfeMov) {
//        this.listNfeMov = listNfeMov;
//    }
//    public List<NotaFiscalServicoMovimento> getListNfsMov() {
//        return listNfsMov;
//    }
//
//    public void setListNfsMov(List<NotaFiscalServicoMovimento> listNfsMov) {
//        this.listNfsMov = listNfsMov;
//    }
    public String getMsgExclusao() {
        return msgExclusao;
    }

    public void setMsgExclusao(String msgExclusao) {
        this.msgExclusao = msgExclusao;
    }

    public boolean isVisReabPed() {
        return visReabPed;
    }

    public void setVisReabPed(boolean visReabPed) {
        this.visReabPed = visReabPed;
    }

//    public boolean isIncFaturaResumoBool() {
//        double x = 0;
//        for (Titulo t : listTitulo) {
//            x += t.getTIT_VALOR();
//        }
//        if (x >= movimento.getMOV_TOTAL()) {
//            incFaturaResumoBool = false;
//        } else {
//            incFaturaResumoBool = true;
//        }
//        return incFaturaResumoBool;
//    }
    public void setIncFaturaResumoBool(boolean incFaturaResumoBool) {
        this.incFaturaResumoBool = incFaturaResumoBool;
    }

//    public void limparFaturas(ActionEvent ae) {
//        cpMov = null;
//        listenerValorFaturas();
//    }
//
//    public void condPagSelect(ActionEvent ae) {
//        listQtdeParc = new ArrayList<>();
//        String parcs[] = null;
//        String tipoParcela = "";
//        listDocCondPag = condPagDao.getLista();
//        qtdeparc = 0;
//        valorEntFat = 0;
//        condPagNovo = new CondicaoPagamento();
//        condPagNovo.setCPT_TIPO_INTERVALO(1);
//
//        ///////////////////////////
//        if (movimento.getMOV_FORMAPGTO() != null && !movimento.getMOV_FORMAPGTO().trim().isEmpty()) {
//            movimento.setMOV_FORMAPGTO(movimento.getMOV_FORMAPGTO().trim());
//            String aux = movimento.getMOV_FORMAPGTO();
//            int i = 0;
//            if (movimento.getMOV_FORMAPGTO().startsWith("0 ")) {
//                condPagNovo.setCPT_ENTRADA(0);
//                aux = movimento.getMOV_FORMAPGTO().replaceFirst("0 ", "");
//            } else {
//                if (movimento.getMOV_FORMAPGTO().startsWith("0+")) {
//                    condPagNovo.setCPT_ENTRADA(0);
//                    aux = movimento.getMOV_FORMAPGTO().replaceFirst("0+", "");
//                } else {
//                    condPagNovo.setCPT_ENTRADA(1);
//                }
//            }
//
//            tipoParcela = "m"; //soma meses
//            if (aux.startsWith("+") && aux.endsWith("x")) {
//                String s;
//                s = aux.substring(1);
//                condPagNovo.setCPT_QTDE_PARCELAS(Integer.valueOf(s.replace("x", "")));
//            } else if (!aux.startsWith("+") && aux.endsWith("x")) {
//                String s;
//                s = aux;
//                condPagNovo.setCPT_QTDE_PARCELAS(Integer.valueOf(s.replace("x", "")));
//            } else {
//                tipoParcela = "d"; //soma dias
//                if (!aux.equals("0")) {
//                    parcs = aux.split(" ");
//
//                    condPagNovo.setCPT_QTDE_PARCELAS(parcs.length);
//                } else {
//                    condPagNovo.setCPT_QTDE_PARCELAS(Integer.valueOf(aux));
//                    condPagNovo.setCPT_ID(1);
//                }
//            }
//
//        } else {
//            System.out.println("nulo ou vazio");
//        }
//
//        ///////
//        rdrPnCondPag = true;
//
//        qtdeparc = condPagNovo.getCPT_QTDE_PARCELAS();
////        if (qtdeparc > 0) {
////            for (int i = 1; i <= qtdeparc; i++) {
////                listQtdeParc.add(i);
////            }
////        } else {
////            listQtdeParc.add(0);
////        }
//
//        listGen = genericoBean.getListaSN();
//        for (StatusOS g : listGen) {
//            if (g.getGEN_ID() == (condPagNovo.getCPT_ENTRADA() + 100)) {
//                entSelected = g;
//                break;
//            }
//        }
//        if (entSelected.getGEN_ID() == 100) {
//            double valorFaturar = movimento.getMOV_TOTAL();
//            if (!listTitulo.isEmpty()) {
//                for (Titulo t : listTitulo) {
//                    valorFaturar -= t.getTIT_VALOR();
//                }
//            }
//            valorEntFat = valorFaturar / (qtdeparc + 1);
////            valorEntFat = movimento.getMOV_TOTAL() / (qtdeparc + 1);
//            selectEnt = true;
//        } else {
//            if (condPagNovo.getCPT_ID() == 1) {
//                selectEnt = false;
//            } else {
//                selectEnt = true;
//            }
//            valorEntFat = 0;
//        }
//
//        calcValJuros();
//        //gerar faturas
//
//        setShowFatBool(true);
//        String idDoc = String.valueOf(movimento.getMOV_ID());
//        parceiro = movimento.getParceiro();
//        aVista = false;
//        int p = 0; // contador for
//        int foo = 0;
//        int idx = 1;
//        int idz = 0;
//        double valorParc = 0;
//        double correcao = 0;
//        String entOrVista = "";
//        String sinal = "";
//        String tipo = movimento.getMOV_TIPO();
//
//        if (condPagNovo.getCPT_ID() == 1) {
//            aVista = true;
//            entOrVista = ", À Vista.";
//        } else {
//            aVista = false;
//            entOrVista = ", Entrada.";
//        }
////
//        if (entSelected.getGEN_ID() == 100 || aVista) { //se tiver entrada
//            foo = qtdeparc + 1;
////            selectedTitulo = new Titulo[foo];
////            cpMov = new CondicaoPagamentoMovimento[foo];
//            cpMov = new ArrayList<>();
//            if (aVista) {
//                valorEntFat = totalFinal;
//            } else {
//                valorParc = (totalFinal - valorEntFat) / qtdeparc;
//                valorParc = Util.convertDoubleMoeda(valorParc);
//                correcao = Util.corrigirParc(valorParc, totalFinal, valorEntFat, qtdeparc, true);
//            }
//            p = 1;
//            cpMovObj = new CondicaoPagamentoMovimento();
//            cpMovObj.setId(0L);
////            cpMov.get(0) = new CondicaoPagamentoMovimento();
//            cpMovObj.setCPM_DATA(new Date());
//            cpMovObj.setCPM_DIAS("0");
//            cpMovObj.setCPM_VALOR(valorEntFat);
//
//            cpMov.add(cpMovObj);
//
////            selectedTitulo[0] = new Titulo();
////            selectedTitulo[0].setTIT_STATUS(1);
//////            selectedTitulo[0].setTIT_TIPO("TMP");
////            selectedTitulo[0].setTIT_TIPO("CXA");
////            selectedTitulo[0].setTIT_PAGAMENTO(new Date());
////            selectedTitulo[0].setTIT_VALOR(valorEntFat);
////            selectedTitulo[0].setTIT_VALORPAGO(valorEntFat);
////            selectedTitulo[0].setTIT_OBSERVACAO("Venda " + idDoc + entOrVista);
////            selectedTitulo[0].setTIT_VENCIMENTO(new Date());
////            selectedTitulo[0].setContabancaria(condPagNovo.getConta());
////            selectedTitulo[0].setDocCondPag(tipoDocCPSelected);
////            selectedTitulo[0].setTIT_ENTRADA(true);
//        } else { //sem entrada
////            cpMov = new CondicaoPagamentoMovimento[qtdeparc];
//            cpMov = new ArrayList<>();
////            cpMovObj = new CondicaoPagamentoMovimento();
////            cpMovObj.setId(0L);
//
////            selectedTitulo = new Titulo[qtdeparc];
//            valorParc = totalFinal / qtdeparc;
//            valorParc = Util.convertDoubleMoeda(valorParc);
//            correcao = Util.corrigirParc(valorParc, totalFinal, 0, qtdeparc, false);
//            p = 1;
////            cpMov.add(cpMovObj);
//        }
//
//        for (int i = p; i <= qtdeparc; i++) { //seta parcelas
////            cpMov[i] = new CondicaoPagamentoMovimento();
//            cpMovObj = new CondicaoPagamentoMovimento();
//            cpMovObj.setId(0L);
//
//            cpMovObj.setCPM_VALOR(valorParc + correcao);
//
////            selectedTitulo[i] = new Titulo();
////            selectedTitulo[i].setTIT_TIPO("REC");
////            selectedTitulo[i].setTIT_VALOR(valorParc + correcao);
////            selectedTitulo[i].setTIT_OBSERVACAO("Venda " + idDoc + ", parcela " + idx + "/" + qtdeparc);
////            selectedTitulo[i].setTIT_DOC(idDoc + "/" + idx);//Nº do Documento para Gerar Boleto
////            selectedTitulo[i].setContabancaria(condPagNovo.getConta());
////            selectedTitulo[i].setDocCondPag(tipoDocCPSelected);
//            //
////            Date dataVencTemp = new Date(movimento.getMOV_FINALIZACAO().getTime());
//            Date dataVencTemp = new Date();
//            Calendar c = Util.dateToCalendar(dataVencTemp);
//            if (tipoParcela.equals("d")) {
//                c.add(Calendar.DAY_OF_MONTH, +Integer.valueOf(parcs[idz]));
//                cpMovObj.setCPM_DIAS(parcs[idz]);
//            }
//            if (tipoParcela.equals("m")) {
//                c.add(Calendar.MONTH, +idx);
//                cpMovObj.setCPM_DIAS(String.valueOf(idz));
//            }
//            dataVencTemp = c.getTime();
//            cpMovObj.setCPM_DATA(dataVencTemp);
//
////            selectedTitulo[i].setTIT_VENCIMENTO(dataVencTemp);
////            selectedTitulo[i].setTIT_ENTRADA(false);
//            correcao = 0;
//            idx++;
//            idz++;
//            cpMov.add(cpMovObj);
//        }
//        listenerValorFaturas();
//    }
//
//    public void listenerValorFaturas() {
//        setParcial(0);
//        if (cpMov != null) {
//            for (int i = 0; i < cpMov.size(); i++) {
//                setParcial(getParcial() + cpMov.get(i).getCPM_VALOR());
//            }
//        }
//    }
//    public CondicaoPagamentoMovimento[] getCpMov() {
//        return cpMov;
//    }
//
//    public void setCpMov(CondicaoPagamentoMovimento[] cpMov) {
//        this.cpMov = cpMov;
//    }
//    public CondicaoPagamento getCondPagNovo() {
//        return condPagNovo;
//    }
//
//    public void setCondPagNovo(CondicaoPagamento condPagNovo) {
//        this.condPagNovo = condPagNovo;
//    }
    public double getParcial() {
        return parcial;
    }

    public void setParcial(double parcial) {
        this.parcial = parcial;
    }

//    public List<CondicaoPagamentoMovimento> getCpMov() {
//        return cpMov;
//    }
//    public void setCpMov(List<CondicaoPagamentoMovimento> cpMov) {
//        this.cpMov = cpMov;
//    }
//
//    public CondicaoPagamentoMovimento getCpMovObj() {
//        return cpMovObj;
//    }
//
//    public void setCpMovObj(CondicaoPagamentoMovimento cpMovObj) {
//        this.cpMovObj = cpMovObj;
//    }
    /**
     * @return the cmpFocus
     */
    public String getCmpFocus() {
        return cmpFocus;
    }

    /**
     * @param cmpFocus the cmpFocus to set
     */
    public void setCmpFocus(String cmpFocus) {
        this.cmpFocus = cmpFocus;
    }

//    public Equipamento getEquipamento() {
//        if (equipamento == null) {
//            equipamento = new Equipamento();
//        }
//        return equipamento;
//    }
//
//    public void setEquipamento(Equipamento equipamento) {
//        this.equipamento = equipamento;
//    }
    public EquipamentoServicoFacade getEquipamentoServicoFacade() {
        if (equipamentoServicoFacade == null) {
            equipamentoServicoFacade = new EquipamentoServicoFacade();
        }

        return equipamentoServicoFacade;
    }

    public void setEquipamentoServicoFacade(EquipamentoServicoFacade equipamentoServicoFacade) {
        this.equipamentoServicoFacade = equipamentoServicoFacade;
    }

    public MovimentoFacade getMovimentoFacade() {
        movimentoFacade = new MovimentoFacade();
        return movimentoFacade;
    }

    public void setMovimentoFacade(MovimentoFacade movimentoFacade) {
        this.movimentoFacade = movimentoFacade;
    }

    public boolean isRdrLabelListaPreco() {
        return rdrLabelListaPreco;
    }

    public boolean isIncFaturaResumoBool() {
        return incFaturaResumoBool;
    }

    public List<Generico> getListGen() {
        return listGen;
    }

    public void setListGen(List<Generico> listGen) {
        this.listGen = listGen;
    }

    public Generico getTipoDataSelected() {
        return tipoDataSelected;
    }

    public void setTipoDataSelected(Generico tipoDataSelected) {
        this.tipoDataSelected = tipoDataSelected;
    }
}
