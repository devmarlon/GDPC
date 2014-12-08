/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gardnerdenver.model;

import com.gardnerdenver.bean.UserItemFactoryBean;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marlon
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historico.findAll", query = "SELECT h FROM Historico h"),
    @NamedQuery(name = "Historico.findByParceiro", query = "SELECT h FROM Historico h WHERE h.parceiro.PAR_ID = :parId"),
    @NamedQuery(name = "Historico.findByEquipamento", query = "SELECT h FROM Historico h WHERE h.equipamento.EQP_ID = :eqpId"),
    @NamedQuery(name = "Historico.findByHisId", query = "SELECT h FROM Historico h WHERE h.hisId = :hisId")})
public class Historico implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_BY_PARCEIRO = "Historico.findByParceiro";
    public static final String FIND_BY_EQUIPAMENTO = "Historico.findByEquipamento";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Integer hisId;
    @Size(max = 1000)
    @Column
    private String HIS_COMENTARIO;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date HIS_EMISSAO;

    @JoinColumn(name = "FUN_ID")
    @ManyToOne
    private Funcionario funcionario;

    @JoinColumn(name = "PAR_ID")
    @ManyToOne
    private Parceiro parceiro;

    @JoinColumn(name = "EQP_ID")
    @ManyToOne
    private Equipamento equipamento;

    public Historico() {
    }

    public Integer getHisId() {
        return hisId;
    }

    public void setHisId(Integer hisId) {
        this.hisId = hisId;
    }

    public String getHIS_COMENTARIO() {
        return HIS_COMENTARIO;
    }

    public void setHIS_COMENTARIO(String HIS_COMENTARIO) {
        this.HIS_COMENTARIO = HIS_COMENTARIO;
    }

    public Date getHIS_EMISSAO() {
        if (HIS_EMISSAO == null) {
            HIS_EMISSAO = new Date();
        }
        return HIS_EMISSAO;
    }

    public void setHIS_EMISSAO(Date HIS_EMISSAO) {
        this.HIS_EMISSAO = HIS_EMISSAO;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Parceiro getParceiro() {
        return parceiro;
    }

    public void setParceiro(Parceiro parceiro) {
        this.parceiro = parceiro;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.hisId);
        hash = 37 * hash + Objects.hashCode(this.HIS_COMENTARIO);
        hash = 37 * hash + Objects.hashCode(this.HIS_EMISSAO);
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
        final Historico other = (Historico) obj;
        if (!Objects.equals(this.hisId, other.hisId)) {
            return false;
        }
        if (!Objects.equals(this.HIS_COMENTARIO, other.HIS_COMENTARIO)) {
            return false;
        }
        if (!Objects.equals(this.HIS_EMISSAO, other.HIS_EMISSAO)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Historico{" + "hisId=" + hisId + '}';
    }

}
