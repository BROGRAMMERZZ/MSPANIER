package com.example.microservice.services;

import com.example.microservice.entities.Item;
import com.example.microservice.entities.Panier;
import com.example.microservice.repository.PanierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class PanierService {

    @Autowired
    private PanierRepo panierRepository;

    public Panier addPanier(Panier panier) {
        return panierRepository.save(panier);
    }

    public Optional<Panier> getPanierById(int id) {
        return panierRepository.findById(id);
    }

    public Panier updatePanier(int id, Panier newPanier) {
        Optional<Panier> optionalPanier = panierRepository.findById(id);
        if (optionalPanier.isPresent()) {
            Panier existingPanier = optionalPanier.get();
            existingPanier.setIdUser(newPanier.getIdUser());
            existingPanier.setPrixtotal(newPanier.getPrixtotal());
            // Update other fields as needed
            return panierRepository.save(existingPanier);
        } else {
            return null; // Handle non-existing panier scenario
        }
    }

    public String deletePanier(int id) {
        if (panierRepository.existsById(id)) {
            panierRepository.deleteById(id);
            return "Panier supprimé";
        } else {
            return "Panier non supprimé"; // Handle non-existing panier scenario
        }
    }


    public Optional<Item> getItemByIdInPanier(int panierId, int itemId) {
        return panierRepository.findItemByIdInPanier(panierId, itemId);
    }


    public Panier addItemsToPanier(int panierId, List<Item> items) {
        Optional<Panier> optionalPanier = panierRepository.findById(panierId);
        if (optionalPanier.isPresent()) {
            Panier panier = optionalPanier.get();
            for (Item item : items) {
                item.setPanier(panier);
            }
            panier.getItems().addAll(items);
            panier.updatePrixtotal();
            return panierRepository.save(panier);
        } else {
            return null;
        }
    }

    public Panier createPanierWithItems(Panier panier, List<Item> items) {
        for (Item item : items) {
            item.setPanier(panier);
        }

        panier.setItems(new HashSet<>(items));
        panier.updatePrixtotal();

        return panierRepository.save(panier);
    }
}