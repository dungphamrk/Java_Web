package com.data.ss19.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Họ không được để trống")
    @Size(min = 3, max = 10, message = "Họ phải từ 3 đến 10 ký tự")
    private String firstName;

    @NotBlank(message = "Tên không được để trống")
    @Size(min = 3, max = 10, message = "Tên phải từ 3 đến 10 ký tự")
    private String lastName;

    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
    private String fileImage;
    private Boolean isActive;
}