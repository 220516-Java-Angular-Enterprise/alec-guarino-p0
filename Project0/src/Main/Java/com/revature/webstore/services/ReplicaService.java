package com.revature.webstore.services;

import com.revature.webstore.DatabaseAccess.ProductDAO;
import com.revature.webstore.DatabaseAccess.ReplicaDAO;
import com.revature.webstore.models.Product;
import com.revature.webstore.models.Replica;

import java.util.List;

public class ReplicaService {

    private final ReplicaDAO replicaDAO;

    public ReplicaService(ReplicaDAO replicaDAO) {
        this.replicaDAO = replicaDAO;
    }

    public void register(Replica product) {
        replicaDAO.save(product);
    }

    public List<Replica> getAll() {
        return replicaDAO.getAll();
    }

    public boolean getExists(String column, String input){
        return replicaDAO.getExistsInColumnByString(column, input);
    }
}
