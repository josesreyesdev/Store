package com.jsrdev.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@SuppressWarnings("all")
@Entity
@Table(name = "products")
@NamedQuery(
        name = "Product.priceEnquiry",
        query = "SELECT P.price FROM Product P WHERE P.name =: name"
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //JOIN => para distribucion en cada tabla
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; //@Column(name = "names")
    private String description;
    private BigDecimal price;
    private final LocalDate registerDate = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    public Product(String name, String description, BigDecimal price, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Product() {}

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
