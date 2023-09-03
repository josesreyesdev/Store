package com.jsrdev.test;

import com.jsrdev.dao.OrderDao;
import com.jsrdev.model.Order;
import com.jsrdev.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.io.FileNotFoundException;

public class TestPerformance {
    public static void main(String[] args) throws FileNotFoundException {
        LoadRecords.loadRecords();

        EntityManager entityManager = JPAUtils.getEntityManager();

        OrderDao orderDao = new OrderDao(entityManager);
        Order orderWithClient = orderDao.getOrderWithClient(2L);

        entityManager.close();
        System.out.println("Name client: "+ orderWithClient.getClient().getName());
    }
}
