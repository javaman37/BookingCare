package com.fx.asm3.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.asm3.dao.AppointmentDAO;
import com.fx.asm3.dao.DoctorDAO;
import com.fx.asm3.dao.HospitalDAO;
import com.fx.asm3.dao.UserDAO;
import com.fx.asm3.dto.AppointmentRequestDTO;
import com.fx.asm3.dto.PatientDTO;
import com.fx.asm3.entity.Appointment;
import com.fx.asm3.entity.Doctor;
import com.fx.asm3.entity.Hospital;
import com.fx.asm3.entity.User;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentDAO appointmentDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private DoctorDAO doctorDAO;

	@Autowired
	private HospitalDAO hospitalDAO;

	@Override
	public String createOrUpdateAppointment(AppointmentRequestDTO appointmentRequestDTO, Integer userId)
			throws RuntimeException {
		// Lấy thông tin người dùng từ userId
		User user = userDAO.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		// Lấy danh sách tất cả các lịch khám của bệnh nhân này
		List<Appointment> existingAppointments = appointmentDAO.findByUserId(userId);

		// Kiểm tra xem có lịch khám nào của bệnh nhân với cùng một bác sĩ hay không
		for (Appointment existingAppointment : existingAppointments) {
			if (existingAppointment.getDoctor().getId().equals(appointmentRequestDTO.getDoctorId())) {
				// Nếu có lịch khám với cùng bác sĩ, cập nhật thông tin lịch khám
				existingAppointment.setTitle(appointmentRequestDTO.getTitle());
				existingAppointment.setDescription(appointmentRequestDTO.getDescription());
				existingAppointment.setAppointmentDate(appointmentRequestDTO.getAppointmentDate());
				existingAppointment.setRegistrationTime(appointmentRequestDTO.getRegistrationTime());

				Hospital hospital = hospitalDAO.findById(appointmentRequestDTO.getHospitalId())
						.orElseThrow(() -> new RuntimeException("Hospital not found"));
				existingAppointment.setHospital(hospital);
				appointmentDAO.save(existingAppointment);
				return "Updated successfully";
			}
		}

		// Tạo đối tượng Appointment từ DTO và userId
		Appointment appointment = new Appointment();
		appointment.setUser(user);

		Doctor doctor = doctorDAO.findById(appointmentRequestDTO.getDoctorId())
				.orElseThrow(() -> new RuntimeException("Doctor not found"));
		appointment.setDoctor(doctor);

		appointment.setTitle(appointmentRequestDTO.getTitle());
		appointment.setDescription(appointmentRequestDTO.getDescription());
		appointment.setAppointmentDate(appointmentRequestDTO.getAppointmentDate());
		appointment.setRegistrationTime(appointmentRequestDTO.getRegistrationTime());

		Hospital hospital = hospitalDAO.findById(appointmentRequestDTO.getHospitalId())
				.orElseThrow(() -> new RuntimeException("Hospital not found"));
		appointment.setHospital(hospital);

		// Lưu appointment vào database
		appointmentDAO.save(appointment);
		return "Appointment booked successfully!";
	}

	@Override
	public List<PatientDTO> getPatientsByDoctorId(Integer doctorId) {
		// Lấy danh sách các cuộc hẹn của bác sĩ
		List<Appointment> appointments = appointmentDAO.findByDoctorId(doctorId);

		// Chuyển đổi từ Appointment và User sang PatientDTO
		List<PatientDTO> patientDTOs = appointments.stream()
				.map(appointment -> new PatientDTO(appointment.getUser(), appointment)).collect(Collectors.toList());

		return patientDTOs;
	}
	
	
	public void acceptAppointment(Integer appointmentId) {
        Appointment appointment = appointmentDAO.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setStatus(1); // Set status to 1 for accepted
        //appointment.setCancellationReason(null); // Xóa lý do hủy nếu có
        appointmentDAO.save(appointment);
    }

    @Override
    public void cancelAppointment(Integer appointmentId, String reason) {
        Appointment appointment = appointmentDAO.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setStatus(0); // Set status to 0 for cancelled
        appointment.setDescription(reason); // Set lý do hủy
        appointmentDAO.save(appointment);
    }

}
