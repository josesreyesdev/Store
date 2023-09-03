package com.jsrdev.dao;

import com.jsrdev.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
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
        String jpql = "SELECT P FROM Product P";
        return entityManager.createQuery(jpql, Product.class).getResultList();
    }

     /* Queries with filters */
    public List<Product> getProductsByName(String name) {
        String jpql = "SELECT P FROM Product P WHERE P.name =: name";

        // set parameter es en que columna y el valor a consultar
        return entityManager.createQuery(jpql, Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Product> getProductsByCategory(String categoryName) {
        String jpql = "SELECT P FROM Product P WHERE P.category.name =: categoryName";
        return entityManager.createQuery(jpql, Product.class)
                .setParameter("categoryName", categoryName)
                .getResultList();
    }

    //Limit data in the query
    public BigDecimal getPriceByNameOfProduct(String name) {
        return entityManager.createNamedQuery("Product.priceEnquiry", BigDecimal.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    // filter con mas de un parametro si existe hacer la consulta o ignorarlo si no existe
    public List<Product> queryWithParameters(String name, BigDecimal price, LocalDate date) {
        StringBuilder jpql = new StringBuilder("SELECT P FROM Product P WHERE 1=1");
        if (name != null && !name.trim().isEmpty()) {
            jpql.append(" AND P.name = :name");
        }
        if (price != null && !price.equals(new BigDecimal(0))) {
            jpql.append(" AND P.price = :price");
        }
        if (date != null) {
            jpql.append(" AND P.registerDate = :date");
        }

        TypedQuery<Product> query = entityManager.createQuery(jpql.toString(), Product.class);

        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("name", name);
        }
        if (price != null && !price.equals(new BigDecimal(0))) {
            query.setParameter("price", price);
        }
        if (date != null) {
            query.setParameter("registerDate", date);
        }

        return query.getResultList();
    }

    public List<Product> queryWithParametersWithAPICriteria(String name, BigDecimal price, LocalDate date) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> from = query.from(Product.class);

        Predicate filter = criteriaBuilder.and();

        if (name != null && !name.trim().isEmpty()) {
            filter = criteriaBuilder.and(filter, criteriaBuilder.equal(from.get("name"), name));
        }
        if (price != null && !price.equals(new BigDecimal(0))) {
            filter = criteriaBuilder.and(filter, criteriaBuilder.equal(from.get("price"), price));
        }
        if (date != null) {
            filter = criteriaBuilder.and(filter, criteriaBuilder.equal(from.get("registerDate"), date));
        }

        query = query.where(filter);

        return entityManager.createQuery(query).getResultList();
    }
}