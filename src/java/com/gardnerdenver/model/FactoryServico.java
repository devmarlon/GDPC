/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gardnerdenver.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Marlon
 */
@Entity
//@Table(name = "servico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FactoryServico.findAll", query = "SELECT f FROM FactoryServico f ORDER BY f.ativo, f.SRV_DESCRICAO"),
    @NamedQuery(name = "FactoryServico.findBySrvId", query = "SELECT f FROM FactoryServico f WHERE f.SRV_ID = :srvId"),
    @NamedQuery(name = "FactoryServico.findBySrvDescricao", query = "SELECT f FROM FactoryServico f WHERE f.SRV_DESCRICAO = :srvDescricao"),
    @NamedQuery(name = "FactoryServico.findBySrvFreqdias", query = "SELECT f FROM FactoryServico f WHERE f.SRV_FREQDIAS = :srvFreqdias"),
    @NamedQuery(name = "FactoryServico.findBySrvFreqhoras", query = "SELECT f FROM FactoryServico f WHERE f.SRV_FREQHORAS = :srvFreqhoras")})
public class FactoryServico implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_LISTA = "FactoryServico.findAll";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private int SRV_ID;
    @Size(max = 255)
    @Column
    private String SRV_DESCRICAO;
    @Column
    private int SRV_FREQDIAS;
    @Column
    private int SRV_FREQHORAS;
    @Column
    private Boolean ativo = true;

    @OneToMany(mappedBy = "factoryServico")
//    @OneToMany(mappedBy = "factoryServico", fetch = FetchType.EAGER)
//    @Fetch(FetchMode.SUBSELECT)
    private List<PadraoManutencaoServico> pmServicos;

    public FactoryServico() {
    }

    public FactoryServico(FactoryServico s) {
        this.SRV_DESCRICAO = s.getSRV_DESCRICAO();
        this.SRV_FREQDIAS = s.getSRV_FREQDIAS();
        this.SRV_FREQHORAS = s.getSRV_FREQHORAS();
    }

    public int getSRV_ID() {
        return SRV_ID;
    }

    public void setSRV_ID(int SRV_ID) {
        this.SRV_ID = SRV_ID;
    }

    public String getSRV_DESCRICAO() {
        if (SRV_DESCRICAO == null) {
            SRV_DESCRICAO = "";
        }
        return SRV_DESCRICAO;
    }

    public void setSRV_DESCRICAO(String SRV_DESCRICAO) {
        this.SRV_DESCRICAO = SRV_DESCRICAO;
    }

    public int getSRV_FREQDIAS() {
        return SRV_FREQDIAS;
    }

    public void setSRV_FREQDIAS(int SRV_FREQDIAS) {
        this.SRV_FREQDIAS = SRV_FREQDIAS;
    }

    public int getSRV_FREQHORAS() {
        return SRV_FREQHORAS;
    }

    public void setSRV_FREQHORAS(int SRV_FREQHORAS) {
        this.SRV_FREQHORAS = SRV_FREQHORAS;
    }

    public List<PadraoManutencaoServico> getPmServicos() {
        return pmServicos;
    }

    public void setPmServicos(List<PadraoManutencaoServico> pmServicos) {
        this.pmServicos = pmServicos;
    }

    public Boolean getAtivo() {
        if (ativo == null) {
            ativo = true;
        }
        
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.SRV_DESCRICAO);
        hash = 89 * hash + Objects.hashCode(this.SRV_FREQDIAS);
        hash = 89 * hash + Objects.hashCode(this.SRV_FREQHORAS);
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
        final FactoryServico other = (FactoryServico) obj;
        if (!Objects.equals(this.SRV_DESCRICAO, other.SRV_DESCRICAO)) {
            return false;
        }
        if (!Objects.equals(this.SRV_FREQDIAS, other.SRV_FREQDIAS)) {
            return false;
        }
        if (!Objects.equals(this.SRV_FREQHORAS, other.SRV_FREQHORAS)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FactoryServico{" + "SRV_ID=" + SRV_ID + ", SRV_DESCRICAO=" + SRV_DESCRICAO + ", SRV_FREQDIAS=" + SRV_FREQDIAS + ", SRV_FREQHORAS=" + SRV_FREQHORAS + '}';
    }

}
