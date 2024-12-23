package com.fx.asm3.dto;

import java.util.List;

import lombok.Data;

@Data
public class SearchResultDTO {
    private List<DoctorDTO> doctors;
    private List<DepartmentDTO> departments;
    private List<HospitalDTO> hospitals;

    // Constructors, getters, and setters
    public SearchResultDTO(List<DoctorDTO> doctors, List<DepartmentDTO> departments, List<HospitalDTO> hospitals) {
        this.doctors = doctors;
        this.departments = departments;
        this.hospitals = hospitals;
    }

    // Getters and Setters
}

