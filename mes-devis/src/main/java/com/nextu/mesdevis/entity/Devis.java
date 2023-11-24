package com.nextu.mesdevis.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Devis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "autoIncrement", sequenceName = "AUTOINCREMENT", allocationSize = 1)
    @Column(name = "ID_DEVIS")
    private long idDevis;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDate creationDate;

    @Column
    private LocalDate validationDate;

    @Column
    private LocalDate paymentDate;

    @Column
    private LocalDate cancellationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_comercial")
    private Commercial commercial;

    @OneToMany(mappedBy = "devis")
    private List<DetailsDevis> detailsDevisList;

    public Devis() {
    }

    public Devis(String name,LocalDate creationDate, LocalDate validationDate, LocalDate paymentDate, LocalDate cancellationDate) {
        this.name = name;
        this.creationDate = creationDate;
        this.validationDate = validationDate;
        this.paymentDate = paymentDate;
        this.cancellationDate = cancellationDate;
    }

    // Getter and Setter methods

    public long getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(long idDevis) {
        this.idDevis = idDevis;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(LocalDate validationDate) {
        this.validationDate = validationDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDate getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(LocalDate cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {return client;}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCommercial(Commercial commercial) {
        this.commercial = commercial;
    }

    public Commercial getCommercial() {
        return commercial;
    }

    public List<DetailsDevis> getDetailsDevisList() {
        return detailsDevisList;
    }

    public void setDetailsDevisList(List<DetailsDevis> detailsDevisList) {
        this.detailsDevisList = detailsDevisList;
    }
}