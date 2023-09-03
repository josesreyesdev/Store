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

    public void update(Category category) {
        this.entityManager.merge(category);
    }

    public void delete(Category category) {
        category = this.entityManager.merge(category);
        this.entityManager.remove(category);
    }

    public Category getCategoryByName( String name) {
        String jpql = "SELECT C FROM Category C WHERE C.categoryId.name = :name";
        return entityManager.createQuery(jpql, Category.class).setParameter("name", name).getSingleResult();
    }
}
