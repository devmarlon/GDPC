package com.gardnerdenver.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PadraoManutencaoPeca")
//@NamedQueries({
//    @NamedQuery(name = "PadraoManutencaoPeca.findListByEqs", query = "select p from PadraoManutencaoPeca p where p.eqs.ID_EQS = :eqsId")})

//public class PecaEqs implements Serializable, Comparator<PecaEqs> {
public class PadraoManutencaoPeca implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_PQS_BY_EQS = "PadraoManutencaoPeca.findListByEqs";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PMP_ID;

//    @Id
    @ManyToOne
    @JoinColumn(name = "PEC_ID")
    private FactoryPeca factoryPeca;

//    @Id
    @ManyToOne
    @JoinColumn(name = "PDS_ID")
    private PadraoManutencaoServico pdmServico;

    @Column
    private double quantidade;

    public PadraoManutencaoPeca() {
    }

    public PadraoManutencaoPeca(FactoryPeca fp) {
        this.PMP_ID = 0;
        this.factoryPeca = fp;
    }

    public int getPMP_ID() {
        return PMP_ID;
    }

    public void setPMP_ID(int PMP_ID) {
        this.PMP_ID = PMP_ID;
    }

    public FactoryPeca getFactoryPeca() {
        return factoryPeca;
    }

    public void setFactoryPeca(FactoryPeca factoryPeca) {
        this.factoryPeca = factoryPeca;
    }

    public PadraoManutencaoServico getPdmServico() {
        return pdmServico;
    }

    public void setPdmServico(PadraoManutencaoServico pdmServico) {
        this.pdmServico = pdmServico;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.PMP_ID;
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
        final PadraoManutencaoPeca other = (PadraoManutencaoPeca) obj;
        if (this.PMP_ID != other.PMP_ID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PecaEqs{" + "ID=" + PMP_ID + '}';
    }

//    @Override
//    public int compare(PecaEqs p1, PecaEqs p2) {
////        return getPeca().compare(p1.getPeca(), p2.getPeca());
//        return p1.getPeca().getCodigo().compareToIgnoreCase(p2.getPeca().getCodigo());
//    }
}
