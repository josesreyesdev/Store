package com.jsrdev.dao;

import com.jsrdev.model.Order;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderDao {
    private final EntityManager entityManager;

    public OrderDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Order order) {
        this.entityManager.persist(order);
    }

    public void update(Order order) {
        this.entityManager.merge(order);
    }

    public void delete(Order order) {
        order = this.entityManager.merge(order);
        this.entityManager.remove(order);
    }

    public Order getOrderById(Long id) {
        return entityManager.find(Order.class, id);
    }

    public List<Order> getOrders() {
        String jpql = "SELECT P FROM Order P";
        return entityManager.createQuery(jpql, Order.class).getResultList();
    }


}
