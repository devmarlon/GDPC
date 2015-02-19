/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gardnerdenver.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marlon
 */
@Entity
@Table(name = "FactoryContrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FactoryContrato.findAll", query = "SELECT c FROM FactoryContrato c"),
    @NamedQuery(name = "FactoryContrato.findByCtoId", query = "SELECT c FROM FactoryContrato c WHERE c.ctoId = :ctoId"),
    @NamedQuery(name = "FactoryContrato.findByCtoCodpromo", query = "SELECT c FROM FactoryContrato c WHERE c.ctoCodpromo = :ctoCodpromo"),
    @NamedQuery(name = "FactoryContrato.findByCtoComissao", query = "SELECT c FROM FactoryContrato c WHERE c.ctoComissao = :ctoComissao"),
    @NamedQuery(name = "FactoryContrato.findByCtoCpfcnpj", query = "SELECT c FROM FactoryContrato c WHERE c.ctoCpfcnpj = :ctoCpfcnpj"),
    @NamedQuery(name = "FactoryContrato.findByCtoDiavenc", query = "SELECT c FROM FactoryContrato c WHERE c.ctoDiavenc = :ctoDiavenc"),
    @NamedQuery(name = "FactoryContrato.findByCtoEmail", query = "SELECT c FROM FactoryContrato c WHERE c.ctoEmail = :ctoEmail"),
    @NamedQuery(name = "FactoryContrato.findByCtoEmissao", query = "SELECT c FROM FactoryContrato c WHERE c.ctoEmissao = :ctoEmissao"),
    @NamedQuery(name = "FactoryContrato.findByCtoFormapag", query = "SELECT c FROM FactoryContrato c WHERE c.ctoFormapag = :ctoFormapag"),
    @NamedQuery(name = "FactoryContrato.findByCtoNomerazao", query = "SELECT c FROM FactoryContrato c WHERE c.ctoNomerazao = :ctoNomerazao"),
    @NamedQuery(name = "FactoryContrato.findByCtoStatus", query = "SELECT c FROM FactoryContrato c WHERE c.ctoStatus = :ctoStatus"),
    @NamedQuery(name = "FactoryContrato.findByCtoTelefone", query = "SELECT c FROM FactoryContrato c WHERE c.ctoTelefone = :ctoTelefone"),
    @NamedQuery(name = "FactoryContrato.findByCtoTipopessoa", query = "SELECT c FROM FactoryContrato c WHERE c.ctoTipopessoa = :ctoTipopessoa"),
    @NamedQuery(name = "FactoryContrato.findByCtoValidade", query = "SELECT c FROM FactoryContrato c WHERE c.ctoValidade = :ctoValidade"),
    @NamedQuery(name = "FactoryContrato.findByPlnId", query = "SELECT c FROM FactoryContrato c WHERE c.plnId = :plnId")})
public class FactoryContrato implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_LIST_STATUS_ATIVO = "FactoryContrato.findByCtoStatus";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CTO_ID")
    private Integer ctoId;
    @Size(max = 50)
    @Column(name = "CTO_CODPROMO")
    private String ctoCodpromo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CTO_COMISSAO")
    private double ctoComissao;
    @Size(max = 20)
    @Column(name = "CTO_CPFCNPJ")
    private String ctoCpfcnpj;
    @Column(name = "CTO_DIAVENC")
    private Integer ctoDiavenc;
    @Size(max = 100)
    @Column(name = "CTO_EMAIL")
    private String ctoEmail;
    @Column(name = "CTO_EMISSAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ctoEmissao;
    @Column(name = "CTO_FORMAPAG")
    private Integer ctoFormapag;
    @Size(max = 100)
    @Column(name = "CTO_NOMERAZAO")
    private String ctoNomerazao;
    @Column(name = "CTO_STATUS")
    private boolean ctoStatus;
    @Size(max = 20)
    @Column(name = "CTO_TELEFONE")
    private String ctoTelefone;
    @Size(max = 1)
    @Column(name = "CTO_TIPOPESSOA")
    private String ctoTipopessoa;
    @Column(name = "CTO_VALIDADE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ctoValidade;
    @Column(name = "PLN_ID")
    private Integer plnId;
    @JoinColumn(name = "USU_ID", referencedColumnName = "USU_ID")
    @ManyToOne
    private FactoryUser usuId;

    public FactoryContrato() {
    }

    public FactoryContrato(Integer ctoId) {
        this.ctoId = ctoId;
    }

    public Integer getCtoId() {
        return ctoId;
    }

    public void setCtoId(Integer ctoId) {
        this.ctoId = ctoId;
    }

    public String getCtoCodpromo() {
        return ctoCodpromo;
    }

    public void setCtoCodpromo(String ctoCodpromo) {
        this.ctoCodpromo = ctoCodpromo;
    }

    public double getCtoComissao() {
        return ctoComissao;
    }

    public void setCtoComissao(double ctoComissao) {
        this.ctoComissao = ctoComissao;
    }

    public String getCtoCpfcnpj() {
        return ctoCpfcnpj;
    }

    public void setCtoCpfcnpj(String ctoCpfcnpj) {
        this.ctoCpfcnpj = ctoCpfcnpj;
    }

    public Integer getCtoDiavenc() {
        return ctoDiavenc;
    }

    public void setCtoDiavenc(Integer ctoDiavenc) {
        this.ctoDiavenc = ctoDiavenc;
    }

    public String getCtoEmail() {
        return ctoEmail;
    }

    public void setCtoEmail(String ctoEmail) {
        this.ctoEmail = ctoEmail;
    }

    public Date getCtoEmissao() {
        return ctoEmissao;
    }

    public void setCtoEmissao(Date ctoEmissao) {
        this.ctoEmissao = ctoEmissao;
    }

    public Integer getCtoFormapag() {
        return ctoFormapag;
    }

    public void setCtoFormapag(Integer ctoFormapag) {
        this.ctoFormapag = ctoFormapag;
    }

    public String getCtoNomerazao() {
        return ctoNomerazao;
    }

    public void setCtoNomerazao(String ctoNomerazao) {
        this.ctoNomerazao = ctoNomerazao;
    }

    public boolean getCtoStatus() {
        return ctoStatus;
    }

    public void setCtoStatus(boolean ctoStatus) {
        this.ctoStatus = ctoStatus;
    }

    public String getCtoTelefone() {
        return ctoTelefone;
    }

    public void setCtoTelefone(String ctoTelefone) {
        this.ctoTelefone = ctoTelefone;
    }

    public String getCtoTipopessoa() {
        return ctoTipopessoa;
    }

    public void setCtoTipopessoa(String ctoTipopessoa) {
        this.ctoTipopessoa = ctoTipopessoa;
    }

    public Date getCtoValidade() {
        return ctoValidade;
    }

    public void setCtoValidade(Date ctoValidade) {
        this.ctoValidade = ctoValidade;
    }

    public Integer getPlnId() {
        return plnId;
    }

    public void setPlnId(Integer plnId) {
        this.plnId = plnId;
    }

    public FactoryUser getUsuId() {
        return usuId;
    }

    public void setUsuId(FactoryUser usuId) {
        this.usuId = usuId;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctoId != null ? ctoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FactoryContrato)) {
            return false;
        }
        FactoryContrato other = (FactoryContrato) object;
        if ((this.ctoId == null && other.ctoId != null) || (this.ctoId != null && !this.ctoId.equals(other.ctoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gardnerdenver.model.FactoryContrato[ ctoId=" + ctoId + " ]";
    }

}
