package com.revature.webstore.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Order {
    UUID accountID;
    ArrayList<Product> items;
    UUID locationID;
    Date date;


}
