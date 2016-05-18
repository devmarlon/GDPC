/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gardnerdenver.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marlon
 */
@Entity
@Table(name = "FactoryModelo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FactoryModelo.findAll", query = "SELECT f FROM FactoryModelo f"),
    @NamedQuery(name = "FactoryModelo.findAllAtivo", query = "SELECT f FROM FactoryModelo f WHERE (f.ativo IS NULL or f.ativo = true)"),
    @NamedQuery(name = "FactoryModelo.findById", query = "SELECT f FROM FactoryModelo f WHERE f.MOD_ID = :modId"),
    @NamedQuery(name = "FactoryModelo.findByNome", query = "SELECT f FROM FactoryModelo f WHERE f.MOD_NOME = :modNome")})
public class FactoryModelo implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_CATEGORIA_BY_CODIGO = "Peca.findById";
    public static final String FIND_LISTA_ATIVOS = "Peca.findAllAtivo";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MOD_ID;
    @Size(max = 255)
    @Column
    private String MOD_NOME;
    @Column
    private Boolean ativo = Boolean.TRUE;

    public FactoryModelo() {
    }

    public FactoryModelo(FactoryModelo fm) {
        this.MOD_NOME = fm.getMOD_NOME();
    }

    public int getMOD_ID() {
        return MOD_ID;
    }

    public void setMOD_ID(int MOD_ID) {
        this.MOD_ID = MOD_ID;
    }

    public String getMOD_NOME() {
        return MOD_NOME;
    }

    public void setMOD_NOME(String MOD_NOME) {
        this.MOD_NOME = MOD_NOME;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.MOD_ID;
        hash = 43 * hash + Objects.hashCode(this.MOD_NOME);
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
        final FactoryModelo other = (FactoryModelo) obj;
        if (this.MOD_ID != other.MOD_ID) {
            return false;
        }
        if (!Objects.equals(this.MOD_NOME, other.MOD_NOME)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FactoryModelo{" + "MOD_ID=" + MOD_ID + ", MOD_NOME=" + MOD_NOME + '}';
    }

}
