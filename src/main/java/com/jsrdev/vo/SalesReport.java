package com.jsrdev.vo;

import java.time.LocalDate;

public class SalesReport {
    private String productName;
    private Long productQuantity;
    private LocalDate dateOfLastSale;

    public SalesReport(String productName, Long productQuantity, LocalDate dateOfLastSale) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.dateOfLastSale = dateOfLastSale;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Long productQuantity) {
        this.productQuantity = productQuantity;
    }

    public LocalDate getDateOfLastSale() {
        return dateOfLastSale;
    }

    public void setDateOfLastSale(LocalDate dateOfLastSale) {
        this.dateOfLastSale = dateOfLastSale;
    }

    @Override
    public String toString() {
        return "SalesReport{" +
                "productName= '" + productName + '\'' +
                ", productQuantity= " + productQuantity +
                ", dateOfLastSale= " + dateOfLastSale +
                '}';
    }
}
