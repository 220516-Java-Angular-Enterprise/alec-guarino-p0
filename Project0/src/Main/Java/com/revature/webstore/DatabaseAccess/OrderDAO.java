package com.revature.webstore.DatabaseAccess;

import com.revature.webstore.models.Order;
import com.revature.webstore.models.Product;
import com.revature.webstore.models.Replica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAO implements CrudDAO<Order>{

    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Order obj) {
        try{
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("INSERT INTO orders (id, account, orderdate, locationid) VALUES(?,?,?,?)");
            ps.setString(1, obj.getId() );
            ps.setString(2, obj.getAccountID() );
            ps.setTimestamp(3, obj.getDate() );
            ps.setString(4, obj.getLocationID() );

            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(Order obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Order getByID(String id) {
        Order nextO = new Order();
        try {

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM orders WHERE id = " + id);
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {

                nextO.setId(rs.getString("id"));
                nextO.setAccountID(rs.getString("account"));
                nextO.setDate(rs.getTimestamp("orderdate"));
                nextO.setLocationID(rs.getString("locationid"));

                System.out.print("Column 1 returned ");
                System.out.println(rs.getString(1));


            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.print("");
            //throw new RuntimeException("An error occurred when trying to access the file.");
        }

        return nextO;
    }

    @Override
    public ArrayList<Order> getAll() {
        ArrayList<Order> orders = new ArrayList<Order>();

        try {

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM orders");
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {
                Order nextO = new Order();

                nextO.setId(rs.getString("id"));
                nextO.setAccountID(rs.getString("account"));
                nextO.setDate(rs.getTimestamp("orderdate"));
                nextO.setLocationID(rs.getString("locationid"));

                System.out.print("Column 1 returned ");
                System.out.println(rs.getString(1));

                orders.add(nextO);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.print("");
            //throw new RuntimeException("An error occurred when trying to access the file.");
        }

        return orders;
    }

    @Override
    public boolean getExistsInColumnByString(String column, String input) {
        return false;
    }

    public Order getRowByColumnValue(String column, String input){
        Order row = new Order();
        try {

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM accounts WHERE " + column + " = " + input);
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {

                row.setId(rs.getString("id"));
                row.setAccountID(rs.getString("account"));
                row.setDate(rs.getTimestamp("orderdate"));
                row.setLocationID(rs.getString("locationid"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {

            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());


            System.out.println("FAILe to get by ! " + column + " using value: " + input);
            //throw new RuntimeException("An error occurred when trying to access the file.");
        }

        return row;

    }

    public ArrayList<String> getAllIDAsString(){
        ArrayList<String> idToReturn = new ArrayList<String>();

        try {
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT id FROM orders");
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {

                idToReturn.add(rs.getString("id"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.print("");
            //throw new RuntimeException("An error occurred when trying to access the file.");
        }

        return idToReturn;
    }

}
