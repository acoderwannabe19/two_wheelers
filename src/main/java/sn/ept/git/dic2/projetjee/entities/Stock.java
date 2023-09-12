/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ept.git.dic2.projetjee.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;

/**
 *
 * @author dell
 */

@Entity
@SequenceGenerator(name = "stock_seq", sequenceName = "stock_seq", initialValue = 200)
public class Stock implements Serializable {

    private int quantite;

    @Id
    @GeneratedValue(generator = "stock_seq")
    @JoinColumn(name = "PRODUIT_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "fk_articlecommande_produit_id", foreignKeyDefinition = "FOREIGN KEY (PRODUIT_ID) REFERENCES produit(ID) ON DELETE CASCADE"))
    @ManyToOne
    private Produit produit;


    @Id
    @JoinColumn(name = "MAGASIN_ID", referencedColumnName = "ID")
    @ManyToOne
    private Magasin magasin;

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }
    public Stock() {
    }

    public Stock(int quantite, Produit produit, Magasin magasin) {

        this.quantite = quantite;
        this.produit = produit;
        this.magasin = magasin;
    }



    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return quantite == stock.quantite && Objects.equals(produit, stock.produit) && Objects.equals(magasin, stock.magasin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantite, produit, magasin);
    }

    public String toString() {
        return produit + " : " + Integer.toString(quantite);
    }
    
}
