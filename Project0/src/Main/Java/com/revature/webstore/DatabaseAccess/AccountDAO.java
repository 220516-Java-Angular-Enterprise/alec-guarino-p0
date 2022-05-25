package com.revature.webstore.DatabaseAccess;

import com.revature.webstore.models.Account;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDAO implements CrudDAO<Account> {

    String path = "src/main/resources/database/products.txt";
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Account obj) {
        try{
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("INSERT INTO products (id, username, password, role, address) VALUES(?,?,?,?,?)");
            ps.setString(1, obj.getId() );
            ps.setString(2, obj.getUsername() );
            ps.setString(3, obj.getPassword() );
            ps.setString(4, obj.getRole() );
            ps.setString(5, obj.getAddress() );

            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(Account obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Account getByID(String id) {
        Account nextA = new Account();
        try {

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM products WHERE id = " + id);
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {

                nextA.setId(rs.getString("id"));
                nextA.setUsername(rs.getString("username"));
                nextA.setPassword(rs.getString("password"));
                nextA.setRole(rs.getString("role"));
                nextA.setAddress(rs.getString("address"));

                System.out.print("Column 1 returned ");
                System.out.println(rs.getString(1));


            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.print("");
            //throw new RuntimeException("An error occurred when trying to access the file.");
        }

        return nextA;
    }

    @Override
    public ArrayList<Account> getAll() {
        ArrayList<Account> Accounts = new ArrayList<Account>();

        try {

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM products");
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {
                Account nextA = new Account();

                nextA.setId(rs.getString("id"));
                nextA.setUsername(rs.getString("username"));
                nextA.setPassword(rs.getString("password"));
                nextA.setRole(rs.getString("role"));
                nextA.setAddress(rs.getString("address"));

                System.out.print("Column 1 returned ");
                System.out.println(rs.getString(1));

                Accounts.add(nextA);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.print("");
            //throw new RuntimeException("An error occurred when trying to access the file.");
        }

        return Accounts;
    }

    @Override
    public boolean getExistsInColumnByString(String column, String input) {
        try {

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT " + column + " FROM accounts WHERE " + column + " = " + input);
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {
                String val = rs.getString(column);
                System.out.println(val);
                return true;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.print("FAIL!");
            //throw new RuntimeException("An error occurred when trying to access the file.");
        }

        return false;
    }

    public Account getAccountByColumnValue(String column, String input){
        Account account = new Account();
        try {

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT " + column + " FROM accounts WHERE " + column + " = " + input);
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {
                String val = rs.getString(column);
                System.out.println(val);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.print("FAIL!");
            //throw new RuntimeException("An error occurred when trying to access the file.");
        }

        return account;

    }

}
