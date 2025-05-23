package com.data.ss11.dto;

import com.data.ss11.validation.PhoneNumber;
import javax.validation.constraints.NotBlank;

public class UserForm {

    @NotBlank(message = "Số điện thoại không được để trống")
    @PhoneNumber
    private String phoneNumber;
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
