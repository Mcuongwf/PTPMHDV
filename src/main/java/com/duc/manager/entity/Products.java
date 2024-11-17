package com.duc.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

import java.util.List;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String name;
    private double price;

    //@Column(columnDefinition = "TEXT")
    private String imgFileName;

    @LastModifiedDate
    private LocalDate updateDate;
    private int stock;
    @OneToMany(mappedBy = "products")
    @JsonIgnore
    private List<OrderDetails> orderDetailsList;


    public Products() {
    }

    public Products(int id, String name, double price, String imgFileName, int stock, List<OrderDetails> orderDetailsList) {
        Id = id;
        this.name = name;
        this.price = price;
        this.imgFileName = imgFileName;
        this.stock = stock;
        this.orderDetailsList = orderDetailsList;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }
}
