package com.gardnerdenver.model;

import com.gardnerdenver.bean.EstadoBean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "Funcionario")
@NamedQueries({
    @NamedQuery(name = "Funcionario.funcByEmail", query = "select u from Funcionario u where u.FUN_LOGIN = :FUN_LOGIN"),
    @NamedQuery(name = "Funcionario.listTec", query = "select u from Funcionario u where u.FUN_TEC = 'S'"),
    @NamedQuery(name = "Funcionario.listVend", query = "select u from Funcionario u where u.FUN_VEND = 'S'")
})
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_BY_FUNCLOGIN = "Funcionario.funcByEmail";
    public static final String FIND_LIST_VEND = "Funcionario.listVend";
    public static final String FIND_LIST_TEC = "Funcionario.listTec";

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int FUN_ID;
    @Column
    private boolean FUN_ADMIN;
    @Column(length = 100)
    private String FUN_NOME;
    @Column(length = 100)
    private String FUN_EMAIL;
    @Column(length = 20)
    private String FUN_TELEFONE;
    @Column(length = 20)
    private String FUN_CEP;
    @Column(length = 100)
    private String FUN_ENDERECO;
    @Column
    private int FUN_NUMERO;
    @Column(length = 50)
    private String FUN_COMPLEMENTO;
    @Column(length = 100)
    private String FUN_BAIRRO;
    @Column(length = 4)
    private int FUN_UF;
    @Column(length = 10)
    private int FUN_CIDADE;
    @Column
    @Temporal(value = TemporalType.DATE)
    private Date FUN_ANIVER;
    @Column(length = 1)
    private String FUN_VEND;
    @Column(length = 1)
    private String FUN_REP;
    @Column(length = 1)
    private String FUN_TEC;
    @Column(length = 1)
    private String FUN_ACESS;
    @Column(columnDefinition = "numeric (5,2)")
    private double FUN_COMISSAO_VENDA;
    @Column(columnDefinition = "numeric (5,2)")
    private double FUN_COMISSAO_SERV;
    @Column(columnDefinition = "numeric (5,2)")
    private double FUN_DESCONTO_ITEM;
    @Column(columnDefinition = "numeric (5,2)")
    private double FUN_DESCONTO_GERAL;
    @Column
    private String FUN_LOGIN;
    @Column
    private String FUN_SENHA;
    @Transient
    private String FUN_VENDSN;
    @Transient
    private String FUN_TECSN;
    @Transient
    private String FUN_ACESSSN;
    @OneToMany(mappedBy = "vendedor")
    @Fetch(FetchMode.SUBSELECT)
    private List<Parceiro> clientes;
    @Transient
    private Estado estado;
    @Transient
    private Municipio municipio;

    @OneToMany(mappedBy = "funcionario")//    @Fetch(FetchMode.SUBSELECT)
    private List<Historico> historicos;
    @OneToMany(mappedBy = "funcionario")//    @Fetch(FetchMode.SUBSELECT)
    private List<Movimento> movimentos;

    public Funcionario() {
    }

    public Funcionario(Funcionario f) {
        this.FUN_ID = f.getFUN_ID();
        this.FUN_ADMIN = f.isFUN_ADMIN();
        this.FUN_NOME = f.getFUN_NOME();
        this.FUN_EMAIL = f.getFUN_EMAIL();
        this.FUN_TELEFONE = f.getFUN_TELEFONE();
        this.FUN_CEP = f.getFUN_CEP();
        this.FUN_ENDERECO = f.getFUN_ENDERECO();
        this.FUN_NUMERO = f.getFUN_NUMERO();
        this.FUN_COMPLEMENTO = f.getFUN_COMPLEMENTO();
        this.FUN_BAIRRO = f.getFUN_BAIRRO();
        this.FUN_UF = f.getFUN_UF();
        this.FUN_CIDADE = f.getFUN_CIDADE();
        this.FUN_ANIVER = f.getFUN_ANIVER();
        this.FUN_VEND = f.getFUN_VEND();
        this.FUN_REP = f.getFUN_REP();
        this.FUN_TEC = f.getFUN_TEC();
        this.FUN_ACESS = f.getFUN_ACESS();
        this.FUN_COMISSAO_VENDA = f.getFUN_COMISSAO_VENDA();
        this.FUN_COMISSAO_SERV = f.getFUN_COMISSAO_SERV();
        this.FUN_DESCONTO_ITEM = f.getFUN_DESCONTO_ITEM();
        this.FUN_DESCONTO_GERAL = f.getFUN_DESCONTO_GERAL();
        this.FUN_LOGIN = f.getFUN_LOGIN();
        this.FUN_SENHA = f.getFUN_SENHA();
        this.FUN_VENDSN = f.getFUN_VENDSN();
        this.FUN_TECSN = f.getFUN_TECSN();
        this.FUN_ACESSSN = f.getFUN_ACESSSN();
    }

    public int getFUN_ID() {
        return FUN_ID;
    }

    public void setFUN_ID(int FUN_ID) {
        this.FUN_ID = FUN_ID;
    }

    public boolean isFUN_ADMIN() {
        return FUN_ADMIN;
    }

    public void setFUN_ADMIN(boolean FUN_ADMIN) {
        this.FUN_ADMIN = FUN_ADMIN;
    }

    public String getFUN_NOME() {
        return FUN_NOME;
    }

    public void setFUN_NOME(String FUN_NOME) {
        this.FUN_NOME = FUN_NOME;
    }

    public String getFUN_EMAIL() {
        return FUN_EMAIL;
    }

    public void setFUN_EMAIL(String FUN_EMAIL) {
        this.FUN_EMAIL = FUN_EMAIL;
    }

    public String getFUN_TELEFONE() {
        return FUN_TELEFONE;
    }

    public void setFUN_TELEFONE(String FUN_TELEFONE) {
        this.FUN_TELEFONE = FUN_TELEFONE;
    }

    public String getFUN_CEP() {
        return FUN_CEP;
    }

    public void setFUN_CEP(String FUN_CEP) {
        this.FUN_CEP = FUN_CEP;
    }

    public String getFUN_ENDERECO() {
        return FUN_ENDERECO;
    }

    public void setFUN_ENDERECO(String FUN_ENDERECO) {
        this.FUN_ENDERECO = FUN_ENDERECO;
    }

    public int getFUN_NUMERO() {
        return FUN_NUMERO;
    }

    public void setFUN_NUMERO(int FUN_NUMERO) {
        this.FUN_NUMERO = FUN_NUMERO;
    }

    public String getFUN_COMPLEMENTO() {
        return FUN_COMPLEMENTO;
    }

    public void setFUN_COMPLEMENTO(String FUN_COMPLEMENTO) {
        this.FUN_COMPLEMENTO = FUN_COMPLEMENTO;
    }

    public String getFUN_BAIRRO() {
        return FUN_BAIRRO;
    }

    public void setFUN_BAIRRO(String FUN_BAIRRO) {
        this.FUN_BAIRRO = FUN_BAIRRO;
    }

    public int getFUN_UF() {
        return FUN_UF;
    }

    public void setFUN_UF(int FUN_UF) {
        this.FUN_UF = FUN_UF;
    }

    public int getFUN_CIDADE() {
        return FUN_CIDADE;
    }

    public void setFUN_CIDADE(int FUN_CIDADE) {
        this.FUN_CIDADE = FUN_CIDADE;
    }

    public Date getFUN_ANIVER() {
        return FUN_ANIVER;
    }

    public void setFUN_ANIVER(Date FUN_ANIVER) {
        this.FUN_ANIVER = FUN_ANIVER;
    }

    public String getFUN_VEND() {
        return FUN_VEND;
    }

    public void setFUN_VEND(String FUN_VEND) {
        this.FUN_VEND = FUN_VEND;
    }

    public String getFUN_REP() {
        return FUN_REP;
    }

    public void setFUN_REP(String FUN_REP) {
        this.FUN_REP = FUN_REP;
    }

    public String getFUN_TEC() {
        return FUN_TEC;
    }

    public void setFUN_TEC(String FUN_TEC) {
        this.FUN_TEC = FUN_TEC;
    }

    public String getFUN_ACESS() {

        return FUN_ACESS;
    }

    public void setFUN_ACESS(String FUN_ACESS) {
        this.FUN_ACESS = FUN_ACESS;
    }

    public double getFUN_COMISSAO_VENDA() {
        return FUN_COMISSAO_VENDA;
    }

    public void setFUN_COMISSAO_VENDA(double FUN_COMISSAO_VENDA) {
        this.FUN_COMISSAO_VENDA = FUN_COMISSAO_VENDA;
    }

    public double getFUN_COMISSAO_SERV() {
        return FUN_COMISSAO_SERV;
    }

    public void setFUN_COMISSAO_SERV(double FUN_COMISSAO_SERV) {
        this.FUN_COMISSAO_SERV = FUN_COMISSAO_SERV;
    }

    public double getFUN_DESCONTO_ITEM() {
        return FUN_DESCONTO_ITEM;
    }

    public void setFUN_DESCONTO_ITEM(double FUN_DESCONTO_ITEM) {
        this.FUN_DESCONTO_ITEM = FUN_DESCONTO_ITEM;
    }

    public double getFUN_DESCONTO_GERAL() {
        return FUN_DESCONTO_GERAL;
    }

    public void setFUN_DESCONTO_GERAL(double FUN_DESCONTO_GERAL) {
        this.FUN_DESCONTO_GERAL = FUN_DESCONTO_GERAL;
    }

    public String getFUN_LOGIN() {
        return FUN_LOGIN;
    }

    public void setFUN_LOGIN(String FUN_LOGIN) {
        this.FUN_LOGIN = FUN_LOGIN;
    }

    public String getFUN_SENHA() {
        return FUN_SENHA;
    }

    public void setFUN_SENHA(String FUN_SENHA) {
        this.FUN_SENHA = FUN_SENHA;
    }

    public String getFUN_VENDSN() {
        if ("S".equalsIgnoreCase(FUN_VEND)) {
            FUN_VENDSN = "Sim";
        } else {
            FUN_VENDSN = "Não";
        }
        return FUN_VENDSN;
    }

    public void setFUN_VENDSN(String FUN_VENDSN) {
        this.FUN_VENDSN = FUN_VENDSN;
    }

    public String getFUN_TECSN() {
        if ("S".equalsIgnoreCase(FUN_TEC)) {
            FUN_TECSN = "Sim";
        } else {
            FUN_TECSN = "Não";
        }
        return FUN_TECSN;
    }

    public void setFUN_TECSN(String FUN_TECSN) {
        this.FUN_TECSN = FUN_TECSN;
    }

    public String getFUN_ACESSSN() {
        if ("S".equalsIgnoreCase(FUN_ACESS)) {
            FUN_ACESSSN = "Sim";
        } else {
            FUN_ACESSSN = "Não";
        }
        return FUN_ACESSSN;
    }

    public void setFUN_ACESSSN(String FUN_ACESSSN) {
        this.FUN_ACESSSN = FUN_ACESSSN;
    }

    public List<Parceiro> getClientes() {
        return clientes;
    }

    public void setClientes(List<Parceiro> clientes) {
        this.clientes = clientes;
    }

    public Estado getEstado() {
        EstadoBean estBean = new EstadoBean();
        estado = estBean.getObjById(FUN_UF);

        return estado;
    }

    public void setEstado(Estado estado) {
        FUN_UF = estado.getJEST_ID();
        this.estado = estado;
    }

    public Municipio getMunicipio() {

        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
//        FUN_CIDADE = municipio.getMUN_ID();
        this.municipio = municipio;
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
        int hash = 3;
        hash = 23 * hash + this.FUN_ID;
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
        final Funcionario other = (Funcionario) obj;
        if (this.FUN_ID != other.FUN_ID) {
            return false;
        }
        if (this.FUN_ADMIN != other.FUN_ADMIN) {
            return false;
        }
        if (!Objects.equals(this.FUN_NOME, other.FUN_NOME)) {
            return false;
        }
        if (!Objects.equals(this.FUN_EMAIL, other.FUN_EMAIL)) {
            return false;
        }
        if (!Objects.equals(this.FUN_TELEFONE, other.FUN_TELEFONE)) {
            return false;
        }
        if (!Objects.equals(this.FUN_CEP, other.FUN_CEP)) {
            return false;
        }
        if (!Objects.equals(this.FUN_ENDERECO, other.FUN_ENDERECO)) {
            return false;
        }
        if (this.FUN_NUMERO != other.FUN_NUMERO) {
            return false;
        }
        if (!Objects.equals(this.FUN_COMPLEMENTO, other.FUN_COMPLEMENTO)) {
            return false;
        }
        if (!Objects.equals(this.FUN_BAIRRO, other.FUN_BAIRRO)) {
            return false;
        }
        if (this.FUN_UF != other.FUN_UF) {
            return false;
        }
        if (this.FUN_CIDADE != other.FUN_CIDADE) {
            return false;
        }
        if (!Objects.equals(this.FUN_ANIVER, other.FUN_ANIVER)) {
            return false;
        }
        if (!Objects.equals(this.FUN_VEND, other.FUN_VEND)) {
            return false;
        }
        if (!Objects.equals(this.FUN_REP, other.FUN_REP)) {
            return false;
        }
        if (!Objects.equals(this.FUN_TEC, other.FUN_TEC)) {
            return false;
        }
        if (!Objects.equals(this.FUN_ACESS, other.FUN_ACESS)) {
            return false;
        }
        if (Double.doubleToLongBits(this.FUN_COMISSAO_VENDA) != Double.doubleToLongBits(other.FUN_COMISSAO_VENDA)) {
            return false;
        }
        if (Double.doubleToLongBits(this.FUN_COMISSAO_SERV) != Double.doubleToLongBits(other.FUN_COMISSAO_SERV)) {
            return false;
        }
        if (Double.doubleToLongBits(this.FUN_DESCONTO_ITEM) != Double.doubleToLongBits(other.FUN_DESCONTO_ITEM)) {
            return false;
        }
        if (Double.doubleToLongBits(this.FUN_DESCONTO_GERAL) != Double.doubleToLongBits(other.FUN_DESCONTO_GERAL)) {
            return false;
        }
        if (!Objects.equals(this.FUN_LOGIN, other.FUN_LOGIN)) {
            return false;
        }
        if (!Objects.equals(this.FUN_SENHA, other.FUN_SENHA)) {
            return false;
        }
        if (!Objects.equals(this.FUN_VENDSN, other.FUN_VENDSN)) {
            return false;
        }
        if (!Objects.equals(this.FUN_TECSN, other.FUN_TECSN)) {
            return false;
        }
        if (!Objects.equals(this.FUN_ACESSSN, other.FUN_ACESSSN)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "FUN_ID=" + FUN_ID + ", FUN_ADMIN=" + FUN_ADMIN + ", FUN_NOME=" + FUN_NOME + ", FUN_EMAIL=" + FUN_EMAIL + ", FUN_TELEFONE=" + FUN_TELEFONE + ", FUN_CEP=" + FUN_CEP + ", FUN_ENDERECO=" + FUN_ENDERECO + ", FUN_NUMERO=" + FUN_NUMERO + ", FUN_COMPLEMENTO=" + FUN_COMPLEMENTO + ", FUN_BAIRRO=" + FUN_BAIRRO + ", FUN_UF=" + FUN_UF + ", FUN_CIDADE=" + FUN_CIDADE + ", FUN_ANIVER=" + FUN_ANIVER + ", FUN_VEND=" + FUN_VEND + ", FUN_REP=" + FUN_REP + ", FUN_TEC=" + FUN_TEC + ", FUN_ACESS=" + FUN_ACESS + ", FUN_COMISSAO_VENDA=" + FUN_COMISSAO_VENDA + ", FUN_COMISSAO_SERV=" + FUN_COMISSAO_SERV + ", FUN_DESCONTO_ITEM=" + FUN_DESCONTO_ITEM + ", FUN_DESCONTO_GERAL=" + FUN_DESCONTO_GERAL + ", FUN_LOGIN=" + FUN_LOGIN + ", FUN_SENHA=" + FUN_SENHA + ", FUN_VENDSN=" + FUN_VENDSN + ", FUN_TECSN=" + FUN_TECSN + ", FUN_ACESSSN=" + FUN_ACESSSN + ", clientes=" + clientes + ", estado=" + estado + ", municipio=" + municipio + '}';
    }

}
