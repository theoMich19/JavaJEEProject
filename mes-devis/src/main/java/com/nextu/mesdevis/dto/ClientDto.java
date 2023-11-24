package com.nextu.mesdevis.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * Objet utilisé pour transférer des données entre des couches d'une application.
 * Il permet d'éviter de manipuler les données directement reçut de l'api
 */
public class ClientDto {
    /**
     * Attribut de la classe
     */
    private Long idClient;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private LocalDate registrationDate;

    private List<Long> idDevis;

    // Constructors defaut
    public ClientDto() {}


    // Constructors
    public ClientDto(Long idClient, String firstName, String lastName, String email, String phone, String address, LocalDate registrationDate, List<Long> idDevis) {
        this.idClient = idClient;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.registrationDate = registrationDate;
        this.idDevis = idDevis;
    }
    // Getter and Setter methods
    public Long getIdClient() {
        return idClient;
    }
    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setIdDevis(List<Long> idDevis) {
        this.idDevis = idDevis;
    }

    public List<Long> getIdDevis() {
        return idDevis;
    }
}