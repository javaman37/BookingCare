package com.fx.asm3.dto;

import java.util.List;

import lombok.Data;

@Data
public class PersonalInfoDTO {
	private Integer userId;
    private String name;
    private String email;
    private String phone;
    private List<AppointmentDTO> appointmentHistory;
    
	public PersonalInfoDTO(Integer userId, String name, String email, String phone,
			List<AppointmentDTO> appointmentHistory) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.appointmentHistory = appointmentHistory;
	}
    
    

}
