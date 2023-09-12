package sn.ept.git.dic2.projetjee.mbeans.employe;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.Employe;
import sn.ept.git.dic2.projetjee.facades.EmployeFacade;

import java.io.Serializable;
import java.util.List;

@Named("listeEmpsMBean")
@ViewScoped
public class ListeEmpsMBean implements Serializable {
    @EJB
    private EmployeFacade employeFacade;

    private Employe employeChoisie;

    private List<Employe> employes;

    public EmployeFacade getEmployeFacade() {
        return employeFacade;
    }

    public void setEmployeFacade(EmployeFacade employeFacade) {
        this.employeFacade = employeFacade;
    }

    public Employe getEmployeChoisie() {
        return employeChoisie;
    }

    public void setEmployeChoisie(Employe employeChoisie) {
        this.employeChoisie = employeChoisie;
    }

    public List<Employe> getEmployes() {
        System.out.println("Liste des employes " + employes);
        if(employes == null) {
            employes = employeFacade.findAll();
        }
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = ListeEmpsMBean.this.employes;
    }


}
