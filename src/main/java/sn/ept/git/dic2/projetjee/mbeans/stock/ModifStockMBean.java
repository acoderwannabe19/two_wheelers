package sn.ept.git.dic2.projetjee.mbeans.stock;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.Magasin;
import sn.ept.git.dic2.projetjee.entities.Produit;
import sn.ept.git.dic2.projetjee.entities.Stock;
import sn.ept.git.dic2.projetjee.facades.StockFacade;

import java.io.Serializable;
import java.util.List;

@Named("modifStockMBean")
@ViewScoped
public class ModifStockMBean implements Serializable {
    @EJB
    private StockFacade stockFacade;
    private List<Produit> produits;
    private List<Magasin> magasins;
    private Integer selectedProduitId;
    private Integer selectedMagasinId;
    private Stock stock;
    private String message;

    @PostConstruct
    public void init() {
        stock = new Stock();
        produits = stockFacade.getAllProduits();
        magasins = stockFacade.getAllMagasins();
    }


    public StockFacade getStockFacade() {
        return stockFacade;
    }

    public void setStockFacade(StockFacade stockFacade) {
        this.stockFacade = stockFacade;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public Integer getSelectedProduitId() {
        return selectedProduitId;
    }

    public void setSelectedProduitId(Integer selectedProduitId) {
        this.selectedProduitId = selectedProduitId;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Magasin> getMagasins() {
        return magasins;
    }

    public void setMagasins(List<Magasin> magasins) {
        this.magasins = magasins;
    }

    public Integer getSelectedMagasinId() {
        return selectedMagasinId;
    }

    public void setSelectedMagasinId(Integer selectedMagasinId) {
        this.selectedMagasinId = selectedMagasinId;
    }

    public void update() {
        Produit selectedProduit = null;
        Magasin selectedMagasin = null;

        // Find the selected Produit object based on the selectedProduitId
        for (Produit produit : produits) {
            if (produit.getId().equals(selectedProduitId)) {
                selectedProduit = produit;
                break;
            }
        }

        // Find the selected Magasin object based on the selectedMagasinId
        for (Magasin magasin : magasins) {
            if (magasin.getId().equals(selectedProduitId)) {
                selectedMagasin = magasin;
                break;
            }
        }


        // Set the selected Marque and Categorie objects in the Produit object
        stock.setProduit(selectedProduit);
        stock.setMagasin(selectedMagasin);


        System.out.println("Enregistrer la stock : " + stock);
        stockFacade.edit(stock);
        message ="enregistré avec succès";
        stock = new Stock();

    }
}
