package com.gardnerdenver.bean;

import com.gardnerdenver.facade.FactoryCategoriaFacade;
import com.gardnerdenver.model.FactoryCategoria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "categoriaBean")
@RequestScoped
public class CategoriaBean implements Serializable {

    private List<FactoryCategoria> listaCategoria = new ArrayList<>();

    public CategoriaBean() {
        listaCategoria = new FactoryCategoriaFacade().listAll();
    }

    public List<FactoryCategoria> getListaCategoria() {
        return listaCategoria;
    }

    public void setListaCategoria(List<FactoryCategoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

}
