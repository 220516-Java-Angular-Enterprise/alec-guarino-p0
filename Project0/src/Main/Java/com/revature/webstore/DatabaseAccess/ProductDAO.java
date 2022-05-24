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

    @Override
    public void save(Product obj) {
        try{
            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("");
            System.out.println("");
        } catch (Exception e) {
            //throw SQLException exc;
        }
    }

    @Override
    public void update(Product obj) {

    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Product getByID(UUID id) {
        return null;
    }

    @Override
    public ArrayList<Product> getAll() {
        ArrayList<Product> products = new ArrayList<Product>();

        try {

            PreparedStatement ps = DatabaseConnection.getCon().prepareStatement("SELECT * FROM mytable");
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                System.out.print("Column 1 returned ");
                System.out.println(rs.getString(1));

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.print("");
            //throw new RuntimeException("An error occurred when trying to access the file.");
        }

        return products;
    }

    public Account getUserByUsernameAndPassword(String un, String pw) {
        Account user = new Account();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            String userData; // id:username:password:role
            while ((userData = br.readLine()) != null) {
                String[] userArr = userData.split(":"); // [id, username, password, role]
                String id = userArr[0];
                String username = userArr[1];
                String password = userArr[2];
                //String role = userArr[3];

                if (un.equals(username)) {
                    user.setUsername(id);
                    user.setUsername(username);
                    //user.set(role);

                    if (pw.equals(password)) user.setPassword(password);
                    else break;
                } else if (pw.equals(password)) user.setPassword(password);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("An error occurred when trying to access the file.");
        } catch (IOException e) {
            throw new RuntimeException("An error occurred when trying to access the file information.");
        }

        return user;
    }

}