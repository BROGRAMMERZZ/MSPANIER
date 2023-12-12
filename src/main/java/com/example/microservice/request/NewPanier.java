package com.example.microservice.request;

import com.example.microservice.entities.Item;
import lombok.Data;

import java.util.List;


@Data
public class NewPanier {
    int userId;
    private List<Item> items;
}
