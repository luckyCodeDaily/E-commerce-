package com.app.dto;

import com.app.UserRoll;
import com.app.models.Address;
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
