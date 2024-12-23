package com.fx.asm3.dto;

import com.fx.asm3.entity.Doctor;

import lombok.Data;

@Data
public class DoctorDTO {
	private Integer id;
    private String name;
    private String specialization;
    private String departmentName;
    
 // Constructor
    public DoctorDTO(Doctor doctor) {
        this.id = doctor.getId();
        this.name = doctor.getName();
        this.specialization = doctor.getSpecialization();
        this.departmentName = doctor.getDepartment() != null ? doctor.getDepartment().getName() : null;
        
    }
    
    

}
