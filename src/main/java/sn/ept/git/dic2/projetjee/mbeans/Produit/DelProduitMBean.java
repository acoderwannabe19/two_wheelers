package sn.ept.git.dic2.projetjee.mbeans.Produit;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import sn.ept.git.dic2.projetjee.entities.Produit;
import sn.ept.git.dic2.projetjee.facades.ProduitFacade;

import java.io.Serializable;
import java.util.List;

@Named("delProduitMBean")
@ViewScoped
public class DelProduitMBean implements Serializable {
    @EJB
    private ProduitFacade produitFacade;
    private Produit produit;
    private String message;
    private Produit selectedProduit;

    private List<Produit> selectedProduits;

    private List<Produit> produits;

    @PostConstruct
    public void init() {
        this.produits = produitFacade.findAll();
    }



    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public ProduitFacade getProduitFacade() {
        return produitFacade;
    }

    public void setProduitFacade(ProduitFacade produitFacade) {
        this.produitFacade = produitFacade;
    }

    public Produit getSelectedProduit() {
        return selectedProduit;
    }

    public void setSelectedProduit(Produit selectedProduit) {
        this.selectedProduit = selectedProduit;
    }

    public List<Produit> getSelectedProduits() {
        return selectedProduits;
    }

    public void setSelectedProduits(List<Produit> selectedProduits) {
        this.selectedProduits = selectedProduits;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void delete(Produit produit) {
        System.out.println("Supprimer la produit : " + produit);
        produitFacade.remove(produit);
        message = "Supprimé avec succès";
    }


    public void deleteProduit() {
        produitFacade.remove(selectedProduit);
        produits.remove(selectedProduit);
        this.selectedProduit = null;

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedProduits()) {
            int size = this.selectedProduits.size();
            return size > 1 ? size + " products selected" : "1 product selected";
        }

        return "Delete";
    }

    public boolean hasSelectedProduits() {
        return this.selectedProduits != null && !this.selectedProduits.isEmpty();
    }

    public void deleteSelectedProduits() {
        for (Produit product : selectedProduits) {
            produitFacade.remove(product);
        }
        produits.removeAll(selectedProduits);
        this.selectedProduits = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }
}
