package com.gardnerdenver.converter;

import com.gardnerdenver.facade.PecaFacade;
import com.gardnerdenver.model.Peca;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = com.gardnerdenver.model.Peca.class)
public class PecaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        PecaFacade fPartFacade = new PecaFacade();
        String partId;

        try {
            partId = arg2;
        } catch (NumberFormatException exception) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a descrição da peça e selecione-a", ""));
        }

        return fPartFacade.findPecaByCod(partId);
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

        if (arg2 == null) {
            return "";
        }
        Peca peca = (Peca) arg2;
        return String.valueOf(peca.getCodigo());
    }
}
