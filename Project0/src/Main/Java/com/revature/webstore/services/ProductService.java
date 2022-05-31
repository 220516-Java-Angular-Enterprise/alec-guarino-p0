package com.revature.webstore.services;

import com.revature.webstore.DatabaseAccess.ProductDAO;
import com.revature.webstore.models.Product;

import java.util.List;

public class ProductService {

    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void register(Product product) {
        productDAO.save(product);
    }

    public List<Product> getAll() {
        return productDAO.getAll();
    }

    public boolean getExists(String column, String input){
        return productDAO.getExistsInColumnByString(column, input);
    }

    public Product getRowByColumnValue(String column, String input){
        return productDAO.getRowByColumnValue(column, input);
    }

}
