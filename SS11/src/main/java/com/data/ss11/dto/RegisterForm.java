package com.data.ss11.dto;

import com.data.ss11.validation.AdminGroup;
import com.data.ss11.validation.UserGroup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RegisterForm {

    @NotBlank(message = "Tên không được để trống", groups = {UserGroup.class, AdminGroup.class})
    private String name;

    @NotBlank(message = "Email không được để trống", groups = {UserGroup.class, AdminGroup.class})
    @Email(message = "Email không hợp lệ", groups = {UserGroup.class, AdminGroup.class})
    private String email;

    @NotNull(message = "Vai trò không được để trống", groups = {UserGroup.class, AdminGroup.class})
    private String role;

    @NotBlank(message = "Mã nhân viên là bắt buộc cho admin", groups = AdminGroup.class)
    private String staffCode;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getStaffCode() { return staffCode; }
    public void setStaffCode(String staffCode) { this.staffCode = staffCode; }
}
