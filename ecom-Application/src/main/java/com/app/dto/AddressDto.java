package com.app.dto;

import lombok.Data;

@Data
public class AddressDto {
    private String houseNo;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}
