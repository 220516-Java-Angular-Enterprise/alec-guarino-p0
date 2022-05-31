package com.revature.webstore.DatabaseAccess.attachments;

import com.revature.webstore.DatabaseAccess.CrudDAO;
import com.revature.webstore.DatabaseAccess.DatabaseConnection;
import com.revature.webstore.models.attachments.GeneralAttachment;
import com.revature.webstore.models.attachments.SightAttachment;
import com.revature.webstore.models.attachments.UnderBarrelAttachment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SightAttachmentDAO implements CrudDAO<SightAttachment> {
    @Override
    public void save(SightAttachment obj) {
        try{
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("INSERT INTO products_sightattachments (id, hasZoom) VALUES(?,?)");
            ps.setString(1, obj.getId() );
            ps.setBoolean(2, obj.isHasZoom() );

            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(SightAttachment obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public SightAttachment getByID(String id) {
        SightAttachment product = new SightAttachment();

        try {
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM products_sightattachments WHERE id = '" + id + "'");
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {

                product.setId(rs.getString("id"));
                product.setHasZoom(rs.getBoolean("hasZoom"));

                System.out.print("Column 1 returned ");
                System.out.println(rs.getString(1));

                return product;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.print("");
            //throw new RuntimeException("An error occurred when trying to access the file.");
        }

        return product;
    }

    @Override
    public ArrayList<SightAttachment> getAll() {
        ArrayList<SightAttachment> products = new ArrayList<SightAttachment>();

        try {
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM products_sightattachments");
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {
                SightAttachment nextP = new SightAttachment();

                nextP.setId(rs.getString("id"));
                nextP.setHasZoom(rs.getBoolean("hasZoom"));

                System.out.print("Column 1 returned ");
                System.out.println(rs.getString(1));

                products.add(nextP);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.print("");
            //throw new RuntimeException("An error occurred when trying to access the file.");
        }

        return products;
    }

    @Override
    public boolean getExistsInColumnByString(String column, String input) {
        try {

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT " + column + " FROM products_sightattachments WHERE " + column + " = " + input);
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {
                String val = rs.getString(column);
                //System.out.println(val);
                rs.close();
                ps.close();
                return true;
                //System.out.print("Column 1 returned ");
                //System.out.println(rs.getString(1));
            }

        } catch (SQLException e) {
            System.out.println("FAILed to see if exists!!");
            //throw new RuntimeException("An error occurred when trying to access the file.");
        }

        return false;
    }

    public SightAttachment getRowByColumnValue(String column, String input){
        SightAttachment row = new SightAttachment();
        try {

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM products_sightattachments WHERE " + column + " = " + input);
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {

                row.setId(rs.getString("id"));
                row.setHasZoom(rs.getBoolean("hasZoom"));
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
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT id FROM products_sightattachments");
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
