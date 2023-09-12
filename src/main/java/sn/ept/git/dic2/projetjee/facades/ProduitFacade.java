package sn.ept.git.dic2.projetjee.facades;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import sn.ept.git.dic2.projetjee.entities.Categorie;
import sn.ept.git.dic2.projetjee.entities.Marque;
import sn.ept.git.dic2.projetjee.entities.Produit;

import java.util.List;

@Stateless
public class ProduitFacade extends AbstractFacade<Produit> {
    @PersistenceContext(name = "velo")
    private EntityManager entityManager;

    public ProduitFacade() {
        super(Produit.class);
    }

    @Override
    protected EntityManager getEntityManager(){

        return entityManager;
    }

    public List<Marque> getAllMarques() {
        Query query = entityManager.createQuery("SELECT m FROM Marque m");
        return query.getResultList();
    }

    public List<Categorie> getAllCategories() {
        Query query = entityManager.createQuery("SELECT c FROM Categorie c");
        return query.getResultList();
    }

    public List<Produit> searchByKeyword(String keyword) {
        Query query = entityManager.createQuery("SELECT p FROM Produit p WHERE p.nom LIKE :keyword");
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }

    public Categorie findCategorieById(Long id) {

        Query query = entityManager.createQuery("SELECT c FROM Categorie c WHERE c.id = :id");
        query.setParameter("id", id);
        return (Categorie) query.getSingleResult();

    }

    public Marque findMarqueById(Long id) {
        Query query = entityManager.createQuery("SELECT c FROM Marque c WHERE c.id = :id");
        query.setParameter("id", id);
        return (Marque) query.getSingleResult();
    }


}
