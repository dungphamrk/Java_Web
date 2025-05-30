package com.data.ss15.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Student {
    private String id;
    private String name;
    private int age;
    private String className;
    private String email;
    private String address;
    private String phone;
}
