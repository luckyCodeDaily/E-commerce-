package com.ecommerce.user.Dto;

import com.ecommerce.user.Roles.UserRoll;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "firstName", "lastName", "email", "phone", "role", "address"})
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRoll role;
    private AddressDto address;


    }

