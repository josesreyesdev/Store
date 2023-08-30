package com.jsrdev.dao;

import com.jsrdev.model.Order;
import com.jsrdev.vo.SalesReport;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
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

    public BigDecimal totalValueSold() {
        String jpql = "SELECT SUM(O.totalValue) FROM Order O";
        return entityManager.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    // valor promedio vendido
    public Double averageValueSold() {
        String jpql = "SELECT AVG(O.totalValue) FROM Order O";
        return entityManager.createQuery(jpql, Double.class).getSingleResult();
    }

    //relatorio de ventas
    public List<Object[]> salesReport() {
        String jpql = "SELECT P.name, " +
                "SUM(OI.quantity), " +
                "MAX(O.date) " +
                "FROM Order O " +
                "JOIN O.orderItems OI " +
                "JOIN OI.product P " +
                "GROUP BY P.name " +
                "ORDER BY OI.quantity DESC";

        return entityManager.createQuery(jpql, Object[].class).getResultList();
    }

    //relatorio de ventas VO
    public List<SalesReport> salesReportVO() {
        String jpql = "SELECT new com.jsrdev.vo.SalesReport (P.name, " +
                "SUM(OI.quantity), " +
                "MAX(O.date)) " +
                "FROM Order O " +
                "JOIN O.orderItems OI " +
                "JOIN OI.product P " +
                "GROUP BY P.name " +
                "ORDER BY OI.quantity DESC";

        return entityManager.createQuery(jpql, SalesReport.class).getResultList();
    }

}