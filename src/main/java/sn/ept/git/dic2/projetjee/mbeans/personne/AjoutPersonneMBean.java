package sn.ept.git.dic2.projetjee.mbeans.personne;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.Personne;
import sn.ept.git.dic2.projetjee.facades.PersonneFacade;

import java.io.Serializable;

@Named("ajoutPersonneMBean")
@ViewScoped
public class AjoutPersonneMBean implements Serializable {
    @EJB
    private PersonneFacade personneFacade;
    private Personne personne = new Personne(56,"NIANG", "Ass", "783452209", "nianga@ept.sn");
    private String message;


    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void save() {
        System.out.println("Enregistrer la personne : " + personne);
        personneFacade.create(personne);
        message ="enregistré avec succès";
        personne = new Personne();

    }
}
