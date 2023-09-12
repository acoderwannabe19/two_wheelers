package sn.ept.git.dic2.projetjee.converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import sn.ept.git.dic2.projetjee.entities.Employe;
import sn.ept.git.dic2.projetjee.facades.EmployeFacade;

@FacesConverter(value="employeConverter",forClass = Employe.class)
public class EmployeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                EmployeFacade employeFacade = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{modifEmpMBean.employeFacade}", EmployeFacade.class);
                return employeFacade.findManagerById(Long.parseLong(value));
            } catch (NumberFormatException e) {
                // Handle conversion error
                throw new IllegalArgumentException("Invalid value: " + value + " for Employe conversion", e);
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Employe) {
            return String.valueOf(((Employe) value).getId());
        }
        return null;
    }
}