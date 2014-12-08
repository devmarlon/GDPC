package com.gardnerdenver.model;

import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "PecaEqs.findListByEqs", query = "select p from PecaEqs p where p.eqs.ID_EQS = :eqsId")})

//public class PecaEqs implements Serializable, Comparator<PecaEqs> {
public class PecaEqs implements Serializable{

    private static final long serialVersionUID = 1L;

    public static final String FIND_PQS_BY_EQS = "PecaEqs.findListByEqs";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PES_ID;

//    @Id
    @ManyToOne
    @JoinColumn(name = "PEC_ID")
    private Peca peca;

//    @Id
    @ManyToOne
    @JoinColumn(name = "ID_EQS")
    private EquipamentoServico eqs;

    @Column
    private double quantidade;

    public PecaEqs() {
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public EquipamentoServico getEqs() {
        return eqs;
    }

    public void setEqs(EquipamentoServico eqs) {
        this.eqs = eqs;
    }

    public int getPES_ID() {
        return PES_ID;
    }

    public void setPES_ID(int PES_ID) {
        this.PES_ID = PES_ID;
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
        hash = 89 * hash + this.PES_ID;
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
        final PecaEqs other = (PecaEqs) obj;
        if (this.PES_ID != other.PES_ID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PecaEqs{" + "ID=" + PES_ID + '}';
    }

//    @Override
//    public int compare(PecaEqs p1, PecaEqs p2) {
////        return getPeca().compare(p1.getPeca(), p2.getPeca());
//        return p1.getPeca().getCodigo().compareToIgnoreCase(p2.getPeca().getCodigo());
//    }
}
