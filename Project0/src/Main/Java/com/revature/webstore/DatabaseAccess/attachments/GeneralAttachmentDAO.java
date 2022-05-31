package com.revature.webstore.DatabaseAccess.attachments;

import com.revature.webstore.DatabaseAccess.CrudDAO;
import com.revature.webstore.DatabaseAccess.DatabaseConnection;
import com.revature.webstore.models.Product;
import com.revature.webstore.models.attachments.GeneralAttachment;
import com.revature.webstore.models.attachments.SightAttachment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GeneralAttachmentDAO implements CrudDAO<GeneralAttachment> {

    @Override
    public void save(GeneralAttachment obj) {
        try{
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("INSERT INTO products_generalattachments (id, hasLight, hasLaser) VALUES(?,?,?)");
            ps.setString(1, obj.getId() );
            ps.setBoolean(2, obj.isHasLight() );
            ps.setBoolean(3, obj.isHasLaser() );

            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(GeneralAttachment obj) {
        throw new RuntimeException("METHOD NOT IMPLEMENTED! update in GeneralAttachmentDAO" );
    }

    @Override
    public void delete(String id) {
        throw new RuntimeException("METHOD NOT IMPLEMENTED! delete in GeneralAttachmentDAO" );
    }

    @Override
    public GeneralAttachment getByID(String id) {
        GeneralAttachment product = new GeneralAttachment();

        try {
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM products_generalattachments WHERE id = '" + id + "'");
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {

                product.setId(rs.getString("id"));
                product.setHasLight(rs.getBoolean("hasLight"));
                product.setHasLaser(rs.getBoolean("hasLaser"));

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
    public ArrayList<GeneralAttachment> getAll() {
        ArrayList<GeneralAttachment> products = new ArrayList<GeneralAttachment>();

        try {
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM products_generalattachments");
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {
                GeneralAttachment nextP = new GeneralAttachment();

                nextP.setId(rs.getString("id"));
                nextP.setHasLight(rs.getBoolean("hasLight"));
                nextP.setHasLaser(rs.getBoolean("hasLaser"));

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

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT " + column + " FROM products_generalattachments WHERE " + column + " = " + input);
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

    public GeneralAttachment getRowByColumnValue(String column, String input){
        GeneralAttachment row = new GeneralAttachment();
        try {

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM products_generalattachments WHERE " + column + " = " + input);
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {

                row.setId(rs.getString("id"));
                row.setHasLight(rs.getBoolean("hasLight"));
                row.setHasLaser(rs.getBoolean("hasLaser"));
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
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT id FROM products_generalattachments");
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
