package com.fx.asm3.dto;

import lombok.Data;

//đăng ký tài khoản
@Data
public class UserRegistrationDTO {
	private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private String address;
    private String phone;
    private String gender;
    private String description;
    private String specialization;
    private int idDepartment;
}
