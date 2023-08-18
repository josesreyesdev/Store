package com.jsrdev.test;

import com.jsrdev.dao.ProductDao;
import com.jsrdev.model.Product;
import com.jsrdev.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class RegisterProduct {
    public static void main(String[] args) {
        Product phone = new Product();
        phone.setName("Samsung");
        phone.setDescription("Samsung modelo: M22, capacidad: 128 GB");
        phone.setPrice(new BigDecimal("10000"));

        EntityManager entityManager = JPAUtils.getEntityManager();

        ProductDao productDao = new ProductDao(entityManager);

        entityManager.getTransaction().begin(); //iniciando transacciones

        entityManager.persist(phone); // guardar el valor del producto en la bd para persistir

        productDao.save(phone);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
