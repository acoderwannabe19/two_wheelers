package sn.ept.git.dic2.projetjee.converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import sn.ept.git.dic2.projetjee.entities.Magasin;
import sn.ept.git.dic2.projetjee.facades.EmployeFacade;


@FacesConverter(value="magasinConverter",forClass = Magasin.class)
public class MagasinConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                EmployeFacade employeFacade = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{modifEmpMBean.employeFacade}", EmployeFacade.class);
                return employeFacade.findMagasinById(Long.parseLong(value));
            } catch (NumberFormatException e) {
                // Handle conversion error
                throw new IllegalArgumentException("Invalid value: " + value + " for Magasin conversion", e);
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Magasin) {
            return String.valueOf(((Magasin) value).getId());
        }
        return null;
    }
}