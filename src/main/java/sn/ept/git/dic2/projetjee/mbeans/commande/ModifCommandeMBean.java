package sn.ept.git.dic2.projetjee.mbeans.commande;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.event.RowEditEvent;
import sn.ept.git.dic2.projetjee.entities.*;
import sn.ept.git.dic2.projetjee.facades.CommandeFacade;

import java.io.Serializable;
import java.util.List;

@Named("modifCommandeMBean")
@ViewScoped
public class ModifCommandeMBean implements Serializable {

    @EJB
    private CommandeFacade commandeFacade;

    private Commande commande;
    private List<Magasin> magasins;
    private List<Client> clients;

    private List<Employe> employes;
    private Integer selectedMagasinId;
    private Integer selectedClientId;
    private Integer selectedEmployeId;

    private String message;

    private List<Commande> commandes;

    @PostConstruct
    public void init() {
        commande = new Commande();
        magasins = commandeFacade.getAllMagasins();
        clients = commandeFacade.getAllClients();
        employes = commandeFacade.getAllEmployes();
        commandes = commandeFacade.findAll();
    }

    public void update() {
        Magasin selectedMagasin = null;
        Client selectedClient = null;
        Employe selectedEmploye = null;

        // Find the selected Magasin object based on the selectedMagasinId
        for (Magasin magasin : magasins) {
            if (magasin.getId().equals(selectedMagasinId)) {
                selectedMagasin = magasin;
                break;
            }
        }

        // Find the selected Client object based on the selectedClientId
        for (Client client : clients) {
            if (client.getId().equals(selectedClientId)) {
                selectedClient = client;
                break;
            }
        }

        for (Employe employe : employes) {
            if (employe.getId().equals(selectedEmployeId)) {
                selectedEmploye = employe;
                break;
            }
        }

        // Set the selected Magasin and Client objects in the Commande object
        commande.setMagasinId(selectedMagasin);
        commande.setClientId(selectedClient);
        commande.setVendeurId(selectedEmploye);

        // Save the commande object
        commandeFacade.edit(commande);

        // Reset the form
        commande = new Commande();
        selectedMagasinId = null;
        selectedClientId = null;
        selectedEmployeId = null;
    }

    public void onRowEdit(RowEditEvent<Commande> event) {
        Commande editedCommande = event.getObject();
        commandeFacade.edit(editedCommande);
        commandes.add(editedCommande);

        FacesMessage msg = new FacesMessage("Order Edited", String.valueOf(event.getObject().getNumero()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Commande> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getNumero()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    // Getters and setters


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public CommandeFacade getCommandeFacade() {
        return commandeFacade;
    }

    public void setCommandeFacade(CommandeFacade commandeFacade) {
        this.commandeFacade = commandeFacade;
    }

    public List<Magasin> getMagasins() {
        return magasins;
    }

    public void setMagasins(List<Magasin> magasins) {
        this.magasins = magasins;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Integer getSelectedMagasinId() {
        return selectedMagasinId;
    }

    public void setSelectedMagasinId(Integer selectedMagasinId) {
        this.selectedMagasinId = selectedMagasinId;
    }

    public Integer getSelectedClientId() {
        return selectedClientId;
    }

    public void setSelectedClientId(Integer selectedClientId) {
        this.selectedClientId = selectedClientId;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public Integer getSelectedEmployeId() {
        return selectedEmployeId;
    }

    public void setSelectedEmployeId(Integer selectedEmployeId) {
        this.selectedEmployeId = selectedEmployeId;
    }

}
