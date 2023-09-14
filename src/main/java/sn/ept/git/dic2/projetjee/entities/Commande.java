/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ept.git.dic2.projetjee.entities;

import java.io.Serializable;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.*;

/**
 *
 * @author dell
 */

@SequenceGenerator(name = "commande_seq", sequenceName = "commande_seq", initialValue = 500)
@Entity
public class Commande implements Serializable {

    @Id
    @GeneratedValue(generator = "commande_seq")
    private Integer numero;

    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "fk_commande_client_id", foreignKeyDefinition = "FOREIGN KEY (CLIENT_ID) REFERENCES personne(ID) ON DELETE CASCADE"))
    @ManyToOne(optional = false)
    private Client clientId;

    private short statut;

    @Temporal(TemporalType.DATE)
    @JsonbDateFormat(value="yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateCommande;

    @Temporal(TemporalType.DATE)
    @JsonbDateFormat(value="yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateLivraisonVoulue;

    @Temporal(TemporalType.DATE)
    @JsonbDateFormat(value="yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateLivraison;


    @JoinColumn(name = "MAGASIN_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "fk_commande_magasin_id", foreignKeyDefinition = "FOREIGN KEY (MAGASIN_ID) REFERENCES magasin(ID) ON DELETE CASCADE"))
    @ManyToOne(optional = false)
    private Magasin magasinId;

    @JoinColumn(name = "VENDEUR_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "fk_commande_vendeur_id", foreignKeyDefinition = "FOREIGN KEY (VENDEUR_ID) REFERENCES personne(ID) ON DELETE CASCADE"))
    @ManyToOne(optional = false)
    private Employe vendeurId;

    public Commande() {
    }

    public Commande(Integer numero, Client clientId, short statut, Date dateCommande, Date dateLivraisonVoulue, Date dateLivraison, Magasin magasinId, Employe vendeurId) {
        this.numero = numero;
        this.clientId = clientId;
        this.statut = statut;
        this.dateCommande = dateCommande;
        this.dateLivraisonVoulue = dateLivraisonVoulue;
        this.dateLivraison = dateLivraison;
        this.magasinId = magasinId;
        this.vendeurId = vendeurId;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public short getStatut() {
        return statut;
    }

    public void setStatut(short statut) {
        this.statut = statut;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Date getDateLivraisonVoulue() {
        return dateLivraisonVoulue;
    }

    public void setDateLivraisonVoulue(Date dateLivraisonVoulue) {
        this.dateLivraisonVoulue = dateLivraisonVoulue;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }


    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public Employe getVendeurId() {
        return vendeurId;
    }

    public void setVendeurId(Employe vendeurId) {
        this.vendeurId = vendeurId;
    }

    public Magasin getMagasinId() {
        return magasinId;
    }

    public void setMagasinId(Magasin magasinId) {
        this.magasinId = magasinId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.tp_jpa_partie1.Commande[ numero=" + numero + " ]";
    }

}
