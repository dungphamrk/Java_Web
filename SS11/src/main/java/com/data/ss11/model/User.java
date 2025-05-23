package com.data.ss11.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class User {

    @NotBlank(message = "Tên không được để trống")
    @Size(min = 2, max = 30, message = "Tên phải có độ dài từ 2 đến 30 ký tự")
    private String name;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Định dạng email không hợp lệ")
    @Size(max = 30, message = "Email không được vượt quá 30 ký tự")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Size(min = 2, max = 30, message = "Số điện thoại phải có độ dài từ 2 đến 30 ký tự")
    private String phone;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min =8, message = "Mật khẩu phải có độ dài từ 2 đến 30 ký tự")
    private String password;

    @NotNull(message = "Trạng thái không được để trống")
    private Boolean status;
}