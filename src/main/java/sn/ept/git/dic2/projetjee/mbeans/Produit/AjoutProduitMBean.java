package sn.ept.git.dic2.projetjee.mbeans.Produit;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.Categorie;
import sn.ept.git.dic2.projetjee.entities.Marque;
import sn.ept.git.dic2.projetjee.entities.Produit;
import sn.ept.git.dic2.projetjee.facades.ProduitFacade;

import java.io.Serializable;
import java.util.List;

@Named("ajoutProduitMBean")
@ViewScoped
public class AjoutProduitMBean implements Serializable {

    @EJB
    private ProduitFacade produitFacade;

    private Produit produit;
    private List<Marque> marques;
    private List<Categorie> categories;
    private Integer selectedMarqueId;
    private Integer selectedCategorieId;

    @PostConstruct
    public void init() {
        produit = new Produit();
        marques = produitFacade.getAllMarques();
        categories = produitFacade.getAllCategories();
    }

    public void save() {
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

        // Save the produit object
        produitFacade.create(produit);

        // Reset the form
        produit = new Produit();
        selectedMarqueId = null;
        selectedCategorieId = null;
    }

    // Getters and setters

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
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

}
