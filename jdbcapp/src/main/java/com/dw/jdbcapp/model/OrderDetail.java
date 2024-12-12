package com.dw.jdbcapp.model;

public class OrderDetail {
    String orderID;
    String productID;
    int price;
    int orderCount;
    double saleRate;

    public OrderDetail() {
    }

    public OrderDetail(String orderID, String productID, int price, int orderCount, double saleRate) {
        this.orderID = orderID;
        this.productID = productID;
        this.price = price;
        this.orderCount = orderCount;
        this.saleRate = saleRate;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public double getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(double saleRate) {
        this.saleRate = saleRate;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderID='" + orderID + '\'' +
                ", productID='" + productID + '\'' +
                ", price=" + price +
                ", orderCount='" + orderCount + '\'' +
                ", saleRate=" + saleRate +
                '}';
    }
}
