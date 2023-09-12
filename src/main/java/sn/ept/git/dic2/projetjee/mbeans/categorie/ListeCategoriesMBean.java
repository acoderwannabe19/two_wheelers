package sn.ept.git.dic2.projetjee.mbeans.categorie;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.Categorie;
import sn.ept.git.dic2.projetjee.facades.CategorieFacade;

import java.io.Serializable;
import java.util.List;

@Named("listeCategoriesMBean")
@ViewScoped
public class ListeCategoriesMBean implements Serializable {
    @EJB
    private CategorieFacade categorieFacade;

    private Categorie categorieChoisie;

    private List<Categorie> categories;

    public CategorieFacade getCategorieFacade() {
        return categorieFacade;
    }

    public void setCategorieFacade(CategorieFacade categorieFacade) {
        this.categorieFacade = categorieFacade;
    }

    public Categorie getCategorieChoisie() {
        return categorieChoisie;
    }

    public void setCategorieChoisie(Categorie categorieChoisie) {
        this.categorieChoisie = categorieChoisie;
    }

    public List<Categorie> getCategories() {
        System.out.println("Liste des categories " + categories);
        if(categories == null) {
            categories = categorieFacade.findAll();
        }
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = ListeCategoriesMBean.this.categories;
    }

}
