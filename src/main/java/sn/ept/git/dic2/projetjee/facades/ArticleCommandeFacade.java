package sn.ept.git.dic2.projetjee.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import sn.ept.git.dic2.projetjee.entities.*;

import java.util.List;

@Stateless
public class ArticleCommandeFacade extends AbstractFacade<ArticleCommande> {
    @PersistenceContext(name = "velo")
    private EntityManager entityManager;

    public ArticleCommandeFacade() {
        super(ArticleCommande.class);
    }


    @Override
    protected EntityManager getEntityManager(){

        return entityManager;
    }

    public List<Produit> getAllProduits() {
        Query query = entityManager.createQuery("SELECT m FROM Produit m");
        return query.getResultList();
    }

    public List<Commande> getAllCommandes() {
        Query query = entityManager.createQuery("SELECT m FROM Commande m");
        return query.getResultList();
    }

    public Produit findProduitById(Long id) {
        Query query = entityManager.createQuery("SELECT c FROM Produit c WHERE c.id = :id");
        query.setParameter("id", id);
        return (Produit) query.getSingleResult();
    }

    public ArticleCommande findArticleCommandeByIDs(Integer ligne, Integer numeroCommande) {
        try {
            String queryStr = "SELECT s FROM ArticleCommande s WHERE s.ligne = :ligne AND s.numeroCommande.numero = :numeroCommande";
            TypedQuery<ArticleCommande> query = entityManager.createQuery(queryStr, ArticleCommande.class);
            query.setParameter("ligne", ligne);
            query.setParameter("numeroCommande", numeroCommande);
            return query.getSingleResult();
        }catch(NoResultException e) {
            return null;
        }
    }
}
