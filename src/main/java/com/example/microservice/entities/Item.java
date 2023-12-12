package com.example.microservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Item {
    @Id
    @GeneratedValue
    private int idItem;
    private double prixProduit;
    private int qte;
    @ManyToOne
    @JoinColumn(name = "idPanier")
    @JsonIgnore
    private Panier panier;


    public Item() {
        super();
    }

    public Item(int idProduit, double prixProduit, int qte) {
        this.idProduit = idProduit;
        this.prixProduit = prixProduit;
        this.qte = qte;
    }

    @PostUpdate
    public void updatePanierPrixtotal() {
        if (panier != null) {
            panier.updatePrixtotal();
        }
    }
}
