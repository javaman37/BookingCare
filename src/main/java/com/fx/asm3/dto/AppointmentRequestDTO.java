package com.fx.asm3.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AppointmentRequestDTO {
	
	private Integer doctorId;
    private String title;
    private String description;
    private LocalDate appointmentDate;
    private String registrationTime;
    private Integer hospitalId;


}
