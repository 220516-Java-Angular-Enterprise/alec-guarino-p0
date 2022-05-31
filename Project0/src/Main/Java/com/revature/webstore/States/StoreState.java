package com.revature.webstore.States;

import com.revature.webstore.DatabaseAccess.ProductDAO;
import com.revature.webstore.DatabaseAccess.ReplicaDAO;
import com.revature.webstore.Main;
import com.revature.webstore.models.Order;
import com.revature.webstore.models.Product;
import com.revature.webstore.services.InputReader;
import com.revature.webstore.services.ProductService;
import com.revature.webstore.services.ReplicaService;

import java.util.ArrayList;
import java.util.Scanner;

public class StoreState {


    private enum CurrentState{
        index,
        view_replicas,
        view_accessories,
        checkout,


        /*
        view_replica_page,
        view_accessories_under_barrel,
        view_accessories_general,
        view_accessories_sight,
        view_accessory_page,

        //Maybe segment out? yeah, for SOLID
        manage_account,
        view_history,
        view_funds,
        add_funds,
        */

        ENDOFSTATES
    }

    private boolean running;
    private CurrentState state;

    ArrayList<Product> productsBeingViewed = new ArrayList<Product>();

    public StoreState(){
        running = true;
        state = CurrentState.index;
    }

    public void main(Scanner inputReader, String input){
        while(running){

            switch(state){
                case index:
                    index(inputReader, input);
                    break;
                case view_replicas:
                    viewReplicas(inputReader, input);
                    break;
                case view_accessories:
                    viewAccessories(inputReader, input);
                    break;
                case checkout:
                    checkout(inputReader, input);
                    break;
                default:
                    state = CurrentState.index;
            }

        }
    }

    private void checkout(Scanner inputReader, String input) {

    }

    private void viewAccessories(Scanner inputReader, String input) {

    }

    private void viewReplicas(Scanner inputReader, String input) {
        ArrayList<String> replicaIDs = new ReplicaService(new ReplicaDAO()).getAllIDAsString();
        ProductService productService = new ProductService(new ProductDAO());

        productsBeingViewed.clear();
        for(String s : replicaIDs){
            productsBeingViewed.add(    productService.getRowByColumnValue("id", "'" + s + "'")     );
        }

        for(Product p : productsBeingViewed){
            System.out.println(p);
        }

        state = CurrentState.index;

    }

    private void index(Scanner inputReader, String input) {
        System.out.println("Welcome to the Airsoft Shop!\n");
        System.out.println("[1] View replicas");
        System.out.println("[2] View accessories");
        System.out.println("[3] Checkout");
        System.out.println("[4] Quit");
        input = inputReader.nextLine();

        int choice = InputReader.getInput(input);

        if ( choice < CurrentState.ENDOFSTATES.ordinal() && choice > 0 ) {
            state = CurrentState.values()[choice];
        }
        if(choice == 4)
            running = false;
    }

}
