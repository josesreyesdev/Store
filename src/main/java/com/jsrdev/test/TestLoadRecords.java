package com.jsrdev.test;

import com.jsrdev.dao.OrderDao;
import com.jsrdev.model.Order;
import com.jsrdev.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.io.FileNotFoundException;

public class TestLoadRecords {
    public static void main(String[] args) throws FileNotFoundException {
        LoadRecords.loadRecords();

        EntityManager entityManager = JPAUtils.getEntityManager();

        //Order order = entityManager.find(Order.class, 3L);
        OrderDao orderDao = new OrderDao(entityManager);
        Order orderWithClient = orderDao.getOrderWithClient(3L);

        entityManager.close();

        //System.out.println("Date: "+ order.getDate());

        //System.out.println("Size Items: "+ order.getOrderItems().size());

        //System.out.println("Client name: "+ order.getClient().getName());
        System.out.println("Client name: "+ orderWithClient.getClient().getName());
    }
}
