package com.jsrdev.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("all")
@Entity
@Table(name = "electronics")
public class Electronic extends Product{

    private String brand; //marca
    private String model; //modelo

    public Electronic() {}

    public Electronic(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
