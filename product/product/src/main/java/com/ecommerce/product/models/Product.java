package com.ecommerce.product.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = "product")
@NoArgsConstructor
@JsonPropertyOrder({"id", "name","description","price","stockQuality","category","imgUrl","created","updated"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
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
    @Column(name = "active")
    private Boolean active=true;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime created;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated;
}
