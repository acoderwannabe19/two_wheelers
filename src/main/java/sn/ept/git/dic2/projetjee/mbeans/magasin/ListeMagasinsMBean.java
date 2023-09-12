package sn.ept.git.dic2.projetjee.mbeans.magasin;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.Magasin;
import sn.ept.git.dic2.projetjee.facades.MagasinFacade;

import java.io.Serializable;
import java.util.List;

@Named("listeMagasinsMBean")
@ViewScoped
public class ListeMagasinsMBean implements Serializable {
    @EJB
    private MagasinFacade magasinFacade;

    private Magasin magasinChoisie;

    private List<Magasin> magasins;

    public MagasinFacade getMagasinFacade() {
        return magasinFacade;
    }

    public void setMagasinFacade(MagasinFacade magasinFacade) {
        this.magasinFacade = magasinFacade;
    }

    public Magasin getMagasinChoisie() {
        return magasinChoisie;
    }

    public void setMagasinChoisie(Magasin magasinChoisie) {
        this.magasinChoisie = magasinChoisie;
    }

    public List<Magasin> getMagasins() {
        System.out.println("Liste des magasins " + magasins);
        if(magasins == null) {
            magasins = magasinFacade.findAll();
        }
        return magasins;
    }

    public void setMagasins(List<Magasin> magasins) {
        this.magasins = ListeMagasinsMBean.this.magasins;
    }

}
