package com.gardnerdenver.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "Servico")
@NamedQueries({
    @NamedQuery(name = "Servico.findServico", query = "select s from Servico s where s.ativo IS NULL or s.ativo = true"),
    @NamedQuery(name = "Servico.findServicoById", query = "select s from Servico s where s.SRV_ID = :servId and (s.ativo IS NULL or s.ativo = true)"),
    @NamedQuery(name = "Servico.findServicoByFab", query = "select s from Servico s where s.fab = :fab"),// and (s.ativo IS NULL or s.ativo = true)"),
    @NamedQuery(name = "Servico.findServicoByDesc", query = "select s from Servico s where s.SRV_DESCRICAO = :servDesc and (s.ativo IS NULL or s.ativo = true)")
})
public class Servico implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "Servico.findServico";
    public static final String FIND_SERVICO_BY_ID = "Servico.findServicoById";
    public static final String FIND_SERVICO_BY_FAB = "Servico.findServicoByFab";
    public static final String FIND_SERVICO_BY_DESC = "Servico.findServicoByDesc";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SRV_ID;
    @Column
    private String SRV_DESCRICAO;
    @OneToMany(mappedBy = "servico", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<EquipamentoServico> equipamentos;
    @Column
    private Integer SRV_FREQHORAS;
    @Column
    private Integer SRV_FREQDIAS;
    @Column
    private Integer fab = 0;
    @Column
    private Boolean ativo = Boolean.TRUE;

    public Servico(Servico s) {
        this.SRV_ID = 0;
        this.SRV_DESCRICAO = s.getSRV_DESCRICAO();
        this.SRV_FREQDIAS = s.getSRV_FREQDIAS();
        this.SRV_FREQHORAS = s.getSRV_FREQHORAS();
    }

    public Servico(FactoryServico f) {
        this.SRV_DESCRICAO = f.getSRV_DESCRICAO();
        this.SRV_FREQHORAS = f.getSRV_FREQHORAS();
        this.SRV_FREQDIAS = f.getSRV_FREQDIAS();
        this.fab = f.getSRV_ID();
        this.ativo = f.getAtivo();
    }

    public Servico() {
    }

    public Integer getFab() {
        if (fab == null) {
            fab = 0;
        }
        return fab;
    }

    public void setFab(Integer fab) {
        this.fab = fab;
    }

    public int getSRV_ID() {
        return SRV_ID;
    }

    public void setSRV_ID(int SRV_ID) {
        this.SRV_ID = SRV_ID;
    }

    public String getSRV_DESCRICAO() {
        return SRV_DESCRICAO;
    }

    public void setSRV_DESCRICAO(String SRV_DESCRICAO) {
        this.SRV_DESCRICAO = SRV_DESCRICAO;
    }

    public List<EquipamentoServico> getEquipamentos() {
        if (equipamentos == null) {
            equipamentos = new ArrayList<>();
        }
        return equipamentos;
    }

    public void setEquipamentos(List<EquipamentoServico> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public Integer getSRV_FREQHORAS() {
        if (SRV_FREQHORAS == null) {
            SRV_FREQHORAS = 0;
        }
        return SRV_FREQHORAS;
    }

    public void setSRV_FREQHORAS(Integer SRV_FREQHORAS) {
        this.SRV_FREQHORAS = SRV_FREQHORAS;
    }

    public Integer getSRV_FREQDIAS() {
        if (SRV_FREQDIAS == null) {
            SRV_FREQDIAS = 0;
        }
        return SRV_FREQDIAS;
    }

    public void setSRV_FREQDIAS(Integer SRV_FREQDIAS) {
        this.SRV_FREQDIAS = SRV_FREQDIAS;
    }

    public Boolean getAtivo() {
        if (ativo == null) {
            ativo = Boolean.TRUE;
        }
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) SRV_ID;
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
        final Servico other = (Servico) obj;
        if (this.SRV_ID != other.SRV_ID) {
            return false;
        }
        if (!Objects.equals(this.SRV_DESCRICAO, other.SRV_DESCRICAO)) {
            return false;
        }
        if (!Objects.equals(this.SRV_FREQHORAS, other.SRV_FREQHORAS)) {
            return false;
        }
        if (!Objects.equals(this.SRV_FREQDIAS, other.SRV_FREQDIAS)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Servico[ SRV_ID=" + SRV_ID + " ]";
    }
}
