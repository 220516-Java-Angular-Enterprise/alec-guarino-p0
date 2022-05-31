package com.revature.webstore.DatabaseAccess.attachments;

import com.revature.webstore.DatabaseAccess.CrudDAO;
import com.revature.webstore.DatabaseAccess.DatabaseConnection;
import com.revature.webstore.models.attachments.GeneralAttachment;
import com.revature.webstore.models.attachments.UnderBarrelAttachment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UnderBarrelAttachmentDAO implements CrudDAO<UnderBarrelAttachment> {
    @Override
    public void save(UnderBarrelAttachment obj) {
        try{
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("INSERT INTO products_underbarrelattachments (id, isM203) VALUES(?,?)");
            ps.setString(1, obj.getId() );
            ps.setBoolean(2, obj.isM203() );

            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(UnderBarrelAttachment obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public UnderBarrelAttachment getByID(String id) {
        UnderBarrelAttachment product = new UnderBarrelAttachment();

        try {
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM products_underbarrelattachments WHERE id = '" + id + "'");
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {

                product.setId(rs.getString("id"));
                product.setM203(rs.getBoolean("isM203"));

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
    public ArrayList<UnderBarrelAttachment> getAll() {
        ArrayList<UnderBarrelAttachment> products = new ArrayList<UnderBarrelAttachment>();

        try {
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM products_underbarrelattachments");
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {
                UnderBarrelAttachment nextP = new UnderBarrelAttachment();

                nextP.setId(rs.getString("id"));
                nextP.setM203(rs.getBoolean("isM203"));

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

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT " + column + " FROM products_underbarrelattachments WHERE " + column + " = " + input);
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
}
