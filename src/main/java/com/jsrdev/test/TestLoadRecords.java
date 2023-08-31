package com.jsrdev.test;

import com.jsrdev.model.Order;
import com.jsrdev.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.io.FileNotFoundException;

public class TestLoadRecords {
    public static void main(String[] args) throws FileNotFoundException {
        LoadRecords.loadRecords();

        EntityManager entityManager = JPAUtils.getEntityManager();

        Order order = entityManager.find(Order.class, 3L);
        System.out.println("Date: "+ order.getDate());
    }
}
