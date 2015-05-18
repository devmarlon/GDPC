package com.gardnerdenver.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.joda.time.DateTime;

@Entity
@Table(name = "EquipamentoServico")
@NamedQueries({
    //    @NamedQuery(name = "EquipamentoServico.findListCarta", query = "SELECT p FROM EquipamentoServico p WHERE (p.MANUTATUAL , p.MANUTATUALRHORAS) = (select max(x.MANUTATUAL), max(x.MANUTATUALRHORAS) FROM EquipamentoServico x WHERE x.servico.SRV_ID = p.servico.SRV_ID AND x.equipamento.EQP_ID = p.equipamento.EQP_ID) ORDER BY p.servico.SRV_ID"),
    @NamedQuery(name = "EquipamentoServico.findListCarta", query = "SELECT p FROM EquipamentoServico p WHERE (p.MANUTATUAL) = (select max(x.MANUTATUAL) FROM EquipamentoServico x WHERE x.servico.SRV_ID = p.servico.SRV_ID AND x.equipamento.EQP_ID = p.equipamento.EQP_ID) AND p.MANUTATUALRHORAS =  (select max(x.MANUTATUALRHORAS) FROM EquipamentoServico x WHERE x.servico.SRV_ID = p.servico.SRV_ID AND x.equipamento.EQP_ID = p.equipamento.EQP_ID) ORDER BY p.servico.SRV_ID"),
//    @NamedQuery(name = "EquipamentoServico.findListByEqp", query = "SELECT p FROM EquipamentoServico p WHERE p.OS = FALSE AND p.REALIZADO = TRUE AND p.equipamento.EQP_ID = :eqpId  AND (p.MANUTATUAL , p.MANUTATUALRHORAS) = (select max(x.MANUTATUAL), max(x.MANUTATUALRHORAS) FROM EquipamentoServico x WHERE x.servico.SRV_ID = p.servico.SRV_ID AND x.equipamento.EQP_ID = p.equipamento.EQP_ID AND x.REALIZADO = p.REALIZADO) ORDER BY p.servico.SRV_ID"),
    @NamedQuery(name = "EquipamentoServico.findListByEqp", query = "SELECT p FROM EquipamentoServico p WHERE p.OS = FALSE AND p.REALIZADO = TRUE AND p.equipamento.EQP_ID = :eqpId  AND (p.MANUTATUAL) = (select max(x.MANUTATUAL) FROM EquipamentoServico x WHERE x.servico.SRV_ID = p.servico.SRV_ID AND x.equipamento.EQP_ID = p.equipamento.EQP_ID AND x.REALIZADO = p.REALIZADO) AND (p.MANUTATUALRHORAS) = (select max(x.MANUTATUALRHORAS) FROM EquipamentoServico x WHERE x.servico.SRV_ID = p.servico.SRV_ID AND x.equipamento.EQP_ID = p.equipamento.EQP_ID AND x.REALIZADO = p.REALIZADO) ORDER BY p.servico.SRV_ID"),
//    @NamedQuery(name = "EquipamentoServico.findListByEqp", query = "SELECT p FROM EquipamentoServico p WHERE p.OS = FALSE AND p.equipamento.EQP_ID = :eqpId AND p.REALIZADO = TRUE AND (p.MANUTATUAL , p.MANUTATUALRHORAS) = (select max(x.MANUTATUAL), max(x.MANUTATUALRHORAS) FROM EquipamentoServico x WHERE x.servico.SRV_ID = p.servico.SRV_ID AND x.equipamento.EQP_ID = p.equipamento.EQP_ID AND x.REALIZADO = p.REALIZADO) ORDER BY p.servico.SRV_ID"),
    @NamedQuery(name = "EquipamentoServico.findListByEqpCarta", query = "select p from EquipamentoServico p WHERE p.OS = FALSE AND p.equipamento.EQP_ID = :eqpId AND (p.MANUTATUAL = (select max(x.MANUTATUAL) FROM EquipamentoServico x WHERE x.servico.SRV_ID = p.servico.SRV_ID AND x.equipamento.EQP_ID = p.equipamento.EQP_ID) AND p.MANUTATUALRHORAS = (select max(y.MANUTATUALRHORAS) FROM EquipamentoServico y WHERE y.servico.SRV_ID = p.servico.SRV_ID AND y.equipamento.EQP_ID = p.equipamento.EQP_ID)) ORDER BY p.servico.SRV_ID"),
    @NamedQuery(name = "EquipamentoServico.findListHistoricoByEqp", query = "select p from EquipamentoServico p where p.equipamento.EQP_ID = :eqpId AND p.REALIZADO <> 0 AND p.OS = 0 ORDER BY p.MANUTATUAL, p.MANUTATUALRHORAS"),
    @NamedQuery(name = "EquipamentoServico.findListByServEqp", query = "select p from EquipamentoServico p WHERE p.OS = FALSE AND p.equipamento.EQP_ID = :eqpId AND p.servico.SRV_ID = :srvId")})

