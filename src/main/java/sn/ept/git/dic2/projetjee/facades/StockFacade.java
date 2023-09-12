package sn.ept.git.dic2.projetjee.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import sn.ept.git.dic2.projetjee.entities.Magasin;
import sn.ept.git.dic2.projetjee.entities.Produit;
import sn.ept.git.dic2.projetjee.entities.Stock;

import java.util.List;

@Stateless
public class StockFacade extends AbstractFacade<Stock> {
    @PersistenceContext(name = "velo")
    private EntityManager entityManager;

    public StockFacade() {
        super(Stock.class);
    }

    @Override
    protected EntityManager getEntityManager(){

        return entityManager;
    }

    public List<Produit> getAllProduits() {
        Query query = entityManager.createQuery("SELECT p FROM Produit p");
        return query.getResultList();
    }

    public List<Magasin> getAllMagasins() {
        Query query = entityManager.createQuery("SELECT p FROM Magasin p");
        return query.getResultList();
    }

    public Stock findStockByIDs(Integer magasinId, Integer produitId) {
        try {
            String queryStr = "SELECT s FROM Stock s WHERE s.magasin.id = :magasinId AND s.produit.id = :produitId";
            TypedQuery<Stock> query = entityManager.createQuery(queryStr, Stock.class);
            query.setParameter("magasinId", magasinId);
            query.setParameter("produitId", produitId);
            return query.getSingleResult();
        }catch(NoResultException e) {
            return null;
        }
    }


}
