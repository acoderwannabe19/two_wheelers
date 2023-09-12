package sn.ept.git.dic2.projetjee.mbeans.marque;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.Marque;
import sn.ept.git.dic2.projetjee.facades.MarqueFacade;

import java.io.Serializable;

@Named("ajoutMarqueMBean")
@ViewScoped
public class AjoutMarqueMBean implements Serializable {

    @EJB
    private MarqueFacade marqueFacade;
    private Marque marque = new Marque();
    private String message;


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

    public void save() {
        System.out.println("Enregistrer la marque : " + marque);
        marqueFacade.create(marque);
        message ="enregistré avec succès";
        marque = new Marque();

    }
}
