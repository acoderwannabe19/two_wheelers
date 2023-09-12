package sn.ept.git.dic2.projetjee.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import sn.ept.git.dic2.projetjee.entities.Personne;

import java.util.List;

@Stateless
public class PersonneFacade extends AbstractFacade<Personne> {
    @PersistenceContext(name = "velo")
    private EntityManager entityManager;

    public PersonneFacade() {
        super(Personne.class);
    }

    public List<Personne> getAllPersonnes() {
        TypedQuery<Personne> query = entityManager.createQuery("SELECT p FROM Personne p", Personne.class);
        return query.getResultList();
    }




    @Override
    protected EntityManager getEntityManager(){

        return entityManager;
    }
}
