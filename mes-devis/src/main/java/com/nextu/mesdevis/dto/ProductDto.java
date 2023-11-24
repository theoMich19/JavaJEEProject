package com.nextu.mesdevis.dto;

import com.nextu.mesdevis.entity.Category;

import java.util.List;

public class ProductDto {

    private long idProduct;

    private String name;
    private String productCode;
    private long stock;

    private long idCategory;

    private List<Long> idDetailsDevis;

    // Constructors

    public ProductDto() {}

    public ProductDto(long idProduct, String name, String productCode, long stock, long idCategory, List<Long> idDetailsDevis) {
        this.idProduct = idProduct;
        this.name = name;
        this.productCode = productCode;
        this.stock = stock;
        this.idCategory = idCategory;
        this.idDetailsDevis = idDetailsDevis;

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

    public long getIdCategory() {return  idCategory;}

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }

    public List<Long> getIdDetailsDevis() {
        return idDetailsDevis;
    }

    public void setIdDetailsDevis(List<Long> idDetailsDevis) {
        this.idDetailsDevis = idDetailsDevis;
    }

}
