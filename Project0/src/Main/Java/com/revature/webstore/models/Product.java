package com.revature.webstore.models;

import java.util.Currency;
import java.util.Map;
import java.util.UUID;

public class Product {
    UUID id;
    String name;
    Currency price;
    String description;
    Map<UUID, Integer> locationStock;


}
