package com.gardnerdenver.converter;

import com.gardnerdenver.bean.CategoriaBean;
import com.gardnerdenver.model.FactoryCategoria;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = com.gardnerdenver.model.FactoryCategoria.class)
public class FactoryCategoriaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String ID) throws ConverterException {
        if ((ID != null) && (!ID.equals("")) && (!ID.equals("Selecione")) && (!ID.equals("Todos"))) {
            CategoriaBean estBean = new CategoriaBean();
            List<FactoryCategoria> listEst = estBean.getListaCategoria();
            //
            int idInt = Integer.valueOf(ID);
            for (int a = 0; a < listEst.size(); a++) {
                if (listEst.get(a).getCAT_ID() == idInt) {
                    return listEst.get(a);
                }
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) throws ConverterException {
        if (object != null && object instanceof FactoryCategoria) {
            return String.valueOf(((FactoryCategoria) object).getCAT_ID());
        }
        return null;
    }
}
