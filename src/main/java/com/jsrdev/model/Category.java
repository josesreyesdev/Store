package com.jsrdev.model;

import javax.persistence.*;

@SuppressWarnings("all")
@Entity
@Table(name = "categories")
public class Category {

    @EmbeddedId
    private CategoryId categoryId;

    public Category() {}

    public Category(String name) {
        this.categoryId = new CategoryId(name, "456");
    }

    public String getName() {
        return categoryId.getName();
    }

    public void setName(String name) {
        this.categoryId.setName(name);
    }
}
