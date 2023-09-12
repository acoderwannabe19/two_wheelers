package sn.ept.git.dic2.projetjee.converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import sn.ept.git.dic2.projetjee.entities.Client;
import sn.ept.git.dic2.projetjee.facades.CommandeFacade;

@FacesConverter(value="clientConverter",forClass = Client.class)
public class ClientConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                CommandeFacade commandeFacade = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{modifCommandeMBean.commandeFacade}", CommandeFacade.class);
                return commandeFacade.findClientById(Long.parseLong(value));
            } catch (NumberFormatException e) {
                // Handle conversion error
                throw new IllegalArgumentException("Invalid value: " + value + " for Client conversion", e);
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Client) {
            return String.valueOf(((Client) value).getId());
        }
        return null;
    }
}