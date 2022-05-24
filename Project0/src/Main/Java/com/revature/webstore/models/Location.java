package com.revature.webstore.models;


import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class Location {

    private UUID id;
    private ArrayList<Order> orders;
    private Map<UUID, Integer> productStock;


}
