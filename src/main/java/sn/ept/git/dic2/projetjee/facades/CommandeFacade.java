package sn.ept.git.dic2.projetjee.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import sn.ept.git.dic2.projetjee.entities.*;

import java.util.List;

@Stateless
public class CommandeFacade extends AbstractFacade<Commande> {
    @PersistenceContext(name = "velo")
    private EntityManager entityManager;

    public CommandeFacade() {
        super(Commande.class);
    }

    @Override
    protected EntityManager getEntityManager(){

        return entityManager;
    }

    public List<Magasin> getAllMagasins() {
        Query query = entityManager.createQuery("SELECT m FROM Magasin m");
        return query.getResultList();
    }

    public List<Employe> getAllEmployes() {
        Query query = entityManager.createQuery("SELECT c FROM Employe c");
        return query.getResultList();
    }

    public List<Client> getAllClients() {
        Query query = entityManager.createQuery("SELECT c FROM Client c");
        return query.getResultList();
    }

    public Client findClientById(Long id) {
        Query query = entityManager.createQuery("SELECT c FROM Client c WHERE c.id = :id");
        query.setParameter("id", id);
        return (Client) query.getSingleResult();
    }
}
