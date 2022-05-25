package com.revature.webstore;

import com.revature.webstore.DatabaseAccess.AccountDAO;
import com.revature.webstore.DatabaseAccess.ProductDAO;
import com.revature.webstore.models.Product;
import com.revature.webstore.services.AccountService;
import com.revature.webstore.services.InputReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    static final boolean testingDatabaseConnection = false;

    enum CurrentState{
        //General home
        homepage,
        login,
        register,

        //shopping
        shopping,
    /*
    shopping_replica_index,
    shopping_replica_page, //product page

    shopping_accessory_index,
    shopping_accessory_general_accessory,
    shopping_accessory_underbarrel_accessory,
    shopping_accessory_sight_accessory,
    shopping_accessory_page, //product page
    */

        //customer settings
        customer_settings,

        //admin
        admin
    /*
    admin_home,
    admin_add_product
    */


    }

    static CurrentState state = CurrentState.homepage;

    static boolean running = true;
    public static void main(String[] args){

        //Used to test database connection. Gets all products.
        if(testingDatabaseConnection) {
            boolean exists = new AccountService(new AccountDAO()).getExists("username", "'foo'");
            System.out.println("Exist: " + exists);

            ArrayList<Product> productList = new ProductDAO().getAll();

            for (Product p : productList) {
                System.out.println(p);
            }
        }

        Scanner inputReader = new Scanner(System.in);
        String input = "";

        while(running){

            switch(state){
                case homepage:
                    homepage(inputReader, input);
                    break;
                case login:
                    login(inputReader, input);
                    break;
                case register:
                    System.out.println("Register:\n");
                    break;
                case shopping:
                    System.out.println("Store:\n");
                    break;
                case customer_settings:
                    break;
                case admin:
                    break;
                default:
                    state = CurrentState.homepage;
            }
        }
    }

    static void homepage(Scanner inputReader, String input){
        System.out.println("Welcome to the Airsoft Shop!\n");
        System.out.println("[1] Login");
        System.out.println("[2] Register");
        System.out.println("[3] View Store");
        System.out.println("[4] Quit");
        input = inputReader.nextLine();

        int choice = InputReader.getInput(input);

        if (choice < 4 || choice > 0){
            state = CurrentState.values()[choice];
        }
        if(choice == 4)
            running = false;
    }

    static void login(Scanner inputReader, String input){
        boolean takingCurrentField = true;
        String username = "";

        System.out.println("Login ([x] to exit)\n");


        while(takingCurrentField) {
            System.out.print("Username: ");

            //Assemble username for SQL
            input = "'";
            input += inputReader.nextLine();
            if ( input.equals("x") ) {
                state = CurrentState.homepage;
                return;
            }
            input += "'";

            //Check name in SQL
            boolean exists = new AccountService(new AccountDAO()).getExists("username", input);
            if ( exists ){
                username = input;
                takingCurrentField = false;
            }
            else{
                System.out.println("Incorrect username! [x] to exit.");
            }
        }

        takingCurrentField = true;

        while(takingCurrentField) {
            System.out.print("Password: ");

            //Assemble username for SQL
            input = "'";
            input += inputReader.nextLine();
            if ( input.equals("x") ) {
                state = CurrentState.homepage;
                return;
            }
            input += "'";

            //Check name in SQL
            boolean exists = new AccountService(new AccountDAO()).getExists("username", input);
            if ( exists ){
                takingCurrentField = false;
            }
        }

    }

}

