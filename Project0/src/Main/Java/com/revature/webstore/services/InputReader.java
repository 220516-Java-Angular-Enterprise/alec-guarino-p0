package com.revature.webstore.services;

import com.revature.webstore.Main;

public class InputReader {

    public static int getInput(String input){
        try{
            int choice = Integer.parseInt(input);
            return choice;
        }catch (Exception e){
            return -1;
        }
    }

}
