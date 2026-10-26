package com.app.dto;

import lombok.Data;

@Data
public class CartItemRequest {
    private  Long productID;
    private Integer quantity;
}
