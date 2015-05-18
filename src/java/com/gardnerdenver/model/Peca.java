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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Marlon
 */
@Entity
@Table(name = "Peca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Peca.findAll", query = "SELECT f FROM Peca f where f.ativo IS NULL or f.ativo = true"),
    @NamedQuery(name = "Peca.findById", query = "SELECT f FROM Peca f WHERE f.PEC_ID = :pedId and  f.ativo IS NULL or f.ativo = true"),
    @NamedQuery(name = "Peca.findByCodigo", query = "SELECT f FROM Peca f WHERE f.codigo = :codigo"),
    @NamedQuery(name = "Peca.findByFab", query = "SELECT f FROM Peca f WHERE f.fab = :fab and f.ativo IS NULL or f.ativo = true"),
    @NamedQuery(name = "Peca.findByDescricao", query = "SELECT f FROM Peca f WHERE f.descricao = :descricao and  f.ativo IS NULL or f.ativo = true")})
public class Peca implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "Peca.findAll";
    public static final String FIND_PECA_BY_CODIGO = "Peca.findByCodigo";
    public static final String FIND_PECA_BY_FAB = "Peca.findByFab";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer PEC_ID;
    @Size(max = 255)
    @Column
    private String codigo;
    @Size(max = 255)
    @Column
    private String descricao;
    @OneToMany(mappedBy = "peca", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<PecaEqs> pecasEquipamentos;
    @Column
    private int fab = 0;
    @Column
    private Boolean ativo;

    public int getFab() {
        return fab;
    }

    public void setFab(int fab) {
        this.fab = fab;
    }

    public Peca(Peca p) {
        this.PEC_ID = p.getPEC_ID();
        this.codigo = p.getCodigo();
        this.descricao = p.getDescricao();
        this.pecasEquipamentos = p.getPecasEquipamentos();
        this.fab = p.getFab();
        this.ativo = p.getAtivo();
    }

    public Peca() {
        this.PEC_ID = 0;
    }

    public Peca(FactoryPeca p) {
        this.PEC_ID = 0;
        this.codigo = p.getCodigo();
        this.descricao = p.getDescricao();
        this.fab = p.getPEC_ID();
        this.ativo = p.getAtivo();
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

    public List<PecaEqs> getPecasEquipamentos() {
        return pecasEquipamentos;
    }

    public void setPecasEquipamentos(List<PecaEqs> pecasEquipamentos) {
        this.pecasEquipamentos = pecasEquipamentos;
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
        final Peca other = (Peca) obj;
        if (!Objects.equals(this.PEC_ID, other.PEC_ID)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.pecasEquipamentos, other.pecasEquipamentos)) {
            return false;
        }
        if (!Objects.equals(this.fab, other.fab)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Peca{" + "PEC_ID=" + PEC_ID + ", codigo=" + codigo + ", descricao=" + descricao + ", pecasEquipamentos=" + pecasEquipamentos + ", fab=" + fab + '}';
    }

}
