package com.ecommerce.product.Dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
@Data
@JsonPropertyOrder({"id", "name","description","price","stockQuality","category","imgUrl","created","updated"})
public class ProductRequest {
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "stock_Quality")
    private Integer stockQuality;
    @Column(name = "category")
    private String category;
    @Column(name = "imgUrl")
    private String imgUrl;

}
