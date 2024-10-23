package com.fx.asm3.service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fx.asm3.dao.AppointmentDAO;
import com.fx.asm3.dao.UserDAO;
import com.fx.asm3.dto.AppointmentDTO;
import com.fx.asm3.dto.PersonalInfoDTO;
import com.fx.asm3.entity.Appointment;
import com.fx.asm3.entity.User;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	@Autowired
	private AppointmentDAO appointmentDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	public PersonalInfoDTO getPersonalInfo(String email) {
	    Optional<User> optionalUser = userDAO.findByEmail(email);

	    // Kiểm tra xem Optional có chứa giá trị hay không
	    User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));

	    // Lấy lịch sử cuộc hẹn
	    List<AppointmentDTO> appointmentHistory = getAppointmentHistory(user.getId());

	    return new PersonalInfoDTO(user.getId(), user.getName(), user.getEmail(), user.getPhone(), appointmentHistory);
	}
	
	
	public List<AppointmentDTO> getAppointmentHistory(Integer userId) {
	    List<Appointment> appointments = appointmentDAO.findByUserId(userId);
	    
	    return appointments.stream().map(appointment -> new AppointmentDTO(
	        appointment.getId(),
	        appointment.getAppointmentDate(),
	        appointment.getDoctor().getName(),
	        appointment.getHospital().getName(),
	        appointment.getStatus()
	    )).collect(Collectors.toList());
	}



	
}
