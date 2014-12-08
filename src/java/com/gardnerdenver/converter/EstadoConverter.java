package com.gardnerdenver.converter;

import com.gardnerdenver.bean.EstadoBean;
import com.gardnerdenver.model.Estado;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = com.gardnerdenver.model.Estado.class)
public class EstadoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String ID) throws ConverterException {
        if ((ID != null) && (!ID.equals("")) && (!ID.equals("Selecione")) && (!ID.equals("Todos"))) {
            EstadoBean estBean = new EstadoBean();
            List<Estado> listEst = estBean.getListaEstado();
            //
            int idInt = Integer.valueOf(ID);
            for (int a = 0; a < listEst.size(); a++) {
                if (listEst.get(a).getJEST_ID() == idInt) {
                    return listEst.get(a);
                }
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) throws ConverterException {
        if (object != null && object instanceof Estado) {
            return String.valueOf(((Estado) object).getJEST_ID());
        }
        return null;
    }
}
