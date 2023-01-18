package com.company;

public class Product {


    private String productCode="0";

    private String price="0.0";

    private String productName="";

    private String manufacturer = "";

    //private String typeOfProduct = "";


    public Product(String productName, String productCode, String manufacturer, String price){
        this.productCode=productCode;
        this.price = price;
        this.productName = productName;
        this.manufacturer = manufacturer;
    }



    public String getProductCode(){
        return productCode;
    }


    public String getPrice(){
        return price;
    }

    public String getProductName(){
        return productName;
    }

    public String getManufacturer(){
        return manufacturer;
    }
//
//    public String getTypeOfProduct() {
//        return typeOfProduct;
//    }




}
