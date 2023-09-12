package sn.ept.git.dic2.projetjee.mbeans.commande;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.Commande;
import sn.ept.git.dic2.projetjee.facades.CommandeFacade;

import java.io.Serializable;
import java.util.List;

@Named("listeCommandesMBean")
@ViewScoped
public class ListeCommandesMBean implements Serializable {

    @EJB
    private CommandeFacade commandeFacade;

    private Commande commandeChoisi;

    private List<Commande> commandes;

    public CommandeFacade getCommandeFacade() {
        return commandeFacade;
    }

    public void setCommandeFacade(CommandeFacade commandeFacade) {
        this.commandeFacade = commandeFacade;
    }

    public Commande getCommandeChoisi() {
        return commandeChoisi;
    }

    public void setCommandeChoisi(Commande commandeChoisi) {
        this.commandeChoisi = commandeChoisi;
    }

    public List<Commande> getCommandes() {
        System.out.println("Liste des commandes " + commandes);
        if(commandes == null) {
            commandes = commandeFacade.findAll();
        }
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = ListeCommandesMBean.this.commandes;
    }

}
