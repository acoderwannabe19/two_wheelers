package sn.ept.git.dic2.projetjee.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.projetjee.entities.GPSLocation;


@Stateless
public class GPSLocationFacade extends AbstractFacade<GPSLocation> {
    @PersistenceContext(name = "velo")
    private EntityManager entityManager;

    public GPSLocationFacade() {
        super(GPSLocation.class);
    }

    @Override
    protected EntityManager getEntityManager(){

        return entityManager;
    }
}
