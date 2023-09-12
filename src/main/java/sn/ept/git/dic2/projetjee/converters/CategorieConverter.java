package sn.ept.git.dic2.projetjee.converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import sn.ept.git.dic2.projetjee.entities.Categorie;
import sn.ept.git.dic2.projetjee.facades.ProduitFacade;

@FacesConverter(value="categorieConverter",forClass = Categorie.class)
public class CategorieConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                ProduitFacade produitFacade = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{modifProduitMBean.produitFacade}", ProduitFacade.class);
                return produitFacade.findCategorieById(Long.parseLong(value));
            } catch (NumberFormatException e) {
                // Handle conversion error
                throw new IllegalArgumentException("Invalid value: " + value + " for category conversion", e);
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Categorie) {
            return String.valueOf(((Categorie) value).getId());
        }
        return null;
    }
}