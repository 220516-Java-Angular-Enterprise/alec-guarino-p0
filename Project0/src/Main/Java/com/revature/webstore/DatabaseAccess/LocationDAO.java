package com.revature.webstore.DatabaseAccess;

import com.revature.webstore.models.Location;

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
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("INSERT INTO products (id, name, address) VALUES(?,?,?)");
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

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM products WHERE id = " + id);
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {

                nextL.setId(rs.getString("id"));
                nextL.setName(rs.getString("username"));
                nextL.setAddress(rs.getString("password"));

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

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM products");
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {
                Location nextL = new Location();

                nextL.setId(rs.getString("id"));
                nextL.setName(rs.getString("username"));
                nextL.setAddress(rs.getString("password"));

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
}
