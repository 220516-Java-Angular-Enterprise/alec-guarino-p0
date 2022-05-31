package com.revature.webstore.DatabaseAccess;

import com.revature.webstore.models.Product;
import com.revature.webstore.models.Replica;

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
}
