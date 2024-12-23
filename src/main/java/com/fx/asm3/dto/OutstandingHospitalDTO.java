package com.fx.asm3.dto;

import lombok.Data;

@Data
public class OutstandingHospitalDTO {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private String description;

 // Constructor phù hợp với các tham số trong truy vấn
    public OutstandingHospitalDTO(Integer id, String name, String address, String phone, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.description = description;
    }
}
