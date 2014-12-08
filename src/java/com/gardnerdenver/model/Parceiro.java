package com.gardnerdenver.model;

import com.gardnerdenver.bean.EstadoBean;
import com.gardnerdenver.facade.MunicipioFacade;
import com.gardnerdenver.util.Util;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Parceiro.findParceiros", query = "select p from Parceiro p")
})
public class Parceiro implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_PARCEIRO = "Parceiro.findParceiros";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PAR_ID;
    @ManyToOne
    @JoinColumn(name = "FUN_ID", referencedColumnName = "FUN_ID")
    private Funcionario funcionario;
    @Column(length = 200)
    private String PAR_NOME = "";
    @Column(length = 200)
    private String PAR_RAZAO = "";
    @Column(length = 1)
    private boolean PAR_CLI; // Sim ou Nao
    @Column(length = 1)
    private boolean PAR_FRN; // Sim ou Nao
    @Column(length = 1)
    private boolean PAR_TRA; // Sim ou Nao
    @Column(length = 20)
    private String PAR_CNPJCPF;
    @Column(length = 1)
    private String PAR_TIPOPESSOA = "J";// TIPO PESSOA. F = FISICA J = JURIDICA
    @Column(length = 20)
    private String PAR_CNPJCPFCOB;
    @Column(length = 1)
    private String PAR_TIPOPESSOACOB = "J";// TIPO PESSOA. F = FISICA J = JURIDICA
    @Column(length = 20)
    private String PAR_CNPJCPFENT;
    @Column(length = 1)
    private String PAR_TIPOPESSOAENT = "J";// TIPO PESSOA. F = FISICA J = JURIDICA
    @Column(length = 20)
    private String PAR_INSCEST;
    @Column(length = 20)
    private String PAR_INSCMUN;
    @Column(length = 30)
    private String PAR_SUFRAMA;
    @Column(length = 20)
    private String PAR_TELEFONE1;
    @Column(length = 20)
    private String PAR_TELEFONE2;
    @Column(length = 20)
    private String PAR_FAX;
    @Column(length = 20)
    private String PAR_CELULAR;
    @Column(length = 10)
    private String PAR_CEP;
    @Column(length = 200)
    private String PAR_ENDERECO;
    @Column(length = 10)
    private String PAR_NUMERO;
    @Column(length = 50)
    private String PAR_COMPLEMENTO;
    @Column(length = 200)
    private String PAR_BAIRRO;
    @Column(length = 10)
    private int PAR_CIDADE;
    @Column(length = 4)
    private int PAR_UF;
    @Column(length = 100)
    private String PAR_SITE;
    @Column(length = 100)
    private String PAR_EMAIL;
    @Transient
    private String PAR_MULTMAILS;
    @Column(length = 100)
    private String PAR_EMAILNFE;
    @Column(length = 40)
    private String PAR_CONTATO;
    @Column(length = 20)
    private String PAR_FONECONTATO;
    @Column(length = 20)
    private String PAR_RAMAL1;
    private String PAR_RAMAL2;
    private String PAR_RAMALFAX;
    @Column(length = 20)
    private String PAR_SETOR;
    @Column(length = 100)
    private String PAR_EMAILCONTATO;
    @Column(length = 5000)
    private String PAR_OBS = "";
    @Column(length = 3)
    private int PAR_ENDENT;
    @Column(length = 3)
    private int PAR_ENDCOB;
    @Transient
    private boolean PAR_ENDENTBOOL = false;
    @Transient
    private boolean PAR_ENDCOBBOOL = false;
    //Entrega
    @Column(length = 10)
    private String PAR_CEPENT;
    @Column(length = 200)
    private String PAR_ENDERECOENT;
    @Column(length = 10)
    private String PAR_NUMEROENT;
    @Column(length = 50)
    private String PAR_COMPLEMENTOENT;
    @Column(length = 200)
    private String PAR_BAIRROENT;
    @Column(length = 10)
    private int PAR_CIDADEENT;
    @Column(length = 4)
    private int PAR_UFENT;
    //Cobrança
    @Column(length = 10)
    private String PAR_CEPCOB;
    @Column(length = 200)
    private String PAR_ENDERECOCOB;
    @Column(length = 10)
    private String PAR_NUMEROCOB;
    @Column(length = 50)
    private String PAR_COMPLEMENTOCOB;
    @Column(length = 200)
    private String PAR_BAIRROCOB;
    @Column(length = 10)
    private int PAR_CIDADECOB;
    @Column(length = 4)
    private int PAR_UFCOB;
    @Column
    @Temporal(value = TemporalType.DATE)
    private Date PAR_NIVER1;
    @Column
    @Temporal(value = TemporalType.DATE)
    private Date PAR_DATARETORNO;
    @Transient
    private String PAR_DATARETORNOSTR;
    @Transient
    private String PAR_ST;
    @Transient
    private String PAR_CIDADESTR;
    @Transient
    private String PAR_UFSTR;
    @OneToMany(mappedBy = "parceiro", fetch = FetchType.EAGER)//    @Fetch(FetchMode.SUBSELECT)
    private List<Equipamento> equipamentos;
    @Transient
    private StringBuilder textoMail;
    @Transient
    private Estado estado;
    @Transient
    private Municipio municipio;
    @Transient
    private Estado estadoEnt;
    @Transient
    private Municipio municipioEnt;
    @Transient
    private Estado estadoCob;
    @Transient
    private Municipio municipioCob;
    @ManyToOne
    @JoinColumn(name = "VEN_ID", referencedColumnName = "FUN_ID")
    private Funcionario vendedor;

    @OneToMany(mappedBy = "parceiro")//    @Fetch(FetchMode.SUBSELECT)
    private List<Historico> historicos;

    @OneToMany(mappedBy = "parceiro")//    @Fetch(FetchMode.SUBSELECT)
    private List<Movimento> movimentos;

    public Parceiro() {
    }

    public Parceiro(Parceiro p) {
        this.PAR_ID = p.getPAR_ID();
        this.funcionario = p.getFuncionario();
        this.PAR_NOME = p.getPAR_NOME();
        this.PAR_RAZAO = p.getPAR_RAZAO();
        this.PAR_CLI = p.isPAR_CLI();
        this.PAR_FRN = p.isPAR_FRN();
        this.PAR_TRA = p.isPAR_TRA();
        this.PAR_CNPJCPF = p.getPAR_CNPJCPF();
        this.PAR_CNPJCPFCOB = p.getPAR_CNPJCPFCOB();
        this.PAR_CNPJCPFENT = p.getPAR_CNPJCPFENT();
        this.PAR_INSCEST = p.getPAR_INSCEST();
        this.PAR_INSCMUN = p.getPAR_INSCMUN();
        this.PAR_SUFRAMA = p.getPAR_SUFRAMA();
        this.PAR_TELEFONE1 = p.getPAR_TELEFONE1();
        this.PAR_TELEFONE2 = p.getPAR_TELEFONE2();
        this.PAR_FAX = p.getPAR_FAX();
        this.PAR_CELULAR = p.getPAR_CELULAR();
        this.PAR_CEP = p.getPAR_CEP();
        this.PAR_ENDERECO = p.getPAR_ENDERECO();
        this.PAR_NUMERO = p.getPAR_NUMERO();
        this.PAR_COMPLEMENTO = p.getPAR_COMPLEMENTO();
        this.PAR_BAIRRO = p.getPAR_BAIRRO();
        this.PAR_CIDADE = p.getPAR_CIDADE();
        this.PAR_UF = p.getPAR_UF();
        this.PAR_SITE = p.getPAR_SITE();
        this.PAR_EMAIL = p.getPAR_EMAIL();
        this.PAR_MULTMAILS = p.getPAR_MULTMAILS();
        this.PAR_EMAILNFE = p.getPAR_EMAILNFE();
        this.PAR_CONTATO = p.getPAR_CONTATO();
        this.PAR_FONECONTATO = p.getPAR_FONECONTATO();
        this.PAR_SETOR = p.getPAR_SETOR();
        this.PAR_EMAILCONTATO = p.getPAR_EMAILCONTATO();
        this.PAR_OBS = p.getPAR_OBS();
        this.PAR_ENDENT = p.getPAR_ENDENT();
        this.PAR_ENDCOB = p.getPAR_ENDCOB();
        this.PAR_CEPENT = p.getPAR_CEPENT();
        this.PAR_ENDERECOENT = p.getPAR_ENDERECOENT();
        this.PAR_NUMEROENT = p.getPAR_NUMEROENT();
        this.PAR_COMPLEMENTOENT = p.getPAR_COMPLEMENTOENT();
        this.PAR_BAIRROENT = p.getPAR_BAIRROENT();
        this.PAR_CIDADEENT = p.getPAR_CIDADEENT();
        this.PAR_UFENT = p.getPAR_UFENT();
        this.PAR_CEPCOB = p.getPAR_CEPCOB();
        this.PAR_ENDERECOCOB = p.getPAR_ENDERECOCOB();
        this.PAR_NUMEROCOB = p.getPAR_NUMEROCOB();
        this.PAR_COMPLEMENTOCOB = p.getPAR_COMPLEMENTOCOB();
        this.PAR_BAIRROCOB = p.getPAR_BAIRROCOB();
        this.PAR_CIDADECOB = p.getPAR_CIDADECOB();
        this.PAR_UFCOB = p.getPAR_UFCOB();
        this.PAR_NIVER1 = p.getPAR_NIVER1();
        this.PAR_DATARETORNO = p.getPAR_DATARETORNO();
        this.PAR_DATARETORNOSTR = p.getPAR_DATARETORNOSTR();
        this.PAR_ST = p.getPAR_ST();
        this.PAR_CIDADESTR = p.getPAR_CIDADESTR();
        this.PAR_UFSTR = p.getPAR_UFSTR();
    }

    public String getPAR_ST() {
        if (!getPAR_DATARETORNOSTR().equalsIgnoreCase("") && PAR_DATARETORNO.before(new Date())) {
            PAR_ST = "color: red;";
        } else {
            PAR_ST = "";
        }

        return PAR_ST;
    }

    public void setPAR_ST(String PAR_ST) {
        this.PAR_ST = PAR_ST;
    }

    public String getPAR_DATARETORNOSTR() {
        PAR_DATARETORNOSTR = "";
        if (PAR_DATARETORNO != null) {
            PAR_DATARETORNOSTR = Util.dateToStr(PAR_DATARETORNO);
        }
        return PAR_DATARETORNOSTR;
    }

    public void setPAR_DATARETORNOSTR(String PAR_DATARETORNOSTR) {
        this.PAR_DATARETORNOSTR = PAR_DATARETORNOSTR;
    }

    public Date getPAR_DATARETORNO() {
        return PAR_DATARETORNO;
    }

    public void setPAR_DATARETORNO(Date PAR_DATARETORNO) {
        this.PAR_DATARETORNO = PAR_DATARETORNO;
    }

    public String getPAR_INSCEST() {
        return PAR_INSCEST;
    }

    public void setPAR_INSCEST(String PAR_INSCEST) {
        this.PAR_INSCEST = PAR_INSCEST;
    }

    public String getPAR_INSCMUN() {
        return PAR_INSCMUN;
    }

    public void setPAR_INSCMUN(String PAR_INSCMUN) {
        this.PAR_INSCMUN = PAR_INSCMUN;
    }

    public int getPAR_CIDADE() {
        return PAR_CIDADE;
    }

    public void setPAR_CIDADE(int PAR_CIDADE) {
        this.PAR_CIDADE = PAR_CIDADE;
    }

    public int getPAR_UF() {
        return PAR_UF;
    }

    public void setPAR_UF(int PAR_UF) {
        this.PAR_UF = PAR_UF;
    }

    public Date getPAR_NIVER1() {
        return PAR_NIVER1;
    }

    public void setPAR_NIVER1(Date PAR_NIVER1) {
        this.PAR_NIVER1 = PAR_NIVER1;
    }

    public boolean isPAR_CLI() {
        return PAR_CLI;
    }

    public void setPAR_CLI(boolean PAR_CLI) {
        this.PAR_CLI = PAR_CLI;
    }

    public boolean isPAR_FRN() {
        return PAR_FRN;
    }

    public void setPAR_FRN(boolean PAR_FRN) {
        this.PAR_FRN = PAR_FRN;
    }

    public boolean isPAR_TRA() {
        return PAR_TRA;
    }

    public void setPAR_TRA(boolean PAR_TRA) {
        this.PAR_TRA = PAR_TRA;
    }

    public String getPAR_TIPOPESSOA() {
        return PAR_TIPOPESSOA;
    }

    public void setPAR_TIPOPESSOA(String PAR_TIPOPESSOA) {
        this.PAR_TIPOPESSOA = PAR_TIPOPESSOA;
    }

    public String getPAR_TIPOPESSOACOB() {
        return PAR_TIPOPESSOACOB;
    }

    public void setPAR_TIPOPESSOACOB(String PAR_TIPOPESSOACOB) {
        this.PAR_TIPOPESSOACOB = PAR_TIPOPESSOACOB;
    }

    public String getPAR_TIPOPESSOAENT() {
        return PAR_TIPOPESSOAENT;
    }

    public void setPAR_TIPOPESSOAENT(String PAR_TIPOPESSOAENT) {
        this.PAR_TIPOPESSOAENT = PAR_TIPOPESSOAENT;
    }

    public String getPAR_BAIRRO() {
        return PAR_BAIRRO;
    }

    public void setPAR_BAIRRO(String PAR_BAIRRO) {
        this.PAR_BAIRRO = PAR_BAIRRO;
    }

    public String getPAR_BAIRROCOB() {
        return PAR_BAIRROCOB;
    }

    public void setPAR_BAIRROCOB(String PAR_BAIRROCOB) {
        this.PAR_BAIRROCOB = PAR_BAIRROCOB;
    }

    public String getPAR_BAIRROENT() {
        return PAR_BAIRROENT;
    }

    public void setPAR_BAIRROENT(String PAR_BAIRROENT) {
        this.PAR_BAIRROENT = PAR_BAIRROENT;
    }

    public String getPAR_CELULAR() {
        return PAR_CELULAR;
    }

    public void setPAR_CELULAR(String PAR_CELULAR) {
        this.PAR_CELULAR = PAR_CELULAR;
    }

    public String getPAR_CEP() {
        return PAR_CEP;
    }

    public void setPAR_CEP(String PAR_CEP) {
        this.PAR_CEP = PAR_CEP;
    }

    public String getPAR_CEPCOB() {
        return PAR_CEPCOB;
    }

    public void setPAR_CEPCOB(String PAR_CEPCOB) {
        this.PAR_CEPCOB = PAR_CEPCOB;
    }

    public String getPAR_CEPENT() {
        return PAR_CEPENT;
    }

    public void setPAR_CEPENT(String PAR_CEPENT) {
        this.PAR_CEPENT = PAR_CEPENT;
    }

    public int getPAR_CIDADECOB() {
        return PAR_CIDADECOB;
    }

    public void setPAR_CIDADECOB(int PAR_CIDADECOB) {
        this.PAR_CIDADECOB = PAR_CIDADECOB;
    }

    public int getPAR_CIDADEENT() {
        return PAR_CIDADEENT;
    }

    public void setPAR_CIDADEENT(int PAR_CIDADEENT) {
        this.PAR_CIDADEENT = PAR_CIDADEENT;
    }

    public String getPAR_CNPJCPF() {
        return PAR_CNPJCPF;
    }

    public void setPAR_CNPJCPF(String PAR_CNPJCPF) {
        this.PAR_CNPJCPF = PAR_CNPJCPF;
    }

    public String getPAR_CNPJCPFCOB() {
        return PAR_CNPJCPFCOB;
    }

    public void setPAR_CNPJCPFCOB(String PAR_CNPJCPFCOB) {
        this.PAR_CNPJCPFCOB = PAR_CNPJCPFCOB;
    }

    public String getPAR_CNPJCPFENT() {
        return PAR_CNPJCPFENT;
    }

    public void setPAR_CNPJCPFENT(String PAR_CNPJCPFENT) {
        this.PAR_CNPJCPFENT = PAR_CNPJCPFENT;
    }

    public String getPAR_COMPLEMENTO() {
        return PAR_COMPLEMENTO;
    }

    public void setPAR_COMPLEMENTO(String PAR_COMPLEMENTO) {
        this.PAR_COMPLEMENTO = PAR_COMPLEMENTO;
    }

    public String getPAR_COMPLEMENTOCOB() {
        return PAR_COMPLEMENTOCOB;
    }

    public void setPAR_COMPLEMENTOCOB(String PAR_COMPLEMENTOCOB) {
        this.PAR_COMPLEMENTOCOB = PAR_COMPLEMENTOCOB;
    }

    public String getPAR_COMPLEMENTOENT() {
        return PAR_COMPLEMENTOENT;
    }

    public void setPAR_COMPLEMENTOENT(String PAR_COMPLEMENTOENT) {
        this.PAR_COMPLEMENTOENT = PAR_COMPLEMENTOENT;
    }

    public String getPAR_CONTATO() {
        return PAR_CONTATO;
    }

    public void setPAR_CONTATO(String PAR_CONTATO) {
        this.PAR_CONTATO = PAR_CONTATO;
    }

    public String getPAR_SUFRAMA() {
        return PAR_SUFRAMA;
    }

    public void setPAR_SUFRAMA(String PAR_SUFRAMA) {
        this.PAR_SUFRAMA = PAR_SUFRAMA;
    }

    public String getPAR_SITE() {
        return PAR_SITE;
    }

    public void setPAR_SITE(String PAR_SITE) {
        this.PAR_SITE = PAR_SITE;
    }

    public String getPAR_EMAIL() {
        return PAR_EMAIL;
    }

    public void setPAR_EMAIL(String PAR_EMAIL) {
        this.PAR_EMAIL = PAR_EMAIL;
    }

    public String getPAR_MULTMAILS() {
        return PAR_MULTMAILS;
    }

    public void setPAR_MULTMAILS(String PAR_MULTMAILS) {
        this.PAR_MULTMAILS = PAR_MULTMAILS;
    }

    public String getPAR_EMAILCONTATO() {
        return PAR_EMAILCONTATO;
    }

    public void setPAR_EMAILCONTATO(String PAR_EMAILCONTATO) {
        this.PAR_EMAILCONTATO = PAR_EMAILCONTATO;
    }

    public String getPAR_EMAILNFE() {
        return PAR_EMAILNFE;
    }

    public void setPAR_EMAILNFE(String PAR_EMAILNFE) {
        this.PAR_EMAILNFE = PAR_EMAILNFE;
    }

    public String getPAR_ENDERECO() {
        return PAR_ENDERECO;
    }

    public void setPAR_ENDERECO(String PAR_ENDERECO) {
        this.PAR_ENDERECO = PAR_ENDERECO;
    }

    public String getPAR_ENDERECOCOB() {
        return PAR_ENDERECOCOB;
    }

    public void setPAR_ENDERECOCOB(String PAR_ENDERECOCOB) {
        this.PAR_ENDERECOCOB = PAR_ENDERECOCOB;
    }

    public String getPAR_ENDERECOENT() {
        return PAR_ENDERECOENT;
    }

    public void setPAR_ENDERECOENT(String PAR_ENDERECOENT) {
        this.PAR_ENDERECOENT = PAR_ENDERECOENT;
    }

    public String getPAR_FAX() {
        return PAR_FAX;
    }

    public void setPAR_FAX(String PAR_FAX) {
        this.PAR_FAX = PAR_FAX;
    }

    public String getPAR_FONECONTATO() {
        return PAR_FONECONTATO;
    }

    public void setPAR_FONECONTATO(String PAR_FONECONTATO) {
        this.PAR_FONECONTATO = PAR_FONECONTATO;
    }

    public String getPAR_RAMAL1() {
        return PAR_RAMAL1;
    }

    public void setPAR_RAMAL1(String PAR_RAMAL1) {
        this.PAR_RAMAL1 = PAR_RAMAL1;
    }

    public String getPAR_RAMAL2() {
        return PAR_RAMAL2;
    }

    public void setPAR_RAMAL2(String PAR_RAMAL2) {
        this.PAR_RAMAL2 = PAR_RAMAL2;
    }

    public String getPAR_RAMALFAX() {
        return PAR_RAMALFAX;
    }

    public void setPAR_RAMALFAX(String PAR_RAMALFAX) {
        this.PAR_RAMALFAX = PAR_RAMALFAX;
    }

    public String getPAR_SETOR() {
        return PAR_SETOR;
    }

    public void setPAR_SETOR(String PAR_SETOR) {
        this.PAR_SETOR = PAR_SETOR;
    }

    public int getPAR_ID() {
        return PAR_ID;
    }

    public void setPAR_ID(int PAR_ID) {
        this.PAR_ID = PAR_ID;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getPAR_NOME() {
        return PAR_NOME;
    }

    public void setPAR_NOME(String PAR_NOME) {
        this.PAR_NOME = PAR_NOME;
    }

    public String getPAR_NUMERO() {
        return PAR_NUMERO;
    }

    public void setPAR_NUMERO(String PAR_NUMERO) {
        this.PAR_NUMERO = PAR_NUMERO;
    }

    public String getPAR_NUMEROCOB() {
        return PAR_NUMEROCOB;
    }

    public void setPAR_NUMEROCOB(String PAR_NUMEROCOB) {
        this.PAR_NUMEROCOB = PAR_NUMEROCOB;
    }

    public String getPAR_NUMEROENT() {
        return PAR_NUMEROENT;
    }

    public void setPAR_NUMEROENT(String PAR_NUMEROENT) {
        this.PAR_NUMEROENT = PAR_NUMEROENT;
    }

    public String getPAR_RAZAO() {
        return PAR_RAZAO;
    }

    public void setPAR_RAZAO(String PAR_RAZAO) {
        this.PAR_RAZAO = PAR_RAZAO;
    }

    public String getPAR_TELEFONE1() {
        return PAR_TELEFONE1;
    }

    public void setPAR_TELEFONE1(String PAR_TELEFONE1) {
        this.PAR_TELEFONE1 = PAR_TELEFONE1;
    }

    public String getPAR_TELEFONE2() {
        return PAR_TELEFONE2;
    }

    public void setPAR_TELEFONE2(String PAR_TELEFONE2) {
        this.PAR_TELEFONE2 = PAR_TELEFONE2;
    }

    public int getPAR_UFCOB() {
        return PAR_UFCOB;
    }

    public void setPAR_UFCOB(int PAR_UFCOB) {
        this.PAR_UFCOB = PAR_UFCOB;
    }

    public int getPAR_UFENT() {
        return PAR_UFENT;
    }

    public void setPAR_UFENT(int PAR_UFENT) {
        this.PAR_UFENT = PAR_UFENT;
    }

    public String getPAR_OBS() {
        return PAR_OBS;
    }

    public void setPAR_OBS(String PAR_OBS) {
        this.PAR_OBS = PAR_OBS;
    }

    public int getPAR_ENDENT() {
        return PAR_ENDENT;
    }

    public void setPAR_ENDENT(int PAR_ENDENT) {
        if (PAR_ENDENTBOOL == true) {
            this.PAR_ENDENT = 1;
        } else {
            this.PAR_ENDENT = PAR_ENDENT;
        }

    }

    public int getPAR_ENDCOB() {
        return PAR_ENDCOB;
    }

    public void setPAR_ENDCOB(int PAR_ENDCOB) {
        if (PAR_ENDCOBBOOL == true) {
            this.PAR_ENDCOB = 1;
        } else {
            this.PAR_ENDCOB = PAR_ENDCOB;
        }
    }

    public boolean isPAR_ENDENTBOOL() {
        return PAR_ENDENTBOOL;
    }

    public void setPAR_ENDENTBOOL(boolean PAR_ENDENTBOOL) {
        this.PAR_ENDENTBOOL = PAR_ENDENTBOOL;
    }

    public boolean isPAR_ENDCOBBOOL() {
        return PAR_ENDCOBBOOL;
    }

    public void setPAR_ENDCOBBOOL(boolean PAR_ENDCOBBOOL) {
        this.PAR_ENDCOBBOOL = PAR_ENDCOBBOOL;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public StringBuilder getTextoMail() {
        textoMail = new StringBuilder();
        if (equipamentos != null) {
            for (Equipamento e : equipamentos) {
                textoMail.append(e.getTextoEmail());
            }
        }
        return textoMail;
    }

    public void setTextoMail(StringBuilder textoMail) {
        this.textoMail = textoMail;
    }

    public Estado getEstado() {
        EstadoBean estBean = new EstadoBean();
        estado = estBean.getObjById(PAR_UF);

        return estado;
    }

    public void setEstado(Estado estado) {
        PAR_UF = estado.getJEST_ID();
        this.estado = estado;
    }

    public Municipio getMunicipio() {
//        MunicipioFacade munFacade = new MunicipioFacade();
//        municipio = munFacade.findMunicipio(PAR_CIDADE);

        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
//        PAR_CIDADE = municipio.getMUN_ID();
        this.municipio = municipio;
    }

    public Estado getEstadoEnt() {
        EstadoBean estBean = new EstadoBean();
        estadoEnt = estBean.getObjById(PAR_UFENT);
        return estadoEnt;
    }

    public void setEstadoEnt(Estado estadoEnt) {
        PAR_UFENT = estadoEnt.getJEST_ID();
        this.estadoEnt = estadoEnt;
    }

    public Municipio getMunicipioEnt() {
//        MunicipioFacade munFacade = new MunicipioFacade();
//        municipioEnt = munFacade.findMunicipio(PAR_CIDADEENT);
        return municipioEnt;
    }

    public void setMunicipioEnt(Municipio municipioEnt) {
//        PAR_CIDADEENT = municipioEnt.getMUN_ID();
        this.municipioEnt = municipioEnt;
    }

    public Estado getEstadoCob() {
        EstadoBean estBean = new EstadoBean();
        estadoCob = estBean.getObjById(PAR_UFCOB);
        return estadoCob;
    }

    public void setEstadoCob(Estado estadoCob) {
        PAR_UFCOB = estadoCob.getJEST_ID();
        this.estadoCob = estadoCob;
    }

    public Municipio getMunicipioCob() {
//        MunicipioFacade munFacade = new MunicipioFacade();
//        municipioCob = munFacade.findMunicipio(PAR_CIDADECOB);
        return municipioCob;
    }

    public void setMunicipioCob(Municipio municipioCob) {
//        PAR_CIDADECOB = municipioCob.getMUN_ID();
        this.municipioCob = municipioCob;
    }

    public String getPAR_CIDADESTR() {
        return PAR_CIDADESTR;
    }

    public void setPAR_CIDADESTR(String PAR_CIDADESTR) {
        this.PAR_CIDADESTR = PAR_CIDADESTR;
    }

    public String getPAR_UFSTR() {
        EstadoBean estBean = new EstadoBean();
        Estado est = estBean.getObjById(PAR_UF);
        PAR_UFSTR = "";
        if (PAR_UF != 0) {
            PAR_UFSTR = est.getJEST_UF();
        }

        return PAR_UFSTR;
    }

    public void setPAR_UFSTR(String PAR_UFSTR) {
        this.PAR_UFSTR = PAR_UFSTR;
    }

    public Funcionario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Funcionario vendedor) {
        this.vendedor = vendedor;
    }

    public List<Historico> getHistoricos() {
        return historicos;
    }

    public void setHistoricos(List<Historico> historicos) {
        this.historicos = historicos;
    }

    public List<Movimento> getMovimentos() {
        return movimentos;
    }

    public void setMovimentos(List<Movimento> movimentos) {
        this.movimentos = movimentos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.PAR_ID;
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
        final Parceiro other = (Parceiro) obj;
        if (this.PAR_ID != other.PAR_ID) {
            return false;
        }
        if (!Objects.equals(this.funcionario, other.funcionario)) {
            return false;
        }
        if (!Objects.equals(this.PAR_NOME, other.PAR_NOME)) {
            return false;
        }
        if (!Objects.equals(this.PAR_RAZAO, other.PAR_RAZAO)) {
            return false;
        }
        if (this.PAR_CLI != other.PAR_CLI) {
            return false;
        }
        if (this.PAR_FRN != other.PAR_FRN) {
            return false;
        }
        if (this.PAR_TRA != other.PAR_TRA) {
            return false;
        }
        if (!Objects.equals(this.PAR_CNPJCPF, other.PAR_CNPJCPF)) {
            return false;
        }
        if (!Objects.equals(this.PAR_TIPOPESSOA, other.PAR_TIPOPESSOA)) {
            return false;
        }
        if (!Objects.equals(this.PAR_CNPJCPFCOB, other.PAR_CNPJCPFCOB)) {
            return false;
        }
        if (!Objects.equals(this.PAR_TIPOPESSOACOB, other.PAR_TIPOPESSOACOB)) {
            return false;
        }
        if (!Objects.equals(this.PAR_CNPJCPFENT, other.PAR_CNPJCPFENT)) {
            return false;
        }
        if (!Objects.equals(this.PAR_TIPOPESSOAENT, other.PAR_TIPOPESSOAENT)) {
            return false;
        }
        if (!Objects.equals(this.PAR_INSCEST, other.PAR_INSCEST)) {
            return false;
        }
        if (!Objects.equals(this.PAR_INSCMUN, other.PAR_INSCMUN)) {
            return false;
        }
        if (!Objects.equals(this.PAR_SUFRAMA, other.PAR_SUFRAMA)) {
            return false;
        }
        if (!Objects.equals(this.PAR_TELEFONE1, other.PAR_TELEFONE1)) {
            return false;
        }
        if (!Objects.equals(this.PAR_TELEFONE2, other.PAR_TELEFONE2)) {
            return false;
        }
        if (!Objects.equals(this.PAR_FAX, other.PAR_FAX)) {
            return false;
        }
        if (!Objects.equals(this.PAR_CELULAR, other.PAR_CELULAR)) {
            return false;
        }
        if (!Objects.equals(this.PAR_CEP, other.PAR_CEP)) {
            return false;
        }
        if (!Objects.equals(this.PAR_ENDERECO, other.PAR_ENDERECO)) {
            return false;
        }
        if (!Objects.equals(this.PAR_NUMERO, other.PAR_NUMERO)) {
            return false;
        }
        if (!Objects.equals(this.PAR_COMPLEMENTO, other.PAR_COMPLEMENTO)) {
            return false;
        }
        if (!Objects.equals(this.PAR_BAIRRO, other.PAR_BAIRRO)) {
            return false;
        }
        if (this.PAR_CIDADE != other.PAR_CIDADE) {
            return false;
        }
        if (this.PAR_UF != other.PAR_UF) {
            return false;
        }
        if (!Objects.equals(this.PAR_SITE, other.PAR_SITE)) {
            return false;
        }
        if (!Objects.equals(this.PAR_EMAIL, other.PAR_EMAIL)) {
            return false;
        }
        if (!Objects.equals(this.PAR_MULTMAILS, other.PAR_MULTMAILS)) {
            return false;
        }
        if (!Objects.equals(this.PAR_EMAILNFE, other.PAR_EMAILNFE)) {
            return false;
        }
        if (!Objects.equals(this.PAR_CONTATO, other.PAR_CONTATO)) {
            return false;
        }
        if (!Objects.equals(this.PAR_FONECONTATO, other.PAR_FONECONTATO)) {
            return false;
        }
        if (!Objects.equals(this.PAR_SETOR, other.PAR_SETOR)) {
            return false;
        }
        if (!Objects.equals(this.PAR_EMAILCONTATO, other.PAR_EMAILCONTATO)) {
            return false;
        }
        if (!Objects.equals(this.PAR_OBS, other.PAR_OBS)) {
            return false;
        }
        if (this.PAR_ENDENT != other.PAR_ENDENT) {
            return false;
        }
        if (this.PAR_ENDCOB != other.PAR_ENDCOB) {
            return false;
        }
        if (this.PAR_ENDENTBOOL != other.PAR_ENDENTBOOL) {
            return false;
        }
        if (this.PAR_ENDCOBBOOL != other.PAR_ENDCOBBOOL) {
            return false;
        }
        if (!Objects.equals(this.PAR_CEPENT, other.PAR_CEPENT)) {
            return false;
        }
        if (!Objects.equals(this.PAR_ENDERECOENT, other.PAR_ENDERECOENT)) {
            return false;
        }
        if (!Objects.equals(this.PAR_NUMEROENT, other.PAR_NUMEROENT)) {
            return false;
        }
        if (!Objects.equals(this.PAR_COMPLEMENTOENT, other.PAR_COMPLEMENTOENT)) {
            return false;
        }
        if (!Objects.equals(this.PAR_BAIRROENT, other.PAR_BAIRROENT)) {
            return false;
        }
        if (this.PAR_CIDADEENT != other.PAR_CIDADEENT) {
            return false;
        }
        if (this.PAR_UFENT != other.PAR_UFENT) {
            return false;
        }
        if (!Objects.equals(this.PAR_CEPCOB, other.PAR_CEPCOB)) {
            return false;
        }
        if (!Objects.equals(this.PAR_ENDERECOCOB, other.PAR_ENDERECOCOB)) {
            return false;
        }
        if (!Objects.equals(this.PAR_NUMEROCOB, other.PAR_NUMEROCOB)) {
            return false;
        }
        if (!Objects.equals(this.PAR_COMPLEMENTOCOB, other.PAR_COMPLEMENTOCOB)) {
            return false;
        }
        if (!Objects.equals(this.PAR_BAIRROCOB, other.PAR_BAIRROCOB)) {
            return false;
        }
        if (this.PAR_CIDADECOB != other.PAR_CIDADECOB) {
            return false;
        }
        if (this.PAR_UFCOB != other.PAR_UFCOB) {
            return false;
        }
        if (!Objects.equals(this.PAR_NIVER1, other.PAR_NIVER1)) {
            return false;
        }
        if (!Objects.equals(this.PAR_DATARETORNO, other.PAR_DATARETORNO)) {
            return false;
        }
        if (!Objects.equals(this.PAR_DATARETORNOSTR, other.PAR_DATARETORNOSTR)) {
            return false;
        }
        if (!Objects.equals(this.PAR_ST, other.PAR_ST)) {
            return false;
        }
        if (!Objects.equals(this.PAR_CIDADESTR, other.PAR_CIDADESTR)) {
            return false;
        }
        if (!Objects.equals(this.PAR_UFSTR, other.PAR_UFSTR)) {
            return false;
        }
        return true;
    }

}
