package com.revature.webstore.DatabaseAccess;

import java.util.ArrayList;
import java.util.UUID;

public interface CrudDAO<T> {
    void save(T obj);
    void update(T obj);
    void delete(String id);
    T getByID(String id);
    ArrayList<T> getAll();

    boolean getExistsInColumnByString(String column, String input);

}
