package com.ecommerce.user.Models;


import com.ecommerce.user.Roles.UserRoll;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users-table")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRoll role = UserRoll.CUSTOMER;

   @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
   @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;
   @CreationTimestamp
   private LocalDateTime created;
   @UpdateTimestamp
   private LocalDateTime updated;
}