public class EquipamentoServico implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_EQS_CARTA = "EquipamentoServico.findListCarta";
    public static final String FIND_EQS_BY_EQP = "EquipamentoServico.findListByEqp";
    public static final String FIND_EQS_BY_EQP_CARTA = "EquipamentoServico.findListByEqpCarta";
    public static final String FIND_EQS_BY_EQP_HIST = "EquipamentoServico.findListHistoricoByEqp";
    public static final String FIND_EQS_BY_SERV_EQP = "EquipamentoServico.findListByServEqp";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_EQS;

//    @Id
    @ManyToOne
    @JoinColumn(name = "EQP_ID")
    private Equipamento equipamento;

//    @Id
    @ManyToOne
    @JoinColumn(name = "SRV_ID")
    private Servico servico;

    @OneToMany(mappedBy = "eqs", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<PecaEqs> equipamentosPecas;

    @Column
    private Integer SRV_FREQUENCIADIAS;

    @Column
    private Integer SRV_FREQUENCIAHORAS;

    @Temporal(TemporalType.DATE)
    private Date MANUTATUAL; //execução

    @Column
    private int MANUTATUALRHORAS;// execução

    @Temporal(TemporalType.DATE)
    private Date MANUTANTERIOR;

    @Column
    private int MANUTANTERIORHORAS;

    @Column
    private boolean REALIZADO = false;

    @Column
    private Boolean OS = false;

    @Transient
    private Date MANUTPROXIMA;

    @Transient
    private Date MANUTPROXIMAHORAS;

    @Transient
    private Date HOJE;

    @Transient
    private Date MANUTANTERIOR_TEMP;

    @Transient
    private int MANUTANTERIORHORAS_TEMP;

    @OneToOne
    @JoinColumn(name = "MOV_ID")
    private Movimento movimento;

    public EquipamentoServico() {
    }

    public EquipamentoServico(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Equipamento getEquipamento() {
        if (equipamento == null) {
            equipamento = new Equipamento();
        }
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Servico getServico() {
        if (servico == null) {
            servico = new Servico();
        }
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
        this.SRV_FREQUENCIADIAS = servico.getSRV_FREQDIAS();
        this.SRV_FREQUENCIAHORAS = servico.getSRV_FREQHORAS();
    }

    public Date getMANUTATUAL() {
        return MANUTATUAL;
    }

    public void setMANUTATUAL(Date MANUTATUAL) {
        this.MANUTATUAL = MANUTATUAL;
    }

    public int getMANUTATUALRHORAS() {
        return MANUTATUALRHORAS;
    }

    public void setMANUTATUALRHORAS(int MANUTATUALRHORAS) {
        this.MANUTATUALRHORAS = MANUTATUALRHORAS;
    }

    public Date getMANUTANTERIOR() {
        return MANUTANTERIOR;
    }

    public void setMANUTANTERIOR(Date MANUTANTERIOR) {
        this.MANUTANTERIOR = MANUTANTERIOR;
    }

    public int getMANUTANTERIORHORAS() {

        return MANUTANTERIORHORAS;
    }

    public void setMANUTANTERIORHORAS(int MANUTANTERIORHORAS) {
        this.MANUTANTERIORHORAS = MANUTANTERIORHORAS;
    }

    public boolean isREALIZADO() {
        return REALIZADO;
    }

    public void setREALIZADO(boolean REALIZADO) {
        this.REALIZADO = REALIZADO;
    }

    public Boolean getOS() {
        if (OS == null) {
            OS = Boolean.FALSE;
        }
        return OS;
    }

    public void setOS(Boolean OS) {
        this.OS = OS;
    }

    public Date getMANUTPROXIMA() {

        DateTime dateTime = new DateTime(MANUTATUAL);
        dateTime = dateTime.plusDays(getSRV_FREQUENCIADIAS());
        MANUTPROXIMA = dateTime.toDate();

        return MANUTPROXIMA;
    }

    public void setMANUTPROXIMA(Date MANUTPROXIMA) {
        this.MANUTPROXIMA = MANUTPROXIMA;
    }

    public Date getMANUTPROXIMAHORAS() {
        int hrSemana = equipamento.getEQP_REGIMEDIASSEMANA() * equipamento.getEQP_REGIMEHORASDIA();
        MANUTPROXIMAHORAS = new Date();
        if (hrSemana > 0) {
            int semanas = getSRV_FREQUENCIAHORAS() / hrSemana;
            int dias = semanas * 7;
            DateTime dateTime = new DateTime(MANUTATUAL);
//            System.out.println("MANUTATUAL CALCULO MANUTPROXIMAHORAS  = " + MANUTATUAL);
//            System.out.println("dias = " + dias);
            dateTime = dateTime.plusDays(dias);
            MANUTPROXIMAHORAS = dateTime.toDate();
            if (dias <= 0) {
                MANUTPROXIMAHORAS = null;
            }
        }

        return MANUTPROXIMAHORAS;
    }

    public void setMANUTPROXIMAHORAS(Date MANUTPROXIMAHORAS) {
        this.MANUTPROXIMAHORAS = MANUTPROXIMAHORAS;
    }

    public Date getHOJE() {
        HOJE = new Date();
        return HOJE;
    }

    public void setHOJE(Date HOJE) {
        this.HOJE = HOJE;
    }

    public int getID_EQS() {
        return ID_EQS;
    }

    public void setID_EQS(int ID_EQS) {
        this.ID_EQS = ID_EQS;
    }

    public Integer getSRV_FREQUENCIADIAS() {
        if (SRV_FREQUENCIADIAS == null) {
            SRV_FREQUENCIADIAS = 0;
        }
        return SRV_FREQUENCIADIAS;
    }

    public void setSRV_FREQUENCIADIAS(Integer SRV_FREQUENCIADIAS) {
        this.SRV_FREQUENCIADIAS = SRV_FREQUENCIADIAS;
    }

    public Integer getSRV_FREQUENCIAHORAS() {
        if (SRV_FREQUENCIAHORAS == null) {
            SRV_FREQUENCIAHORAS = 0;
        }
        return SRV_FREQUENCIAHORAS;
    }

    public void setSRV_FREQUENCIAHORAS(Integer SRV_FREQUENCIAHORAS) {
        this.SRV_FREQUENCIAHORAS = SRV_FREQUENCIAHORAS;
    }

    public Date getMANUTANTERIOR_TEMP() {
        return MANUTANTERIOR_TEMP;
    }

    public void setMANUTANTERIOR_TEMP(Date MANUTANTERIOR_TEMP) {
        this.MANUTANTERIOR_TEMP = MANUTANTERIOR_TEMP;
    }

    public int getMANUTANTERIORHORAS_TEMP() {
        return MANUTANTERIORHORAS_TEMP;
    }

    public void setMANUTANTERIORHORAS_TEMP(int MANUTANTERIORHORAS_TEMP) {
        this.MANUTANTERIORHORAS_TEMP = MANUTANTERIORHORAS_TEMP;
    }

    public Movimento getMovimento() {
        if (movimento == null) {
            movimento = new Movimento();
        }
        return movimento;
    }

    public void setMovimento(Movimento movimento) {
        this.movimento = movimento;
    }

    public List<PecaEqs> getEquipamentosPecas() {
        if (equipamentosPecas == null) {
            equipamentosPecas = new ArrayList<>();
        }
        return equipamentosPecas;
    }

    public void setEquipamentosPecas(List<PecaEqs> equipamentosPecas) {
        this.equipamentosPecas = equipamentosPecas;
    }

    @Override
    public String toString() {
        return "EquipamentoServico{" + "equipamento=" + equipamento + ", servico=" + servico + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.ID_EQS;
        hash = 67 * hash + Objects.hashCode(this.equipamento);
        hash = 67 * hash + Objects.hashCode(this.servico);
        hash = 67 * hash + Objects.hashCode(this.equipamentosPecas);
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
        final EquipamentoServico other = (EquipamentoServico) obj;
        if (this.ID_EQS != other.ID_EQS) {
            return false;
        }
//        if (!Objects.equals(this.getEquipamento(), other.getEquipamento())) {
//            return false;
//        }
        if (!Objects.equals(this.getServico(), other.getServico())) {
            return false;
        }
        if (!Objects.equals(this.getEquipamentosPecas(), other.getEquipamentosPecas())) {
            return false;
        }
        return true;
    }

}
