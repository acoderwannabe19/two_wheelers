package sn.ept.git.dic2.projetjee.converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@FacesConverter("dateConverter")
public class DateConverter implements Converter {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        try {
            return dateFormat.parse(value);
        } catch (ParseException e) {
            throw new ConverterException("Invalid date format. Please use dd/MM/yyyy format.", e);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Date) {
            return dateFormat.format((Date) value);
        } else {
            throw new ConverterException("Invalid value type. Expected java.util.Date.");
        }
    }
}
