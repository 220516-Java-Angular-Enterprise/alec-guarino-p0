package com.revature.webstore.DatabaseAccess;

import java.util.ArrayList;
import java.util.UUID;

public interface CrudDAO<T> {
    void save(T obj);
    void update(T obj);
    void delete(UUID id);
    T getByID(UUID id);
    ArrayList<T> getAll();

}
