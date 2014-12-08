package com.gardnerdenver.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.gardnerdenver.facade.EquipamentoFacade;
import com.gardnerdenver.model.Equipamento;

@FacesConverter(value = "equipamentoConverter", forClass = com.gardnerdenver.model.Equipamento.class)
public class EquipamentoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        EquipamentoFacade equipamentoFacade = new EquipamentoFacade();
        int eqpId;

        try {
            eqpId = Integer.parseInt(arg2);
        } catch (NumberFormatException exception) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione o equipamento", ""));
        }

        return equipamentoFacade.findEquipamento(eqpId);
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

//        if (arg2 == null) {
//            return "";
//        }
//        Equipamento eqp = (Equipamento) arg2;
//        return String.valueOf(eqp.getEQP_ID());
        if (arg2 != null) {
            return String.valueOf(((Equipamento) arg2).getEQP_ID());
        } else {
            return null;
        }
    }
}
