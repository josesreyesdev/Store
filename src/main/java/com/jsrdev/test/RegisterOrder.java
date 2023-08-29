package com.jsrdev.test;

import com.jsrdev.dao.CategoryDao;
import com.jsrdev.dao.ClientDao;
import com.jsrdev.dao.OrderDao;
import com.jsrdev.dao.ProductDao;
import com.jsrdev.model.*;
import com.jsrdev.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class RegisterOrder {
    public static void main(String[] args) {
        registerProduct();

        EntityManager entityManager = JPAUtils.getEntityManager();
        
        ProductDao productDao = new ProductDao(entityManager);
        Product product = productDao.getProductById(1L);

        ClientDao clientDao = new ClientDao(entityManager);
        OrderDao orderDao = new OrderDao(entityManager);

        Client client = new Client("Juan", "123ABC");
        Order order = new Order(client);
        order.addItems(new OrderItem(5, product, order));

        entityManager.getTransaction().begin();

        clientDao.save(client);
        orderDao.save(order);

        entityManager.getTransaction().commit();
    }

    private static void registerProduct() {
        Category phonesCategory = new Category("PHONES");

        Product phone = new Product(
                "MOTOROLA",
                "Motorola modelo: Moto-G12, capacidad: 128 GB",
                new BigDecimal("5000"),
                phonesCategory
        );

        EntityManager entityManager = JPAUtils.getEntityManager();

        ProductDao productDao = new ProductDao(entityManager);
        CategoryDao categoryDao = new CategoryDao(entityManager);

        entityManager.getTransaction().begin();

        entityManager.persist(phone);

        productDao.save(phone);
        categoryDao.save(phonesCategory);

        entityManager.getTransaction().commit();
        entityManager.close();

        phonesCategory.setName("SOFTWARE");
    }
}