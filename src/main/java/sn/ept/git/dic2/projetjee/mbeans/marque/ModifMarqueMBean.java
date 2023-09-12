package sn.ept.git.dic2.projetjee.mbeans.marque;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.Marque;
import sn.ept.git.dic2.projetjee.facades.MarqueFacade;

import java.io.Serializable;

@Named("modifMarqueMBean")
@ViewScoped
public class ModifMarqueMBean implements Serializable {

    @EJB
    private MarqueFacade marqueFacade;
    private Marque marque;
    private String message;

    public ModifMarqueMBean() {
        marque = new Marque();
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void loadMarque(int marqueId) {
        marque = marqueFacade.find(marqueId);
    }

    public void update() {
        System.out.println("Mettre à jour la marque : " + marque);
        marqueFacade.edit(marque);
        message = "mis à jour avec succès";
        marque = new Marque();
    }
}
