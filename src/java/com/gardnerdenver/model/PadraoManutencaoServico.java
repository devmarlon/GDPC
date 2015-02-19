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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Marlon
 */
@Entity
@Table(name = "PadraoManutencaoServico")
public class PadraoManutencaoServico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int PDS_ID;

    @ManyToOne(targetEntity = PadraoManutencao.class)
    @JoinColumn(name = "PDM_ID")
    private PadraoManutencao pdrManutencao;

    @ManyToOne
    @JoinColumn(name = "SRV_ID")
    private FactoryServico factoryServico;

    @OneToMany(mappedBy = "pdmServico", cascade = CascadeType.ALL)
//    @Fetch(FetchMode.SUBSELECT)
    private List<PadraoManutencaoPeca> pdsPecas;

    @Column
    private int PMS_FREQDIAS;

    @Column
    private int PMS_FREQHORAS;

    @Transient
    private int idTemp;

    public PadraoManutencaoServico() {
    }

    public PadraoManutencaoServico(FactoryServico fs) {
        this.PDS_ID = 0;
//        this.pdrManutencao = pdrManutencao;
        this.factoryServico = fs;
        this.pdsPecas = new ArrayList<>();
        this.PMS_FREQDIAS = fs.getSRV_FREQDIAS();
        this.PMS_FREQHORAS = fs.getSRV_FREQHORAS();
    }

    public int getPDS_ID() {
        return PDS_ID;
    }

    public void setPDS_ID(int PDS_ID) {
        this.PDS_ID = PDS_ID;
    }

    public FactoryServico getFactoryServico() {
        if (factoryServico == null) {
            factoryServico = new FactoryServico();
        }
        return factoryServico;
    }

    public void setFactoryServico(FactoryServico factoryServico) {
        this.factoryServico = factoryServico;
    }

    public int getPMS_FREQDIAS() {
        return PMS_FREQDIAS;
    }

    public void setPMS_FREQDIAS(int PMS_FREQDIAS) {
        this.PMS_FREQDIAS = PMS_FREQDIAS;
    }

    public int getPMS_FREQHORAS() {
        return PMS_FREQHORAS;
    }

    public void setPMS_FREQHORAS(int PMS_FREQHORAS) {
        this.PMS_FREQHORAS = PMS_FREQHORAS;
    }

    public PadraoManutencao getPdrManutencao() {
        return pdrManutencao;
    }

    public void setPdrManutencao(PadraoManutencao pdrManutencao) {
        this.pdrManutencao = pdrManutencao;
    }

    public List<PadraoManutencaoPeca> getPdsPecas() {
        if (pdsPecas == null) {
            pdsPecas = new ArrayList<>();
        }
        return pdsPecas;
    }

    public void setPdsPecas(List<PadraoManutencaoPeca> pdsPecas) {
        this.pdsPecas = pdsPecas;
    }

    public int getIdTemp() {
        return idTemp;
    }

    public void setIdTemp(int idTemp) {
        this.idTemp = idTemp;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.PDS_ID;
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
        final PadraoManutencaoServico other = (PadraoManutencaoServico) obj;
        if (this.PDS_ID != other.PDS_ID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gardnerdenver.model.PadraoManutencao[ id=" + PDS_ID + " ]";
    }

}
