package sn.ept.git.dic2.projetjee.mbeans.client;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.Client;
import sn.ept.git.dic2.projetjee.facades.ClientFacade;

import java.io.Serializable;
import java.util.List;

@Named("listeClientsMBean")
@ViewScoped
public class ListeClientsMBean implements Serializable {
    @EJB
    private ClientFacade clientFacade;

    private Client clientChoisie;

    private List<Client> clients;

    public ClientFacade getClientFacade() {
        return clientFacade;
    }

    public void setClientFacade(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    public Client getClientChoisie() {
        return clientChoisie;
    }

    public void setClientChoisie(Client clientChoisie) {
        this.clientChoisie = clientChoisie;
    }

    public List<Client> getClients() {
        System.out.println("Liste des clients " + clients);
        if(clients == null) {
            clients = clientFacade.findAll();
        }
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = ListeClientsMBean.this.clients;
    }

}
