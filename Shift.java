package com.company;

public class Shift  {

    private String date = "";

    private String startTime = "";
    private String endTime = "";

    private String firstName="";

    private String lastName = "";



    public Shift(String date, String startTime, String endTime){
        this.date=date;
        this.startTime= startTime;
        this.endTime = endTime;

    }

    public String getDate(){
        return date;
    }

    public String getStartTime(){
        return startTime;
    }

    public String getEndTime(){
        return endTime;
    }






}
