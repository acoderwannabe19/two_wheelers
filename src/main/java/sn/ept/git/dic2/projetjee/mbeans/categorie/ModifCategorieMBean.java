package sn.ept.git.dic2.projetjee.mbeans.categorie;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.Categorie;
import sn.ept.git.dic2.projetjee.facades.CategorieFacade;

import java.io.Serializable;

@Named("modifCategorieMBean")
@ViewScoped
public class ModifCategorieMBean implements Serializable {

    @EJB
    private CategorieFacade categorieFacade;
    private Categorie categorie;
    private String message;

    public ModifCategorieMBean() {
        categorie = new Categorie();
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

    public void loadCategorie(int categorieId) {
        categorie = categorieFacade.find(categorieId);
    }

    public void update() {
        System.out.println("Mettre à jour la categorie : " + categorie);
        categorieFacade.edit(categorie);
        message = "mis à jour avec succès";
        categorie = new Categorie();
    }
}
