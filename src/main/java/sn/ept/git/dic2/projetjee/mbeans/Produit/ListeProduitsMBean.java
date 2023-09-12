package sn.ept.git.dic2.projetjee.mbeans.Produit;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import sn.ept.git.dic2.projetjee.entities.Produit;
import sn.ept.git.dic2.projetjee.facades.ProduitFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("listeProduitsMBean")
@ViewScoped
public class ListeProduitsMBean implements Serializable {

    @EJB
    private ProduitFacade produitFacade;

    private Produit produitChoisi;

    private String searchKeyword;
    private List<Produit> produits;
    private List<Produit> filteredProduits;

    private List<SortMeta> sortBy;

    @PostConstruct
    public void init() {
//        products1 = service.getProducts(10);
//        products2 = service.getProducts(10);
//        products3 = service.getProducts(50);

        sortBy = new ArrayList<>();
        sortBy.add(SortMeta.builder()
                .field("name")
                .order(SortOrder.ASCENDING)
                .build());

        sortBy.add(SortMeta.builder()
                .field("category")
                .order(SortOrder.ASCENDING)
                .priority(1)
                .build());
    }

    public List<Produit> getFilteredProduits() {
        return filteredProduits;
    }

    public void setFilteredProduits(List<Produit> filteredProduits) {
        this.filteredProduits = filteredProduits;
    }

    public List<SortMeta> getSortBy() {
        return sortBy;
    }

    public void setSortBy(List<SortMeta> sortBy) {
        this.sortBy = sortBy;
    }

    public ProduitFacade getProduitFacade() {
        return produitFacade;
    }

    public void setProduitFacade(ProduitFacade produitFacade) {
        this.produitFacade = produitFacade;
    }

    public Produit getProduitChoisi() {
        return produitChoisi;
    }

    public void setProduitChoisi(Produit produitChoisi) {
        this.produitChoisi = produitChoisi;
    }

    public List<Produit> getProduits() {
        System.out.println("Liste des produits " + produits);
        if(produits == null) {
            produits = produitFacade.findAll();
        }
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = ListeProduitsMBean.this.produits;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public void search() {
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            produits = produitFacade.searchByKeyword(searchKeyword); // Implement the search logic in your facade
        } else {
            produits = produitFacade.findAll();
        }

        filteredProduits = new ArrayList<>();

        for (Produit produit : produits) {
            if (produit.getNom().contains(searchKeyword)) {
                filteredProduits.add(produit);
            }
        }
    }


}

