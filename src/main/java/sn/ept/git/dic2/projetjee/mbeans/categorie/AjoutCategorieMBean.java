package sn.ept.git.dic2.projetjee.mbeans.categorie;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.Categorie;
import sn.ept.git.dic2.projetjee.facades.CategorieFacade;

import java.io.Serializable;

@Named("ajoutCategorieMBean")
@ViewScoped
public class AjoutCategorieMBean implements Serializable {

    @EJB
    private CategorieFacade categorieFacade;
    private Categorie categorie = new Categorie();
    private String message;


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

    public void save() {
        System.out.println("Enregistrer la categorie : " + categorie);
        categorieFacade.create(categorie);
        message ="enregistré avec succès";
        categorie = new Categorie();

    }
}
