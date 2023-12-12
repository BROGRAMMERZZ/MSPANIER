package com.example.microservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue
    private int idItem;
    private String nomProduit;
    private double prixProduit;
    private int qte;
    @ManyToOne
    @JoinColumn(name = "id_panier")
    @JsonIgnore
    private Panier panier;


    public Item() {
        super();
    }

    public Item(String nomProduit, double prixProduit, int qte) {
        this.nomProduit=nomProduit;
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
