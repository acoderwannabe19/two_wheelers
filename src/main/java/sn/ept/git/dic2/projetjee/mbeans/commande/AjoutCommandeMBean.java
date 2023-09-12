package sn.ept.git.dic2.projetjee.mbeans.commande;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.Client;
import sn.ept.git.dic2.projetjee.entities.Employe;
import sn.ept.git.dic2.projetjee.entities.Magasin;
import sn.ept.git.dic2.projetjee.entities.Commande;
import sn.ept.git.dic2.projetjee.facades.CommandeFacade;

import java.io.Serializable;
import java.util.List;

@Named("ajoutCommandeMBean")
@ViewScoped
public class AjoutCommandeMBean implements Serializable {

    @EJB
    private CommandeFacade commandeFacade;

    private Commande commande;
    private List<Magasin> magasins;
    private List<Client> clients;

    private List<Employe> employes;
    private Integer selectedMagasinId;
    private Integer selectedClientId;
    private Integer selectedEmployeId;

    @PostConstruct
    public void init() {
        commande = new Commande();
        magasins = commandeFacade.getAllMagasins();
        clients = commandeFacade.getAllClients();
        employes = commandeFacade.getAllEmployes();
    }

    public void save() {
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
        commandeFacade.create(commande);

        // Reset the form
        commande = new Commande();
        selectedMagasinId = null;
        selectedClientId = null;
        selectedEmployeId = null;
    }

    // Getters and setters

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
