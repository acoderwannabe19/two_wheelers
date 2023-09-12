/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ept.git.dic2.projetjee.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.persistence.*;

/**
 *
 * @author dell
 */
@Entity
@SequenceGenerator(name = "produit_seq", sequenceName = "produit_seq", initialValue = 500)
public class Produit implements Serializable {

    @Id
    @GeneratedValue(generator = "produit_seq")
    private Integer id;

    private String nom;

    @JoinColumn(name = "MARQUE_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "fk_produit_marque_id", foreignKeyDefinition = "FOREIGN KEY (MARQUE_ID) REFERENCES marque(ID) ON DELETE CASCADE"))
    @ManyToOne(optional = false)
    private Marque marqueId;

    @JoinColumn(name = "CATEGORIE_ID", referencedColumnName = "ID",foreignKey = @ForeignKey(name = "fk_produit_categorie_id", foreignKeyDefinition = "FOREIGN KEY (CATEGORIE_ID) REFERENCES categorie(ID) ON DELETE CASCADE"))
    @ManyToOne(optional = false)
    private Categorie categorieId;

    private int anneeModel;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    private BigDecimal prixDepart;


    public Produit() {
    }

    public Produit(Integer id) {
        this.id = id;
    }

    public Produit(Integer id, String nom, Marque marqueId, Categorie categorieId, int anneeModel, BigDecimal prixDepart ) {
        this.id = id;
        this.nom = nom;
        this.marqueId = marqueId;
        this.categorieId = categorieId;
        this.anneeModel = anneeModel;
        this.prixDepart = prixDepart;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAnneeModel() {
        return anneeModel;
    }

    public void setAnneeModel(int anneeModel) {
        this.anneeModel = anneeModel;
    }

    public BigDecimal getPrixDepart() {
        return prixDepart;
    }

    public void setPrixDepart(BigDecimal prixDepart) {
        this.prixDepart = prixDepart;
    }

    public Categorie getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Categorie categorieId) {
        this.categorieId = categorieId;
    }

    public Marque getMarqueId() {
        return marqueId;
    }

    public void setMarqueId(Marque marqueId) {
        this.marqueId = marqueId;
    }




    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom;
    }
    
}
