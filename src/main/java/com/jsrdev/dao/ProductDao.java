package com.jsrdev.dao;

import com.jsrdev.model.Product;

import javax.persistence.EntityManager;

public class ProductDao {

    private final EntityManager entityManager;

    public ProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Product product) {
        this.entityManager.persist(product);
    }
}
