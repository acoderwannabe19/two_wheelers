package sn.ept.git.dic2.projetjee.mbeans.personne;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.Personne;
import sn.ept.git.dic2.projetjee.facades.PersonneFacade;

import java.io.Serializable;
import java.util.List;

@Named("listePersonnesMBean")
@ViewScoped
public class ListePersonnesMBean implements Serializable {
    @EJB
    private PersonneFacade personneFacade;

    private Personne personneChoisie;

    private List<Personne> personnes;

    public PersonneFacade getPersonneFacade() {
        return personneFacade;
    }

    public void setPersonneFacade(PersonneFacade personneFacade) {
        this.personneFacade = personneFacade;
    }

    public Personne getPersonneChoisie() {
        return personneChoisie;
    }

    public void setPersonneChoisie(Personne personneChoisie) {
        this.personneChoisie = personneChoisie;
    }

    public List<Personne> getPersonnes() {
        System.out.println("Liste des personnes " + personnes);
        if(personnes == null) {
            personnes = personneFacade.findAll();
        }
        return personnes;
    }

    public void setPersonnes(List<Personne> personnes) {
        this.personnes = ListePersonnesMBean.this.personnes;
    }
}



