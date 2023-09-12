package sn.ept.git.dic2.projetjee.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.projetjee.entities.Categorie;


@Stateless
public class CategorieFacade extends AbstractFacade<Categorie> {
    @PersistenceContext(name = "velo")
    private EntityManager entityManager;

    public CategorieFacade() {
        super(Categorie.class);
    }

    @Override
    protected EntityManager getEntityManager(){

        return entityManager;
    }
}
