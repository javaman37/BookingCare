package com.fx.asm3.dto;

import com.fx.asm3.entity.Appointment;
import com.fx.asm3.entity.User;

import lombok.Data;

@Data
public class PatientDTO {
    private String fullName; // Họ và tên của bệnh nhân
    private String gender; // Giới tính
    private String address; // Địa chỉ của bệnh nhân
    private String detailedMedicalHistory; // Mô tả chi tiết bệnh lý

    // Constructor để chuyển dữ liệu từ User và Appointment sang PatientDTO
    public PatientDTO(User user, Appointment appointment) {
        this.fullName = user.getName();
        this.gender = user.getGender();
        this.address = user.getAddress();
        this.detailedMedicalHistory = appointment.getDescription();
    }
}
