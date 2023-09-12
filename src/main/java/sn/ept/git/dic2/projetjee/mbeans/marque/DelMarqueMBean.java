package sn.ept.git.dic2.projetjee.mbeans.marque;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import sn.ept.git.dic2.projetjee.entities.Marque;
import sn.ept.git.dic2.projetjee.facades.MarqueFacade;

import java.io.Serializable;
import java.util.List;

@Named("delMarqueMBean")
@ViewScoped
public class DelMarqueMBean implements Serializable {

    @EJB
    private MarqueFacade marqueFacade;
    private Marque marque;
    private String message;
    private Marque selectedMarque;
    private List<Marque> selectedMarques;
    private List<Marque> marques;

    @PostConstruct
    public void init() {
        this.marques = marqueFacade.findAll();
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

    public Marque getSelectedMarque() {
        return selectedMarque;
    }

    public void setSelectedMarque(Marque selectedMarque) {
        this.selectedMarque = selectedMarque;
    }

    public List<Marque> getSelectedMarques() {
        return selectedMarques;
    }

    public void setSelectedMarques(List<Marque> selectedMarques) {
        this.selectedMarques = selectedMarques;
    }

    public List<Marque> getMarques() {
        return marques;
    }

    public void setMarques(List<Marque> marques) {
        this.marques = marques;
    }

    public void delete(Marque marque) {
        System.out.println("Supprimer la marque : " + marque);
        marqueFacade.remove(marque);
        marques.remove(marque);
        message = "Supprimé avec succès";
    }

    public void deleteMarque() {
        marqueFacade.remove(selectedMarque);
        marques.remove(selectedMarque);
        this.selectedMarque = null;

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Brand Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dv-marques");
    }


}
