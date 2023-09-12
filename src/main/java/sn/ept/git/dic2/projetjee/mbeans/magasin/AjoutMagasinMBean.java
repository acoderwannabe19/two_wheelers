package sn.ept.git.dic2.projetjee.mbeans.magasin;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.*;
import sn.ept.git.dic2.projetjee.facades.MagasinFacade;

import java.io.Serializable;

@Named("ajoutMagasinMBean")
@ViewScoped
public class AjoutMagasinMBean implements Serializable {
    @EJB
    private MagasinFacade magasinFacade;

    private Magasin magasin;
    private String message;

    @PostConstruct
    public void init() {
        magasin = new Magasin();
    }


    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void save() {
        System.out.println("Enregistrer la magasin : " + magasin);
        magasinFacade.create(magasin);
        message ="enregistré avec succès";
        magasin = new Magasin();

    }
}
