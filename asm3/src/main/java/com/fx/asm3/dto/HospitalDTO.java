package com.fx.asm3.dto;

import com.fx.asm3.entity.Hospital;

import lombok.Data;

@Data
public class HospitalDTO {
	
	private Integer id;
    private String name;
    private String address;
    private String phone;
    private String description;
    

	// Constructor
    public HospitalDTO(Hospital hospital) {
        this.id = hospital.getId();
        this.name = hospital.getName();
        this.address = hospital.getAddress();
        this.phone = hospital.getPhone();
        this.description = hospital.getDescription();
    }
    

}
