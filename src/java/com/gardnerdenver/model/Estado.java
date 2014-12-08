package com.gardnerdenver.model;

import java.io.Serializable;
import java.util.Objects;

public class Estado implements Serializable {

    private int JEST_ID;
    private String JEST_UF;
    private String JEST_NOME;

    public Estado(int JEST_ID, String JEST_UF, String JEST_NOME) {
        this.JEST_ID = JEST_ID;
        this.JEST_UF = JEST_UF;
        this.JEST_NOME = JEST_NOME;
    }

    public Estado() {
    }

    public int getJEST_ID() {
        return JEST_ID;
    }

    public void setJEST_ID(int JEST_ID) {
        this.JEST_ID = JEST_ID;
    }

    public String getJEST_NOME() {
        return JEST_NOME;
    }

    public void setJEST_NOME(String JEST_NOME) {
        this.JEST_NOME = JEST_NOME;
    }

    public String getJEST_UF() {
        return JEST_UF;
    }

    public void setJEST_UF(String JEST_UF) {
        this.JEST_UF = JEST_UF;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.JEST_ID;
        hash = 97 * hash + Objects.hashCode(this.JEST_UF);
        hash = 97 * hash + Objects.hashCode(this.JEST_NOME);
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
        final Estado other = (Estado) obj;
        if (this.JEST_ID != other.JEST_ID) {
            return false;
        }
        if (!Objects.equals(this.JEST_UF, other.JEST_UF)) {
            return false;
        }
        if (!Objects.equals(this.JEST_NOME, other.JEST_NOME)) {
            return false;
        }
        return true;
    }

}
