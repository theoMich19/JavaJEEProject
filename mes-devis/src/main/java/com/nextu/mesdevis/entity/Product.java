package com.nextu.mesdevis.entity;

import com.nextu.mesdevis.dto.CategoryDto;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "autoIncrement", sequenceName = "AUTOINCREMENT", allocationSize = 1)
    @Column(name = "ID_PRODUCT")
    private long idProduct;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String productCode;

    @Column(nullable = false)
    private long stock;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = DetailsDevis.class, mappedBy = "product")
    private List<DetailsDevis> detailsDevisList;

    public Product() {}

    public Product(String name, String productCode, long stock) {
        this.name = name;
        this.productCode = productCode;
        this.stock = stock;
    }

    // Getter and Setter methods

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }


    public Category getCategory() {return  category;}

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<DetailsDevis> getDetailsDevisList() {
        return detailsDevisList;
   }

    public void setDetailsDevisList(List<DetailsDevis> detailsDevisList) {
        this.detailsDevisList = detailsDevisList;
    }
}