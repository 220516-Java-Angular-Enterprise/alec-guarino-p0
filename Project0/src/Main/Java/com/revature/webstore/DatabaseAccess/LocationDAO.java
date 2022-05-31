package com.revature.webstore.DatabaseAccess;

import com.revature.webstore.models.Location;
import com.revature.webstore.models.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocationDAO implements CrudDAO<Location>{
    //String path = "src/main/resources/database/products.txt";
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Location obj) {
        try{
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("INSERT INTO locations (id, name, address) VALUES(?,?,?)");
            ps.setString(1, obj.getId() );
            ps.setString(2, obj.getName() );
            ps.setString(3, obj.getAddress() );

            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(Location obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Location getByID(String id) {
        Location nextL = new Location();
        try {

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM locations WHERE id = " + id);
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {

                nextL.setId(rs.getString("id"));
                nextL.setName(rs.getString("name"));
                nextL.setAddress(rs.getString("address"));

                System.out.print("Column 1 returned ");
                System.out.println(rs.getString(1));


            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.print("");
            //throw new RuntimeException("An error occurred when trying to access the file.");
        }

        return nextL;
    }

    @Override
    public ArrayList<Location> getAll() {
        ArrayList<Location> locations = new ArrayList<Location>();

        try {

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM locations");
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {
                Location nextL = new Location();

                nextL.setId(rs.getString("id"));
                nextL.setName(rs.getString("name"));
                nextL.setAddress(rs.getString("address"));

                System.out.print("Column 1 returned ");
                System.out.println(rs.getString(1));

                locations.add(nextL);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.print("");
            //throw new RuntimeException("An error occurred when trying to access the file.");
        }

        return locations;
    }

    @Override
    public boolean getExistsInColumnByString(String column, String input) {
        return false;
    }

    public Location getRowByColumnValue(String column, String input){
        Location row = new Location();
        try {

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM accounts WHERE " + column + " = " + input);
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {

                row.setId(rs.getString("id"));
                row.setName(rs.getString("name"));
                row.setAddress(rs.getString("address"));
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
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT id FROM locations");
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
