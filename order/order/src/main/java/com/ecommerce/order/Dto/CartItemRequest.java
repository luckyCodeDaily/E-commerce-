package com.ecommerce.order.Dto;

import lombok.Data;

@Data
public class CartItemRequest {
    private  Long productID;
    private Integer quantity;
}
