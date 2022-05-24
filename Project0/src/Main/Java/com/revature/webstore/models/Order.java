package com.revature.webstore.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Order {
    private UUID accountID;
    private ArrayList<Product> items;
    private UUID locationID;
    private Date date;

}
