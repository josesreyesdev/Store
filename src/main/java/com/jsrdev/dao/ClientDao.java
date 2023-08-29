package com.jsrdev.dao;

import com.jsrdev.model.Client;

import javax.persistence.EntityManager;

public class ClientDao {
    private final EntityManager entityManager;

    public ClientDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Client client) {
        this.entityManager.persist(client);
    }

    public void update(Client client) {
        this.entityManager.merge(client);
    }

    public void delete(Client client) {
        client = this.entityManager.merge(client);
        this.entityManager.remove(client);
    }
}
