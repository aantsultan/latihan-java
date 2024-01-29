package com.backendservice.dto;

import java.math.BigDecimal;

public class ProductResponseDto {

    private String productName;
    private Integer productQuantity;
    private BigDecimal productAmount;

    public ProductResponseDto(String productName, Integer productQuantity, BigDecimal productAmount) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productAmount = productAmount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public BigDecimal getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(BigDecimal productAmount) {
        this.productAmount = productAmount;
    }
}
