package com.gardnerdenver.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.gardnerdenver.facade.ParceiroFacade;
import com.gardnerdenver.model.Parceiro;

@FacesConverter(value = "parceiroConverter", forClass = com.gardnerdenver.model.Parceiro.class)
//@FacesConverter("parceiroConverter")
public class ParceiroConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        ParceiroFacade parceiroFacade = new ParceiroFacade();
        int parId;

        try {
            parId = Integer.parseInt(arg2);
        } catch (NumberFormatException exception) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome de um cliente e selecione-o", "Digite o nome de um cliente e selecione-o"));
        }

        return parceiroFacade.findParceiro(parId);
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
//
//        if (arg2 == null) {
//            return "";
//        }
//        Parceiro par = (Parceiro) arg2;
//        return String.valueOf(par.getPAR_ID());

        if (arg2 != null) {
            return String.valueOf(((Parceiro) arg2).getPAR_ID());
        } else {
            return null;
        }
    }
}
