package sn.ept.git.dic2.projetjee.mbeans.employe;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.event.RowEditEvent;
import sn.ept.git.dic2.projetjee.entities.Employe;
import sn.ept.git.dic2.projetjee.entities.Employe;
import sn.ept.git.dic2.projetjee.entities.Magasin;
import sn.ept.git.dic2.projetjee.facades.EmployeFacade;

import java.io.Serializable;
import java.util.List;

@Named("modifEmpMBean")
@ViewScoped
public class ModifEmpMBean implements Serializable {

    @EJB
    private EmployeFacade employeFacade;
    private Employe employe;

    private List<Magasin> magasins;
    private List<Employe> managers;
    private Integer selectedMagasinId;
    private Integer selectedManagerId;
    private String message;
    private List<Employe> employes;

    @PostConstruct
    public void init() {
        employe = new Employe();
        magasins = employeFacade.getAllMagasins();
        managers = employeFacade.getAllManagers();
        employes = employeFacade.findAll();
    }


    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
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

    public List<Employe> getManagers() {
        return managers;
    }

    public void setManagers(List<Employe> managers) {
        this.managers = managers;
    }

    public Integer getSelectedMagasinId() {
        return selectedMagasinId;
    }

    public void setSelectedMagasinId(Integer selectedMagasinId) {
        this.selectedMagasinId = selectedMagasinId;
    }

    public Integer getSelectedManagerId() {
        return selectedManagerId;
    }

    public void setSelectedManagerId(Integer selectedManagerId) {
        this.selectedManagerId = selectedManagerId;
    }

    public ModifEmpMBean() {
        employe = new Employe();
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

    public void update() {
        Magasin selectedMagasin = null;
        Employe selectedManager = null;

        // Find the selected Magasin object based on the selectedMagasinId
        for (Magasin magasin : magasins) {
            if (magasin.getId().equals(selectedMagasinId)) {
                selectedMagasin = magasin;
                break;
            }
        }

        // Find the selected Manager object based on the selectedManagerId
        for (Employe manager : managers) {
            if (manager.getId().equals(selectedManagerId)) {
                selectedManager = manager;
                break;
            }
        }

        // Set the selected Magasin and Manager objects in the Employe object
        employe.setMagasinId(selectedMagasin);
        employe.setManagerId(selectedManager);

        System.out.println("Mettre à jour l'employe : " + employe);
        employeFacade.edit(employe);
        message = "mis à jour avec succès";
        employe = new Employe();
    }
    public void onRowEdit(RowEditEvent<Employe> event) {
        Employe editedEmploye = event.getObject();
        employeFacade.edit(editedEmploye);
        employes.add(editedEmploye);

        FacesMessage msg = new FacesMessage("Employee Edited", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Employe> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
