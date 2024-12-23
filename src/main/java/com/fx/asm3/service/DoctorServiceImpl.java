package com.fx.asm3.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.asm3.dao.DoctorDAO;
import com.fx.asm3.dto.DoctorDTO;

@Service
public class DoctorServiceImpl implements DoctorService {
	
	@Autowired
	private DoctorDAO doctorDAO;

	@Override
	public List<DoctorDTO> searchDoctorsByKeyword(String keyword) {
		return doctorDAO.searchDoctorsByKeyword("%" + keyword + "%")
                .stream()
                .map(doctor -> new DoctorDTO(doctor))
                .collect(Collectors.toList());
	}
	
	
	public List<DoctorDTO> searchDoctorsBySpecialization(String keyword) {
        return doctorDAO.findDoctorsBySpecialization(keyword);
    }

}
