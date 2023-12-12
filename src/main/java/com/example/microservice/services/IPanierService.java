package com.example.microservice.services;

import com.example.microservice.entities.Item;
import com.example.microservice.entities.Panier;

import java.util.List;

public interface IPanierService {
    Panier createPanierIfUserDontHaveOne(long idUser);
    Panier getPanierWithItems(long idUser);
    Panier updatePanier(long idUser, List<Item> items);
    void deletePanier(long idUser);
    Panier findPanierByIdUser(long idUser);
    public List<Panier> getAll() ;

    }
