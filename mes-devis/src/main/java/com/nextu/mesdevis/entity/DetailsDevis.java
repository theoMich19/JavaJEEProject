package com.nextu.mesdevis.entity;

import com.nextu.mesdevis.dto.DetailsDevisDto;
import jakarta.persistence.*;

@Entity
public class DetailsDevis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "autoIncrement", sequenceName = "AUTOINCREMENT", allocationSize = 1)
    @Column(name = "ID_DETAILSDEVIS")
    private long idDetailsDevis;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private long inventory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idProduct", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "devis_id")
    private Devis devis;


    public DetailsDevis() {
    }

    public DetailsDevis(float price, long inventory) {
        this.price = price;
        this.inventory = inventory;
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


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    public Devis getDevis() {
        return devis;
    }

}