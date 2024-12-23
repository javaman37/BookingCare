package com.fx.asm3.service;

import java.util.List;

import com.fx.asm3.dto.DoctorDTO;

public interface DoctorService {

	public List<DoctorDTO> searchDoctorsByKeyword(String keyword);
	
	public List<DoctorDTO> searchDoctorsBySpecialization(String keyword);

}
