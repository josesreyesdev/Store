package com.jsrdev.test;

import com.jsrdev.dao.CategoryDao;
import com.jsrdev.dao.ProductDao;
import com.jsrdev.model.Category;
import com.jsrdev.model.Product;
import com.jsrdev.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class RegisterProduct {

    public static void main(String[] args) {
        saveTest();

        // updateTest();

        // deleteTest();

        //getProductById();
        //getAllProducts();
        //getProductByName();
        //getProductByCategory();
        getSingleProductByPrice();
    }

    private static void saveTest() {
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

        entityManager.getTransaction().commit(); // valores definitivos en la bd
        entityManager.close(); // Cierra la conexion => estado detach

        phonesCategory.setName("SOFTWARE");
    }

    private static void updateTest() {
        Category phonesCategory = new Category("PHONES"); // estado transiente

        EntityManager entityManager = JPAUtils.getEntityManager();

        entityManager.getTransaction().begin(); //iniciando transacciones

        entityManager.persist(phonesCategory); // guardar el producto en la bd para persistir => estado managed

        phonesCategory.setName("BOOKS");

        entityManager.flush(); // permite hacer un rollback dentro de la BD
        entityManager.clear(); // estado detach

        // para hacer un update en la bd
        phonesCategory = entityManager.merge(phonesCategory); // trae las entidades del estado detach al managed nuevamente

        phonesCategory.setName("SOFTWARE");
        entityManager.flush();

         update2();
    }

    private static void update2() {
        Category phonesCategory = new Category("PHONES"); // estado transiente

        EntityManager entityManager = JPAUtils.getEntityManager();
        CategoryDao categoryDao = new CategoryDao(entityManager);

        entityManager.getTransaction().begin(); //iniciando transacciones

        entityManager.persist(phonesCategory); // guardar el producto en la bd para persistir => estado managed

        Category foodCategory = new Category("BOOKS");
        categoryDao.update(foodCategory);

        entityManager.flush(); // permite hacer un rollback dentro de la BD
        entityManager.clear(); // estado detach

        // para hacer un update en la bd
        phonesCategory = entityManager.merge(foodCategory); // trae las entidades del estado detach al managed nuevamente

        phonesCategory.setName("SOFTWARE");
        entityManager.flush();
    }

    private static void deleteTest() {
        /* Para eliminar un registro debe estar en esado managed o gerenciado */

        Category phones = new Category("PHONES");
        EntityManager entityManager = JPAUtils.getEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(phones);

        entityManager.flush();
        entityManager.clear();

        phones = entityManager.merge(phones); // estado managed

        phones.setName("SOFTWARE");
        entityManager.flush();

        entityManager.remove(phones);
        entityManager.flush();
        /* Aplicando el metodo remove y flush => puedo hacer rollback para regresar al estado anterior
         Aplicando el metodo remove y commit => estoy borrando de forma definitiva el registro */

    }

    private static void getProductById() {
        EntityManager entityManager = JPAUtils.getEntityManager();
        ProductDao productDao = new ProductDao(entityManager);

        Product product1 = productDao.getProductById(1L);
        System.out.println(product1.getName());
    }

    private static void getAllProducts() {
        EntityManager entityManager = JPAUtils.getEntityManager();
        ProductDao productDao = new ProductDao(entityManager);

        List<Product> productList = productDao.getProducts();
        productList.forEach(product -> System.out.println("Product:" + product.getDescription()));

    }

    private static void getProductByName() {
        EntityManager entityManager = JPAUtils.getEntityManager();
        ProductDao productDao = new ProductDao(entityManager);

        List<Product> productList = productDao.getProductsByName("Samsung");
        productList.forEach(product -> System.out.println("Product:" + product.getDescription()));

    }

    private static void getProductByCategory() {
        EntityManager entityManager = JPAUtils.getEntityManager();
        ProductDao productDao = new ProductDao(entityManager);

        List<Product> productList = productDao.getProductsByCategory("PHONES");
        productList.forEach(product -> System.out.println("Product:" + product.getDescription()));

    }

    private static void getSingleProductByPrice() {
        EntityManager entityManager = JPAUtils.getEntityManager();
        ProductDao productDao = new ProductDao(entityManager);

        BigDecimal price = productDao.getPriceByNameOfProduct("Samsung");
        System.out.println("Price:" + price);
    }
}
