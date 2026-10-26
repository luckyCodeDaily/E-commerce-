package com.app.models;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String houseNo;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}
