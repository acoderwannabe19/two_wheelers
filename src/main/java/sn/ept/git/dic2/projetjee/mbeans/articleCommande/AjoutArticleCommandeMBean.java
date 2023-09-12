package sn.ept.git.dic2.projetjee.mbeans.articleCommande;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.Commande;
import sn.ept.git.dic2.projetjee.entities.Produit;
import sn.ept.git.dic2.projetjee.entities.ArticleCommande;
import sn.ept.git.dic2.projetjee.facades.ArticleCommandeFacade;

import java.io.Serializable;
import java.util.List;

@Named("ajoutArticleCommandeMBean")
@ViewScoped
public class AjoutArticleCommandeMBean implements Serializable {

    @EJB
    private ArticleCommandeFacade articleCommandeFacade;

    private ArticleCommande articleCommande;
    private List<Produit> produits;
    private List<Commande> commandes;
    private Integer selectedProduitId;
    private Integer selectedCommandeId;

    @PostConstruct
    public void init() {
        articleCommande = new ArticleCommande();
        produits = articleCommandeFacade.getAllProduits();
        commandes = articleCommandeFacade.getAllCommandes();
    }

    public void save() {
        Produit selectedProduit = null;
        Commande selectedCommande = null;

        // Find the selected Produit object based on the selectedProduitId
        for (Produit produit : produits) {
            if (produit.getId().equals(selectedProduitId)) {
                selectedProduit = produit;
                break;
            }
        }

        // Find the selected Commande object based on the selectedCommandeId
        for (Commande commande : commandes) {
            if (commande.getNumero().equals(selectedCommandeId)) {
                selectedCommande = commande;
                break;
            }
        }

        // Set the selected Produit and Commande objects in the ArticleCommande object
        articleCommande.setProduitId(selectedProduit);
        articleCommande.setNumeroCommande(selectedCommande);

        // Save the articleCommande object
        articleCommandeFacade.create(articleCommande);

        // Reset the form
        articleCommande = new ArticleCommande();
        selectedProduitId = null;
        selectedCommandeId = null;
    }

    // Getters and setters

    public ArticleCommande getArticleCommande() {
        return articleCommande;
    }

    public void setArticleCommande(ArticleCommande articleCommande) {
        this.articleCommande = articleCommande;
    }

    public ArticleCommandeFacade getArticleCommandeFacade() {
        return articleCommandeFacade;
    }

    public void setArticleCommandeFacade(ArticleCommandeFacade articleCommandeFacade) {
        this.articleCommandeFacade = articleCommandeFacade;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public Integer getSelectedProduitId() {
        return selectedProduitId;
    }

    public void setSelectedProduitId(Integer selectedProduitId) {
        this.selectedProduitId = selectedProduitId;
    }

    public Integer getSelectedCommandeId() {
        return selectedCommandeId;
    }

    public void setSelectedCommandeId(Integer selectedCommandeId) {
        this.selectedCommandeId = selectedCommandeId;
    }

}
