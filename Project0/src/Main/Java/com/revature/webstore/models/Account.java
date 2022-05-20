package com.revature.webstore.models;

import java.util.ArrayList;
import java.util.UUID;

public class Account {
    UUID id;
    String username;
    String password;
    ArrayList<Order> order;
    Order cart;
}
