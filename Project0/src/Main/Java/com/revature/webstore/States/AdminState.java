package com.revature.webstore.States;

import com.revature.webstore.Main;
import com.revature.webstore.services.InputReader;

import java.util.Scanner;

public class AdminState {

    private enum CurrentState{
        index,
        add_product,
        alter_product,
        delete_product,
        view_order_history,
        adjust_account,

        ENDOFSTATES
    }


    private boolean running;
    private CurrentState state;

    public AdminState(){
        running = true;
        state = CurrentState.index;
    }

    public void main(Scanner inputReader, String input){
        while(running){

            switch(state){
                case index:
                    index(inputReader, input);
                    break;
                case add_product:

                    break;
                case alter_product:

                    break;
                case delete_product:

                    break;
                case view_order_history:

                    break;
                case adjust_account:

                    break;
                default:
                    state = CurrentState.index;
            }

        }
    }

    public void index(Scanner inputReader, String input){
        System.out.println( "User: " + Main.getLoggedInAccount().getUsername() );
        System.out.println("Welcome to the ADMIN menu!\n");
        System.out.println("[1] Add a product");
        System.out.println("[2] Alter a product");
        System.out.println("[3] Delete a product");
        System.out.println("[4] View a customer's order history");
        System.out.println("[5] Adjust an account's settings");
        System.out.println("[6] Exit admin menu");
        input = inputReader.nextLine();

        int choice = InputReader.getInput(input);

        if ( choice < CurrentState.ENDOFSTATES.ordinal() || choice > 0 ){
            state = CurrentState.values()[choice];
        }
        if(choice == 6)
            running = false;

    }



}
