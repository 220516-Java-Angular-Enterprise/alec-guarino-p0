package com.revature.webstore.services;

import com.revature.webstore.DatabaseAccess.LocationDAO;
import com.revature.webstore.models.Location;

import java.util.List;

public class LocationService {

    private final LocationDAO locationDAO;

    public LocationService(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    public void register(Location location) {
        locationDAO.save(location);
    }

    public List<Location> getAll() {
        return locationDAO.getAll();
    }
}
