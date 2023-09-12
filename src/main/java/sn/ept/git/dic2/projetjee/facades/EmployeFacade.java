package sn.ept.git.dic2.projetjee.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import sn.ept.git.dic2.projetjee.entities.Employe;
import sn.ept.git.dic2.projetjee.entities.Magasin;

import java.util.List;

@Stateless
public class EmployeFacade extends AbstractFacade<Employe> {
    @PersistenceContext(name = "velo")
    private EntityManager entityManager;

    public EmployeFacade() {
        super(Employe.class);
    }

    @Override
    protected EntityManager getEntityManager(){
        return entityManager;
    }

    public List<Magasin> getAllMagasins() {
        Query query = entityManager.createQuery("SELECT m FROM Magasin m");
        return query.getResultList();
    }

    public List<Employe> getAllManagers() {
        Query query = entityManager.createQuery("SELECT c FROM Employe c");
        return query.getResultList();
    }

    public Magasin findMagasinById(Long id) {
        Query query = entityManager.createQuery("SELECT c FROM Magasin c WHERE c.id = :id");
        query.setParameter("id", id);
        return (Magasin) query.getSingleResult();
    }
    public Employe findManagerById(Long id) {
        Query query = entityManager.createQuery("SELECT c FROM Employe c WHERE c.id = :id");
        query.setParameter("id", id);
        return (Employe) query.getSingleResult();
    }

}
