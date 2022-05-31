package com.revature.webstore.States;

import com.revature.webstore.DatabaseAccess.ProductDAO;
import com.revature.webstore.DatabaseAccess.ReplicaDAO;
import com.revature.webstore.DatabaseAccess.attachments.GeneralAttachmentDAO;
import com.revature.webstore.DatabaseAccess.attachments.SightAttachmentDAO;
import com.revature.webstore.DatabaseAccess.attachments.UnderBarrelAttachmentDAO;
import com.revature.webstore.Main;
import com.revature.webstore.models.Product;
import com.revature.webstore.models.Replica;
import com.revature.webstore.models.attachments.GeneralAttachment;
import com.revature.webstore.models.attachments.SightAttachment;
import com.revature.webstore.models.attachments.UnderBarrelAttachment;
import com.revature.webstore.services.InputReader;
import com.revature.webstore.services.ProductService;
import com.revature.webstore.services.ReplicaService;
import com.revature.webstore.services.attachments.GeneralAttachmentService;
import com.revature.webstore.services.attachments.SightAttachmentService;
import com.revature.webstore.services.attachments.UnderBarrelAttachmentService;

import java.util.Scanner;
import java.util.UUID;

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

    public enum ProductType{
        general_attachment,
        under_barrel_attachment,
        sight_attachment,
        replica,

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
                    addProduct(inputReader, input);
                    break;
                case alter_product:
                    state = CurrentState.index;
                    break;
                case delete_product:
                    state = CurrentState.index;
                    break;
                case view_order_history:
                    state = CurrentState.index;
                    break;
                case adjust_account:
                    state = CurrentState.index;
                    break;
                default:
                    state = CurrentState.index;
            }

        }
    }

    private void addProduct(Scanner inputReader, String input) {
        boolean takingCurrentField = true;
        ProductType currentProductType = ProductType.ENDOFSTATES;
        Product newProduct = new Product();
        newProduct.setId(UUID.randomUUID().toString());

        System.out.println("Add a product ([x] to exit)\n");

        System.out.println( "User: " + Main.getLoggedInAccount().getUsername() );
        System.out.println("Welcome to the ADMIN menu!\n");
        System.out.println("[1] Add a general attachment");
        System.out.println("[2] Add an under barrel attachment");
        System.out.println("[3] Add a sight attachment");
        System.out.println("[4] Add a replica");
        System.out.println("[5] Return to Admin index.");
        input = inputReader.nextLine();

        int choice = InputReader.getInput(input);

        if ( choice < ProductType.ENDOFSTATES.ordinal() || choice > 0 ){
            state = CurrentState.values()[choice];
        }
        if(choice == 5)
            state = CurrentState.index;

        //Product name:
        while(takingCurrentField) {
            System.out.print("Product name: ");

            //Assemble username for SQL
            input = "";
            input += inputReader.nextLine();
            if ( input.equals("x") ) {
                state = CurrentState.index;
                return;
            }
            input += "";


            System.out.println("Name input: " + input);
            //Check name in SQL
            //boolean exists = new AccountService(new AccountDAO()).getExists("username", input);
            newProduct.setName(input);
            System.out.println("Confirm: '" + newProduct.getName() + "' y/n");
            input = inputReader.nextLine();
            if(input.equals("y"))
                takingCurrentField = false;
        }

        //Fail out if product with same name exists.
        ProductService productService = new ProductService(new ProductDAO());
        boolean exists = productService.getExists("name", newProduct.getName());
        if (exists){
            System.out.println("Product exists! No longer adding product.");
            state = CurrentState.index;
            return;
        }


        takingCurrentField = true;
        //Price:
        while(takingCurrentField) {
            System.out.println("Enter Price including cents (IE 1000 = $10.00): ");

            //Assemble username for SQL
            input = inputReader.nextLine();

            int newPrice = InputReader.getInput(input);
            if ( input.equals("x") ) {
                state = CurrentState.index;
                return;
            }
            if (newPrice > 0) {
                System.out.println("Confirm: '" + newPrice + "' y/n");
                input = inputReader.nextLine();
                if (input.equals("y"))
                    takingCurrentField = false;
            }
            else{
                System.out.println("Please enter digits only.");
            }
        }

        takingCurrentField = true;
        //Description:
        while(takingCurrentField) {
            System.out.print("Product description(Last chance to cancel [x]): ");

            //Assemble username for SQL
            input = "";
            input += inputReader.nextLine();
            if ( input.equals("x") ) {
                state = CurrentState.index;
                return;
            }
            input += "";


            System.out.println("Description input: " + input);
            //Check name in SQL
            //boolean exists = new AccountService(new AccountDAO()).getExists("username", input);
            newProduct.setDescription(input);
            System.out.println("Confirm: '" + newProduct.getDescription() + "' y/n");
            input = inputReader.nextLine();
            if(input.equals("y"))
                takingCurrentField = false;
        }

        //Request details for type of product this is.
        switch (currentProductType){
            case general_attachment:
                addGeneralAttachment(inputReader, input, newProduct.getId());
                break;
            case under_barrel_attachment:
                addUnderBarrelAttachment(inputReader, input, newProduct.getId());
                break;
            case sight_attachment:
                addSightAttachment(inputReader, input, newProduct.getId());
                break;
            case replica:
                addReplica(inputReader, input, newProduct.getId());
                break;
            default:
                System.out.println("FAILED TO DETERMINE PRODUCT TYPE!!!! Product type selected: " + currentProductType.toString());
                break;
        }


        System.out.println("Registering...");
        //<editor-fold desc="Account setting">
        //</editor-fold>

        productService.register(newProduct);
    }


    //<editor-fold desc="Add sub product types">
    private void addGeneralAttachment(Scanner inputReader, String input, String id){
        GeneralAttachment newGeneralAttachment = new GeneralAttachment();
        newGeneralAttachment.setId(id);


        boolean takingCurrentField = true;
        //has Light:
        while(takingCurrentField) {
            System.out.print("Does this product have a light? [y] [n]: ");

            input = "";
            input += inputReader.nextLine();
            input.toLowerCase();

            if(input.equals("y")) {
                newGeneralAttachment.setHasLight(true);
                takingCurrentField = false;
            }
            else if(input.equals("n")){
                newGeneralAttachment.setHasLight(false);
                takingCurrentField = false;
            }
            else{
                System.out.println("Incorrect input!");
            }
        }

        takingCurrentField = true;
        //has Laser:
        while(takingCurrentField) {
            System.out.print("Does this product have a laser? [y] [n]: ");

            input = "";
            input += inputReader.nextLine();
            input.toLowerCase();

            if(input.equals("y")) {
                newGeneralAttachment.setHasLaser(true);
                takingCurrentField = false;
            }
            else if(input.equals("n")){
                newGeneralAttachment.setHasLaser(false);
                takingCurrentField = false;
            }
            else{
                System.out.println("Incorrect input!");
            }
        }
        new GeneralAttachmentService(new GeneralAttachmentDAO()).register(newGeneralAttachment);
    }

    private void addUnderBarrelAttachment(Scanner inputReader, String input, String id){
        UnderBarrelAttachment newAttachment = new UnderBarrelAttachment();
        newAttachment.setId(id);

        boolean takingCurrentField = true;

        //Is M203:
        while(takingCurrentField) {
            System.out.print("Is this product an M203 launcher? [y] [n]: ");

            input = "";
            input += inputReader.nextLine();
            input.toLowerCase();

            if(input.equals("y")) {
                newAttachment.setM203(true);
                takingCurrentField = false;
            }
            else if(input.equals("n")){
                newAttachment.setM203(false);
                takingCurrentField = false;
            }
            else{
                System.out.println("Incorrect input!");
            }
        }
        new UnderBarrelAttachmentService(new UnderBarrelAttachmentDAO()).register(newAttachment);
    }

    private void addSightAttachment(Scanner inputReader, String input, String id){
        SightAttachment newAttachment = new SightAttachment();
        newAttachment.setId(id);

        boolean takingCurrentField = true;

        //Has zoom:
        while(takingCurrentField) {
            System.out.print("Does this sight have zoom? [y] [n]: ");

            input = "";
            input += inputReader.nextLine();
            input.toLowerCase();

            if(input.equals("y")) {
                newAttachment.setHasZoom(true);
                takingCurrentField = false;
            }
            else if(input.equals("n")){
                newAttachment.setHasZoom(false);
                takingCurrentField = false;
            }
            else{
                System.out.println("Incorrect input!");
            }
        }
        new SightAttachmentService(new SightAttachmentDAO()).register(newAttachment);
    }

    private void addReplica(Scanner inputReader, String input, String id){
        Replica newReplica = new Replica();
        newReplica.setId(id);

        boolean takingCurrentField = true;

        //Has zoom:
        while(takingCurrentField) {
            System.out.print("Does this replica take under barrel attachments? [y] [n]: ");

            input = "";
            input += inputReader.nextLine();
            input.toLowerCase();

            if(input.equals("y")) {
                newReplica.setTakeUnderBarrelAttachment(true);
                takingCurrentField = false;
            }
            else if(input.equals("n")){
                newReplica.setTakeUnderBarrelAttachment(false);
                takingCurrentField = false;
            }
            else{
                System.out.println("Incorrect input!");
            }
        }
        takingCurrentField = true;

        //Has zoom:
        while(takingCurrentField) {
            System.out.print("Does this replica take general attachments? [y] [n]: ");

            input = "";
            input += inputReader.nextLine();
            input.toLowerCase();

            if(input.equals("y")) {
                newReplica.setTakeGeneralAttachment(true);
                takingCurrentField = false;
            }
            else if(input.equals("n")){
                newReplica.setTakeGeneralAttachment(false);
                takingCurrentField = false;
            }
            else{
                System.out.println("Incorrect input!");
            }
        }

        takingCurrentField = true;

        //Has zoom:
        while(takingCurrentField) {
            System.out.print("Does this replica take sights as attachments? [y] [n]: ");

            input = "";
            input += inputReader.nextLine();
            input.toLowerCase();

            if(input.equals("y")) {
                newReplica.setTakeSightAttachment(true);
                takingCurrentField = false;
            }
            else if(input.equals("n")){
                newReplica.setTakeSightAttachment(false);
                takingCurrentField = false;
            }
            else{
                System.out.println("Incorrect input!");
            }
        }
        new ReplicaService(new ReplicaDAO()).register(newReplica);
    }
    //</editor-fold>


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
