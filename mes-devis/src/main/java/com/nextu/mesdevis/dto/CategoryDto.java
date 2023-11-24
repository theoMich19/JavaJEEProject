package com.nextu.mesdevis.dto;

import com.nextu.mesdevis.entity.Product;

import java.util.List;

public class CategoryDto {

    private long idCategory;
    private String name;
    private List<Long> idProducts;

    // Constructors

    public CategoryDto() {
    }

    public CategoryDto(long idCategory, String name, List<Long> idProducts) {
        this.idCategory = idCategory;
        this.name = name;
        this.idProducts = idProducts;
    }

    // Getter and Setter methods

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

    public List<Long> getProductIds() { return idProducts; }

    public void setProductsIds(List<Long> idProducts) { this.idProducts = idProducts;}
}
