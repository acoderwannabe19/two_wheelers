package sn.ept.git.dic2.projetjee.converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import sn.ept.git.dic2.projetjee.entities.Produit;
import sn.ept.git.dic2.projetjee.facades.ArticleCommandeFacade;

@FacesConverter(value="produitConverter",forClass = Produit.class)
public class ProduitConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                ArticleCommandeFacade articleCommandeFacade = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{modifArticleCommandeMBean.articleCommandeFacade}", ArticleCommandeFacade.class);
                return articleCommandeFacade.findProduitById(Long.parseLong(value));
            } catch (NumberFormatException e) {
                // Handle conversion error
                throw new IllegalArgumentException("Invalid value: " + value + " for product conversion", e);
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Produit) {
            return String.valueOf(((Produit) value).getId());
        }
        return null;
    }
}