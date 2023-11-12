package com.example.microservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Item {
    @Id
    @GeneratedValue
    private int idItem;
    private int idProduit;
    private double prixProduit;
    private int qte;
    @ManyToOne
    @JoinColumn(name = "idPanier")
    private Panier panier;


    public Item() {
        super();
    }

    public Item(int idProduit, double prixProduit, int qte) {
        this.idProduit = idProduit;
        this.prixProduit = prixProduit;
        this.qte = qte;
    }
}
