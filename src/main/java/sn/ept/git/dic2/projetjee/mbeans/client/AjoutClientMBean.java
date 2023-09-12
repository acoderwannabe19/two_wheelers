package sn.ept.git.dic2.projetjee.mbeans.client;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.Client;
import sn.ept.git.dic2.projetjee.facades.ClientFacade;

import java.io.Serializable;

@Named("ajoutClientMBean")
@ViewScoped
public class AjoutClientMBean implements Serializable {
    @EJB
    private ClientFacade clientFacade;
    private Client client;
    private String message;


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

    public void save() {
        System.out.println("Enregistrer la client : " + client);
        clientFacade.create(client);
        message ="enregistré avec succès";
        client = new Client();

    }
}
