package com.gardnerdenver.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.gardnerdenver.facade.ServicoFacade;
import com.gardnerdenver.model.Servico;

@FacesConverter(forClass = com.gardnerdenver.model.Servico.class)
public class ServicoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        ServicoFacade servicoFacade = new ServicoFacade();
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
        Servico serv = (Servico) arg2;
        return String.valueOf(serv.getSRV_ID());
    }
}
