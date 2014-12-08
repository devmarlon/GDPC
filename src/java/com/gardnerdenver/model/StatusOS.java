package com.gardnerdenver.model;

import java.util.Objects;

public class StatusOS {

    private int SOS_ID;
    private String SOS_NOME;

    public StatusOS() {
    }

    public StatusOS(int SOS_ID, String SOS_NOME) {
        this.SOS_ID = SOS_ID;
        this.SOS_NOME = SOS_NOME;
    }

    public int getSOS_ID() {
        return SOS_ID;
    }

    public void setSOS_ID(int SOS_ID) {
        this.SOS_ID = SOS_ID;
    }

    public String getSOS_NOME() {
        return SOS_NOME;
    }

    public void setSOS_NOME(String SOS_NOME) {
        this.SOS_NOME = SOS_NOME;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.SOS_ID;
        hash = 79 * hash + Objects.hashCode(this.SOS_NOME);
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
        final StatusOS other = (StatusOS) obj;
        if (this.SOS_ID != other.SOS_ID) {
            return false;
        }
        if (!Objects.equals(this.SOS_NOME, other.SOS_NOME)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return SOS_ID + " - " + SOS_NOME;
    }
}
