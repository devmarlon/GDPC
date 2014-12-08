package com.gardnerdenver.model;

import com.gardnerdenver.bean.EstadoBean;
//import com.gardnerdenver.facade.MunicipioFacade;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@NamedQuery(name = "Configuracao.findConfig", query = "select c from Configuracao c WHERE c.CFG_ID = :cfg_id")
public class FactoryConfiguracao implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_CONFIG = "Configuracao.findConfig";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CFG_ID;
    //certificado digital
    @Column(length = 30)
    private String CER_AMBIENTE;
    @Column
    private int CER_SERIE;
    @Column(length = 200)
    private String CER_LOGO;
    @Column(length = 1000)
    private String CER_INFADIC;
    @Column(length = 500)
    private String CER_CERTIFICADO;
    @Column(length = 30)
    private String CER_SENHACERT;
    @Column
    @Temporal(value = TemporalType.DATE)
    private Date CER_VALIDADE;
    //email emitente nota
    @Column(length = 1)
    private String EMT_EMAILPROPRIO;
    @Column(length = 100)
    private String EMT_SMTP;
    @Column(length = 30)
    private String EMT_PORTA;
    @Column(length = 100)
    private String EMT_EMAILENVIO;
    @Column(length = 100)
    private String EMT_USUARIO;
    @Column(length = 30)
    private String EMT_SENHAEMAIL;
    ///EMPRESA
    @Column(length = 100)
    private String EMP_IDENTIFICADOR;
    @Column(length = 20)
    private String EMP_CNAE;
    @Column
    private int EMP_REGIME;
    @Column(columnDefinition = "numeric (12,5)")
    private double EMP_ICMSSNCRED;
    @Column(length = 100)
    private String EMP_RAZAO;
    @Column(length = 100)
    private String EMP_FANTASIA;
    @Column(length = 1)
    private String EMP_TIPOPESSOA = "F";
    @Column(length = 20)
    private String EMP_CNPJ;
    @Column(length = 30)
    private String EMP_IE;
    @Column(length = 30)
    private String EMP_INSCMUN;
    @Column(length = 100)
    private String EMP_RESPONSAVEL;
    @Column
    private int EST_IDEMP = 12;
    @Column
    private int CID_IDEMP = 1200013;
    @Column(length = 20)
    private String EMP_CEP;
    @Column(length = 100)
    private String EMP_ENDERECO;
    @Column
    private int EMP_NUMERO;
    @Column(length = 30)
    private String EMP_COMP;
    @Column(length = 100)
    private String EMP_BAIRRO;
    @Column(length = 20)
    private String EMP_FONE;
    @Column(length = 100)
    private String EMP_EMAIL;
    @Column(length = 100)
    private String EMP_SITE;
    @Column
    private boolean EMP_EMAILPROPRIO;
    @Column(length = 100)
    private String EMP_SMTP;
    @Column(length = 30)
    private String EMP_PORTA;
    @Column(length = 100)
    private String EMP_EMAILENVIO;
    //CAMPOS PARA EMAIL DE RESPOSTA
    @Column(length = 100)
    private String EMP_EMAILRESPOSTAOC;
    @Column(length = 100)
    private String EMP_EMAILRESPOSTAOV;
    @Column(length = 100)
    private String EMP_EMAILRESPOSTAOS;
    @Column(length = 100)
    private String EMP_EMAILRESPOSTANF;
    @Column(length = 100)
    private String EMP_EMAILRESPOSTABO;
    //
    @Column(length = 100)
    private String EMP_USUARIO;
    @Column(length = 30)
    private String EMP_SENHAEMAIL;
    @Column(length = 1)
    private int EMP_SEGURANCA;
    @Column(length = 500)
    private String EMP_LOGO;
    @Column(length = 500)
    private String EMP_CAB;
    @Column(length = 2000)
    private String EMP_CABECALHO;
    //movimento
    //@Column
    //private int PLC_IDOS;
//    @ManyToOne
//    @JoinColumn(name = "PLC_ID_OS")
//    private PlanoConta planocontaOS;
    @Column
    private int MOV_OSGARANTIA;
    @Column(length = 2000)
    private String MOV_OSOBS;
    @Column
    private int MOV_OSVIAS;
    @Column
    private int MOV_OSPREVISAO;
    //@Column
    //private int CPT_IDOS;
