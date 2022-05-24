package com.revature.webstore.models;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Map;
import java.util.UUID;


public class Product {

    public enum ProductType {
        standalone,
        Replica,
        PicitinyScope,
        PicitinyBottomRail,
        PicitinyAccessory,
        AKSideRail

    }

    private UUID id;
    private String name;
    private Currency price;
    private String description;
    private Map<UUID, Integer> locationStock;
    private ProductType productType;
    private ArrayList<ProductType> compatibleAttachments;

}
