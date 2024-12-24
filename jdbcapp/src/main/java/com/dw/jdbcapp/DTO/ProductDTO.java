package com.dw.jdbcapp.DTO;

import com.dw.jdbcapp.model.Product;

public class ProductDTO {
    private int productId;
    private String productName;
    private double unitPrice;
    private int stock;
    private double stockValue;

    public ProductDTO() {
    }

    public ProductDTO(int productId, String productName, double unitPrice, int stock, double stockValue) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.stockValue = stockValue;
    }

    // fromProduct()와 동일한 기능을 가진 생성자를 만들수 있음
    public ProductDTO(Product product){
        this.productId = product.getProductNumber();
        this.productName = product.getProductName();
        this.unitPrice = product.getPrice();
        this.stock = product.getInventory();
        this.stockValue = product.getPrice()* product.getInventory();
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getStockValue() {
        return stockValue;
    }

    public void setStockValue(double stockValue) {
        this.stockValue = stockValue;
    }

    public static ProductDTO fromProduct(Product product){  // Product형태(db)로 되있는 정보를 유저에게 내보내줘야함
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductNumber());
        productDTO.setProductName(product.getProductName());
        productDTO.setUnitPrice(product.getPrice());
        productDTO.setStockValue(product.getPrice() * product.getInventory());
        return productDTO;
    }

}
