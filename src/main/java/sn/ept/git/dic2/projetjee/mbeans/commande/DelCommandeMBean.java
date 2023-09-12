package sn.ept.git.dic2.projetjee.mbeans.commande;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import sn.ept.git.dic2.projetjee.entities.Commande;
import sn.ept.git.dic2.projetjee.facades.CommandeFacade;

import java.io.Serializable;
import java.util.List;

@Named("delCommandeMBean")
@ViewScoped
public class DelCommandeMBean implements Serializable {
    @EJB
    private CommandeFacade commandeFacade;
    private Commande commande;
    private String message;

    private Commande selectedCommande;

    private List<Commande> selectedCommandes;

    private List<Commande> commandes;

    @PostConstruct
    public void init() {
        this.commandes = commandeFacade.findAll();
    }

    public Commande getSelectedCommande() {
        return selectedCommande;
    }

    public void setSelectedCommande(Commande selectedCommande) {
        this.selectedCommande = selectedCommande;
    }

    public List<Commande> getSelectedCommandes() {
        return selectedCommandes;
    }

    public void setSelectedCommandes(List<Commande> selectedCommandes) {
        this.selectedCommandes = selectedCommandes;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void delete(Commande commande) {
        System.out.println("Supprimer la commande : " + commande);
        commandeFacade.remove(commande);
        message = "Supprimé avec succès";

    }

    public void deleteCommande() {
        commandeFacade.remove(selectedCommande);
        commandes.remove(selectedCommande);
        this.selectedCommande = null;

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Order Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-commandes");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedCommandes()) {
            int size = this.selectedCommandes.size();
            return size > 1 ? size + " commandes selected" : "1 commande selected";
        }

        return "Delete";
    }

    public boolean hasSelectedCommandes() {
        return this.selectedCommandes != null && !this.selectedCommandes.isEmpty();
    }

    public void deleteSelectedCommandes() {
        for (Commande commande : selectedCommandes) {
            commandeFacade.remove(commande);
        }
        commandes.removeAll(selectedCommandes);
        this.selectedCommandes = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Orders Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-commandes");
        PrimeFaces.current().executeScript("PF('dtCommandes').clearFilters()");
    }

    public CommandeFacade getCommandeFacade() {
        return commandeFacade;
    }

    public void setCommandeFacade(CommandeFacade commandeFacade) {
        this.commandeFacade = commandeFacade;
    }

}
