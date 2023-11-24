package com.nextu.mesdevis.dto;

import com.nextu.mesdevis.entity.DetailsDevis;

import java.time.LocalDate;
import java.util.List;

public class DevisDto {

    private  long idDevis;
    private String name;
    private LocalDate creationDate;
    private LocalDate validationDate;
    private LocalDate paymentDate;
    private LocalDate cancellationDate;
    private long idClient;
    private Long idCommercial;
    private List<DetailsDevisDto> detailsDevisList;

    // Constructors

    public DevisDto() {
    }

    public DevisDto(long idDevis, String name,LocalDate creationDate, LocalDate validationDate, LocalDate paymentDate, LocalDate cancellationDate, long idClient, long idCommercial, List<DetailsDevisDto> detailsDevisList) {
        this.idDevis = idDevis;
        this.name = name;
        this.creationDate = creationDate;
        this.validationDate = validationDate;
        this.paymentDate = paymentDate;
        this.cancellationDate = cancellationDate;
        this.idClient = idClient;
        this.idCommercial = idCommercial;
        this.detailsDevisList = detailsDevisList;
    }

    // Getter and Setter methods


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public Long getIdCommercial() {
        return idCommercial;
    }

    public void setIdCommercial(Long idCommercial) {
        this.idCommercial = idCommercial;
    }


    public List<DetailsDevisDto> getDetailsDevisList() {
        return detailsDevisList;
    }

    public void setDetailsDevisList(List<DetailsDevisDto> detailsDevisList) {
        this.detailsDevisList = detailsDevisList;
    }
}