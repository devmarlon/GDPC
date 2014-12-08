package com.gardnerdenver.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.gardnerdenver.facade.MunicipioFacade;
import com.gardnerdenver.model.Municipio;

@FacesConverter(forClass = com.gardnerdenver.model.Municipio.class)
public class MunicipioConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        MunicipioFacade munfacade = new MunicipioFacade();
        int munId;

        try {
            munId = Integer.parseInt(arg2);
        } catch (NumberFormatException exception) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escolha a cidade", "Type the name of a Municipio and select it (or use the dropdow)"));
        }

        return munfacade.findMunicipio(munId);
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

        if (arg2 == null) {
            return "";
        }
        Municipio municipio = (Municipio) arg2;
        return String.valueOf(municipio.getMUN_ID());
    }
}
