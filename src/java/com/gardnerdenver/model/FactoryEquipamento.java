package com.gardnerdenver.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "FactoryEquipamento")
//@NamedQueries({
//    @NamedQuery(name = "Equipamento.busca", query = "select e from Equipamento e where e.EQP_DESCRICAO like '%:DESC%' AND (CASE ':PAR_ID' WHEN '0' THEN e.parceiro.PAR_ID <> 0 ELSE e.parceiro.PAR_ID = :PAR_ID END)")
//})
public class FactoryEquipamento implements Serializable {

    private static final long serialVersionUID = 1L;

//    public static final String FIND_BY_BUSCA = "Equipamento.busca";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int EQP_ID;
    @Column//(unique = true)
    private String EQP_SERIE;
    private String EQP_TENSAO;
    @Column
    private int EQP_REGIMEHORASDIA;
    @Column
    private int EQP_REGIMEDIASSEMANA;
    @Transient
    private int EQP_REGIMEMEDIA;
    @Column(length = 5000)
    private String EQP_OBS;
    @Column
    private String EQP_DESCRICAO;
    private String EQP_FABRICANTE;
    private String EQP_CATEGORIA;
    private int EQP_CATID;
    private String EQP_MODELO;
    private String EQP_PRESSAOOPERACAO;
    @Temporal(TemporalType.DATE)
    private Date EQP_DATAPARTIDA;

//    @OneToMany(mappedBy = "equipamento")
    @Column
    private Boolean fab = false;

    public FactoryEquipamento() {
    }

    public FactoryEquipamento(FactoryEquipamento e) {
        this.EQP_ID = e.getEQP_ID();
        this.EQP_SERIE = e.getEQP_SERIE();
        this.EQP_TENSAO = e.getEQP_TENSAO();
        this.EQP_REGIMEHORASDIA = e.getEQP_REGIMEHORASDIA();
        this.EQP_REGIMEDIASSEMANA = e.getEQP_REGIMEDIASSEMANA();
        this.EQP_REGIMEMEDIA = e.getEQP_REGIMEMEDIA();
        this.EQP_OBS = e.getEQP_OBS();
        this.EQP_DESCRICAO = e.getEQP_DESCRICAO();
        this.EQP_FABRICANTE = e.getEQP_FABRICANTE();
        this.EQP_CATEGORIA = e.getEQP_CATEGORIA();
        this.EQP_CATID = e.getEQP_CATID();
        this.EQP_MODELO = e.getEQP_MODELO();
        this.EQP_PRESSAOOPERACAO = e.getEQP_PRESSAOOPERACAO();
        this.EQP_DATAPARTIDA = e.getEQP_DATAPARTIDA();
    }

    public Boolean isFab() {
        if (fab == null) {
            fab = false;
        }
        return fab;
    }

    public void setFab(Boolean fab) {
        this.fab = fab;
    }

    public int getEQP_ID() {
        return EQP_ID;
    }

    public void setEQP_ID(int EQP_ID) {
        this.EQP_ID = EQP_ID;
    }

    public String getEQP_SERIE() {
        return EQP_SERIE;
    }

    public void setEQP_SERIE(String EQP_SERIE) {
        this.EQP_SERIE = EQP_SERIE;
    }

    public String getEQP_TENSAO() {
        return EQP_TENSAO;
    }

    public void setEQP_TENSAO(String EQP_TENSAO) {
        this.EQP_TENSAO = EQP_TENSAO;
    }

    public int getEQP_REGIMEHORASDIA() {
        return EQP_REGIMEHORASDIA;
    }

    public void setEQP_REGIMEHORASDIA(int EQP_REGIMEHORASDIA) {
        this.EQP_REGIMEHORASDIA = EQP_REGIMEHORASDIA;
    }

    public int getEQP_REGIMEDIASSEMANA() {
        return EQP_REGIMEDIASSEMANA;
    }

    public void setEQP_REGIMEDIASSEMANA(int EQP_REGIMEDIASSEMANA) {
        this.EQP_REGIMEDIASSEMANA = EQP_REGIMEDIASSEMANA;
    }

    public int getEQP_REGIMEMEDIA() {
        EQP_REGIMEMEDIA = (EQP_REGIMEDIASSEMANA * EQP_REGIMEHORASDIA) / 7;
        return EQP_REGIMEMEDIA;
    }

    public void setEQP_REGIMEMEDIA(int EQP_REGIMEMEDIA) {
        this.EQP_REGIMEMEDIA = EQP_REGIMEMEDIA;
    }

    public String getEQP_OBS() {
        return EQP_OBS;
    }

