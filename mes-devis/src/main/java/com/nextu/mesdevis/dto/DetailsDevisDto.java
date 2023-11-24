package com.nextu.mesdevis.dto;

import com.nextu.mesdevis.entity.Devis;
import com.nextu.mesdevis.entity.Product;

public class DetailsDevisDto {

    private long idDetailsDevis;
    private float price;
    private long inventory;

    private long idProduct;
    private long idDevis;

    // Constructors

    public DetailsDevisDto() {
    }

    public DetailsDevisDto(long idDetailsDevis,float price, long inventory,long idDevis, long idProduct) {
        this.idDetailsDevis = idDetailsDevis;
        this.price = price;
        this.inventory = inventory;
        this.idDevis = idDevis;
        this.idProduct  = idProduct;
    }

    // Getter and Setter methods
    public long getIdDetailsDevis() {
        return idDetailsDevis;
    }

    public void setIdDetailsDevis(long idDetailsDevis) {
        this.idDetailsDevis = idDetailsDevis;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getInventory() {
        return inventory;
    }

    public void setInventory(long inventory) {
        this.inventory = inventory;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public long getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(long idDevis) {
        this.idDevis = idDevis;
    }
}