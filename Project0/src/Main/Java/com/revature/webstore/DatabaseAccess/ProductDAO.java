package com.revature.webstore.DatabaseAccess;

import com.revature.webstore.models.Product;
import com.revature.webstore.models.Account;

import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.sql.*;


/*
int foovalue = 500;
PreparedStatement st = conn.prepareStatement("SELECT * FROM mytable WHERE columnfoo = ?");
st.setInt(1, foovalue);
ResultSet rs = st.executeQuery();
while (rs.next())
{
    System.out.print("Column 1 returned ");
    System.out.println(rs.getString(1));
}
rs.close();
st.close();

 */

public class ProductDAO implements CrudDAO<Product> {

    String path = "src/main/resources/database/products.txt";
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Product obj) {
        try{
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("INSERT INTO products (id, name, price, description) VALUES(?,?,?,?)");
            ps.setString(1, obj.getId() );
            ps.setString(2, obj.getName() );
            ps.setInt(3, obj.getPrice() );
            ps.setString(4, obj.getDescription() );

            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(Product obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Product getByID(String id) {
        Product nextP = new Product();
        try {

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM products WHERE id = " + id);
            ResultSet rs = ps.executeQuery();

        while (rs.next())  {

            nextP.setId(rs.getString("id"));
            nextP.setName(rs.getString("name"));
            nextP.setPrice(rs.getInt("price"));
            nextP.setDescription(rs.getString("description"));

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
    public ArrayList<Product> getAll() {
        ArrayList<Product> products = new ArrayList<Product>();

        try {
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM products");
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {
                Product nextP = new Product();

                nextP.setId(rs.getString("id"));
                nextP.setName(rs.getString("name"));
                nextP.setPrice(rs.getInt("price"));
                nextP.setDescription(rs.getString("description"));

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

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT " + column + " FROM products WHERE " + column + " = " + input);
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