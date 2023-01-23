package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Date;



import java.util.*;
import java.lang.*;



public class Main extends Application {

    Stage login_stage;

    Stage employeeMenu_stage;
    Stage managerMenu_stage;


    Employee worker;


    public static List<Employee> em = new ArrayList();

    public static List<Employee> managerList = new ArrayList();

    public static List<Product> productList = new ArrayList();

    public static List<Double> transactions = new ArrayList();

    public static List<Double> creditDebit = new ArrayList();

    public static List<Double> cashTransac = new ArrayList();

    public static List<Shift> shiftList = new ArrayList();





    public static void main(String[] args) throws IOException {



        setUp();


        launch(args);


    }


    public static void setUp() throws IOException {

        try {
            File employees = new File("employee.txt");
            File products = new File("product.txt");
            File shifts = new File("shift.txt");

            if (employees.createNewFile()){
                System.out.println("Employee File created: ");

            }

            if(products.createNewFile()){
                System.out.println("Product File Created");
            }

            if(shifts.createNewFile()){
                System.out.println("Schedule/Shifts File created");
            }

            else if (!employees.createNewFile()){
                System.out.println("Employee File already exists");

                Scanner sc = new Scanner(employees);
                while(sc.hasNextLine()){
                    String line = sc.nextLine();
                    String[] line_parts = line.split(" ");




                    if(line_parts[0].equals("M")){
                        Employee employee = new Employee(line_parts[0],line_parts[1],line_parts[2],line_parts[3],line_parts[4],line_parts[5]);
                        managerList.add(employee);
                    }

                    else if (line_parts[0].equals("E")){
                        Employee employee = new Employee(line_parts[0],line_parts[1],line_parts[2],line_parts[3],line_parts[4],line_parts[5]);
                        em.add(employee);

                    }
                }
            }

            else if (!products.createNewFile()){
                System.out.println("Product File already exists");

                Scanner scan = new Scanner(products);
                while(scan.hasNextLine()){

                    String lines =scan.nextLine();
                    String [] lines_parts = lines.split(" ");

                    Product product = new Product(lines_parts[0],lines_parts[1],lines_parts[2],lines_parts[3]);

                    productList.add(product);
                }
            }





        } catch (IOException e){
            System.out.println("Error Occured");
        }

        // Reading Existing Files


       // FileReader fr = new FileReader("employee.txt");


       // for(Employee employ : e)


    }

    @Override
    public void start(Stage stage) throws Exception {

       login();









    }

    // u don't wanna keep readign and splitting the files everytime--- store it in an array so u have easy access



    // add position

    public void login(){

        Scanner scan = new Scanner(System.in);


        System.out.println("Enter first name: ");
        String first_name = scan.nextLine();

        System.out.println("Enter Last Name : ");

        String last_name = scan.nextLine();

        System.out.println("Enter pin code: ");

        String user_code = scan.nextLine();

        int tries = 0;

        boolean employee = false;
        boolean manager=false;

        for(Employee temp: em){
            if(temp.getFirstName().equals(first_name)){
                if(temp.getLastName().equals(last_name)){
                    if(temp.getPincode().equals(user_code)){
                        employee=true;
                        employeeOptions(temp);






                    }

                    else{
                        System.out.println("Incorrect Password . Please try again");
                    }
                }
                else{
                    System.out.println("Incorrect Password . Please try again");
                }
            }
            else{

                System.out.println("Incorrect Password . Please try again");
            }

        }

        if(employee==false){
            for(Employee man : managerList){
                if(man.getFirstName().equals(first_name)){
                    if(man.getLastName().equals(last_name)){
                        if(man.getPincode().equals(user_code)){
                            manager=true;

                            managerOptions(man);
                        }

                        else {

                            System.out.println("Incorrect Password . Please try again");


                        }
                    }

                    else{
                        System.out.println("Incorrect Password . Please try again");

                    }
                }

                else{
                    System.out.println("Incorrect Password . Please try again");

                }
            }
        }












    }

    public  void employeeOptions(Employee worker){


        System.out.println("Logged in as : " + worker.getFirstName() + " " + worker.getLastName());

        System.out.println("\n Employee Menu    1.Cash register  2. Look up an Item  3.Schedule Options  3. Log Out");

        Scanner input = new Scanner(System.in);

        String option=input.nextLine();

        if(option.equals("1")){
            cashSystem(worker);
        }

        else if (option.equals("2")){

            System.out.println("Product Serial Number : ");
            String look_up = input.nextLine();
            itemLookUp(worker);

        }

        else if (option.equals("3")){
            shiftOptions(worker);
        }

        else if (option.equals("4")){
            login();
        }


    }

