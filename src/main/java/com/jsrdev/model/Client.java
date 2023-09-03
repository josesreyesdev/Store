package com.jsrdev.model;

import javax.persistence.*;

@SuppressWarnings("all")
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PersonalData personalData;

    public Client() {}
    public Client(String name, String dni) {
        this.personalData = new PersonalData(name, dni);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return personalData.getName();
    }

    public void setName(String name) {
        this.personalData.setName(name);
    }

    public String getDni() {
        return personalData.getDni();
    }

    public void setDni(String dni) {
        this.personalData.setDni(dni);
    }
}