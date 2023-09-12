package sn.ept.git.dic2.projetjee.mbeans.employe;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.Employe;
import sn.ept.git.dic2.projetjee.entities.Magasin;
import sn.ept.git.dic2.projetjee.facades.EmployeFacade;

import java.io.Serializable;
import java.util.List;

@Named("ajoutEmpMBean")
@ViewScoped
public class AjoutEmpMBean implements Serializable {
    @EJB
    private EmployeFacade employeFacade;

    private Employe employe;

    public List<Employe> getManagers() {
        return managers;
    }

    public void setManagers(List<Employe> managers) {
        this.managers = managers;
    }

    public Integer getSelectedManagerId() {
        return selectedManagerId;
    }

    public void setSelectedManagerId(Integer selectedManagerId) {
        this.selectedManagerId = selectedManagerId;
    }

    private List<Magasin> magasins;
    private List<Employe> managers;
    private Integer selectedMagasinId;
    private Integer selectedManagerId;

    @PostConstruct
    public void init() {
        employe = new Employe();
        magasins = employeFacade.getAllMagasins();
        managers = employeFacade.getAllManagers();
    }

    public void save() {
        Magasin selectedMagasin = null;
        Employe selectedManager = null;

        // Find the selected Magasin object based on the selectedMagasinId
        for (Magasin marque : magasins) {
            if (marque.getId().equals(selectedMagasinId)) {
                selectedMagasin = marque;
                break;
            }
        }

        // Find the selected Employe object based on the selectedEmployeId
        for (Employe manager : managers) {
            if (manager.getId().equals(selectedManagerId)) {
                selectedManager = manager;
                break;
            }
        }

        // Set the selected Magasin and Employe objects in the Employe object
        employe.setMagasinId(selectedMagasin);
        employe.setManagerId(selectedManager);

        // Save the employe object
        employeFacade.create(employe);

        // Reset the form
        employe = new Employe();
        selectedMagasinId = null;
        selectedManagerId = null;
    }

    // Getters and setters

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public EmployeFacade getEmployeFacade() {
        return employeFacade;
    }

    public void setEmployeFacade(EmployeFacade employeFacade) {
        this.employeFacade = employeFacade;
    }

    public List<Magasin> getMagasins() {
        return magasins;
    }

    public void setMagasins(List<Magasin> magasins) {
        this.magasins = magasins;
    }

    public List<Employe> getEmployes() {
        return managers;
    }

    public void setEmployes(List<Employe> managers) {
        this.managers = managers;
    }

    public Integer getSelectedMagasinId() {
        return selectedMagasinId;
    }

    public void setSelectedMagasinId(Integer selectedMagasinId) {
        this.selectedMagasinId = selectedMagasinId;
    }

    public Integer getSelectedEmployeId() {
        return selectedManagerId;
    }

    public void setSelectedEmployeId(Integer selectedEmployeId) {
        this.selectedManagerId = selectedEmployeId;
    }

}
