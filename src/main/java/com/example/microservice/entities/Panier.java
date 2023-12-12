package com.example.microservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@Table(name = "Panier")
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idPanier;
    private long idUser;
    private double prixtotal;
    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL)
    private Set<Item> items = new HashSet<>();

    public Panier(long idPanier, long idUser, double prixtotal, Set<Item> items) {
        this.idPanier = idPanier;
        this.idUser = idUser;
        this.prixtotal = prixtotal;
        this.items = items;

    }

    @PostUpdate
    @PostConstruct
    public void updatePrixtotal() {
        if (items != null) {
            prixtotal = items.stream()
                    .mapToDouble(item -> item.getPrixProduit() * item.getQte())
                    .sum();
        } else {
            prixtotal = 0.0;
        }
    }


    public Panier() {
    }


    public Panier(long idUser, double prixtotal) {
        this.idUser = idUser;
        this.prixtotal = prixtotal;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
