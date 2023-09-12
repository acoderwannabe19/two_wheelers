package sn.ept.git.dic2.projetjee.mbeans.marque;


import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.Marque;
import sn.ept.git.dic2.projetjee.facades.MarqueFacade;

import java.io.Serializable;
import java.util.List;

@Named("listeMarquesMBean")
@ViewScoped
public class ListeMarquesMBean implements Serializable {

    @EJB
    private MarqueFacade marqueFacade;

    private Marque marqueChoisie;

    private List<Marque> marques;

    public MarqueFacade getMarqueFacade() {
        return marqueFacade;
    }

    public void setMarqueFacade(MarqueFacade marqueFacade) {
        this.marqueFacade = marqueFacade;
    }

    public Marque getMarqueChoisie() {
        return marqueChoisie;
    }

    public void setMarqueChoisie(Marque marqueChoisie) {
        this.marqueChoisie = marqueChoisie;
    }

    public List<Marque> getMarques() {
        System.out.println("Liste des marques " + marques);
        if(marques == null) {
            marques = marqueFacade.findAll();
        }
        return marques;
    }

    public void setMarques(List<Marque> marques) {
        this.marques = ListeMarquesMBean.this.marques;
    }

}
