package com.revature.webstore.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.Date;

public class Order {
    private String id;
    private String accountID;
    private Timestamp date;
    private String locationID;


    //<editor-fold desc="Get/Set">
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    //</editor-fold>


}
