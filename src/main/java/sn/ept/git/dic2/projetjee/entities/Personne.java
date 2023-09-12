package sn.ept.git.dic2.projetjee.entities;

import jakarta.xml.bind.annotation.XmlRootElement;

import jakarta.persistence.*;

import java.io.Serializable;

@XmlRootElement(name = "personne")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "personne_seq", sequenceName = "personne_seq", initialValue = 200)
@Entity
public class Personne implements Serializable {

    @Id
    @GeneratedValue(generator = "personne_seq")
    private Integer id;

    private String prenom;

    private String nom;

    private String telephone;

    private String email;

    public Personne(Integer id, String prenom, String nom, String telephone, String email) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Personne() {
    }


}
