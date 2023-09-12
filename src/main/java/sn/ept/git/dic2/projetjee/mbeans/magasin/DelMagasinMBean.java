package sn.ept.git.dic2.projetjee.mbeans.magasin;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import sn.ept.git.dic2.projetjee.entities.Magasin;
import sn.ept.git.dic2.projetjee.facades.MagasinFacade;

import java.io.Serializable;
import java.util.List;

@Named("delMagasinMBean")
@ViewScoped
public class DelMagasinMBean implements Serializable {
    @EJB
    private MagasinFacade magasinFacade;
    private Magasin magasin;
    private String message;

    private Magasin selectedMagasin;

    private List<Magasin> selectedMagasins;

    private List<Magasin> magasins;

    @PostConstruct
    public void init() {
        this.magasins = magasinFacade.findAll();
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

    public Magasin getSelectedMagasin() {
        return selectedMagasin;
    }

    public void setSelectedMagasin(Magasin selectedMagasin) {
        this.selectedMagasin = selectedMagasin;
    }

    public List<Magasin> getSelectedMagasins() {
        return selectedMagasins;
    }

    public void setSelectedMagasins(List<Magasin> selectedMagasins) {
        this.selectedMagasins = selectedMagasins;
    }

    public List<Magasin> getMagasins() {
        return magasins;
    }

    public void setMagasins(List<Magasin> magasins) {
        this.magasins = magasins;
    }

    public void delete(Magasin magasin) {
        System.out.println("Supprimer la magasin : " + magasin);
        magasinFacade.remove(magasin);
        message = "Supprimé avec succès";
    }

    public void deleteMagasin() {
        magasinFacade.remove(selectedMagasin);
        magasins.remove(selectedMagasin);
        this.selectedMagasin = null;

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Store Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-magasins");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedMagasins()) {
            int size = this.selectedMagasins.size();
            return size > 1 ? size + " stores selected" : "1 store selected";
        }

        return "Delete";
    }

    public boolean hasSelectedMagasins() {
        return this.selectedMagasins != null && !this.selectedMagasins.isEmpty();
    }

    public void deleteSelectedMagasins() {
        for (Magasin magasin : selectedMagasins) {
            magasinFacade.remove(magasin);
        }
        magasins.removeAll(selectedMagasins);
        this.selectedMagasins = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Magasins Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-magasins");
        PrimeFaces.current().executeScript("PF('dtMagasins').clearFilters()");
    }
}
