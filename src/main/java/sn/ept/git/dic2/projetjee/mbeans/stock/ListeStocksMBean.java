package sn.ept.git.dic2.projetjee.mbeans.stock;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.Stock;
import sn.ept.git.dic2.projetjee.facades.StockFacade;

import java.io.Serializable;
import java.util.List;

@Named("listeStocksMBean")
@ViewScoped
public class ListeStocksMBean implements Serializable {
    @EJB
    private StockFacade stockFacade;

    private Stock stockChoisie;

    private List<Stock> stocks;

    public StockFacade getStockFacade() {
        return stockFacade;
    }

    public void setStockFacade(StockFacade stockFacade) {
        this.stockFacade = stockFacade;
    }

    public Stock getStockChoisie() {
        return stockChoisie;
    }

    public void setStockChoisie(Stock stockChoisie) {
        this.stockChoisie = stockChoisie;
    }

    public List<Stock> getStocks() {
        System.out.println("Liste des stocks " + stocks);
        if(stocks == null) {
            stocks = stockFacade.findAll();
        }


        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = ListeStocksMBean.this.stocks;
    }

}