    public void setEQP_OBS(String EQP_OBS) {
        this.EQP_OBS = EQP_OBS;
    }

    public String getEQP_DESCRICAO() {
        return EQP_DESCRICAO;
    }

    public void setEQP_DESCRICAO(String EQP_DESCRICAO) {
        this.EQP_DESCRICAO = EQP_DESCRICAO;
    }

    public String getEQP_MODELO() {
        return EQP_MODELO;
    }

    public void setEQP_MODELO(String EQP_MODELO) {
        this.EQP_MODELO = EQP_MODELO;
    }

    public String getEQP_FABRICANTE() {
        return EQP_FABRICANTE;
    }

    public void setEQP_FABRICANTE(String EQP_FABRICANTE) {
        this.EQP_FABRICANTE = EQP_FABRICANTE;
    }

    public String getEQP_CATEGORIA() {
        return EQP_CATEGORIA;
    }

    public void setEQP_CATEGORIA(String EQP_CATEGORIA) {
        this.EQP_CATEGORIA = EQP_CATEGORIA;
    }

    public int getEQP_CATID() {
        return EQP_CATID;
    }

    public void setEQP_CATID(int EQP_CATID) {
        this.EQP_CATID = EQP_CATID;
    }

    public Date getEQP_DATAPARTIDA() {
        return EQP_DATAPARTIDA;
    }

    public void setEQP_DATAPARTIDA(Date EQP_DATAPARTIDA) {
        this.EQP_DATAPARTIDA = EQP_DATAPARTIDA;
    }

    public String getEQP_PRESSAOOPERACAO() {
        return EQP_PRESSAOOPERACAO;
    }

    public void setEQP_PRESSAOOPERACAO(String EQP_PRESSAOOPERACAO) {
        this.EQP_PRESSAOOPERACAO = EQP_PRESSAOOPERACAO;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.EQP_ID;
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
        final FactoryEquipamento other = (FactoryEquipamento) obj;
        if (this.EQP_ID != other.EQP_ID) {
            return false;
        }
        if (!Objects.equals(this.EQP_SERIE, other.EQP_SERIE)) {
            return false;
        }
        if (!Objects.equals(this.EQP_TENSAO, other.EQP_TENSAO)) {
            return false;
        }
        if (this.EQP_REGIMEHORASDIA != other.EQP_REGIMEHORASDIA) {
            return false;
        }
        if (this.EQP_REGIMEDIASSEMANA != other.EQP_REGIMEDIASSEMANA) {
            return false;
        }
        if (this.EQP_REGIMEMEDIA != other.EQP_REGIMEMEDIA) {
            return false;
        }
        if (!Objects.equals(this.EQP_OBS, other.EQP_OBS)) {
            return false;
        }
        if (!Objects.equals(this.EQP_DESCRICAO, other.EQP_DESCRICAO)) {
            return false;
        }
        if (!Objects.equals(this.EQP_FABRICANTE, other.EQP_FABRICANTE)) {
            return false;
        }
        if (!Objects.equals(this.EQP_CATEGORIA, other.EQP_CATEGORIA)) {
            return false;
        }
        if (this.EQP_CATID != other.EQP_CATID) {
            return false;
        }
        if (!Objects.equals(this.EQP_MODELO, other.EQP_MODELO)) {
            return false;
        }
        if (!Objects.equals(this.EQP_PRESSAOOPERACAO, other.EQP_PRESSAOOPERACAO)) {
            return false;
        }
        if (!Objects.equals(this.EQP_DATAPARTIDA, other.EQP_DATAPARTIDA)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "FactoryEquipamento{" + "EQP_ID=" + EQP_ID + ", EQP_SERIE=" + EQP_SERIE + ", EQP_TENSAO=" + EQP_TENSAO + ", EQP_REGIMEHORASDIA=" + EQP_REGIMEHORASDIA + ", EQP_REGIMEDIASSEMANA=" + EQP_REGIMEDIASSEMANA + ", EQP_REGIMEMEDIA=" + EQP_REGIMEMEDIA + ", EQP_OBS=" + EQP_OBS + ", EQP_DESCRICAO=" + EQP_DESCRICAO + ", EQP_FABRICANTE=" + EQP_FABRICANTE + ", EQP_CATEGORIA=" + EQP_CATEGORIA + ", EQP_CATID=" + EQP_CATID + ", EQP_MODELO=" + EQP_MODELO + ", EQP_PRESSAOOPERACAO=" + EQP_PRESSAOOPERACAO + ", EQP_DATAPARTIDA=" + EQP_DATAPARTIDA + ", fab=" + fab + '}';
    }

}
