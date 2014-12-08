/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gardnerdenver.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Marlon
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Municipio.findMunicipio", query = "select m from Municipio m WHERE m.MUN_ID = :munId"),
    @NamedQuery(name = "Municipio.findListMunicipioByUF", query = "select m from Municipio m WHERE m.MUN_UF = :ufId")
})
public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_MUNICIPIO = "Municipio.findMunicipio";
    public static final String FIND_LIST_MUNICIPIO_BY_UF = "Municipio.findListMunicipioByUF";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int MUN_ID;
    @Column
    private String MUN_NOME;
    @Column
    private int MUN_UF;

    public int getMUN_ID() {
        return MUN_ID;
    }

    public void setMUN_ID(int MUN_ID) {
        this.MUN_ID = MUN_ID;
    }

    public String getMUN_NOME() {
        return MUN_NOME;
    }

    public void setMUN_NOME(String MUN_NOME) {
        this.MUN_NOME = MUN_NOME;
    }

    public int getMUN_UF() {
        return MUN_UF;
    }

    public void setMUN_UF(int MUN_UF) {
        this.MUN_UF = MUN_UF;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) MUN_ID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipio)) {
            return false;
        }
        Municipio other = (Municipio) object;
        if (this.MUN_ID != other.MUN_ID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Municipio[ MUN_ID=" + MUN_ID + " ]";
    }

}
