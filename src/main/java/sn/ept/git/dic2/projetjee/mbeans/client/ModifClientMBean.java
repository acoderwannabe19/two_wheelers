package sn.ept.git.dic2.projetjee.mbeans.client;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.event.RowEditEvent;
import sn.ept.git.dic2.projetjee.entities.Client;
import sn.ept.git.dic2.projetjee.facades.ClientFacade;

import java.io.Serializable;
import java.util.List;

@Named("modifClientMBean")
@ViewScoped
public class ModifClientMBean implements Serializable {

    //AvecFormulaire
    @EJB
    private ClientFacade clientFacade;
    private Client client;
    private String message;
    private List<Client> clients;

    @PostConstruct
    public void init() {
        client = new Client();
        clients = clientFacade.findAll();
    }

    public ModifClientMBean() {
        client = new Client();
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

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void update() {
        System.out.println("Mettre à jour la client : " + client);
        clientFacade.edit(client);
        message = "mis à jour avec succès";
        client = new Client();
    }

    public void onRowEdit(RowEditEvent<Client> event) {
        Client editedClient = event.getObject();
        clientFacade.edit(editedClient);
        clients.add(editedClient);

        FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Client> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
