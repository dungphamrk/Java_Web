package com.data.ss17.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @Column(columnDefinition = "varchar(255) default 'USER'")
    private String role;
    @Column(columnDefinition = "bit default 1")
    private Boolean status = true ;
}