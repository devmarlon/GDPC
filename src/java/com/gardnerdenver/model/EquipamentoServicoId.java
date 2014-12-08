package com.gardnerdenver.model;

import java.io.Serializable;
import java.util.Objects;

public class EquipamentoServicoId implements Serializable {

    private int ID;

//    private int equipamento;
//    private int servico;
//    public int getEquipamento() {
//        return equipamento;
//    }
//
//    public void setEquipamento(int equipamento) {
//        this.equipamento = equipamento;
//    }
//
//    public int getServico() {
//        return servico;
//    }
//
//    public void setServico(int servico) {
//        this.servico = servico;
//    }
//    @Override
//    public int hashCode() {
//        return equipamento + servico;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj instanceof EquipamentoServicoId) {
//            EquipamentoServicoId equipamentoServicoId = (EquipamentoServicoId) obj;
//            return equipamentoServicoId.servico == servico && equipamentoServicoId.equipamento == equipamento;
//        }
//
//        return false;
//    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.ID);
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
        final EquipamentoServicoId other = (EquipamentoServicoId) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        return true;
    }

}
