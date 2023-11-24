package com.nextu.mesdevis.dto;

import java.util.List;

public class CommercialDto {

    private  long idCommercial;
    private String firstName;
    private String lastName;
    private String role;
    private String email;

    private List<Long> idCategorys;
    private List<Long> idDevis;
    // Constructors

    public CommercialDto() {
    }

    public CommercialDto(long idCommercial,String firstName, String lastName, String role, String email, List<Long> idCategorys, List<Long> idDevis) {
        this.idCommercial = idCommercial;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.idCategorys = idCategorys;
        this.idDevis = idDevis;
    }

    // Getter and Setter methods

    public Long getIdCommercial() {
        return idCommercial;
    }

    public void setIdCommercial(Long idCommercial) {
        this.idCommercial = idCommercial;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getIdCategorys() {
        return idCategorys;
    }

    public void setIdCategorys(List<Long> idCategorys) {
        this.idCategorys = idCategorys;
    }

    public void setIdCommercial(long idCommercial) {
        this.idCommercial = idCommercial;
    }

    public void setIdDevis(List<Long> idDevis) {
        this.idDevis = idDevis;
    }

    public List<Long> getIdDevis() {
        return idDevis;
    }



}