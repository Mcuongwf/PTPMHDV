package com.duc.manager.dto.request;

import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.Date;

public class ProductUpdateRequest{
    private String name;
    private double price;
    private String imgFileName;
    private int stock;
    @LastModifiedDate
    private LocalDate updateDate;


    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }
}
