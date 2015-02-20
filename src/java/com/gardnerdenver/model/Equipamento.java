package com.gardnerdenver.model;

import com.gardnerdenver.facade.EquipamentoMedicaoFacade;
import com.gardnerdenver.facade.FactoryCategoriaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "Equipamento")
@NamedQueries({
    @NamedQuery(name = "Equipamento.eqpBySN", query = "select e from Equipamento e where e.EQP_SERIE = :sn")
})
public class Equipamento implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_BY_SN = "Equipamento.eqpBySN";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int EQP_ID;
    @Column(unique = true)
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
    private String EQP_FABRICANTE;
    private int EQP_CATID;
    private int EQP_HORIMETROINICIAL = 0;
    private String EQP_PRESSAOOPERACAO;
    @Temporal(TemporalType.DATE)
    private Date EQP_DATAPARTIDA;

//    @OneToMany(mappedBy = "equipamento")
    @OneToMany(targetEntity = Movimento.class)
//    @JoinColumn(name = "MOV_ID")
    private List<Movimento> movimentosOs;

//    @OneToMany(targetEntity = Movimento.class)
//    private List<Movimento> movimentosOsT;
    @ManyToOne
    @JoinColumn(name = "PAR_ID")
    private Parceiro parceiro;

    @ManyToOne
    @JoinColumn(name = "MOD_ID")
    private Modelo modelo;

