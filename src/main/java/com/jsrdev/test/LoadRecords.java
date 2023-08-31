package com.jsrdev.test;

import com.jsrdev.dao.CategoryDao;
import com.jsrdev.dao.ClientDao;
import com.jsrdev.dao.OrderDao;
import com.jsrdev.dao.ProductDao;
import com.jsrdev.model.*;
import com.jsrdev.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//cargar registros
public class LoadRecords {
    public static void loadRecords() throws FileNotFoundException {
        EntityManager em = JPAUtils.getEntityManager();
        CategoryDao categoryDao = new CategoryDao(em);
        ProductDao productDao = new ProductDao(em);
        ClientDao clientDao = new ClientDao(em);
        OrderDao orderDao = new OrderDao(em);
        em.getTransaction().begin();

        loadCategory("category",categoryDao,em);

        loadProduct("product",productDao,categoryDao,em);

        loadClient("client",clientDao,em);

        List<Client> clientList = clientDao.getClients();
        List<Order> orderList= new ArrayList<>();

        for(Client cl:clientList) {
            orderList.add(new Order(cl));
        }

        for(int i=0;i<orderList.size();i++) {
            orderList.get(i).addItems(new OrderItem(i+1,productDao.getProductById((long) (i+1)),orderList.get(i)));
            orderDao.save(orderList.get(i));
        }

        em.getTransaction().commit();
        em.close();

    }

    private static void loadProduct(String type, ProductDao productDao, CategoryDao categoryDao, EntityManager em) throws FileNotFoundException {
        List<String> productsTxt =readFile(type);
        for(int i=0;i<productsTxt.size();i++) {
            String[] line = productsTxt.get(i).split(";");
            if(line.length>1) {
                Category category = categoryDao.getCategoryByName(line[3]);
                Product product = new Product(line[4],line[0],new BigDecimal(line[1]), category);
                productDao.save(product);
                em.flush();
            }
        }
    }

    private static void loadCategory(String type, CategoryDao categoryDao, EntityManager em) throws FileNotFoundException {
        List<String> categoriesTxt =readFile(type);
        for(int i=0;i<categoriesTxt.size();i++) {
            String[] line = categoriesTxt.get(i).split(";");
            if(line.length==1) {
                Category category = new Category(categoriesTxt.get(i));
                categoryDao.save(category);
                em.flush();
            }
        }
    }

    private static void loadClient(String type, ClientDao clientDao, EntityManager em) throws FileNotFoundException {
        List<String> clientsTxt =readFile(type);
        for(int i=0;i<clientsTxt.size();i++) {
            String[] line = clientsTxt.get(i).split("~");
            System.out.println(line[0]+line[1]);
            if(line.length>1) {
                Client client= new Client(line[0],line[1]);
                clientDao.save(client);
                em.flush();
            }
        }
    }

    private static List<String> readFile(String type) throws FileNotFoundException {
        //File file = new File("C:\\Users\\Public\\Alura\\jpa\\"+type+".txt");
        File file = new File("C:\\Users\\Jose\\Desktop\\Alura\\Backend\\Projects\\store\\src\\main\\resources\\utils\\"+type+".txt");
        Scanner scan = new Scanner(file);
        List<String> order= new ArrayList<>();
        while(scan.hasNextLine()){
            order.add(scan.nextLine());
        }
        scan.close();
        return order;
    }
}