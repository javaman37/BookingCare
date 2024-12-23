package com.fx.asm3.dto;

import lombok.Data;

@Data
public class OutstandingDepartmentDTO {
	
	private Integer id;
    private String name;
    private String phone;
    private String description;
    private Integer status;
    private String hospitalName; // Tên của bệnh viện liên kết với khoa
    private Long appointmentCount; // Số lượng đặt lịch khám cho khoa đó
    
 // Constructor phù hợp với query để lấy dữ liệu từ database
    public OutstandingDepartmentDTO(Integer id, String name, String phone, String description, Integer status, String hospitalName, Long appointmentCount) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.status = status;
        this.hospitalName = hospitalName;
        this.appointmentCount = appointmentCount;
    }

}
