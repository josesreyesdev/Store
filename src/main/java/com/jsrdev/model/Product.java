package com.jsrdev.model;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products") // Nombre de la tabla en la BD
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La estrategia depende de la BD utilizada usualmente es Identity
    private Long id;
    private String name; //@Column(name = "names") // usada en caso de que el nombre de la columna en la Bd no sea la misma
    private String description;
    private BigDecimal price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
