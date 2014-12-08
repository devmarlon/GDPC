package com.gardnerdenver.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.gardnerdenver.facade.FactoryServicoFacade;
import com.gardnerdenver.model.FactoryServico;

@FacesConverter(value = "fsConverter", forClass = com.gardnerdenver.model.FactoryServico.class)
public class FactoryServicoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        FactoryServicoFacade servicoFacade = new FactoryServicoFacade();
        int servId;

        try {
            servId = Integer.parseInt(arg2);
        } catch (NumberFormatException exception) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a descrição do serviço e selecione-o", "Digite a descrição do serviço e selecione-o"));
        }

        return servicoFacade.findServico(servId);
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

        if (arg2 == null) {
            return "";
        }
        FactoryServico serv = (FactoryServico) arg2;
        return String.valueOf(serv.getSRV_ID());
    }
}
