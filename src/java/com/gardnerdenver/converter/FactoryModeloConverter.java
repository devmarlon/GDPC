package com.gardnerdenver.converter;

import com.gardnerdenver.facade.FactoryModeloFacade;
import com.gardnerdenver.model.FactoryModelo;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = com.gardnerdenver.model.FactoryModelo.class)
public class FactoryModeloConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        FactoryModeloFacade modFacase = new FactoryModeloFacade();
        String partId;

        try {
            partId = arg2;
        } catch (NumberFormatException exception) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a descrição da peça e selecione-a", ""));
        }

        return modFacase.findModelo(Integer.valueOf(partId));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

        if (arg2 == null) {
            return "";
        }
        FactoryModelo modelo = (FactoryModelo) arg2;
        return String.valueOf(modelo.getMOD_ID());
    }
}
