package com.example.microservice.services;

import com.example.microservice.entities.Item;
import com.example.microservice.entities.Panier;
import com.example.microservice.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepository;

    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    public Optional<Item> getItemById(int id) {
        return itemRepository.findById(id);
    }

    public Item updateItem(int id, Item newItem) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            Item existingItem = optionalItem.get();
            existingItem.setPrixProduit(newItem.getPrixProduit());
            existingItem.setQte(newItem.getQte());
            return itemRepository.save(existingItem);
        } else {
            return null;
        }
    }

    public String deleteItem(int id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return "Item supprimé";
        } else {
            return "Item non supprimé";
        }
    }

    public List<Item> getItemsByPanier(Panier panier) {
        return itemRepository.findByPanier(panier);
    }

}