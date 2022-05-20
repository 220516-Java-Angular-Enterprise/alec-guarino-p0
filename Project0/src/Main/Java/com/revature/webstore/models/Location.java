package com.revature.webstore.models;


import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class Location {

    UUID id;
    ArrayList<Order> orders;

    Map<UUID, Integer> productStock;


}
