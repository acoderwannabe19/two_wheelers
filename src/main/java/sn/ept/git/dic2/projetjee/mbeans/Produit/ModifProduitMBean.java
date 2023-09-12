package sn.ept.git.dic2.projetjee.mbeans.Produit;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.event.RowEditEvent;
import sn.ept.git.dic2.projetjee.entities.Categorie;
import sn.ept.git.dic2.projetjee.entities.Marque;
import sn.ept.git.dic2.projetjee.entities.Produit;
import sn.ept.git.dic2.projetjee.facades.ProduitFacade;

import java.io.Serializable;
import java.util.List;

@Named("modifProduitMBean")
@ViewScoped
public class ModifProduitMBean implements Serializable {
    @EJB
    private ProduitFacade produitFacade;
    private Produit produit;

    private List<Marque> marques;
    private List<Categorie> categories;
    private Integer selectedMarqueId;
    private Integer selectedCategorieId;

    private String message;

    private List<Produit> produits;




    @PostConstruct
    public void init() {
        produit = new Produit();
        marques = produitFacade.getAllMarques();
        categories = produitFacade.getAllCategories();
        produits = produitFacade.findAll();
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

    public List<Marque> getMarques() {
        return marques;
    }

    public void setMarques(List<Marque> marques) {
        this.marques = marques;
    }

    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

    public Integer getSelectedMarqueId() {
        return selectedMarqueId;
    }

    public void setSelectedMarqueId(Integer selectedMarqueId) {
        this.selectedMarqueId = selectedMarqueId;
    }

    public Integer getSelectedCategorieId() {
        return selectedCategorieId;
    }

    public void setSelectedCategorieId(Integer selectedCategorieId) {
        this.selectedCategorieId = selectedCategorieId;
    }

    public ModifProduitMBean() {
        produit = new Produit();
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


    public void onRowEdit(RowEditEvent<Produit> event) {
        Produit editedProduit = event.getObject();
        produitFacade.edit(editedProduit);
        produits.add(editedProduit);

        FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Produit> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


    public void update() {
        Marque selectedMarque = null;
        Categorie selectedCategorie = null;

        // Find the selected Marque object based on the selectedMarqueId
        for (Marque marque : marques) {
            if (marque.getId().equals(selectedMarqueId)) {
                selectedMarque = marque;
                break;
            }
        }

        // Find the selected Categorie object based on the selectedCategorieId
        for (Categorie categorie : categories) {
            if (categorie.getId().equals(selectedCategorieId)) {
                selectedCategorie = categorie;
                break;
            }
        }

        // Set the selected Marque and Categorie objects in the Produit object
        produit.setMarqueId(selectedMarque);
        produit.setCategorieId(selectedCategorie);

        System.out.println("Mettre à jour la produit : " + produit);
        produitFacade.edit(produit);
        message = "mis à jour avec succès";
        produit = new Produit();

    }





}
