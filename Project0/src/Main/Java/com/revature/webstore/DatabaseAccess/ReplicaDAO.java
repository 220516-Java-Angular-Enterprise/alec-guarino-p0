package com.revature.webstore.DatabaseAccess;

import com.revature.webstore.models.Product;
import com.revature.webstore.models.Replica;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReplicaDAO implements CrudDAO<Replica> {
    @Override
    public void save(Replica obj) {
        try{
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("INSERT INTO products_replicas (id, takeunderbarrelattachment, takegeneralattachment, takesightattachment) VALUES(?,?,?,?)");
            ps.setString(1, obj.getId() );
            ps.setBoolean(2, obj.isTakeUnderBarrelAttachment() );
            ps.setBoolean(3, obj.isTakeGeneralAttachment() );
            ps.setBoolean(4, obj.isTakeSightAttachment() );

            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(Replica obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Replica getByID(String id) {
        Replica nextP = new Replica();
        try {

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM products_replicas WHERE id = " + id);
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {

                nextP.setId(rs.getString("id"));
                nextP.setTakeUnderBarrelAttachment( rs.getBoolean("takeunderbarrelattachment"));
                nextP.setTakeGeneralAttachment(     rs.getBoolean("takegeneralattachment"));
                nextP.setTakeSightAttachment(       rs.getBoolean("takesightattachment"));

                System.out.print("Column 1 returned ");
                System.out.println(rs.getString(1));


            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.print("");
            //throw new RuntimeException("An error occurred when trying to access the file.");
        }

        return nextP;
    }

    @Override
    public ArrayList<Replica> getAll() {
        ArrayList<Replica> products = new ArrayList<Replica>();

        try {
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM products_replicas");
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {
                Replica nextP = new Replica();

                nextP.setId(rs.getString("id"));
                nextP.setTakeUnderBarrelAttachment( rs.getBoolean("takeunderbarrelattachment"));
                nextP.setTakeGeneralAttachment(     rs.getBoolean("takegeneralattachment"));
                nextP.setTakeSightAttachment(       rs.getBoolean("takesightattachment"));

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

    public ArrayList<String> getAllIDAsString(){
        ArrayList<String> idToReturn = new ArrayList<String>();

        try {
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT id FROM products_replicas");
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

    @Override
    public boolean getExistsInColumnByString(String column, String input) {

        try {

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT " + column + " FROM products_replicas WHERE " + column + " = " + input);
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

    public Replica getRowByColumnValue(String column, String input){
        Replica row = new Replica();
        try {

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM accounts WHERE " + column + " = " + input);
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {

                row.setId(rs.getString("id"));
                row.setTakeUnderBarrelAttachment( rs.getBoolean("takeunderbarrelattachment"));
                row.setTakeGeneralAttachment(     rs.getBoolean("takegeneralattachment"));
                row.setTakeSightAttachment(       rs.getBoolean("takesightattachment"));
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

}
