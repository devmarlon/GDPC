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
@Table(name = "FactoryCategoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FactoryCategoria.findAll", query = "SELECT f FROM FactoryCategoria f"),
    @NamedQuery(name = "FactoryCategoria.findById", query = "SELECT f FROM FactoryCategoria f WHERE f.CAT_ID = :catId"),
    @NamedQuery(name = "FactoryCategoria.findByCodigo", query = "SELECT f FROM FactoryCategoria f WHERE f.CAT_NOME = :catNome")})
public class FactoryCategoria implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_CATEGORIA_BY_CODIGO = "Peca.findByCodigo";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CAT_ID;
    @Size(max = 255)
    @Column
    private String CAT_NOME;

    public FactoryCategoria() {
        CAT_ID = 0;
    }
    
    

    public int getCAT_ID() {
        return CAT_ID;
    }

    public void setCAT_ID(int CAT_ID) {
        this.CAT_ID = CAT_ID;
    }

    public String getCAT_NOME() {
        return CAT_NOME;
    }

    public void setCAT_NOME(String CAT_NOME) {
        this.CAT_NOME = CAT_NOME;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.CAT_ID);
        hash = 79 * hash + Objects.hashCode(this.CAT_NOME);
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
        final FactoryCategoria other = (FactoryCategoria) obj;
        if (!Objects.equals(this.CAT_ID, other.CAT_ID)) {
            return false;
        }
        if (!Objects.equals(this.CAT_NOME, other.CAT_NOME)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FactoryCategoria{" + "CAT_ID=" + CAT_ID + ", CAT_NOME=" + CAT_NOME + '}';
    }

}
