package com.fx.asm3.dto;

import com.fx.asm3.entity.Department;

import lombok.Data;

@Data
public class DepartmentDTO {
	private Integer id;
    private String name;
    private String description;
    private String phone;
    private String hospitalName;
    
 // Constructor
    public DepartmentDTO(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.description = department.getDescription();
        this.phone = department.getPhone();
        this.hospitalName = department.getHospital() != null ? department.getHospital().getName() : null;
    }
    

}
