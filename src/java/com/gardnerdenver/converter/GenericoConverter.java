package com.gardnerdenver.converter;

import com.gardnerdenver.bean.GenericoBean;
import com.gardnerdenver.model.Generico;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = com.gardnerdenver.model.Generico.class)
public class GenericoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String ID) throws ConverterException {
        if ((ID != null) && (!ID.equals("")) && (!ID.equals("Selecione")) && (!ID.equals("Todos"))) {
            GenericoBean estBean = new GenericoBean();
            List<Generico> listGen = estBean.getLista();
            //
            int idInt = Integer.valueOf(ID);
            for (Generico listGen1 : listGen) {
                if (listGen1.getGEN_ID() == idInt) {
                    return listGen1;
                }
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) throws ConverterException {
        if (object != null && object instanceof Generico) {
            return String.valueOf(((Generico) object).getGEN_ID());
        }
        return null;
    }
}
