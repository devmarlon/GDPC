package com.gardnerdenver.model;

import com.gardnerdenver.bean.StatusOSBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Movimento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MOV_ID;// Id do movimento
    @Column
    private int MOV_STATUS = 0;
    @Transient
    private StatusOS status;
    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date MOV_EMISSAO = new Date();
    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date MOV_PREVISAO;
    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date MOV_FINALIZACAO;
    @Column
    private Boolean FINALIZADO;
    @ManyToOne
    @JoinColumn(name = "PAR_ID")//parceiro (cliente)
    private Parceiro parceiro;
    @ManyToOne
    @JoinColumn(name = "FUN_ID")
    private Funcionario funcionario;// funcionario (Vendedor)
    @Column
    private int TEC_ID;
    @Column(length = 50)
    private String MOV_REPROVADO;
    @Column(length = 50)
    private String MOV_CONTATO;
    @Column(length = 20)
    private String MOV_TELEFONE;
    @Column(length = 20)
    private String MOV_CELULAR;
    @Column(length = 100)
    private String MOV_EMAIL;
    @Column(length = 4)
    private int MOV_GARANTIA;
    @Column
    private String MOV_OBJETO;
    @Column
    private String MOV_DEFEITO;
    @Column
    private String MOV_OBSTEC;
    @Column
    private String MOV_OBSGER;
    @Column
    private String MOV_DESCRICAO;
    @Column
    private boolean MOV_PRODORDESC;

    @Column(columnDefinition = "NUMERIC(12,2)")
    private double MOV_TOTALPRO;
    @Column(columnDefinition = "NUMERIC(12,2)")
    private double MOV_TOTALSERV;
    @Column(columnDefinition = "NUMERIC(12,2)")
    private double MOV_TOTALFRETE;
    @Column(columnDefinition = "NUMERIC(12,2)")
    private double MOV_TOTALDESC;
    @Column(columnDefinition = "NUMERIC(12,2)")
    private double MOV_TOTALSEGURO;
    @Column(columnDefinition = "NUMERIC(12,2)")
    private double MOV_TOTALOUTRAS;
    @Column(columnDefinition = "NUMERIC(12,2)")
    private double MOV_TOTALICMSST;
    @Column(columnDefinition = "NUMERIC(12,2)")
    private double MOV_TOTALIPI;
    @Column(columnDefinition = "NUMERIC(12,2)")
    private double MOV_TOTAL;

    // parceiro //
    @Column(length = 2000)
    private String MOV_RESUMO;
    @Column(length = 20)
    private String PAR_TELEFONE1;
    @Column(length = 20)
    private String PAR_CELULAR;
    @Column(length = 100)
    private String PAR_EMAIL;
    // parceiro //

    @ManyToOne(targetEntity = Equipamento.class)
    private Equipamento equipamento;

    @OneToMany(mappedBy = "movimento", cascade = CascadeType.ALL)
