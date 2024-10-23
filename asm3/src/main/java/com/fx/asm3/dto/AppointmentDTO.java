package com.fx.asm3.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class AppointmentDTO {
	
	private Integer id;
    private LocalDate appointmentDate;
    private String doctorName;
    private String hospitalName;
    private Integer status;
    
 // Constructor, Getters and Setters
	public AppointmentDTO(Integer id, LocalDate appointmentDate, String doctorName, String hospitalName,
			Integer status) {
		this.id = id;
		this.appointmentDate = appointmentDate;
		this.doctorName = doctorName;
		this.hospitalName = hospitalName;
		this.status = status;
	}

    

  

}
