package com.jsrdev.test;

import com.jsrdev.dao.CategoryDao;
import com.jsrdev.dao.ProductDao;
import com.jsrdev.model.Category;
import com.jsrdev.model.Product;
import com.jsrdev.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class TestOfParameters {
    public static void main(String[] args) {
        loadData();

        EntityManager entityManager = JPAUtils.getEntityManager();
        ProductDao productDao = new ProductDao(entityManager);

        List<Product> productList = productDao.queryWithParameters("X", null, null);
        for (Product product: productList) {
            System.out.println("Name: "+ product.getName());
        }

        List<Product> products = productDao.queryWithParametersWithAPICriteria("FIFA", null, null);
        System.out.println("Product name: "+ products.get(0).getName());

    }

    private static void loadData() {
        Category phones = new Category("PHONES");
        Category videoGames = new Category("VIDEO_GAMES");
        Category electronics = new Category("ELECTRONICS");

        Product phone = new Product("X", "New product", new BigDecimal(10000), phones);
        Product videoGame = new Product("FIFA", "2000", new BigDecimal(10000), videoGames);
        Product electronic = new Product("RAM Memory", "30 GB", new BigDecimal(10000), electronics);

        EntityManager entityManager = JPAUtils.getEntityManager();
        ProductDao productDao = new ProductDao(entityManager);
        CategoryDao categoryDao = new CategoryDao(entityManager);

        entityManager.getTransaction().begin();

        categoryDao.save(phones);
        categoryDao.save(videoGames);
        categoryDao.save(electronics);

        productDao.save(phone);
        productDao.save(videoGame);
        productDao.save(electronic);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
