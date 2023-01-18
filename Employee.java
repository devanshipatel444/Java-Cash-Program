package com.company;

public class Employee {


    private String position = "";
    private String firstName = "";
    private String lastName = "";
    private String pincode="";
    private String number = "";
    private String attendance="0";
    private String memberID="";

    public Employee(String position, String firstName, String lastName, String number, String pincode, String attendance){
        this.position=position;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pincode = pincode;
        this.number=number;
        this.attendance= attendance;


    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getNumber(){
        return number;
    }

    public String getPincode(){
        return pincode;
    }

    public String getAttendance(){
        return attendance;
    }

    public String getPosition(){
        return position;
    }

    public void logged_on(){
        int frequency= Integer.parseInt(attendance);
        frequency++;


    }






}
