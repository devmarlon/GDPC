package com.gardnerdenver.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(name = "FactoryUserItem.findListFabrica", query = "select u from FactoryUserItem u WHERE u.UserFactory.rol = :rol"),
    @NamedQuery(name = "FactoryUserItem.findUserByEmail", query = "select u from FactoryUserItem u where u.USI_LOGIN = :USI_LOGIN")
})
public class FactoryUserItem implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_LIST_FABRICA = "FactoryUserItem.findListFabrica";
    public static final String FIND_BY_EMAIL = "FactoryUserItem.findUserByEmail";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int USI_ID;
    @Column(unique = true)
    private String USI_LOGIN; // email
    @Column
    private String USI_SENHA;
    private String USI_NOME;
    private boolean ADM = false;

    @ManyToOne
    @JoinColumn(name = "USU_ID")
    private FactoryUser UserFactory;

    public int getUSI_ID() {
        return USI_ID;
    }

    public void setUSI_ID(int USI_ID) {
        this.USI_ID = USI_ID;
    }

    public String getUSI_LOGIN() {
        return USI_LOGIN;
    }

    public void setUSI_LOGIN(String USI_LOGIN) {
        this.USI_LOGIN = USI_LOGIN;
    }

    public String getUSI_SENHA() {
        return USI_SENHA;
    }

    public void setUSI_SENHA(String USI_SENHA) {
        this.USI_SENHA = USI_SENHA;
    }

    public String getUSI_NOME() {
        return USI_NOME;
    }

    public void setUSI_NOME(String USI_NOME) {
        this.USI_NOME = USI_NOME;
    }

    public boolean isADM() {
        return ADM;
    }

    public void setADM(boolean ADM) {
        this.ADM = ADM;
    }

    public FactoryUser getUserFactory() {
        return UserFactory;
    }

    public void setUserFactory(FactoryUser UserFactory) {
        this.UserFactory = UserFactory;
    }

    public FactoryUserItem() {
    }

    public FactoryUserItem(FactoryUserItem f) {
        this.USI_LOGIN = f.getUSI_LOGIN();
        this.USI_SENHA = f.getUSI_SENHA();
        this.USI_NOME = f.getUSI_NOME();
    }

    @Override
    public int hashCode() {
        return USI_ID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FactoryUserItem other = (FactoryUserItem) obj;
        if (!Objects.equals(this.USI_LOGIN, other.USI_LOGIN)) {
            return false;
        }
        if (!Objects.equals(this.USI_SENHA, other.USI_SENHA)) {
            return false;
        }
        if (!Objects.equals(this.USI_NOME, other.USI_NOME)) {
            return false;
        }
        return true;
    }

   
    @Override
    public String toString() {
        return "FactoryUserItem{" + "USI_ID=" + USI_ID + ", USI_LOGIN=" + USI_LOGIN + ", USI_SENHA=" + USI_SENHA + ", UserFactory=" + UserFactory + '}';
    }

}
