package com.ecommerce.user.Dto;


import com.ecommerce.user.Roles.UserRoll;
import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRoll role;
    private AddressDto address;
}
