package com.jsrdev.dao;

import com.jsrdev.model.Client;

import javax.persistence.EntityManager;
import java.util.List;

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

    public Client getClientById(Long id) {
        return entityManager.find(Client.class, id);
    }

    public List<Client> getClients() {
        String jpql = "SELECT P FROM Client P";
        return entityManager.createQuery(jpql, Client.class).getResultList();
    }

    public List<Client> getClientByName(String name){
        String jpql =" SELECT C FROM Client C WHERE C.name=:name ";
        return entityManager.createQuery(jpql,Client.class).setParameter("name", name).getResultList();
    }
}
