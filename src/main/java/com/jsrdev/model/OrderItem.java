package com.jsrdev.model;

import javax.persistence.*;
import java.math.BigDecimal;

@SuppressWarnings("all")
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private BigDecimal unitPrice;

    @ManyToOne
    private Product product;
    @ManyToOne
    private Order order;

    /*itemOrder 1 product 1 order 2
    * itemOrder 2 product 2 order 2
    * itemOrder 3 product 1 order 1
    * itemOrder 4 product 3 order 1
    * */

    public OrderItem(){}
    public OrderItem(int quantity, Product product, Order order) {
        this.quantity = quantity;
        this.product = product;
        this.order = order;
        this.unitPrice = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getValue() {
        return this.unitPrice.multiply(new BigDecimal(this.quantity));
    }
}
