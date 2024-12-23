package com.fx.asm3.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fx.asm3.config.CustomUserDetails;
import com.fx.asm3.dto.PatientDTO;
import com.fx.asm3.service.AppointmentService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
    private AppointmentService appointmentService;
	
	@GetMapping("/patients")
    public ResponseEntity<?> getPatients(Authentication authentication) {
        try {
            // Lấy thông tin doctorId từ JWT token
            CustomUserDetails doctorDetails = (CustomUserDetails) authentication.getPrincipal();
            Integer doctorId = doctorDetails.getUserId();

            // Gọi service để lấy danh sách bệnh nhân
            List<PatientDTO> patientList = appointmentService.getPatientsByDoctorId(doctorId);

            return ResponseEntity.ok(patientList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }
	
	
	@PutMapping("/appointments/{appointmentId}/accept")
    public ResponseEntity<String> acceptAppointment(@PathVariable Integer appointmentId) {
        try {
            appointmentService.acceptAppointment(appointmentId);
            return ResponseEntity.ok("Appointment accepted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/appointments/{appointmentId}/cancel")
    public ResponseEntity<String> cancelAppointment(@PathVariable Integer appointmentId, @RequestBody String reason) {
        try {
            appointmentService.cancelAppointment(appointmentId, reason);
            return ResponseEntity.ok("Appointment cancelled successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }


}
