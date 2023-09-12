package sn.ept.git.dic2.projetjee.mbeans.employe;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import sn.ept.git.dic2.projetjee.entities.Employe;
import sn.ept.git.dic2.projetjee.facades.EmployeFacade;

import java.io.Serializable;
import java.util.List;

@Named("delEmpMBean")
@ViewScoped
public class DelEmpMBean implements Serializable {
    @EJB
    private EmployeFacade employeFacade;
    private Employe employe;
    private String message;
    private Employe selectedEmploye;

    private List<Employe> selectedEmployes;

    private List<Employe> employes;

    @PostConstruct
    public void init() {
        this.employes = employeFacade.findAll();
    }
    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EmployeFacade getEmployeFacade() {
        return employeFacade;
    }

    public void setEmployeFacade(EmployeFacade employeFacade) {
        this.employeFacade = employeFacade;
    }

    public Employe getSelectedEmploye() {
        return selectedEmploye;
    }

    public void setSelectedEmploye(Employe selectedEmploye) {
        this.selectedEmploye = selectedEmploye;
    }

    public List<Employe> getSelectedEmployes() {
        return selectedEmployes;
    }

    public void setSelectedEmployes(List<Employe> selectedEmployes) {
        this.selectedEmployes = selectedEmployes;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public void delete(Employe employe) {
        System.out.println("Supprimer la employe : " + employe);
        employeFacade.remove(employe);
        message = "Supprimé avec succès";

    }
    public void deleteEmploye() {
        employeFacade.remove(selectedEmploye);
        employes.remove(selectedEmploye);
        this.selectedEmploye = null;

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Employe Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-employes");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedEmployes()) {
            int size = this.selectedEmployes.size();
            return size > 1 ? size + " employees selected" : "1 employee selected";
        }

        return "Delete";
    }

    public boolean hasSelectedEmployes() {
        return this.selectedEmployes != null && !this.selectedEmployes.isEmpty();
    }

    public void deleteSelectedEmployes() {
        for (Employe employee : selectedEmployes) {
            employeFacade.remove(employee);
        }
        employes.removeAll(selectedEmployes);
        this.selectedEmployes = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Employees Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-employes");
        PrimeFaces.current().executeScript("PF('dtEmployes').clearFilters()");
    }

}
