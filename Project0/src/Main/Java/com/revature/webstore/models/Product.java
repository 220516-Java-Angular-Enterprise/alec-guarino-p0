package com.revature.webstore.models;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Map;
import java.util.UUID;


public class Product {

    public enum ProductType {
        none,
        replica,
        sight_attachment,
        under_barrel_attachment,
        general_attachment

    }

    private String id;
    private String name;
    private int price;
    private String description;

    //<editor-fold desc = "Get/Set">


    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //</editor-fold>


}
