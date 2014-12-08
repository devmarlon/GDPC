package com.gardnerdenver.model;

import java.io.Serializable;
import java.util.Objects;

public class Generico implements Serializable {

    private int GEN_ID;
    private String GEN_NOME;

    public Generico(int GEN_ID, String GEN_NOME) {
        this.GEN_ID = GEN_ID;
        this.GEN_NOME = GEN_NOME;
    }

    public int getGEN_ID() {
        return GEN_ID;
    }

    public void setGEN_ID(int GEN_ID) {
        this.GEN_ID = GEN_ID;
    }

    public String getGEN_NOME() {
        return GEN_NOME;
    }

    public void setGEN_NOME(String GEN_NOME) {
        this.GEN_NOME = GEN_NOME;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.GEN_ID;
        hash = 73 * hash + Objects.hashCode(this.GEN_NOME);
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
        final Generico other = (Generico) obj;
        if (this.GEN_ID != other.GEN_ID) {
            return false;
        }
        if (!Objects.equals(this.GEN_NOME, other.GEN_NOME)) {
            return false;
        }
        return true;
    }
}
