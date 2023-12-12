package com.example.microservice.services;

import com.example.microservice.entities.Item;
import com.example.microservice.entities.Panier;
import com.example.microservice.repository.PanierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class PanierService implements IPanierService {

    @Autowired
    private PanierRepo panierRepository;
    @Autowired
    private ItemService itemService;


    @Override
    public Panier createPanierIfUserDontHaveOne(long idUser) {
        Panier p = panierRepository.findByIdUser(idUser);
        if (p==null){
            panierRepository.save(new Panier(idUser,0.0));
            return p;
        }else
            return p;
    }

    @Override
    public Panier getPanierWithItems(long idUser) {
        return panierRepository.findByIdUser(idUser);
    }

    @Override
    public List<Panier> getAll() {
        return panierRepository.findAll();
    }


    @Override
    public Panier updatePanier(long idUser, List<Item> items) {
        Panier panier = panierRepository.findByIdUser(idUser);
        if (panier == null) {
            throw new RuntimeException("There's no such panier for that user");
        } else {
            Set<Item> itemSet = new HashSet<>(items);

            for (Item item : itemSet) {
                item.setPanier(panier);
            }

            panier.setItems(itemSet);
            panier.updatePrixtotal();
            panierRepository.save(panier);
            return panier;
        }
    }


    @Override
    public void deletePanier(long idUser) {
        panierRepository.deleteById(panierRepository.findByIdUser(idUser).getIdPanier());
    }

    @Override
    public Panier findPanierByIdUser(long idUser) {
        return panierRepository.findByIdUser(idUser);
    }

    public void deletePanierIdPanier(long idPanier) {
        panierRepository.deleteById(idPanier);
    }





//    public Panier addPanierWithItems(int idUser, List<Item> items) {
//        Panier panier = Panier.builder()
//                .idUser(idUser)
//                .items(items)
//                .build();
//        System.out.println("IDUSER IS ====="+idUser);
//        System.out.println("ITEEEEMMMMSSS =====     "+panier.getIdUser()+ panier.getPrixtotal());
//        for (Item item : items) {
//            item.setPanier(panier);
//            System.out.println("TRASH");
//        }
//        panier.updatePrixtotal();
//        panier = panierRepository.save(panier);
//        return panier;
//    }
//
//    public Panier addPanier(int idUser, List<Item> items) {
//        Panier panier = Panier.builder()
//                .idUser(idUser)
//                .items(items)
//                .build();
//        panier.updatePrixtotal();
//        return panierRepository.save(panier);
//    }
//
//    public Optional<Panier> findById(int idPanier) {
//        return panierRepository.findById(idPanier);
//    }
//
//    public Panier findByUserId(int idUser) {
//        return panierRepository.findByIdUser(idUser);
//    }
//
//    public Panier updatePanier(int idPanier, List<Item> newItems) {
//        Optional<Panier> optionalPanier = panierRepository.findById(idPanier);
//        System.out.println("Itemnsare "+newItems);
//        if (optionalPanier.isPresent()) {
//            Panier panier = optionalPanier.get();
//            panier.setItems(newItems);
//            panier.updatePrixtotal();
//            return panierRepository.save(panier);
//        } else {
//            return null;
//        }
//    }


}
