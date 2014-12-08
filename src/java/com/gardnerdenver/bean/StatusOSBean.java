package com.gardnerdenver.bean;

import com.gardnerdenver.model.StatusOS;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class StatusOSBean implements Serializable {

    private List<StatusOS> lista = new ArrayList<>();

    public List<StatusOS> getLista() {
        lista.add(new StatusOS(0, "A determinar"));
        lista.add(new StatusOS(1, "Serviço"));
        lista.add(new StatusOS(2, "Garantia"));
        lista.add(new StatusOS(3, "Cortesia"));

        return lista;
    }

    public void setLista(List<StatusOS> lista) {
        this.lista = lista;
    }

}
