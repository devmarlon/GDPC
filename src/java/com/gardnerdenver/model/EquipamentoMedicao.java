package com.gardnerdenver.model;

import com.gardnerdenver.util.Util;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "EquipamentoMedicao")
@NamedQueries({
    @NamedQuery(name = "EquipamentoMedicao.findListByEqp", query = "select m from EquipamentoMedicao m where m.equipamento.EQP_ID = :eqpId ORDER BY m.EQM_DATAATUALIZACAO desc, m.EQM_HORASTOTAIS asc"),
//    @NamedQuery(name = "EquipamentoMedicao.findLastUpdate", query = "SELECT m FROM EquipamentoMedicao m where m.equipamento.EQP_ID = :eqpId and m.EQM_DATAATUALIZACAO >= (SELECT MAX(n.EQM_DATAATUALIZACAO) FROM EquipamentoMedicao n where n.equipamento.EQP_ID = :eqpId) and m.EQM_HORASTOTAIS >= (SELECT MAX(n.EQM_HORASTOTAIS) FROM EquipamentoMedicao n where n.equipamento.EQP_ID = :eqpId) GROUP BY m.EQM_DATAATUALIZACAO order by m.EQM_DATAATUALIZACAO DESC")
    @NamedQuery(name = "EquipamentoMedicao.findLastUpdate", query = "SELECT m FROM EquipamentoMedicao m where m.equipamento.EQP_ID = :eqpId and m.EQM_ID >= (SELECT MAX(n.EQM_ID) FROM EquipamentoMedicao n where n.equipamento.EQP_ID = :eqpId)")
})
public class EquipamentoMedicao implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_LAST_UPDATE = "EquipamentoMedicao.findLastUpdate";
    public static final String FIND_LIST_BY_EQP = "EquipamentoMedicao.findListByEqp";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int EQM_ID;

    @Temporal(TemporalType.DATE)
    private Date EQM_DATAATUALIZACAO;
    @Column
    private int EQM_HORASTOTAIS = 0;
    private int EQM_HORASEMCARGA;
    @Transient
    private int EQM_HORASESTIMADAS;
    @ManyToOne
    @JoinColumn(name = "EQP_ID")
    private Equipamento equipamento;

    public EquipamentoMedicao() {
    }

    public EquipamentoMedicao(EquipamentoMedicao eqm) {
        this.EQM_ID = 0;
        this.EQM_DATAATUALIZACAO = eqm.getEQM_DATAATUALIZACAO();
        this.EQM_HORASEMCARGA = eqm.getEQM_HORASEMCARGA();
        this.EQM_HORASTOTAIS = eqm.getEQM_HORASTOTAIS();
        this.equipamento = eqm.getEquipamento();
    }

    public int getEQM_ID() {
        return EQM_ID;
    }

    public void setEQM_ID(int EQM_ID) {
        this.EQM_ID = EQM_ID;
    }

    public Date getEQM_DATAATUALIZACAO() {
        if (EQM_DATAATUALIZACAO == null) {
            EQM_DATAATUALIZACAO = new Date();
        }
        return EQM_DATAATUALIZACAO;
    }

    public void setEQM_DATAATUALIZACAO(Date EQM_DATAATUALIZACAO) {
        this.EQM_DATAATUALIZACAO = EQM_DATAATUALIZACAO;
    }

    public int getEQM_HORASTOTAIS() {
        return EQM_HORASTOTAIS;
    }

    public void setEQM_HORASTOTAIS(int EQM_HORASTOTAIS) {
        this.EQM_HORASTOTAIS = EQM_HORASTOTAIS;
    }

    public int getEQM_HORASEMCARGA() {
        return EQM_HORASEMCARGA;
    }

    public void setEQM_HORASEMCARGA(int EQM_HORASEMCARGA) {
        this.EQM_HORASEMCARGA = EQM_HORASEMCARGA;
    }

    public int getEQM_HORASESTIMADAS() {
        if (equipamento != null) {

            int diasCorridos = Util.difEntreDias(new Date(), this.EQM_DATAATUALIZACAO);
            EQM_HORASESTIMADAS = (diasCorridos * equipamento.getEQP_REGIMEMEDIA()) + EQM_HORASTOTAIS;

            int semanas = diasCorridos / 7;

//            EQM_HORASESTIMADAS = (equipamento.getEQP_REGIMEDIASSEMANA()
//                    * equipamento.getEQP_REGIMEHORASDIA() * semanas) + EQM_HORASTOTAIS;
        }
        return EQM_HORASESTIMADAS;
    }

    public void setEQM_HORASESTIMADAS(int EQM_HORASESTIMADAS) {
        this.EQM_HORASESTIMADAS = EQM_HORASESTIMADAS;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.EQM_ID;
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
        final EquipamentoMedicao other = (EquipamentoMedicao) obj;
        if (this.EQM_ID != other.EQM_ID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EquipamentoMedicao{" + "EQM_ID=" + EQM_ID + ", EQM_DATAATUALIZACAO=" + EQM_DATAATUALIZACAO + ", EQM_HORASTOTAIS=" + EQM_HORASTOTAIS + ", EQM_HORASEMCARGA=" + EQM_HORASEMCARGA + ", EQM_HORASESTIMADAS=" + EQM_HORASESTIMADAS + ", equipamento=" + equipamento + '}';
    }
}
