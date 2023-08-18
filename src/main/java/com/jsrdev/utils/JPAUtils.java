package com.jsrdev.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {

    // Crear una class dentro de la interfaz, usando en nombre unico dentro de persistence
    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("store");

    // Encargado de realizar las op en la bd.
    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}
