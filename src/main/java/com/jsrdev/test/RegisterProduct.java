package com.jsrdev.test;

import com.jsrdev.dao.CategoryDao;
import com.jsrdev.dao.ProductDao;
import com.jsrdev.model.Category;
import com.jsrdev.model.Product;
import com.jsrdev.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class RegisterProduct {

    public static void main(String[] args) {
        Category phonesCategory = new Category("PHONES");

        Product phone = new Product("Samsung",
                "Samsung modelo: M22, capacidad: 128 GB",
                new BigDecimal("10000"),
                phonesCategory
        );

        EntityManager entityManager = JPAUtils.getEntityManager();

        ProductDao productDao = new ProductDao(entityManager);
        CategoryDao categoryDao = new CategoryDao(entityManager);

        entityManager.getTransaction().begin(); //iniciando transacciones

        entityManager.persist(phone); // guardar el valor del producto en la bd para persistir

        productDao.save(phone);
        categoryDao.save(phonesCategory);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
