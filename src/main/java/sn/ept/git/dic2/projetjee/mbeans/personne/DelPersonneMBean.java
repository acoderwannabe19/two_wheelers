package sn.ept.git.dic2.projetjee.mbeans.personne;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.Personne;
import sn.ept.git.dic2.projetjee.facades.PersonneFacade;

import java.io.Serializable;

@Named("delPersonneMBean")
@ViewScoped
public class DelPersonneMBean implements Serializable {
    @EJB
    private PersonneFacade personneFacade;
    private Personne personne;
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

    public void delete(Personne personne) {
        System.out.println("Supprimer la personne : " + personne);
        personneFacade.remove(personne);
        message = "Supprimé avec succès";


    }

}