    public void managerOptions(Employee man){

        System.out.println("Logged in as : " + worker.getFirstName() + " " + worker.getLastName());

        Scanner scan = new Scanner(System.in);


        System.out.println("\n Manager Menu \n     \n 1.Add an Employee   2.Add a Product  3. Cahs Register 4. Look up a Product 5. Schedule Options 6.Logout  :"  );

        String input = scan.nextLine();

        if(input.equals("1")){
            addEmployee(worker);
        }

        else if (input.equals("2")){
            addProduct(worker);
        }

        else if (input.equals("3")){
            cashSystem(worker);
        }

        else if (input.equals("4")){
            itemLookUp(worker);
        }

        else if (input.equals("5")){
            shiftOptions(worker);
        }

        else if (input.equals("6")){
            login();
        }

    }


    public  void addEmployee(Employee manager) {




        System.out.println("Create Account\n");
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter Position: O: owner  M: manager E: Basic Employee");
        String position = scan.nextLine();

        System.out.println("Enter First Name: ");
        String firstName = scan.nextLine();

        System.out.println("Enter Last Name: ");
        String lastName = scan.nextLine();


        System.out.println("Enter your phone number : ");
        String number = scan.nextLine();

        System.out.println("Create a 4 digit Pin Code : ");
        String pincode = scan.nextLine();

        int attendance=0;

/*

        while (!(pincode.length()==4)){
            accepted=false;
            System.out.println("Pin Code needs to be 4 digits. Please re-try :  ");
            pincode = scan.nextLine();
        }




        for(Employee e: em){
            if(e.getPincode().equals(pincode)){
                accepted=false;
                System.out.println("Pin Code already taken. Please try again : ");
                pincode = scan.nextLine();
            }
            else{
                accepted=true;
                break;
            }
        }

*/
        //Path filePath = Path.of("employee.txt");


        try{

            FileWriter employeeWriter = new FileWriter("employee.txt",true);
            employeeWriter.write("\n" + position + " " + firstName + " " + lastName + " " + number + " " + pincode + " " + attendance);
            employeeWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }


        Employee newEmployee = new Employee (position,firstName, lastName, number, pincode, "0");

        if(newEmployee.getPosition().equals("M")){
            managerList.add(newEmployee);
            System.out.println("Account created succesfully " + newEmployee.getFirstName());

        }

        if(newEmployee.getPosition().equals("E"));
            em.add(newEmployee);

        login();

    }

