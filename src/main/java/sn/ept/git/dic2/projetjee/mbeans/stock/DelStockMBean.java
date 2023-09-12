package sn.ept.git.dic2.projetjee.mbeans.stock;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import sn.ept.git.dic2.projetjee.entities.Stock;
import sn.ept.git.dic2.projetjee.facades.StockFacade;

import java.io.Serializable;
import java.util.List;

@Named("delStockMBean")
@ViewScoped
public class DelStockMBean implements Serializable {
    @EJB
    private StockFacade stockFacade;
    private Stock stock;
    private String message;
    private Stock selectedStock;
    private List<Stock> selectedStocks;
    private List<Stock> stocks;

    @PostConstruct
    public void init() {
        this.stocks = stockFacade.findAll();
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


    public Stock getSelectedStock() {
        return selectedStock;
    }

    public void setSelectedStock(Stock selectedStock) {
        this.selectedStock = selectedStock;
    }

    public List<Stock> getSelectedStocks() {
        return selectedStocks;
    }

    public void setSelectedStocks(List<Stock> selectedStocks) {
        this.selectedStocks = selectedStocks;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public void delete(Stock stock) {
        System.out.println("Supprimer le stock : " + stock);
        stockFacade.remove(stock);
        message = "Supprimé avec succès";
    }

    public void deleteStock() {
        stockFacade.remove(selectedStock);
        stocks.remove(selectedStock);
        this.selectedStock = null;

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Stock Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dv-stocks");
    }

}
