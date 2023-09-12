package sn.ept.git.dic2.projetjee.facades;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.projetjee.entities.Marque;

@Stateless
public class MarqueFacade extends AbstractFacade<Marque> {
    @PersistenceContext(name = "velo")
    private EntityManager entityManager;

    public MarqueFacade() {
        super(Marque.class);
    }

    @Override
    protected EntityManager getEntityManager(){

        return entityManager;
    }
}
