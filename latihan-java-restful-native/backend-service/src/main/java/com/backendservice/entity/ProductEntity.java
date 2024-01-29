package com.backendservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "product")
public class ProductEntity {

    @Id
    private Integer productId;
    private String productName;
    private Integer productQuantity;
    private BigDecimal productAmount;

    public ProductEntity() {
    }

    public ProductEntity(String productName, Integer productQuantity, BigDecimal productAmount) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productAmount = productAmount;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
