package com.example.microservice.controller;

import com.example.microservice.entities.Item;
import com.example.microservice.entities.Panier;
import com.example.microservice.request.NewPanier;
import com.example.microservice.request.PanierWithItemsRequest;
import com.example.microservice.services.ItemService;
import com.example.microservice.services.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@EnableEurekaClient
@RequestMapping("/panierRestController")
@RestController
public class PanierController {

    @Autowired
    private PanierService panierService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/GetPanier")
    @ResponseStatus(HttpStatus.CREATED)
    public Panier getPanier(@RequestParam long idUser) {
        return panierService.createPanierIfUserDontHaveOne(idUser);
    }

    @GetMapping("/GetAllPanier")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Panier> getAllPaniers() {
        return panierService.getAll();
    }

    @GetMapping("/GetPanierWithItems")
    @ResponseStatus(HttpStatus.OK)
    public Panier getPanierWithItems(@RequestParam long idUser) {
        return panierService.getPanierWithItems(idUser);
    }

    @PutMapping("/UpdatePanier/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public Panier updatePanier(@PathVariable("idUser") long idUser,@RequestBody List<Item> items) {
        return panierService.updatePanier(idUser, items);
    }

    @DeleteMapping("/deletePanier/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePanier(@PathVariable("idUser") long idUser) {
        panierService.deletePanier(idUser);
    }




//    @GetMapping("/getPanier/{idPanier}")
//    public ResponseEntity<Panier> getPanierById(@PathVariable(value = "idPanier") int idPanier) {
//        Optional<Panier> panier = panierService.findById(idPanier);
//        return panier.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

//    @GetMapping("/getPanierByUserId/{idUser}")
//    public ResponseEntity<Panier> getPanierByUserId(@PathVariable(value = "idUser") int idUser) {
//        Optional<Panier> panier = Optional.ofNullable(panierService.findByUserId(idUser));
//        return panier.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @PutMapping("/updatePanier/{idPanier}")
//    public ResponseEntity<Panier> updatePanier(@PathVariable int idPanier, @RequestBody List<Item> newItems) {
//        Panier updatedPanier = panierService.updatePanier(idPanier, newItems);
//        return updatedPanier != null
//                ? new ResponseEntity<>(updatedPanier, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @DeleteMapping("/deletePanier/{idPanier}")
//    public ResponseEntity<String> deletePanier(@PathVariable(value = "idPanier") int idPanier) {
////        panierService.deletePanier(idPanier);
//        return new ResponseEntity<>("Panier supprimé", HttpStatus.OK);
//    }

}
