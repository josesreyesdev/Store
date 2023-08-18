package com.jsrdev.dao;

import com.jsrdev.model.Category;

import javax.persistence.EntityManager;

public class CategoryDao {
    private final EntityManager entityManager;

    public CategoryDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Category category) {
        this.entityManager.persist(category);
    }
}
