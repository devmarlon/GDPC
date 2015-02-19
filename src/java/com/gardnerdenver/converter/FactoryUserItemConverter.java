package com.gardnerdenver.converter;

import com.gardnerdenver.facade.UserItemFactoryFacade;
import com.gardnerdenver.model.FactoryUserItem;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = com.gardnerdenver.model.FactoryUserItem.class)
public class FactoryUserItemConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        UserItemFactoryFacade funcionarioFacade = new UserItemFactoryFacade();
        int funId;

        try {
            funId = Integer.parseInt(arg2);
        } catch (NumberFormatException exception) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome de um funcionário e selecione-o", "Digite o nome de um funcionário e selecione-o"));
        }

        return funcionarioFacade.findUsi(funId);
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

        if (arg2 == null) {
            return "";
        }
        FactoryUserItem func = (FactoryUserItem) arg2;
        return String.valueOf(func.getUSI_ID());
    }
}
