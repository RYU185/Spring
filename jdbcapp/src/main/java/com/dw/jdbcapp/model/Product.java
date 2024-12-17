package com.dw.jdbcapp.model;

public class Product {
    String productName;
    int productNumber;
    String packUnit;
    double price;
    int inventory;

    public Product() {
    }

    public Product(String productName, int productNumber, String packUnit, double price, int inventory) {
        this.productName = productName;
        this.productNumber = productNumber;
        this.packUnit = packUnit;
        this.price = price;
        this.inventory = inventory;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public String getPackUnit() {
        return packUnit;
    }

    public void setPackUnit(String packUnit) {
        this.packUnit = packUnit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productNumber=" + productNumber +
                ", packUnit='" + packUnit + '\'' +
                ", price=" + price +
                ", inventory=" + inventory +
                '}';
    }
}