//    @Fetch(FetchMode.SUBSELECT)
    private List<MovimentoItem> listMovimentoItem;

    @Temporal(javax.persistence.TemporalType.TIME)
    private Date MOV_TIMESAIDACLI;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date MOV_TIMECHEGADACLI;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date MOV_TIMESAIDAEMP;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date MOV_TIMECHEGADAEMP;
    @OneToOne(mappedBy = "movimento", cascade = CascadeType.ALL)
    private EquipamentoServico equipamentoServico;

    public Movimento() {
//        this.parceiro = new Parceiro();
//        this.funcionario = new Funcionario();
//        this.condicaoPagamento = new CondicaoPagamento();
    }

    //usado qndo se cria um novo pedido 
    //ao entregar um pedido parcial
    public Movimento(Movimento mov) {
        this.MOV_EMISSAO = new Date();
        this.MOV_PREVISAO = new Date();
        this.parceiro = mov.parceiro;
        this.funcionario = mov.funcionario;
        this.MOV_CONTATO = mov.MOV_CONTATO;
        this.MOV_TELEFONE = mov.MOV_TELEFONE;
        this.MOV_CELULAR = mov.MOV_CELULAR;
        this.MOV_EMAIL = mov.MOV_EMAIL;
        this.MOV_STATUS = mov.MOV_STATUS;
        this.MOV_GARANTIA = mov.MOV_GARANTIA;
        this.MOV_OBJETO = mov.MOV_OBJETO;
        this.MOV_DEFEITO = mov.MOV_DEFEITO;
        this.MOV_OBSTEC = mov.MOV_OBSTEC;
        this.MOV_OBSGER = mov.MOV_OBSGER;
    }

    public int getMOV_ID() {
        return MOV_ID;
    }

    public void setMOV_ID(int MOV_ID) {
        this.MOV_ID = MOV_ID;
    }

    public Date getMOV_EMISSAO() {
        return MOV_EMISSAO;
    }

    public void setMOV_EMISSAO(Date MOV_EMISSAO) {
        this.MOV_EMISSAO = MOV_EMISSAO;
    }

    public Date getMOV_PREVISAO() {
        return MOV_PREVISAO;
    }

    public void setMOV_PREVISAO(Date MOV_PREVISAO) {
        this.MOV_PREVISAO = MOV_PREVISAO;
    }

    public Date getMOV_FINALIZACAO() {
        return MOV_FINALIZACAO;
    }

    public void setMOV_FINALIZACAO(Date MOV_FINALIZACAO) {
        this.MOV_FINALIZACAO = MOV_FINALIZACAO;
    }

    public Boolean getFINALIZADO() {
        if (FINALIZADO == null) {
            FINALIZADO = false;
        }
        return FINALIZADO;
    }

    public void setFINALIZADO(Boolean FINALIZADO) {
        this.FINALIZADO = FINALIZADO;
    }

    public Parceiro getParceiro() {
        return parceiro;
    }

    public void setParceiro(Parceiro parceiro) {
        this.parceiro = parceiro;
    }

    public Funcionario getFuncionario() {
//        if (funcionario == null) {
//            funcionario = new Funcionario();
//        }
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getMOV_REPROVADO() {
        return MOV_REPROVADO;
    }

    public void setMOV_REPROVADO(String MOV_REPROVADO) {
        this.MOV_REPROVADO = MOV_REPROVADO;
    }

    public String getMOV_CONTATO() {
        return MOV_CONTATO;
    }

    public void setMOV_CONTATO(String MOV_CONTATO) {
        this.MOV_CONTATO = MOV_CONTATO;
    }

    public String getMOV_TELEFONE() {
        return MOV_TELEFONE;
    }

    public void setMOV_TELEFONE(String MOV_TELEFONE) {
        this.MOV_TELEFONE = MOV_TELEFONE;
    }

    public String getMOV_CELULAR() {
        return MOV_CELULAR;
    }

    public void setMOV_CELULAR(String MOV_CELULAR) {
        this.MOV_CELULAR = MOV_CELULAR;
    }

    public String getMOV_EMAIL() {
        return MOV_EMAIL;
    }

    public void setMOV_EMAIL(String MOV_EMAIL) {
        this.MOV_EMAIL = MOV_EMAIL;
    }

    public int getMOV_STATUS() {

        return MOV_STATUS;
    }

    public void setMOV_STATUS(int MOV_STATUS) {
        this.MOV_STATUS = MOV_STATUS;
    }

    public StatusOS getStatus() {
        for (StatusOS sos : new StatusOSBean().getLista()) {
            if (MOV_STATUS == sos.getSOS_ID()) {
                status = sos;
            }
        }
        return status;
    }

    public void setStatus(StatusOS status) {
        this.status = status;
    }

    public int getMOV_GARANTIA() {
        return MOV_GARANTIA;
    }

    public void setMOV_GARANTIA(int MOV_GARANTIA) {
        this.MOV_GARANTIA = MOV_GARANTIA;
    }

    public String getMOV_OBJETO() {
        return MOV_OBJETO;
    }

    public void setMOV_OBJETO(String MOV_OBJETO) {
        this.MOV_OBJETO = MOV_OBJETO;
    }

    public String getMOV_DEFEITO() {
        return MOV_DEFEITO;
    }

    public void setMOV_DEFEITO(String MOV_DEFEITO) {
        this.MOV_DEFEITO = MOV_DEFEITO;
    }

    public String getMOV_OBSTEC() {
        return MOV_OBSTEC;
    }

    public void setMOV_OBSTEC(String MOV_OBSTEC) {
        this.MOV_OBSTEC = MOV_OBSTEC;
    }

    public String getMOV_OBSGER() {
        return MOV_OBSGER;
    }

    public void setMOV_OBSGER(String MOV_OBSGER) {
        this.MOV_OBSGER = MOV_OBSGER;
    }

    public String getMOV_DESCRICAO() {
        return MOV_DESCRICAO;
    }

    public void setMOV_DESCRICAO(String MOV_DESCRICAO) {
        this.MOV_DESCRICAO = MOV_DESCRICAO;
    }

    public boolean isMOV_PRODORDESC() {
        return MOV_PRODORDESC;
    }

    public void setMOV_PRODORDESC(boolean MOV_PRODORDESC) {
        this.MOV_PRODORDESC = MOV_PRODORDESC;
    }

    public double getMOV_TOTALPRO() {
        return MOV_TOTALPRO;
    }

    public void setMOV_TOTALPRO(double MOV_TOTALPRO) {
        this.MOV_TOTALPRO = MOV_TOTALPRO;
    }

    public double getMOV_TOTALSERV() {
        return MOV_TOTALSERV;
    }

    public void setMOV_TOTALSERV(double MOV_TOTALSERV) {
        this.MOV_TOTALSERV = MOV_TOTALSERV;
    }

    public double getMOV_TOTALFRETE() {
        return MOV_TOTALFRETE;
    }

    public void setMOV_TOTALFRETE(double MOV_TOTALFRETE) {
        this.MOV_TOTALFRETE = MOV_TOTALFRETE;
    }

    public double getMOV_TOTALDESC() {
        return MOV_TOTALDESC;
    }

    public void setMOV_TOTALDESC(double MOV_TOTALDESC) {
        this.MOV_TOTALDESC = MOV_TOTALDESC;
    }

    public double getMOV_TOTALSEGURO() {
        return MOV_TOTALSEGURO;
    }

    public void setMOV_TOTALSEGURO(double MOV_TOTALSEGURO) {
        this.MOV_TOTALSEGURO = MOV_TOTALSEGURO;
    }

    public double getMOV_TOTALOUTRAS() {
        return MOV_TOTALOUTRAS;
    }

    public void setMOV_TOTALOUTRAS(double MOV_TOTALOUTRAS) {
        this.MOV_TOTALOUTRAS = MOV_TOTALOUTRAS;
    }

    public double getMOV_TOTALICMSST() {
        return MOV_TOTALICMSST;
    }

    public void setMOV_TOTALICMSST(double MOV_TOTALICMSST) {
        this.MOV_TOTALICMSST = MOV_TOTALICMSST;
    }

    public double getMOV_TOTALIPI() {
        return MOV_TOTALIPI;
    }

    public void setMOV_TOTALIPI(double MOV_TOTALIPI) {
        this.MOV_TOTALIPI = MOV_TOTALIPI;
    }

    public double getMOV_TOTAL() {
        return MOV_TOTAL;
    }

    public void setMOV_TOTAL(double MOV_TOTAL) {
        this.MOV_TOTAL = MOV_TOTAL;
    }

    public String getMOV_RESUMO() {
        return MOV_RESUMO;
    }

    public void setMOV_RESUMO(String MOV_RESUMO) {
        this.MOV_RESUMO = MOV_RESUMO;
    }

    public String getPAR_TELEFONE1() {
        return PAR_TELEFONE1;
    }

    public void setPAR_TELEFONE1(String PAR_TELEFONE1) {
        this.PAR_TELEFONE1 = PAR_TELEFONE1;
    }

    public String getPAR_CELULAR() {
        return PAR_CELULAR;
    }

    public void setPAR_CELULAR(String PAR_CELULAR) {
        this.PAR_CELULAR = PAR_CELULAR;
    }

    public String getPAR_EMAIL() {
        return PAR_EMAIL;
    }

    public void setPAR_EMAIL(String PAR_EMAIL) {
        this.PAR_EMAIL = PAR_EMAIL;
    }

//    public String getMOV_STATUSSTR() {
//        List<Generico> listG = null;
//        int foo = 0;
//        if (MOV_TIPO == null || MOV_TIPO.equals("")) {
//            return "";
//        }
//        switch (MOV_TIPO) {
//            case "ODS":
//                foo = 160; //ORDEM SERVICO
//                listG = new GenericoBean().getListStODSBusca();
//                break;
//            case "ORC":
//                listG = new GenericoBean().getListStPedOrcTable();
//                foo = 240; //ORÇAMENTO
//                break;
//            case "PED":
//                listG = new GenericoBean().getListStPedOrcTable();
//                foo = 250; //PEDIDO
//                break;
//            case "CTO":
//                listG = new GenericoBean().getListStPedOrcTable();
//                foo = 250; //PEDIDO
//                break;
//            case "ODC":
//                listG = new GenericoBean().getListStOrdemCompra();
//                foo = 280; //ORDEM COMPRA
//                break;
//            default:
//                foo = 0;
//                break;
//        }
//        for (Generico st : listG) {
//            if ((MOV_STATUS + foo) == st.getGEN_ID()) {
//                MOV_STATUSSTR = st.getGEN_NOME();
//                break;
//            }
//        }
//        return MOV_STATUSSTR;
//    }
    public int getTEC_ID() {
        return TEC_ID;
    }

    public void setTEC_ID(int TEC_ID) {
        this.TEC_ID = TEC_ID;
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

    public List<MovimentoItem> getListMovimentoItem() {
        if (listMovimentoItem == null) {
            listMovimentoItem = new ArrayList<>();
        }
        return listMovimentoItem;
    }

    public void setListMovimentoItem(List<MovimentoItem> listMovimentoItem) {
        this.listMovimentoItem = listMovimentoItem;
    }

    public Date getMOV_TIMESAIDACLI() {
        return MOV_TIMESAIDACLI;
    }

    public void setMOV_TIMESAIDACLI(Date MOV_TIMESAIDACLI) {
        this.MOV_TIMESAIDACLI = MOV_TIMESAIDACLI;
    }

    public Date getMOV_TIMECHEGADACLI() {
        return MOV_TIMECHEGADACLI;
    }

    public void setMOV_TIMECHEGADACLI(Date MOV_TIMECHEGADACLI) {
        this.MOV_TIMECHEGADACLI = MOV_TIMECHEGADACLI;
    }

    public Date getMOV_TIMESAIDAEMP() {
        return MOV_TIMESAIDAEMP;
    }

    public void setMOV_TIMESAIDAEMP(Date MOV_TIMESAIDAEMP) {
        this.MOV_TIMESAIDAEMP = MOV_TIMESAIDAEMP;
    }

    public Date getMOV_TIMECHEGADAEMP() {
        return MOV_TIMECHEGADAEMP;
    }

    public void setMOV_TIMECHEGADAEMP(Date MOV_TIMECHEGADAEMP) {
        this.MOV_TIMECHEGADAEMP = MOV_TIMECHEGADAEMP;
    }

    public EquipamentoServico getEquipamentoServico() {
        if (equipamentoServico == null) {
            equipamentoServico = new EquipamentoServico();
        }
        return equipamentoServico;
    }

    public void setEquipamentoServico(EquipamentoServico equipamentoServico) {
        this.equipamentoServico = equipamentoServico;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.MOV_ID;
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
        final Movimento other = (Movimento) obj;
        if (this.MOV_ID != other.MOV_ID) {
            return false;
        }
        return true;
    }

}
