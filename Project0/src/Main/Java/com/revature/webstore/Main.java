package com.revature.webstore;

import com.revature.webstore.DatabaseAccess.AccountDAO;
import com.revature.webstore.DatabaseAccess.ProductDAO;
import com.revature.webstore.States.AdminState;
import com.revature.webstore.models.Account;
import com.revature.webstore.models.Product;
import com.revature.webstore.services.AccountService;
import com.revature.webstore.services.InputReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;


public class Main {

    static final boolean testingDatabaseConnection = true;

    private enum CurrentState{
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
        admin,
    /*
    admin_home,
    admin_add_product
    */

        ENDOFSTATES
    }

    static CurrentState state = CurrentState.homepage;

    static Account loggedInAccount;
    public static  Account getLoggedInAccount(){return loggedInAccount;}
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
                    register(inputReader, input);
                    break;
                case shopping:
                    System.out.println("Store:\n");
                    break;
                case customer_settings:
                    break;
                case admin:
                    new AdminState().main(inputReader, input);
                    break;
                default:
                    state = CurrentState.homepage;
            }
        }
    }

    static void homepage(Scanner inputReader, String input){
        if (loggedInAccount != null)
            System.out.println( "User: " + loggedInAccount.getUsername() );
        System.out.println("Welcome to the Airsoft Shop!\n");
        System.out.println("[1] Login");
        System.out.println("[2] Register");
        System.out.println("[3] View Store");
        System.out.println("[4] Quit");
        if ( loggedInAccount != null && loggedInAccount.getRole().equals("admin") )
            System.out.println("[5] Admin Menu");
        input = inputReader.nextLine();

        int choice = InputReader.getInput(input);

        if (choice < CurrentState.ENDOFSTATES.ordinal() || choice > 0 ||
                (loggedInAccount != null && loggedInAccount.getRole().equals("admin") && choice == 5)
        ){
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
            if ( input.equals("'x") ) {
                state = CurrentState.homepage;
                return;
            }
            input += "'";


            System.out.println("Username input: " + input);
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

        Account loggingInAccount = new AccountService( new AccountDAO()).getAccountByColumnValue("username", username);
        takingCurrentField = true;

        while(takingCurrentField) {
            System.out.print("Password: ");

            //Assemble username for SQL
            input = "";
            input += inputReader.nextLine();
            if ( input.equals("x") ) {

                state = CurrentState.homepage;
                return;
            }


            //Check name in SQL

            if ( input.equals(loggingInAccount.getPassword()) ){
                takingCurrentField = false;
                System.out.println("Log in verified...\n");
            }
        }

        state = CurrentState.homepage;
        loggedInAccount = loggingInAccount;
    }

    static void register(Scanner inputReader, String input){
        boolean takingCurrentField = true;
        String username = "";
        String password = "";

        System.out.println("Register ([x] to exit)\n");


        while(takingCurrentField) {
            System.out.print("Username: ");

            //Assemble username for SQL
            input = "";
            input += inputReader.nextLine();
            if ( input.equals("x") ) {
                state = CurrentState.homepage;
                return;
            }
            input += "";


            System.out.println("Username input: " + input);
            //Check name in SQL
            boolean exists = new AccountService(new AccountDAO()).getExists("username", input);
            if ( !exists ){
                username = input;
                System.out.println("Confirm: '" + username + "' y/n");
                input = inputReader.nextLine();
                if(input.equals("y"))
                    takingCurrentField = false;
            }
            else{
                System.out.println("Username exists! [x] to exit.");
            }
        }

        //Account loggingInAccount = new AccountService( new AccountDAO()).getAccountByColumnValue("username", username);
        takingCurrentField = true;

        while(takingCurrentField) {
            System.out.print("Password: ");

            //Assemble username for SQL
            input = "";
            input += inputReader.nextLine();
            if ( input.equals("x") ) {

                state = CurrentState.homepage;
                return;
            }

            password = input;
            System.out.println("Confirm: '" + password + "' y/n");
            input = inputReader.nextLine();
            if(input.equals("y"))
                takingCurrentField = false;
        }


        System.out.println("Registering...");
        //<editor-fold desc="Account setting">
        Account accountToAdd = new Account();

        UUID newID = UUID.randomUUID();

        accountToAdd.setId(newID.toString());
        accountToAdd.setUsername(username);
        accountToAdd.setPassword(password);
        accountToAdd.setRole("guest");
        accountToAdd.setAddress("NO ADDRESS");
        //</editor-fold>

        AccountService accService = new AccountService(new AccountDAO());
        boolean exists = accService.getExists("username", username);

        if (!exists)
            accService.register(accountToAdd);
        else{
            System.out.println("Account exists! No longer creating account.");
        }





        state = CurrentState.homepage;
        //loggedInAccount = loggingInAccount;



    }


}

