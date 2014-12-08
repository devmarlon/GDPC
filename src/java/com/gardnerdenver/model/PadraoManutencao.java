/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gardnerdenver.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Marlon
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "PadraoManutencao.findPadraoByModelo", query = "select p from PadraoManutencao p WHERE p.factoryModelo.MOD_ID = :modeloId ")
})
public class PadraoManutencao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public static final String FIND_PADRAO_BY_MODELO = "PadraoManutencao.findPadraoByModelo";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int PDM_ID;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "MOD_ID")
    private FactoryModelo factoryModelo;

    @OneToMany(mappedBy = "pdrManutencao", cascade = CascadeType.ALL)
//    @OneToMany(mappedBy = "pdrManutencao", fetch = FetchType.EAGER)
//    @Fetch(FetchMode.SUBSELECT)
    private List<PadraoManutencaoServico> pdmServico;

    public int getPDM_ID() {
        return PDM_ID;
    }

    public void setPDM_ID(int PDM_ID) {
        this.PDM_ID = PDM_ID;
    }

    public FactoryModelo getFactoryModelo() {
        return factoryModelo;
    }

    public void setFactoryModelo(FactoryModelo factoryModelo) {
        this.factoryModelo = factoryModelo;
    }

    public List<PadraoManutencaoServico> getPdmServico() {
        if (pdmServico == null) {
            pdmServico = new ArrayList<>();
        }
        return pdmServico;
    }

    public void setPdmServico(List<PadraoManutencaoServico> pdmServico) {
        this.pdmServico = pdmServico;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.PDM_ID;
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
        final PadraoManutencao other = (PadraoManutencao) obj;
        if (this.PDM_ID != other.PDM_ID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gardnerdenver.model.PadraoManutencao[ id=" + PDM_ID + " ]";
    }

}
