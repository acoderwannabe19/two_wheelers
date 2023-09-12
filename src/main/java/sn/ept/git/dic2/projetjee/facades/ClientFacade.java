package sn.ept.git.dic2.projetjee.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.projetjee.entities.Client;

@Stateless
public class ClientFacade extends AbstractFacade<Client> {
    @PersistenceContext(name = "velo")
    private EntityManager entityManager;

    public ClientFacade() {
        super(Client.class);
    }

    @Override
    protected EntityManager getEntityManager(){
        return entityManager;
    }
}
