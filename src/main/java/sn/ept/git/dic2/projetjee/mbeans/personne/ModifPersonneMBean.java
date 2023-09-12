package sn.ept.git.dic2.projetjee.mbeans.personne;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.Personne;
import sn.ept.git.dic2.projetjee.facades.PersonneFacade;

import java.io.Serializable;

@Named("modifPersonneMBean")
@ViewScoped
public class ModifPersonneMBean implements Serializable {


//    AvecFormulaire
    @EJB
    private PersonneFacade personneFacade;
    private Personne personne;
    private String message;

    public ModifPersonneMBean() {
        personne = new Personne();
    }

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

    public void loadPersonne(int personneId) {
        personne = personneFacade.find(personneId);
    }

    public void update() {
        System.out.println("Mettre à jour la personne : " + personne);
        personneFacade.edit(personne);
        message = "mis à jour avec succès";
        personne = new Personne();
    }
}
