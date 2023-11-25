package com.example.microservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;



@Getter
@Setter
@Entity
public class Panier {
    @Id
    @GeneratedValue
    private int idPanier;
    private int idUser;
    private double prixtotal;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Item> items;

    public void updatePrixtotal() {
        if (items != null) {
            prixtotal = items.stream().mapToDouble(Item::getPrixProduit).sum();
        } else {
            prixtotal = 0.0;
        }
    }

}
