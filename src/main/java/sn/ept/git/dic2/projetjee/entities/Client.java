/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ept.git.dic2.projetjee.entities;

import java.util.Objects;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dell
 */
@XmlRootElement(name = "client")
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Client extends Personne {
    private String adresse;

    private String ville;

    private String etat;

    private String codeZip;

    public Client() {
    }

    public Client(Integer id, String prenom, String nom, String telephone, String email, String adresse, String ville, String etat, String codeZip) {
        super(id, prenom, nom, telephone, email);
        this.adresse = adresse;
        this.ville = ville;
        this.etat = etat;
        this.codeZip = codeZip;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(adresse, client.adresse) && Objects.equals(ville, client.ville) && Objects.equals(etat, client.etat) && Objects.equals(codeZip, client.codeZip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adresse, ville, etat, codeZip);
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getCodeZip() {
        return codeZip;
    }

    public void setCodeZip(String codeZip) {
        this.codeZip = codeZip;
    }
}
