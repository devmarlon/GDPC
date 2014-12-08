package com.gardnerdenver.bean;

import com.gardnerdenver.model.Estado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "estadoBean")
@RequestScoped
public class EstadoBean implements Serializable {

    private List<Estado> listaEstado = new ArrayList<>();

    public void setListaEstado(List<Estado> listaEstado) {
        this.listaEstado = listaEstado;
    }

    public List<Estado> getListaEstado() {

        listaEstado = new ArrayList<>();

        listaEstado.add(new Estado(12, "AC", "Acre"));
        listaEstado.add(new Estado(27, "AL", "Alagoas"));
        listaEstado.add(new Estado(16, "AP", "Amapá"));
        listaEstado.add(new Estado(13, "AM", "Amazonas"));
        listaEstado.add(new Estado(29, "BA", "Bahia"));
        listaEstado.add(new Estado(23, "CE", "Ceará"));
        listaEstado.add(new Estado(53, "DF", "Distrito Federal"));
        listaEstado.add(new Estado(32, "ES", "Espírito Santo"));
        listaEstado.add(new Estado(52, "GO", "Goiás"));
        listaEstado.add(new Estado(21, "MA", "Maranhão"));
        listaEstado.add(new Estado(51, "MT", "Mato Grosso"));
        listaEstado.add(new Estado(50, "MS", "Mato Grosso do Sul"));
        listaEstado.add(new Estado(31, "MG", "Minas Gerais"));
        listaEstado.add(new Estado(15, "PA", "Pará"));
        listaEstado.add(new Estado(25, "PB", "Paraíba"));
        listaEstado.add(new Estado(41, "PR", "Paraná"));
        listaEstado.add(new Estado(26, "PE", "Pernambuco"));
        listaEstado.add(new Estado(22, "PI", "Piauí"));
        listaEstado.add(new Estado(33, "RJ", "Rio de Janeiro"));
        listaEstado.add(new Estado(24, "RN", "Rio Grande do Norte"));
        listaEstado.add(new Estado(43, "RS", "Rio Grande do Sul"));
        listaEstado.add(new Estado(11, "RO", "Rondônia"));
        listaEstado.add(new Estado(14, "RR", "Roraima"));
        listaEstado.add(new Estado(42, "SC", "Santa Catarina"));
        listaEstado.add(new Estado(35, "SP", "São Paulo"));
        listaEstado.add(new Estado(28, "SE", "Sergipe"));
        listaEstado.add(new Estado(17, "TO", "Tocantins"));

        return listaEstado;
    }

    public Estado getObjById(int id) {
        Estado est = null;

        for (Estado estAux : getListaEstado()) {
            if (estAux.getJEST_ID() == id) {
                est = estAux;
                break;
            }
        }

        return est;
    }
}
