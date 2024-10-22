package com.fx.asm3.service;

import java.util.List;

import com.fx.asm3.dto.OutstandingHospitalDTO;

public interface HospitalService {

	List<OutstandingHospitalDTO> getOutstandingHospitals(int status);

}
