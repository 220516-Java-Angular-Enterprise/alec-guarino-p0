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
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("INSERT INTO accounts (id, username, password, role, address, credits) VALUES(?,?,?,?,?,?)");
            ps.setString(   1, obj.getId()          );
            ps.setString(   2, obj.getUsername()    );
            ps.setString(   3, obj.getPassword()    );
            ps.setString(   4, obj.getRole()        );
            ps.setString(   5, obj.getAddress()     );
            ps.setInt(      6, obj.getCredits()     );

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

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM accounts WHERE id = " + id);
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {

                nextA.setId(rs.getString("id"));
                nextA.setUsername(rs.getString("username"));
                nextA.setPassword(rs.getString("password"));
                nextA.setRole(rs.getString("role"));
                nextA.setAddress(rs.getString("address"));
                nextA.setCredits(rs.getInt("credits"));

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

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM accounts");
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {
                Account nextA = new Account();

                nextA.setId(rs.getString("id"));
                nextA.setUsername(rs.getString("username"));
                nextA.setPassword(rs.getString("password"));
                nextA.setRole(rs.getString("role"));
                nextA.setAddress(rs.getString("address"));
                nextA.setCredits(rs.getInt("credits"));

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

    public Account getRowByColumnValue(String column, String input){
        Account account = new Account();
        try {

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM accounts WHERE " + column + " = " + input);
            ResultSet rs = ps.executeQuery();

            while (rs.next())  {
                //System.out.println(rs.getString( "id" ));
                //System.out.println(rs.getString( "username" ));
                //System.out.println(rs.getString( "password" ));

                account.setId(rs.getString("id"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setRole(rs.getString("role"));
                account.setAddress(rs.getString("address"));
                account.setCredits(rs.getInt("credits"));
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

        return account;

    }

    public ArrayList<String> getAllIDAsString(){
        ArrayList<String> idToReturn = new ArrayList<String>();

        try {
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT id FROM accounts");
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
