package com.revature.webstore.services;

import com.revature.webstore.DatabaseAccess.LocationDAO;
import com.revature.webstore.DatabaseAccess.OrderDAO;
import com.revature.webstore.models.Location;
import com.revature.webstore.models.Order;

import java.util.List;

public class OrderService {

    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public void register(Order order) {
        orderDAO.save(order);
    }

    public List<Order> getAll() {
        return orderDAO.getAll();
    }


}
