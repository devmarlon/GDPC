/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gardnerdenver.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FactoryPeca.findAll", query = "SELECT f FROM FactoryPeca f"),
    @NamedQuery(name = "FactoryPeca.findById", query = "SELECT f FROM FactoryPeca f WHERE f.PEC_ID = :pedId"),
    @NamedQuery(name = "FactoryPeca.findByCodigo", query = "SELECT f FROM FactoryPeca f WHERE f.codigo = :codigo"),
    @NamedQuery(name = "FactoryPeca.findByDescricao", query = "SELECT f FROM FactoryPeca f WHERE f.descricao = :descricao")})
public class FactoryPeca implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_PECA_BY_CODIGO = "Peca.findByCodigo";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer PEC_ID;
    @Size(max = 255)
    @Column
    private String codigo;
    @Size(max = 255)
    @Column
    private String descricao;

    @OneToMany(mappedBy = "factoryPeca", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<PadraoManutencaoPeca> pdsPecas;

    public FactoryPeca(FactoryPeca p) {
        this.PEC_ID = p.getPEC_ID();
        this.codigo = p.getCodigo();
        this.descricao = p.getDescricao();
    }

    public FactoryPeca() {
        this.PEC_ID = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getPEC_ID() {
        return PEC_ID;
    }

    public void setPEC_ID(Integer PEC_ID) {
        this.PEC_ID = PEC_ID;
    }

    public List<PadraoManutencaoPeca> getPdsPecas() {
        return pdsPecas;
    }

    public void setPdsPecas(List<PadraoManutencaoPeca> pdsPecas) {
        this.pdsPecas = pdsPecas;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.PEC_ID);
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
        final FactoryPeca other = (FactoryPeca) obj;
        if (!Objects.equals(this.PEC_ID, other.PEC_ID)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FactoryPeca{" + "PEC_ID=" + PEC_ID + ", codigo=" + codigo + ", descricao=" + descricao + '}';
    }
}
