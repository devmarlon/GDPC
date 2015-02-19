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
@Table(name = "Modelo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modelo.findAll", query = "SELECT m FROM Modelo m"),
    @NamedQuery(name = "Modelo.findByModId", query = "SELECT m FROM Modelo m WHERE m.MOD_ID = :modId"),
    @NamedQuery(name = "Modelo.findByFab", query = "SELECT m FROM Modelo m WHERE m.fab = :fab"),
    @NamedQuery(name = "Modelo.findByModNome", query = "SELECT m FROM Modelo m WHERE m.MOD_NOME = :modNome")})
public class Modelo implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_MODELO_BY_FAB = "Modelo.findByFab";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MOD_ID")
    private int MOD_ID;
    @Size(max = 255)
    @Column(name = "MOD_NOME")
    private String MOD_NOME;
    @Column
    private int fab = 0;
    @Column
    private Boolean ativo = Boolean.TRUE;
    @OneToMany(mappedBy = "modelo")
    @Fetch(FetchMode.SUBSELECT)
    private List<Equipamento> equipamentos;

    public Modelo() {
    }

    public Modelo(int modId) {
        this.MOD_ID = modId;
    }

    public Modelo(FactoryModelo f) {
        this.MOD_ID = 0;
        this.MOD_NOME = f.getMOD_NOME();
        this.fab = f.getMOD_ID();
        this.ativo = f.getAtivo();
    }

    public Modelo(Modelo m) {
        this.MOD_NOME = m.MOD_NOME;
    }

    public int getMOD_ID() {
        return MOD_ID;
    }

    public void setMOD_ID(int MOD_ID) {
        this.MOD_ID = MOD_ID;
    }

    public String getMOD_NOME() {
        if (MOD_NOME == null) {
            MOD_NOME = "";
        }
        return MOD_NOME;
    }

    public void setMOD_NOME(String MOD_NOME) {
        this.MOD_NOME = MOD_NOME;
    }

    public int getFab() {
        return fab;
    }

    public void setFab(int fab) {
        this.fab = fab;
    }

    public Boolean getAtivo() {
        if (ativo == null) {
            ativo = Boolean.TRUE;
        }
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.MOD_NOME);
        hash = 11 * hash + this.fab;
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
        final Modelo other = (Modelo) obj;
        if (!Objects.equals(this.MOD_NOME, other.MOD_NOME)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gardnerdenver.model.Modelo[ modId=" + MOD_ID + " ]";
    }

}
