package com.gardnerdenver.model;

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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
@NamedQueries({
    @NamedQuery(name = "MovimentoItem.findByMov", query = "Select m from MovimentoItem m WHERE m.movimento.MOV_ID = :movId")
})
public class MovimentoItem implements Serializable {

    public static final String FIND_BY_MOV = "MovimentoItem.findByMov";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int MIT_ID;

    @ManyToOne
    @JoinColumn(name = "MOV_ID")
    private Movimento movimento;
//    @ManyToOne(targetEntity = EquipamentoServico.class)
//    private EquipamentoServico equipamentoServico;
    @OneToOne(targetEntity = Servico.class)
    private Servico servico;

    @OneToOne(targetEntity = Peca.class)
    private Peca peca;

    @Column
    private Boolean CHECK_OS = false;

    @Column(length = 2000)
    private String MIT_RESUMO;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date MIT_DATA;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date MIT_TIMEINI;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date MIT_TIMEFIN;
    

    public MovimentoItem() {
    }

    public MovimentoItem(Movimento movimento) {
        this.movimento = movimento;
    }

//    public MovimentoItem(EquipamentoServico equipamentoServico) {
//        equipamentoServico.getMovimentoItem().add(this);
//        this.equipamentoServico = equipamentoServico;
//    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getMIT_DATA() {
        if (MIT_DATA == null) {
            MIT_DATA = new Date();
        }
        return MIT_DATA;
    }

    public void setMIT_DATA(Date MIT_DATA) {
        this.MIT_DATA = MIT_DATA;
    }

    public Date getMIT_TIMEINI() {
        if (MIT_TIMEINI == null) {
            MIT_TIMEINI = new Date();
        }
        return MIT_TIMEINI;
    }

    public void setMIT_TIMEINI(Date MIT_TIMEINI) {
        this.MIT_TIMEINI = MIT_TIMEINI;
    }

    public Date getMIT_TIMEFIN() {
        if (MIT_TIMEFIN == null) {
            MIT_TIMEFIN = new Date();
        }
        return MIT_TIMEFIN;
    }

    public void setMIT_TIMEFIN(Date MIT_TIMEFIN) {
        this.MIT_TIMEFIN = MIT_TIMEFIN;
    }

    public int getMIT_ID() {
        return MIT_ID;
    }

    public void setMIT_ID(int MIT_ID) {
        this.MIT_ID = MIT_ID;
    }

    public Movimento getMovimento() {
        return movimento;
    }

    public void setMovimento(Movimento movimento) {
        this.movimento = movimento;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public Boolean getCHECK_OS() {
        if (CHECK_OS == null) {
            CHECK_OS = Boolean.FALSE;
        }
        return CHECK_OS;
    }

    public void setCHECK_OS(Boolean CHECK_OS) {
        this.CHECK_OS = CHECK_OS;
    }

    public String getMIT_RESUMO() {
        return MIT_RESUMO;
    }

    public void setMIT_RESUMO(String MIT_RESUMO) {
        this.MIT_RESUMO = MIT_RESUMO;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.id;
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
        final MovimentoItem other = (MovimentoItem) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MovimentoItem{" + "id=" + id + ", MIT_ID=" + MIT_ID + ", movimento=" + movimento + ", MIT_RESUMO=" + MIT_RESUMO + ", MIT_DATA=" + MIT_DATA + ", MIT_TIMEINI=" + MIT_TIMEINI + ", MIT_TIMEFIN=" + MIT_TIMEFIN + '}';
    }

}
