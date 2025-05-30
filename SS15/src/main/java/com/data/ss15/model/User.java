package com.data.ss15.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Size(min = 1, max = 50, message = "Tên phải có ít nhất 1 ký tự")
    private String name;
    private String password;
    @Email(message = "Không đúng định dạng email")
    private String email;
}
