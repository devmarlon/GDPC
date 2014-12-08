package com.gardnerdenver.model;

import com.gardnerdenver.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@NamedQuery(name = "FactoryUser.findListDist", query = "select u from FactoryUser u WHERE u.rol = :rol")
public class FactoryUser implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_LIST = "FactoryUser.findListDist";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int USU_ID;
    @Column(length = 100)
    private String USU_EMPRESA;
    @Column(length = 50)
    private String USU_BANCO;
    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date USU_CADASTRO;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date USU_ULTACESSO;
    @Column
    private int USU_CREDITOMES;
    @Column(columnDefinition = "NUMERIC(12,2)")
    private double USU_CREDITOVAL;
    @Column(columnDefinition = "NUMERIC(12,2)")
    private double USU_CREDITO;
    @Transient
    private Date VALIDADE;
    @Transient
    private String PLANO;
    @Transient
    private String STATUS;
    @Transient
    private String STYLE;
    @Transient
    private String USU_ULTACESSOSTR;
    @Column
    private boolean USU_COBRABOLETO;
    @Column
    private boolean USU_EXTINTOR = false;
    @Column
    private boolean USU_PRODUCAO = false;
    @Enumerated(EnumType.STRING)
    private Role rol;

    @OneToMany(mappedBy = "UserFactory")
    @Fetch(FetchMode.SUBSELECT)
    private List<FactoryUserItem> usuarios;

    @OneToMany(mappedBy = "usuId", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<FactoryContrato> contratoList;
    @Column
    private Boolean atualizado;

    public boolean isCli() {
        return Role.CLI.equals(rol);
    }

    public boolean isDis() {
        return Role.DIS.equals(rol);
    }

    public boolean isFab() {
        return Role.FAB.equals(rol);
    }

    public int getUSU_ID() {
        return USU_ID;
    }

    public void setUSU_ID(int USU_ID) {
        this.USU_ID = USU_ID;
    }

    public String getUSU_EMPRESA() {
        return USU_EMPRESA;
    }

    public void setUSU_EMPRESA(String USU_EMPRESA) {
        this.USU_EMPRESA = USU_EMPRESA;
    }

    public String getUSU_BANCO() {
        return USU_BANCO;
    }

    public void setUSU_BANCO(String USU_BANCO) {
        this.USU_BANCO = USU_BANCO;
    }

    public Date getUSU_CADASTRO() {
        return USU_CADASTRO;
    }

    public void setUSU_CADASTRO(Date USU_CADASTRO) {
        this.USU_CADASTRO = USU_CADASTRO;
    }

    public Date getUSU_ULTACESSO() {
        return USU_ULTACESSO;
    }

    public void setUSU_ULTACESSO(Date USU_ULTACESSO) {
        this.USU_ULTACESSO = USU_ULTACESSO;
    }

    public int getUSU_CREDITOMES() {
        return USU_CREDITOMES;
    }

    public void setUSU_CREDITOMES(int USU_CREDITOMES) {
        this.USU_CREDITOMES = USU_CREDITOMES;
    }

    public double getUSU_CREDITOVAL() {
        return USU_CREDITOVAL;
    }

    public void setUSU_CREDITOVAL(double USU_CREDITOVAL) {
        this.USU_CREDITOVAL = USU_CREDITOVAL;
    }

    public double getUSU_CREDITO() {
        return USU_CREDITO;
    }

    public void setUSU_CREDITO(double USU_CREDITO) {
        this.USU_CREDITO = USU_CREDITO;
    }

    public Date getVALIDADE() {
        return VALIDADE;
    }

    public void setVALIDADE(Date VALIDADE) {
        this.VALIDADE = VALIDADE;
    }

    public String getPLANO() {
        return PLANO;
    }

    public void setPLANO(String PLANO) {
        this.PLANO = PLANO;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getSTYLE() {
        return STYLE;
    }

    public void setSTYLE(String STYLE) {
        this.STYLE = STYLE;
    }

    public String getUSU_ULTACESSOSTR() {
        if (USU_ULTACESSO != null) {
            int diff = Util.difEntreDias(new Date(), this.USU_ULTACESSO);

            if (diff == 0) {
                setUSU_ULTACESSOSTR("Hoje");
            } else if (diff == 1) {
                setUSU_ULTACESSOSTR("Ontem");
            } else {
                setUSU_ULTACESSOSTR(diff + " Dias");
            }
        }
        return USU_ULTACESSOSTR;
    }

    public void setUSU_ULTACESSOSTR(String USU_ULTACESSOSTR) {
        this.USU_ULTACESSOSTR = USU_ULTACESSOSTR;
    }

    public boolean isUSU_COBRABOLETO() {
        return USU_COBRABOLETO;
    }

    public void setUSU_COBRABOLETO(boolean USU_COBRABOLETO) {
        this.USU_COBRABOLETO = USU_COBRABOLETO;
    }

    public boolean isUSU_EXTINTOR() {
        return USU_EXTINTOR;
    }

    public void setUSU_EXTINTOR(boolean USU_EXTINTOR) {
        this.USU_EXTINTOR = USU_EXTINTOR;
    }

    public boolean isUSU_PRODUCAO() {
        return USU_PRODUCAO;
    }

    public void setUSU_PRODUCAO(boolean USU_PRODUCAO) {
        this.USU_PRODUCAO = USU_PRODUCAO;
    }

    public List<FactoryUserItem> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<FactoryUserItem> usuarios) {
        this.usuarios = usuarios;
    }

    public List<FactoryContrato> getContratoList() {
        if (contratoList == null) {
            contratoList = new ArrayList<>();
        }
        return contratoList;
    }

    public void setContratoList(List<FactoryContrato> contratoList) {
        this.contratoList = contratoList;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

    public Boolean isAtualizado() {
        return atualizado;
    }

    public void setAtualizado(Boolean atualizado) {
        this.atualizado = atualizado;
    }

    @Override
    public int hashCode() {
        return USU_ID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FactoryUser) {
            FactoryUser dog = (FactoryUser) obj;
            return dog.getUSU_ID() == USU_ID;
        }

        return false;
    }

    @Override
    public String toString() {
        return USU_EMPRESA;
    }
}
