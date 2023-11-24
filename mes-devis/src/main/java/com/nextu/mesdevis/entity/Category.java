package com.nextu.mesdevis.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "autoIncrement", sequenceName = "AUTOINCREMENT", allocationSize = 1)
    @Column(name = "ID_CATEGORY")
    private long idCategory;

    @Column(nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Product.class, mappedBy = "category")
    private List<Product> products;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCommercial")
    private Commercial commercial;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProduct() { return products; }

    public void setProducts(List<Product> products) { this.products = products;}

    public Commercial getCommercial() {
        return commercial;
    }

    public void setCommercial(Commercial commercial) {
        this.commercial = commercial;
    }
}