//    @ManyToOne
//    @JoinColumn(name = "CTP_ID_ODS")
//    private CondicaoPagamento condPagODS;
    @Column(length = 2000)
    private String MOV_PEDOBS;
    @Column
    private int MOV_PEDVIAS;
    @Column
    private int MOV_PEDPREVISAO;
    @Column
    private int CPT_IDPED;
    @Column
    private int MOV_COMALTPRECO;
    //TRIBUTACAO
    @Column
    private int PRO_CFOP;
    @Column
    private int PRO_ICMSCST;
    @Column(columnDefinition = "numeric (12,5)")
    private double PRO_ICMSALIQ = 0;
    @Column(columnDefinition = "numeric (12,5)")
    private double PRO_ICMSREDBC = 0;
    @Column
    private int PRO_ICMSSTMOD;
    @Column(columnDefinition = "numeric (12,5)")
    private double PRO_ICMSSTMVA = 0;
    @Column(columnDefinition = "numeric (12,5)")
    private double PRO_ICMSSTREDBC = 0;
    @Column(columnDefinition = "numeric (12,5)")
    private double PRO_ICMSSTVALUNIT = 0;
    @Column(columnDefinition = "numeric (12,5)")
    private double PRO_ICMSSTALIQ = 0;
    @Column
    private int PRO_MOTDESONERACAO;
    @Column
    private int PRO_IPICST;
    @Column
    private int PRO_IPIMOD;
    @Column(columnDefinition = "numeric (12,5)")
    private double PRO_IPIALIQ = 0;
    @Column(columnDefinition = "numeric (12,5)")
    private double PRO_IPIVALUNIT = 0;
    @Column(length = 20)
    private String PRO_IPICLASSEENQ;
    @Column
    private int PRO_IPICODENQ;
    @Column
    private int PRO_PISCST;
    @Column(columnDefinition = "numeric (12,5)")
    private double PRO_PISALIQ = 0;
    @Column(columnDefinition = "numeric (12,5)")
    private double PRO_PISVALUNIT = 0;
    @Column
    private int PRO_PISSTMOD;
    @Column(columnDefinition = "numeric (12,5)")
    private double PRO_PISSTALIQ = 0;
    @Column(columnDefinition = "numeric (12,5)")
    private double PRO_PISSTVALUNIT = 0;
    @Column
    private int PRO_COFINSCST;
    @Column(columnDefinition = "numeric (12,5)")
    private double PRO_COFINSALIQ = 0;
    @Column(columnDefinition = "numeric (12,5)")
    private double PRO_COFINSVALUNIT = 0;
    @Column
    private int PRO_COFINSSTMOD;
    @Column(columnDefinition = "numeric (12,5)")
    private double PRO_COFINSSTALIQ = 0;
    @Column(columnDefinition = "numeric (12,5)")
    private double PRO_COFINSSTVALUNIT = 0;
    @Column
    private int PRO_ISSQN;
    @Column(length = 2)
    private int PRO_TITEM;
    @Column
    private int PRO_ORIGEM;
    //NFF
    @Column
    private int NFF_DATAHORAAUT;
    @Column(length = 100)
    private String NFF_NATOP;
    //OUTROS
    @Column(length = 1)
    private int CFG_PRAZO = 0;//USADO PARA INDICAR SE O USUARIO GRATUITO JA UTILIZOU O CAMPO MAIS 5 DIAS DE TESTE | 0 = NAO, 1 = SIM
    @Column(length = 1)
    private int CFG_ACESSO = 1;
    @Column
    private int CFG_NUMREGPAG = 50;
    @Column
    private int CFG_BEMVINDO = 0;
    @Column
    private int CFG_DIASPREVISAO = 5;//dias padrao para previsao de entrega e validade orcamento
    //transients
    @Column
    private String CER_NOMECERTIFICADO;
    @Transient
    private String EMP_LOGOLABEL;
    //COMPRAS
//    @ManyToOne
//    @JoinColumn(name = "PLC_ID_COMPRA")
//    private PlanoConta planocontaCompra;
    @Column(length = 2000)
    private String MOV_OBSCOMPRA;
    @Column
    private int MOV_PREVISAOCOMPRA;
    @Column
    private int MOV_ALTPRECOVENDA;
    //VENDAS
//    @ManyToOne
//    @JoinColumn(name = "PLC_ID_VENDA")
//    private PlanoConta planocontaVenda;
    @Column(length = 2000)
    private String MOV_OBSVENDA;
    @Column
    private int MOV_PREVISAOVENDA;
//    @ManyToOne
//    @JoinColumn(name = "CTP_ID_VENDA")
//    private CondicaoPagamento condPagVenda;
    @Column(columnDefinition = "numeric (12,5)")
    private double MOV_METAVENDA;
    //ORÇAMENTO
