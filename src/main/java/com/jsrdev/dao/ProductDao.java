package com.jsrdev.dao;

import com.jsrdev.model.Product;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProductDao {

    private final EntityManager entityManager;

    public ProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Product product) {
        this.entityManager.persist(product);
    }

    public void update(Product product) {
        this.entityManager.merge(product);
    }

    public void delete(Product product) {
        product = this.entityManager.merge(product);
        this.entityManager.remove(product);
    }

    /* Consultas */
    public Product getProductById(Long id) {
        return entityManager.find(Product.class, id);
    }

    public List<Product> getProducts() {
        String jpql = "SELECT P FROM Product AS P";
        return entityManager.createQuery(jpql, Product.class).getResultList();
    }

     /* Queries with filters */
    public List<Product> getProductsByName(String name) {
        String jpql = "SELECT P FROM Product AS P WHERE P.name =: name";
        //String jpql2 = "SELECT P FROM Product AS P WHERE P.name =: name AND P.description =: description"; // consulta con mas parametros

        // set parameter es en que columna y el valor a consultar
        return entityManager.createQuery(jpql, Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Product> getProductsByCategory(String categoryName) {
        String jpql = "SELECT P FROM Product AS P WHERE P.category.name =: categoryName";
        return entityManager.createQuery(jpql, Product.class)
                .setParameter("categoryName", categoryName)
                .getResultList();
    }

    //Limit data in the query
    public BigDecimal getPriceByNameOfProduct(String name) {
        String jpql = "SELECT P.price FROM Product AS P WHERE P.name =: name";
        return entityManager.createQuery(jpql, BigDecimal.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
