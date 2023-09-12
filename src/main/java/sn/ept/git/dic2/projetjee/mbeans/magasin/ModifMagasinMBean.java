package sn.ept.git.dic2.projetjee.mbeans.magasin;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.event.RowEditEvent;
import sn.ept.git.dic2.projetjee.entities.Magasin;
import sn.ept.git.dic2.projetjee.facades.MagasinFacade;

import java.io.Serializable;
import java.util.List;

@Named("modifMagasinMBean")
@ViewScoped
public class ModifMagasinMBean implements Serializable {
    @EJB
    private MagasinFacade magasinFacade;
    private Magasin magasin;
    private String message;
    private List<Magasin> magasins;

    @PostConstruct
    public void init() {
        magasin = new Magasin();
        magasins = magasinFacade.findAll();
    }

    public ModifMagasinMBean() {
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

    public MagasinFacade getMagasinFacade() {
        return magasinFacade;
    }

    public void setMagasinFacade(MagasinFacade magasinFacade) {
        this.magasinFacade = magasinFacade;
    }

    public List<Magasin> getMagasins() {
        return magasins;
    }

    public void setMagasins(List<Magasin> magasins) {
        this.magasins = magasins;
    }

    public void update() {
        System.out.println("Mettre à jour la magasin : " + magasin);
        magasinFacade.edit(magasin);
        message = "mis à jour avec succès";
        magasin = new Magasin();
    }

    public void onRowEdit(RowEditEvent<Magasin> event) {
        Magasin editedMagasin = event.getObject();
        magasinFacade.edit(editedMagasin);
        magasins.add(editedMagasin);

        FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Magasin> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