    public  void addProduct(Employee manager){

        System.out.println("Add New Item\n");
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter Product Name: ");
        String product_name = scan.nextLine();

        System.out.println("Enter Product Serial Number: ");
        String product_number = scan.nextLine();

        System.out.println("Enter Manufacturer Company: ");
        String manufacturer = scan.nextLine();


        System.out.println("Enter your Price for this item  : ");
        String price = scan.nextLine();

        try{

            FileWriter productWriter = new FileWriter("product.txt",true);
            productWriter.write("\n" + product_name + " " + product_number + " " + manufacturer + " " + price);
            productWriter.close();

            Product newProduct = new Product (product_name,product_number, manufacturer, price);

            productList.add(newProduct);

        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
        System.out.println("Product added succesfully ");

// use while loop to check if input is correct

        Scanner x = new Scanner(System.in);
        System.out.println("\n 1.Add another product  2.Go Back to Manager Menu : ");
        String manager_input = x.nextLine();

        if(manager_input.equals("1")){
            addProduct(manager);
        }
        else if (manager_input.equals("2")){
            managerOptions(manager);
        }

        else {
            login();
        }

    }

    public  void removeEmployee(Employee employee){

        try {
            File employees = new File("employee.txt");

            if (employees.createNewFile()){
                System.out.println("Filed created: " + employees.getName());

            }

            else {
                System.out.println("File already exists");





                String buffer="";




/*
                BufferedReader reader = new BufferedReader(new FileReader(employees));
                BufferedWriter writer = new BufferedWriter(new FileWriter(employees));
                String currentLine="";
                while((currentLine = reader.readLine())!= null){

                    System.out.println(currentLine);
                    //String[] trimming = currentLine.split(" ");

                    if(trimming[1]==first_name && trimming[2]==last_name){
                        currentLine="";
                    }


                }
                reader.close();
                writer.close();
*/


                Scanner removing = new Scanner(employees);



                boolean deleted=false;

                while(removing.hasNext()){
                    buffer=removing.nextLine();
                    System.out.println(buffer);

                    String[] split = buffer.split(" ");

                    if(split[1]==employee.getFirstName()&& split[2]==employee.getLastName()){
                        buffer="";
                    }



                }
            }



        } catch (IOException e){
            System.out.println("Error Occured");
        }

    }


    public  void shiftOptions(Employee e) {


        Scanner sc = new Scanner(System.in);
        System.out.println("Scheduling Menu : 1.Schedule a shift  2. Remove a shift  3. View Specific Shift  4. View All Shifts 5. Go back to Manager Menu :  ");
        String input = sc.nextLine();

        if (input.equals("1")) {
            addShift(e);
        } else if (input.equals("2")) {
            removeShift(e);
        } else if (input.equals("3")) {
            viewShift(e);
        }

        else if (input.equals("4")){
            viewAllShifts(e);
        }

    }

    public  void addShift(Employee e){

        Shift babe = new Shift("20220803","01:00 PM","07:00 PM");

        shiftList.add(babe);

        Shift bear = new Shift("20220803","01:00 PM","07:00 PM");

        shiftList.add(bear);

        Scanner sc= new Scanner(System.in);
        System.out.println("Enter Date (yymmdd) : \n");
        String input_date = sc.nextLine();

        System.out.println("Enter start time (hh:mm AM/PM) : \n");
        String input_start_time = sc.nextLine();

        System.out.println("Enter end time (hh:mm AM/PM : \n" );
        String input_end_time = sc.nextLine();


        int count=0;

// make sure time don't overlap
// make owner enter their operating hours

        for(Shift shift: shiftList){
            if(shift.getStartTime().equals(input_start_time)){
                if(shift.getEndTime().equals(input_end_time)){
                    count++;
                }
            }
        }

        if(count>=2){
            System.out.println("This time slot is full. Please select anothe time : ");
            addShift(e);

        }


    }

    public  void removeShift(Employee e){


    }

    public  void viewShift(Employee e){

    }

    public  void viewAllShifts(Employee e){


    }

    public  void cashSystem(Employee e){

        List<Double> cost_list = new ArrayList<Double>() ;
        boolean approved = false;

        String name = "";

        double d =0.0;

        Scanner scan = new Scanner(System.in);
        System.out.println("Keep entering amounts. To stop press 1)Cash  2)Credit Card  3) Debit : ");

        System.out.println("\nEnter Product Serial Number :  ");

        String input = scan.nextLine();


        while(!input.equals("1")||!input.equals("2")||!input.equals("3")){
            System.out.println("\nEnter Product Serial Number :  ");

            input = scan.nextLine();

            for(Product pro: productList){
                if(pro.getProductCode().equals(input)){
                    approved=true;
                    d = Double.parseDouble(pro.getPrice());
                    cost_list.add(d);
                    name=pro.getProductName();
                }
            }

            if(approved==true){
                System.out.println("\n" + name + "      " + "$ " + d + "\n");

            }

            if(input.equals("1")||input.equals("2")||(input.equals("3"))){
                break;
            }
        }


        double sum = 0.0;

        double change = 0.0;

        for(int i=0; i<cost_list.size();i++){
            sum+=cost_list.get(i);
        }

        double tax_cost = (sum*0.13)+sum;

        double roundOff = Math.round(tax_cost * 100.0) / 100.0;


        System.out.println("Total : " + roundOff);

        if(input.equals("1")){
            System.out.println("Cash : ");
            double cash = scan.nextDouble();
            change = cash-tax_cost;

            double rounding_change = Math.round(change * 100.0) / 100.0;

            System.out.println("Press d if done : ");
            String done = scan.nextLine();

            if(done.equals("d")){
                cashTransac.add(roundOff);
            }

        }

        else if (input.equals("2")|| input.equals("3")){
            System.out.println("Please tap card ");
            System.out.println("Transaction approved");

        }


        System.out.println("\n a)Perform another transaction   b)Return to Menu : ");

        String after = scan.nextLine();

        if(after.equals("a")){
            cashSystem(e);
        }
        else{
            if(e.getPosition().equals("M")){
                managerOptions(e);
            }

            else if (e.getPosition().equals("E")){
                employeeOptions(e);
            }

        }

    }

    public  void itemLookUp(Employee e){

        for(Product p: productList){
            System.out.println(p.getProductCode());
        }

        Scanner sc= new Scanner(System.in);

        System.out.println("Enter item serial number : ");

        String item= sc.nextLine();
        System.out.println(item);


        for(Product p: productList){
            System.out.println(p.getProductCode());
            if(p.getProductCode().equals(item)){
                System.out.println("\n Name : " + p.getProductName() + "\n");
                System.out.println("\n Price : " + "$ " +  p.getPrice() + "\n");
                System.out.println("\n Serial Number : " + p.getProductCode() + "\n");
                System.out.println("\n Manufacturer : " + p.getManufacturer() + "\n");


            }
        }


        System.out.println("1) Look up another item   2) Back to Menu:  ");

        String last = sc.nextLine();

        if(last.equals("1")){
            itemLookUp(e);
        }

        else{
            if(e.getPosition().equals("M")){
                managerOptions(e);
            }

            else if (e.getPosition().equals("E")){
                employeeOptions(e);
            }
        }


    }



}
