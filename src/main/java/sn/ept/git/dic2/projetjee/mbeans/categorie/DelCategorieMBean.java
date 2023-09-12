package sn.ept.git.dic2.projetjee.mbeans.categorie;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import sn.ept.git.dic2.projetjee.entities.Categorie;
import sn.ept.git.dic2.projetjee.facades.CategorieFacade;

import java.io.Serializable;
import java.util.List;

@Named("delCategorieMBean")
@ViewScoped
public class DelCategorieMBean implements Serializable {

    @EJB
    private CategorieFacade categorieFacade;
    private Categorie categorie;
    private String message;
    private Categorie selectedCategorie;
    private List<Categorie> selectedCategories;
    private List<Categorie> categories;

    @PostConstruct
    public void init() {
        this.categories = categorieFacade.findAll();
    }
    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Categorie getSelectedCategorie() {
        return selectedCategorie;
    }

    public void setSelectedCategorie(Categorie selectedCategorie) {
        this.selectedCategorie = selectedCategorie;
    }

    public List<Categorie> getSelectedCategories() {
        return selectedCategories;
    }

    public void setSelectedCategories(List<Categorie> selectedCategories) {
        this.selectedCategories = selectedCategories;
    }

    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

    public void delete(Categorie categorie) {
        System.out.println("Supprimer la categorie : " + categorie);
        categorieFacade.remove(categorie);
        categories.remove(categorie);
        message = "Supprimé avec succès";
    }

    public void deleteCategorie() {
        categorieFacade.remove(selectedCategorie);
        categories.remove(selectedCategorie);
        this.selectedCategorie = null;

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Brand Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dv-categories");
    }


}
