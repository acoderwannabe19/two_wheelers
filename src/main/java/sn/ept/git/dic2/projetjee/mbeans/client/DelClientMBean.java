package sn.ept.git.dic2.projetjee.mbeans.client;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import sn.ept.git.dic2.projetjee.entities.Client;
import sn.ept.git.dic2.projetjee.facades.ClientFacade;

import java.io.Serializable;
import java.util.List;

@Named("delClientMBean")
@ViewScoped
public class DelClientMBean implements Serializable {
    @EJB
    private ClientFacade clientFacade;
    private Client client;
    private String message;

    private Client selectedClient;

    private List<Client> selectedClients;

    private List<Client> clients;

    @PostConstruct
    public void init() {
        this.clients = clientFacade.findAll();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ClientFacade getClientFacade() {
        return clientFacade;
    }

    public void setClientFacade(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    public Client getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
    }

    public List<Client> getSelectedClients() {
        return selectedClients;
    }

    public void setSelectedClients(List<Client> selectedClients) {
        this.selectedClients = selectedClients;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void delete(Client client) {
        System.out.println("Supprimer la client : " + client);
        clientFacade.remove(client);
        message = "Supprimé avec succès";
    }
    public void deleteClient() {
        clientFacade.remove(selectedClient);
        clients.remove(selectedClient);
        this.selectedClient = null;

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Client Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-clients");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedClients()) {
            int size = this.selectedClients.size();
            return size > 1 ? size + " clients selected" : "1 client selected";
        }

        return "Delete";
    }

    public boolean hasSelectedClients() {
        return this.selectedClients != null && !this.selectedClients.isEmpty();
    }

    public void deleteSelectedClients() {
        for (Client client : selectedClients) {
            clientFacade.remove(client);
        }
        clients.removeAll(selectedClients);
        this.selectedClients = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Clients Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-clients");
        PrimeFaces.current().executeScript("PF('dtClients').clearFilters()");
    }
}