//    @OneToMany(mappedBy = "equipamento", fetch = FetchType.EAGER)
//    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "equipamento", cascade = CascadeType.ALL)
    private List<EquipamentoServico> servicos;

    @Transient
    private DataModel<EquipamentoServico> dataServicos;

    @OneToMany(mappedBy = "equipamento", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<EquipamentoMedicao> equipamentoMedicao;

    @Transient
    private EquipamentoMedicao eqpMedicao;

    @Transient
    private FactoryCategoria categoria;

    @Transient
    private StringBuilder textoEmail;

    @Column
    private Boolean fab = false;

    @Column
    private Boolean ativo = true;

    @OneToMany(mappedBy = "equipamento")//    @Fetch(FetchMode.SUBSELECT)
    private List<Historico> historicos;

    public Equipamento() {

    }

    public Equipamento(Equipamento e) {
        this.EQP_ID = e.getEQP_ID();
        this.EQP_SERIE = e.getEQP_SERIE();
        this.EQP_TENSAO = e.getEQP_TENSAO();
        this.EQP_REGIMEHORASDIA = e.getEQP_REGIMEHORASDIA();
        this.EQP_REGIMEDIASSEMANA = e.getEQP_REGIMEDIASSEMANA();
        this.EQP_REGIMEMEDIA = e.getEQP_REGIMEMEDIA();
        this.EQP_OBS = e.getEQP_OBS();
        this.EQP_FABRICANTE = e.getEQP_FABRICANTE();
        this.EQP_CATID = e.getEQP_CATID();
        this.modelo = e.getModelo();
        this.EQP_PRESSAOOPERACAO = e.getEQP_PRESSAOOPERACAO();
        this.EQP_DATAPARTIDA = e.getEQP_DATAPARTIDA();
        this.parceiro = e.getParceiro();
        this.ativo = e.getAtivo();
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

    public Boolean getAtivo() {
        if (ativo == null) {
            ativo = true;
        }
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<Historico> getHistoricos() {
        return historicos;
    }

    public void setHistoricos(List<Historico> historicos) {
        this.historicos = historicos;
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

    public String getEQP_FABRICANTE() {
        return EQP_FABRICANTE;
    }

    public void setEQP_FABRICANTE(String EQP_FABRICANTE) {
        this.EQP_FABRICANTE = EQP_FABRICANTE;
    }

    public int getEQP_CATID() {
        return EQP_CATID;
    }

    public void setEQP_CATID(int EQP_CATID) {
        this.EQP_CATID = EQP_CATID;
    }

    public int getEQP_HORIMETROINICIAL() {
        return EQP_HORIMETROINICIAL;
    }

    public void setEQP_HORIMETROINICIAL(int EQP_HORIMETROINICIAL) {
        this.EQP_HORIMETROINICIAL = EQP_HORIMETROINICIAL;
    }

    public Date getEQP_DATAPARTIDA() {
        return EQP_DATAPARTIDA;
    }

    public void setEQP_DATAPARTIDA(Date EQP_DATAPARTIDA) {
        this.EQP_DATAPARTIDA = EQP_DATAPARTIDA;
    }

    public Parceiro getParceiro() {
        return parceiro;
    }

    public void setParceiro(Parceiro parceiro) {
        this.parceiro = parceiro;
    }

    public List<EquipamentoServico> getServicos() {
        if (servicos == null) {
            servicos = new ArrayList<>();
        }
        return servicos;
    }

    public void setServicos(List<EquipamentoServico> servicos) {
        this.servicos = servicos;
    }

    public DataModel<EquipamentoServico> getDataServicos() {
        dataServicos = new ListDataModel<>(getServicos());
        return dataServicos;
    }

    public void setDataServicos(DataModel<EquipamentoServico> dataServicos) {
        this.dataServicos = dataServicos;
    }

    public List<EquipamentoMedicao> getEquipamentoMedicao() {
        return equipamentoMedicao;
    }

    public void setEquipamentoMedicao(List<EquipamentoMedicao> equipamentoMedicao) {
        this.equipamentoMedicao = equipamentoMedicao;
    }

    public EquipamentoMedicao getEqpMedicao() {

        if (eqpMedicao == null && EQP_ID != 0) {
            EquipamentoMedicaoFacade eqpMedFacade = new EquipamentoMedicaoFacade();
            setEqpMedicao(eqpMedFacade.findLastUpdate(EQP_ID));
        }

        if (eqpMedicao == null) {
            setEqpMedicao(new EquipamentoMedicao());
        }

//        if (eqpMedicao == null && EQP_ID != 0) {
//            EquipamentoMedicaoFacade eqpMedFacade = new EquipamentoMedicaoFacade();
//            eqpMedicao = eqpMedFacade.findLastUpdate(EQP_ID);
//        }
//
//        if (eqpMedicao == null) {
//            eqpMedicao = new EquipamentoMedicao();
//        }
        return eqpMedicao;
    }

    public void setEqpMedicao(EquipamentoMedicao eqpMedicao) {
        this.eqpMedicao = eqpMedicao;
    }

    public FactoryCategoria getCategoria() {
        if (categoria == null && EQP_ID != 0) {
            FactoryCategoriaFacade catFacade = new FactoryCategoriaFacade();
            categoria = catFacade.findCategoriaById(EQP_CATID);
        }
        if (categoria == null) {
            categoria = new FactoryCategoria();
        }
        return categoria;
    }

    public void setCategoria(FactoryCategoria categoria) {
        this.categoria = categoria;
    }

    public StringBuilder getTextoEmail() {
        if (textoEmail == null) {
            textoEmail = new StringBuilder();
        }
        return textoEmail;
    }

    public void setTextoEmail(StringBuilder textoEmail) {
        this.textoEmail = textoEmail;
    }

    public String getEQP_PRESSAOOPERACAO() {
        return EQP_PRESSAOOPERACAO;
    }

    public void setEQP_PRESSAOOPERACAO(String EQP_PRESSAOOPERACAO) {
        this.EQP_PRESSAOOPERACAO = EQP_PRESSAOOPERACAO;
    }

    public List<Movimento> getMovimentosOs() {
        return movimentosOs;
    }

    public void setMovimentosOs(List<Movimento> movimentosOs) {
        this.movimentosOs = movimentosOs;
    }

    public Modelo getModelo() {
        if (modelo == null) {
            modelo = new Modelo();
        }
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
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
        final Equipamento other = (Equipamento) obj;
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
        if (!Objects.equals(this.EQP_FABRICANTE, other.EQP_FABRICANTE)) {
            return false;
        }
        if (this.EQP_CATID != other.EQP_CATID) {
            return false;
        }

        if (!Objects.equals(this.EQP_PRESSAOOPERACAO, other.EQP_PRESSAOOPERACAO)) {
            return false;
        }
        if (!Objects.equals(this.EQP_DATAPARTIDA, other.EQP_DATAPARTIDA)) {
            return false;
        }
        if (!Objects.equals(this.parceiro, other.parceiro)) {
            return false;
        }
        if (!this.getAtivo().equals(other.getAtivo())) {
            return false;
        }
//        if (!Objects.equals(this.getServicos(), other.getServicos())) {
//            return false;
//        } else {
//            for (EquipamentoServico esThis : this.getServicos()) {
//                for (EquipamentoServico esOther : other.getServicos()) {
//                    if (esThis.getID_EQS() == esOther.getID_EQS() && !esThis.equals(esOther)) {
//                        return false;
//                    }
//
//                }
//
//            }
//        }

        return true;
    }

    @Override
    public String toString() {
        return "Equipamento{" + "EQP_ID=" + EQP_ID + ", EQP_SERIE=" + EQP_SERIE + ", EQP_TENSAO=" + EQP_TENSAO + ", EQP_REGIMEHORASDIA=" + EQP_REGIMEHORASDIA + ", EQP_REGIMEDIASSEMANA=" + EQP_REGIMEDIASSEMANA + ", EQP_OBS=" + EQP_OBS + ", EQP_FABRICANTE=" + EQP_FABRICANTE + ", parceiro=" + parceiro + '}';
    }

}
