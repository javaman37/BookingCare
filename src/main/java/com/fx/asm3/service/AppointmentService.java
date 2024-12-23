package com.fx.asm3.service;

import java.util.List;

import com.fx.asm3.dto.AppointmentRequestDTO;
import com.fx.asm3.dto.PatientDTO;

public interface AppointmentService {
	
    String createOrUpdateAppointment(AppointmentRequestDTO appointmentRequestDTO, Integer userId);
    
    List<PatientDTO> getPatientsByDoctorId(Integer doctorId);
    
    void acceptAppointment(Integer appointmentId);
	void cancelAppointment(Integer appointmentId, String reason);
}