//    @ManyToOne
//    @JoinColumn(name = "CTP_ID_ORC")
//    private CondicaoPagamento condPagOrc;
    @Column
    private int MOV_ORCVALID;
    @Column
    private int CPT_IDORC;
    @Column(length = 2000)
    private String MOV_OBSORCAMENTO;
    @Column(length = 2000)
    private String TEXTO_EMAIL;
    ////
    @Column
    private int CER_NFECNAE;
    //NFS-E
    @Column
    private int CFG_NFS = 0;//0 = FALSE  1 = TRUE
    @Column
    private int NFS_AMBIENTE = 0;
    @Column
    private int NFS_CODTRIBMUN;
    @Column
    private int NFS_NATOP = 1;
    @Column
    private int NFS_CNAE;
    @Column(columnDefinition = "numeric (12,5)")
    private double NFS_ALIQISS;
    @Column
    private int NFS_RPSAUT = 1;
    @Column(length = 1000)
    private String NFS_INFADIC;
    @Column
    private int NFS_ITEMSERVDEF;
    @Column
    private int NFS_SERIE;
    ////
    @Transient
    private String CER_AMBIENTEXMLNFE;

    @Transient
    private Estado estado;
    @Transient
    private Municipio municipio;

    public FactoryConfiguracao() {
    }

    public int getCFG_ID() {
        return CFG_ID;
    }

    public void setCFG_ID(int CFG_ID) {
        this.CFG_ID = CFG_ID;
    }

    public int getNFS_ITEMSERVDEF() {
        return NFS_ITEMSERVDEF;
    }

    public void setNFS_ITEMSERVDEF(int NFS_ITEMSERVDEF) {
        this.NFS_ITEMSERVDEF = NFS_ITEMSERVDEF;
    }

    public int getCFG_NUMREGPAG() {
        return CFG_NUMREGPAG;
    }

    public void setCFG_NUMREGPAG(int CFG_NUMREGPAG) {
        this.CFG_NUMREGPAG = CFG_NUMREGPAG;
    }

    public int getCFG_PRAZO() {
        return CFG_PRAZO;
    }

    public void setCFG_PRAZO(int CFG_PRAZO) {
        this.CFG_PRAZO = CFG_PRAZO;
    }

    public int getCFG_ACESSO() {
        return CFG_ACESSO;
    }

    public void setCFG_ACESSO(int CFG_ACESSO) {
        this.CFG_ACESSO = CFG_ACESSO;
    }

    public int getCFG_BEMVINDO() {
        return CFG_BEMVINDO;
    }

    public void setCFG_BEMVINDO(int CFG_BEMVINDO) {
        this.CFG_BEMVINDO = CFG_BEMVINDO;
    }

    public int getCFG_DIASPREVISAO() {
        return CFG_DIASPREVISAO;
    }

    public void setCFG_DIASPREVISAO(int CFG_DIASPREVISAO) {
        this.CFG_DIASPREVISAO = CFG_DIASPREVISAO;
    }

    public int getCFG_NFS() {
        return CFG_NFS;
    }

    public void setCFG_NFS(int CFG_NFS) {
        this.CFG_NFS = CFG_NFS;
    }

    public String getEMP_CNAE() {
        return EMP_CNAE;
    }

    public void setEMP_CNAE(String EMP_CNAE) {
        this.EMP_CNAE = EMP_CNAE;
    }

    public int getEMP_REGIME() {
        return EMP_REGIME;
    }

    public void setEMP_REGIME(int EMP_REGIME) {
        this.EMP_REGIME = EMP_REGIME;
    }

    public String getCER_AMBIENTE() {
        return CER_AMBIENTE;
    }

    public void setCER_AMBIENTE(String CER_AMBIENTE) {
        this.CER_AMBIENTE = CER_AMBIENTE;
    }

    public String getCER_AMBIENTEXMLNFE() {
        if (this.getCER_AMBIENTE().equalsIgnoreCase("0")) {
            CER_AMBIENTEXMLNFE = "2";
        } else {
            CER_AMBIENTEXMLNFE = "1";
        }
        return CER_AMBIENTEXMLNFE;
    }

    public void setCER_AMBIENTEXMLNFE(String CER_AMBIENTEXMLNFE) {
        this.CER_AMBIENTEXMLNFE = CER_AMBIENTEXMLNFE;
    }

    public int getCER_SERIE() {
        return CER_SERIE;
    }

    public void setCER_SERIE(int CER_SERIE) {
        this.CER_SERIE = CER_SERIE;
    }

    public String getCER_LOGO() {
        return CER_LOGO;
    }

    public void setCER_LOGO(String CER_LOGO) {
        this.CER_LOGO = CER_LOGO;
    }

    public String getCER_INFADIC() {
        return CER_INFADIC;
    }

    public void setCER_INFADIC(String CER_INFADIC) {
        this.CER_INFADIC = CER_INFADIC;
    }

    public String getCER_CERTIFICADO() {
        return CER_CERTIFICADO;
    }

    public void setCER_CERTIFICADO(String CER_CERTIFICADO) {
        this.CER_CERTIFICADO = CER_CERTIFICADO;
    }

    public String getCER_SENHACERT() {
        return CER_SENHACERT;
    }

    public void setCER_SENHACERT(String CER_SENHACERT) {
        this.CER_SENHACERT = CER_SENHACERT;
    }

    public Date getCER_VALIDADE() {
        return CER_VALIDADE;
    }

    public void setCER_VALIDADE(Date CER_VALIDADE) {
        this.CER_VALIDADE = CER_VALIDADE;
    }

    public String getEMT_EMAILPROPRIO() {
        return EMT_EMAILPROPRIO;
    }

    public void setEMT_EMAILPROPRIO(String EMT_EMAILPROPRIO) {
        this.EMT_EMAILPROPRIO = EMT_EMAILPROPRIO;
    }

    public String getEMT_SMTP() {
        return EMT_SMTP;
    }

    public void setEMT_SMTP(String EMT_SMTP) {
        this.EMT_SMTP = EMT_SMTP;
    }

    public String getEMT_PORTA() {
        return EMT_PORTA;
    }

    public void setEMT_PORTA(String EMT_PORTA) {
        this.EMT_PORTA = EMT_PORTA;
    }

    public String getEMT_EMAILENVIO() {
        return EMT_EMAILENVIO;
    }

    public void setEMT_EMAILENVIO(String EMT_EMAILENVIO) {
        this.EMT_EMAILENVIO = EMT_EMAILENVIO;
    }

    public String getEMT_USUARIO() {
        return EMT_USUARIO;
    }

    public void setEMT_USUARIO(String EMT_USUARIO) {
        this.EMT_USUARIO = EMT_USUARIO;
    }

    public String getEMT_SENHAEMAIL() {
        return EMT_SENHAEMAIL;
    }

    public void setEMT_SENHAEMAIL(String EMT_SENHAEMAIL) {
        this.EMT_SENHAEMAIL = EMT_SENHAEMAIL;
    }

    public String getEMP_IDENTIFICADOR() {
        return EMP_IDENTIFICADOR;
    }

    public void setEMP_IDENTIFICADOR(String EMP_IDENTIFICADOR) {
        this.EMP_IDENTIFICADOR = EMP_IDENTIFICADOR;
    }

    public String getEMP_RAZAO() {
        return EMP_RAZAO;
    }

    public void setEMP_RAZAO(String EMP_RAZAO) {
        this.EMP_RAZAO = EMP_RAZAO;
    }

    public String getEMP_FANTASIA() {
        return EMP_FANTASIA;
    }

    public void setEMP_FANTASIA(String EMP_FANTASIA) {
        this.EMP_FANTASIA = EMP_FANTASIA;
    }

    public String getEMP_TIPOPESSOA() {
        return EMP_TIPOPESSOA;
    }

    public void setEMP_TIPOPESSOA(String EMP_TIPOPESSOA) {
        this.EMP_TIPOPESSOA = EMP_TIPOPESSOA;
    }

    public String getEMP_CNPJ() {
        return EMP_CNPJ;
    }

    public void setEMP_CNPJ(String EMP_CNPJ) {
        this.EMP_CNPJ = EMP_CNPJ;
    }

    public String getEMP_IE() {
        return EMP_IE;
    }

    public void setEMP_IE(String EMP_IE) {
        this.EMP_IE = EMP_IE;
    }

    public String getEMP_INSCMUN() {
        return EMP_INSCMUN;
    }

    public void setEMP_INSCMUN(String EMP_INSCMUN) {
        this.EMP_INSCMUN = EMP_INSCMUN;
    }

    public String getEMP_RESPONSAVEL() {
        return EMP_RESPONSAVEL;
    }

    public void setEMP_RESPONSAVEL(String EMP_RESPONSAVEL) {
        this.EMP_RESPONSAVEL = EMP_RESPONSAVEL;
    }

    public int getEST_IDEMP() {
        return EST_IDEMP;
    }

    public void setEST_IDEMP(int EST_IDEMP) {
        this.EST_IDEMP = EST_IDEMP;
    }

    public int getCID_IDEMP() {
        return CID_IDEMP;
    }

    public void setCID_IDEMP(int CID_IDEMP) {
        this.CID_IDEMP = CID_IDEMP;
    }

    public String getEMP_CEP() {
        return EMP_CEP;
    }

    public void setEMP_CEP(String EMP_CEP) {
        this.EMP_CEP = EMP_CEP;
    }

    public String getEMP_ENDERECO() {
        return EMP_ENDERECO;
    }

    public void setEMP_ENDERECO(String EMP_ENDERECO) {
        this.EMP_ENDERECO = EMP_ENDERECO;
    }

    public int getEMP_NUMERO() {
        return EMP_NUMERO;
    }

    public void setEMP_NUMERO(int EMP_NUMERO) {
        this.EMP_NUMERO = EMP_NUMERO;
    }

    public String getEMP_COMP() {
        return EMP_COMP;
    }

    public void setEMP_COMP(String EMP_COMP) {
        this.EMP_COMP = EMP_COMP;
    }

    public String getEMP_BAIRRO() {
        return EMP_BAIRRO;
    }

    public void setEMP_BAIRRO(String EMP_BAIRRO) {
        this.EMP_BAIRRO = EMP_BAIRRO;
    }

    public String getEMP_FONE() {
        return EMP_FONE;
    }

    public void setEMP_FONE(String EMP_FONE) {
        this.EMP_FONE = EMP_FONE;
    }

    public String getEMP_EMAIL() {
        return EMP_EMAIL;
    }

    public void setEMP_EMAIL(String EMP_EMAIL) {
        this.EMP_EMAIL = EMP_EMAIL;
    }

    public String getEMP_SITE() {
        return EMP_SITE;
    }

    public void setEMP_SITE(String EMP_SITE) {
        this.EMP_SITE = EMP_SITE;
    }

    public boolean isEMP_EMAILPROPRIO() {
        return EMP_EMAILPROPRIO;
    }

    public void setEMP_EMAILPROPRIO(boolean EMP_EMAILPROPRIO) {
        this.EMP_EMAILPROPRIO = EMP_EMAILPROPRIO;
    }

    public String getEMP_SMTP() {
        return EMP_SMTP;
    }

    public void setEMP_SMTP(String EMP_SMTP) {
        this.EMP_SMTP = EMP_SMTP;
    }

    public String getEMP_PORTA() {
        return EMP_PORTA;
    }

    public void setEMP_PORTA(String EMP_PORTA) {
        this.EMP_PORTA = EMP_PORTA;
    }

    public String getEMP_EMAILENVIO() {
        return EMP_EMAILENVIO;
    }

    public void setEMP_EMAILENVIO(String EMP_EMAILENVIO) {
        this.EMP_EMAILENVIO = EMP_EMAILENVIO;
    }

    public String getEMP_EMAILRESPOSTAOC() {
        return EMP_EMAILRESPOSTAOC;
    }

    public void setEMP_EMAILRESPOSTAOC(String EMP_EMAILRESPOSTAOC) {
        this.EMP_EMAILRESPOSTAOC = EMP_EMAILRESPOSTAOC;
    }

    public String getEMP_EMAILRESPOSTAOV() {
        return EMP_EMAILRESPOSTAOV;
    }

    public void setEMP_EMAILRESPOSTAOV(String EMP_EMAILRESPOSTAOV) {
        this.EMP_EMAILRESPOSTAOV = EMP_EMAILRESPOSTAOV;
    }

    public String getEMP_EMAILRESPOSTAOS() {
        return EMP_EMAILRESPOSTAOS;
    }

    public void setEMP_EMAILRESPOSTAOS(String EMP_EMAILRESPOSTAOS) {
        this.EMP_EMAILRESPOSTAOS = EMP_EMAILRESPOSTAOS;
    }

    public String getEMP_EMAILRESPOSTANF() {
        return EMP_EMAILRESPOSTANF;
    }

    public void setEMP_EMAILRESPOSTANF(String EMP_EMAILRESPOSTANF) {
        this.EMP_EMAILRESPOSTANF = EMP_EMAILRESPOSTANF;
    }

    public String getEMP_EMAILRESPOSTABO() {
        return EMP_EMAILRESPOSTABO;
    }

    public void setEMP_EMAILRESPOSTABO(String EMP_EMAILRESPOSTABO) {
        this.EMP_EMAILRESPOSTABO = EMP_EMAILRESPOSTABO;
    }

    public String getEMP_USUARIO() {
        return EMP_USUARIO;
    }

    public void setEMP_USUARIO(String EMP_USUARIO) {
        this.EMP_USUARIO = EMP_USUARIO;
    }

    public String getEMP_SENHAEMAIL() {
        return EMP_SENHAEMAIL;
    }

    public void setEMP_SENHAEMAIL(String EMP_SENHAEMAIL) {
        this.EMP_SENHAEMAIL = EMP_SENHAEMAIL;
    }

    public int getEMP_SEGURANCA() {
        return EMP_SEGURANCA;
    }

    public void setEMP_SEGURANCA(int EMP_SEGURANCA) {
        this.EMP_SEGURANCA = EMP_SEGURANCA;
    }

    public String getEMP_LOGO() {
        return EMP_LOGO;
    }

    public void setEMP_LOGO(String EMP_LOGO) {
        this.EMP_LOGO = EMP_LOGO;
    }

    public String getEMP_CAB() {
        return EMP_CAB;
    }

    public void setEMP_CAB(String EMP_CAB) {
        this.EMP_CAB = EMP_CAB;
    }

    public String getEMP_CABECALHO() {
        return EMP_CABECALHO;
    }

    public void setEMP_CABECALHO(String EMP_CABECALHO) {
        this.EMP_CABECALHO = EMP_CABECALHO;
    }

    //    public int getPLC_IDOS() {
    //        return PLC_IDOS;
    //    }
    //
    //    public void setPLC_IDOS(int PLC_IDOS) {
    //        this.PLC_IDOS = PLC_IDOS;
    //    }
    public int getMOV_OSGARANTIA() {
        return MOV_OSGARANTIA;
    }

    public void setMOV_OSGARANTIA(int MOV_OSGARANTIA) {
        this.MOV_OSGARANTIA = MOV_OSGARANTIA;
    }

    public String getMOV_OSOBS() {
        return MOV_OSOBS;
    }

    public void setMOV_OSOBS(String MOV_OSOBS) {
        this.MOV_OSOBS = MOV_OSOBS;
    }

    public int getMOV_OSVIAS() {
        return MOV_OSVIAS;
    }

    public void setMOV_OSVIAS(int MOV_OSVIAS) {
        this.MOV_OSVIAS = MOV_OSVIAS;
    }

    public int getMOV_OSPREVISAO() {
        return MOV_OSPREVISAO;
    }

    public void setMOV_OSPREVISAO(int MOV_OSPREVISAO) {
        this.MOV_OSPREVISAO = MOV_OSPREVISAO;
    }

    public double getMOV_METAVENDA() {
        return MOV_METAVENDA;
    }

    public void setMOV_METAVENDA(double MOV_METAVENDA) {
        this.MOV_METAVENDA = MOV_METAVENDA;
    }

    //    public int getCPT_IDOS() {
    //        return CPT_IDOS;
    //    }
    //
    //    public void setCPT_IDOS(int CPT_IDOS) {
    //        this.CPT_IDOS = CPT_IDOS;
    //    }
    public String getMOV_PEDOBS() {
        return MOV_PEDOBS;
    }

    public void setMOV_PEDOBS(String MOV_PEDOBS) {
        this.MOV_PEDOBS = MOV_PEDOBS;
    }

    public int getMOV_PEDVIAS() {
        return MOV_PEDVIAS;
    }

    public void setMOV_PEDVIAS(int MOV_PEDVIAS) {
        this.MOV_PEDVIAS = MOV_PEDVIAS;
    }

    public int getMOV_PEDPREVISAO() {
        return MOV_PEDPREVISAO;
    }

    public void setMOV_PEDPREVISAO(int MOV_PEDPREVISAO) {
        this.MOV_PEDPREVISAO = MOV_PEDPREVISAO;
    }

    public int getCPT_IDPED() {
        return CPT_IDPED;
    }

    public void setCPT_IDPED(int CPT_IDPED) {
        this.CPT_IDPED = CPT_IDPED;
    }

    public int getCPT_IDORC() {
        return CPT_IDORC;
    }

    public void setCPT_IDORC(int CPT_IDORC) {
        this.CPT_IDORC = CPT_IDORC;
    }

    public int getMOV_COMALTPRECO() {
        return MOV_COMALTPRECO;
    }

    public void setMOV_COMALTPRECO(int MOV_COMALTPRECO) {
        this.MOV_COMALTPRECO = MOV_COMALTPRECO;
    }

    public int getPRO_CFOP() {
        return PRO_CFOP;
    }

    public void setPRO_CFOP(int PRO_CFOP) {
        this.PRO_CFOP = PRO_CFOP;
    }

    public int getPRO_ICMSCST() {
        return PRO_ICMSCST;
    }

    public void setPRO_ICMSCST(int PRO_ICMSCST) {
        this.PRO_ICMSCST = PRO_ICMSCST;
    }

    public double getPRO_ICMSALIQ() {
        return PRO_ICMSALIQ;
    }

    public void setPRO_ICMSALIQ(double PRO_ICMSALIQ) {
        this.PRO_ICMSALIQ = PRO_ICMSALIQ;
    }

    public double getPRO_ICMSREDBC() {
        return PRO_ICMSREDBC;
    }

    public void setPRO_ICMSREDBC(double PRO_ICMSREDBC) {
        this.PRO_ICMSREDBC = PRO_ICMSREDBC;
    }

    public int getPRO_ICMSSTMOD() {
        return PRO_ICMSSTMOD;
    }

    public void setPRO_ICMSSTMOD(int PRO_ICMSSTMOD) {
        this.PRO_ICMSSTMOD = PRO_ICMSSTMOD;
    }

    public double getPRO_ICMSSTMVA() {
        return PRO_ICMSSTMVA;
    }

    public void setPRO_ICMSSTMVA(double PRO_ICMSSTMVA) {
        this.PRO_ICMSSTMVA = PRO_ICMSSTMVA;
    }

    public double getPRO_ICMSSTREDBC() {
        return PRO_ICMSSTREDBC;
    }

    public void setPRO_ICMSSTREDBC(double PRO_ICMSSTREDBC) {
        this.PRO_ICMSSTREDBC = PRO_ICMSSTREDBC;
    }

    public double getPRO_ICMSSTVALUNIT() {
        return PRO_ICMSSTVALUNIT;
    }

    public void setPRO_ICMSSTVALUNIT(double PRO_ICMSSTVALUNIT) {
        this.PRO_ICMSSTVALUNIT = PRO_ICMSSTVALUNIT;
    }

    public double getPRO_ICMSSTALIQ() {
        return PRO_ICMSSTALIQ;
    }

    public void setPRO_ICMSSTALIQ(double PRO_ICMSSTALIQ) {
        this.PRO_ICMSSTALIQ = PRO_ICMSSTALIQ;
    }

    public int getPRO_MOTDESONERACAO() {
        return PRO_MOTDESONERACAO;
    }

    public void setPRO_MOTDESONERACAO(int PRO_MOTDESONERACAO) {
        this.PRO_MOTDESONERACAO = PRO_MOTDESONERACAO;
    }

    public int getPRO_IPICST() {
        return PRO_IPICST;
    }

    public void setPRO_IPICST(int PRO_IPICST) {
        this.PRO_IPICST = PRO_IPICST;
    }

    public int getPRO_IPIMOD() {
        return PRO_IPIMOD;
    }

    public void setPRO_IPIMOD(int PRO_IPIMOD) {
        this.PRO_IPIMOD = PRO_IPIMOD;
    }

    public double getPRO_IPIALIQ() {
        return PRO_IPIALIQ;
    }

    public void setPRO_IPIALIQ(double PRO_IPIALIQ) {
        this.PRO_IPIALIQ = PRO_IPIALIQ;
    }

    public double getPRO_IPIVALUNIT() {
        return PRO_IPIVALUNIT;
    }

    public void setPRO_IPIVALUNIT(double PRO_IPIVALUNIT) {
        this.PRO_IPIVALUNIT = PRO_IPIVALUNIT;
    }

    public String getPRO_IPICLASSEENQ() {
        return PRO_IPICLASSEENQ;
    }

    public void setPRO_IPICLASSEENQ(String PRO_IPICLASSEENQ) {
        this.PRO_IPICLASSEENQ = PRO_IPICLASSEENQ;
    }

    public int getPRO_IPICODENQ() {
        return PRO_IPICODENQ;
    }

    public void setPRO_IPICODENQ(int PRO_IPICODENQ) {
        this.PRO_IPICODENQ = PRO_IPICODENQ;
    }

    public int getPRO_PISCST() {
        return PRO_PISCST;
    }

    public void setPRO_PISCST(int PRO_PISCST) {
        this.PRO_PISCST = PRO_PISCST;
    }

    public double getPRO_PISALIQ() {
        return PRO_PISALIQ;
    }

    public void setPRO_PISALIQ(double PRO_PISALIQ) {
        this.PRO_PISALIQ = PRO_PISALIQ;
    }

    public double getPRO_PISVALUNIT() {
        return PRO_PISVALUNIT;
    }

    public void setPRO_PISVALUNIT(double PRO_PISVALUNIT) {
        this.PRO_PISVALUNIT = PRO_PISVALUNIT;
    }

    public int getPRO_PISSTMOD() {
        return PRO_PISSTMOD;
    }

    public void setPRO_PISSTMOD(int PRO_PISSTMOD) {
        this.PRO_PISSTMOD = PRO_PISSTMOD;
    }

    public double getPRO_PISSTALIQ() {
        return PRO_PISSTALIQ;
    }

    public void setPRO_PISSTALIQ(double PRO_PISSTALIQ) {
        this.PRO_PISSTALIQ = PRO_PISSTALIQ;
    }

    public double getPRO_PISSTVALUNIT() {
        return PRO_PISSTVALUNIT;
    }

    public void setPRO_PISSTVALUNIT(double PRO_PISSTVALUNIT) {
        this.PRO_PISSTVALUNIT = PRO_PISSTVALUNIT;
    }

    public int getPRO_COFINSCST() {
        return PRO_COFINSCST;
    }

    public void setPRO_COFINSCST(int PRO_COFINSCST) {
        this.PRO_COFINSCST = PRO_COFINSCST;
    }

    public double getPRO_COFINSALIQ() {
        return PRO_COFINSALIQ;
    }

    public void setPRO_COFINSALIQ(double PRO_COFINSALIQ) {
        this.PRO_COFINSALIQ = PRO_COFINSALIQ;
    }

    public double getPRO_COFINSVALUNIT() {
        return PRO_COFINSVALUNIT;
    }

    public void setPRO_COFINSVALUNIT(double PRO_COFINSVALUNIT) {
        this.PRO_COFINSVALUNIT = PRO_COFINSVALUNIT;
    }

    public int getPRO_COFINSSTMOD() {
        return PRO_COFINSSTMOD;
    }

    public void setPRO_COFINSSTMOD(int PRO_COFINSSTMOD) {
        this.PRO_COFINSSTMOD = PRO_COFINSSTMOD;
    }

    public double getPRO_COFINSSTALIQ() {
        return PRO_COFINSSTALIQ;
    }

    public void setPRO_COFINSSTALIQ(double PRO_COFINSSTALIQ) {
        this.PRO_COFINSSTALIQ = PRO_COFINSSTALIQ;
    }

    public double getPRO_COFINSSTVALUNIT() {
        return PRO_COFINSSTVALUNIT;
    }

    public void setPRO_COFINSSTVALUNIT(double PRO_COFINSSTVALUNIT) {
        this.PRO_COFINSSTVALUNIT = PRO_COFINSSTVALUNIT;
    }

    public int getPRO_ISSQN() {
        return PRO_ISSQN;
    }

    public void setPRO_ISSQN(int PRO_ISSQN) {
        this.PRO_ISSQN = PRO_ISSQN;
    }

    public int getPRO_TITEM() {
        return PRO_TITEM;
    }

    public void setPRO_TITEM(int PRO_TITEM) {
        this.PRO_TITEM = PRO_TITEM;
    }

    public int getPRO_ORIGEM() {
        return PRO_ORIGEM;
    }

    public void setPRO_ORIGEM(int PRO_ORIGEM) {
        this.PRO_ORIGEM = PRO_ORIGEM;
    }

    public String getNFF_NATOP() {
        return NFF_NATOP;
    }

    public void setNFF_NATOP(String NFF_NATOP) {
        this.NFF_NATOP = NFF_NATOP;
    }

    public int getNFF_DATAHORAAUT() {
        return NFF_DATAHORAAUT;
    }

    public void setNFF_DATAHORAAUT(int NFF_DATAHORAAUT) {
        this.NFF_DATAHORAAUT = NFF_DATAHORAAUT;
    }

    public String getCER_NOMECERTIFICADO() {
        return CER_NOMECERTIFICADO;
    }

    public void setCER_NOMECERTIFICADO(String CER_NOMECERTIFICADO) {
        this.CER_NOMECERTIFICADO = CER_NOMECERTIFICADO;
    }

    public double getEMP_ICMSSNCRED() {
        return EMP_ICMSSNCRED;
    }

    public void setEMP_ICMSSNCRED(double EMP_ICMSSNCRED) {
        this.EMP_ICMSSNCRED = EMP_ICMSSNCRED;
    }

    public String getEMP_LOGOLABEL() {
        return EMP_LOGOLABEL;
    }

    public void setEMP_LOGOLABEL(String EMP_LOGOLABEL) {
        this.EMP_LOGOLABEL = EMP_LOGOLABEL;
    }

    public String getMOV_OBSCOMPRA() {
        return MOV_OBSCOMPRA;
    }

    public void setMOV_OBSCOMPRA(String MOV_OBSCOMPRA) {
        this.MOV_OBSCOMPRA = MOV_OBSCOMPRA;
    }

    public int getMOV_PREVISAOCOMPRA() {
        return MOV_PREVISAOCOMPRA;
    }

    public void setMOV_PREVISAOCOMPRA(int MOV_PREVISAOCOMPRA) {
        this.MOV_PREVISAOCOMPRA = MOV_PREVISAOCOMPRA;
    }

    public int getMOV_ALTPRECOVENDA() {
        return MOV_ALTPRECOVENDA;
    }

    public void setMOV_ALTPRECOVENDA(int MOV_ALTPRECOVENDA) {
        this.MOV_ALTPRECOVENDA = MOV_ALTPRECOVENDA;
    }

    public String getMOV_OBSVENDA() {
        return MOV_OBSVENDA;
    }

    public void setMOV_OBSVENDA(String MOV_OBSVENDA) {
        this.MOV_OBSVENDA = MOV_OBSVENDA;
    }

    public int getMOV_PREVISAOVENDA() {
        return MOV_PREVISAOVENDA;
    }

    public void setMOV_PREVISAOVENDA(int MOV_PREVISAOVENDA) {
        this.MOV_PREVISAOVENDA = MOV_PREVISAOVENDA;
    }

    public int getMOV_ORCVALID() {
        return MOV_ORCVALID;
    }

    public void setMOV_ORCVALID(int MOV_ORCVALID) {
        this.MOV_ORCVALID = MOV_ORCVALID;
    }

    public String getMOV_OBSORCAMENTO() {
        return MOV_OBSORCAMENTO;
    }

    public void setMOV_OBSORCAMENTO(String MOV_OBSORCAMENTO) {
        this.MOV_OBSORCAMENTO = MOV_OBSORCAMENTO;
    }

    public String getTEXTO_EMAIL() {
        return TEXTO_EMAIL;
    }

    public void setTEXTO_EMAIL(String TEXTO_EMAIL) {
        this.TEXTO_EMAIL = TEXTO_EMAIL;
    }

    public int getCER_NFECNAE() {
        return CER_NFECNAE;
    }

    public void setCER_NFECNAE(int CER_NFECNAE) {
        this.CER_NFECNAE = CER_NFECNAE;
    }

    public int getNFS_AMBIENTE() {
        return NFS_AMBIENTE;
    }

    public void setNFS_AMBIENTE(int NFS_AMBIENTE) {
        this.NFS_AMBIENTE = NFS_AMBIENTE;
    }

    public int getNFS_CODTRIBMUN() {
        return NFS_CODTRIBMUN;
    }

    public void setNFS_CODTRIBMUN(int NFS_CODTRIBMUN) {
        this.NFS_CODTRIBMUN = NFS_CODTRIBMUN;
    }

    public int getNFS_NATOP() {
        return NFS_NATOP;
    }

    public void setNFS_NATOP(int NFS_NATOP) {
        this.NFS_NATOP = NFS_NATOP;
    }

    public int getNFS_CNAE() {
        return NFS_CNAE;
    }

    public void setNFS_CNAE(int NFS_CNAE) {
        this.NFS_CNAE = NFS_CNAE;
    }

    public double getNFS_ALIQISS() {
        return NFS_ALIQISS;
    }

    public void setNFS_ALIQISS(double NFS_ALIQISS) {
        this.NFS_ALIQISS = NFS_ALIQISS;
    }

    public int getNFS_RPSAUT() {
        return NFS_RPSAUT;
    }

    public void setNFS_RPSAUT(int NFS_RPSAUT) {
        this.NFS_RPSAUT = NFS_RPSAUT;
    }

    public String getNFS_INFADIC() {
        return NFS_INFADIC;
    }

    public void setNFS_INFADIC(String NFS_INFADIC) {
        this.NFS_INFADIC = NFS_INFADIC;
    }

    public int getNFS_SERIE() {
        return NFS_SERIE;
    }

    public void setNFS_SERIE(int NFS_SERIE) {
        this.NFS_SERIE = NFS_SERIE;
    }

    public Estado getEstado() {
        EstadoBean estBean = new EstadoBean();
        estado = estBean.getObjById(EST_IDEMP);

        return estado;
    }

    public void setEstado(Estado estado) {
        EST_IDEMP = estado.getJEST_ID();
        this.estado = estado;
    }

    public Municipio getMunicipio() {

        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        CID_IDEMP = municipio.getMUN_ID();
        this.municipio = municipio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.CFG_ID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FactoryConfiguracao other = (FactoryConfiguracao) obj;
        if (this.CFG_ID != other.CFG_ID) {
            return false;
        }
        return true;
    }
}
