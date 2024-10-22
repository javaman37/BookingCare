package com.fx.asm3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fx.asm3.dto.OutstandingHospitalDTO;

@Repository
public class HospitalServiceImpl implements HospitalService {
	
	@Autowired
	private HospitalDAO hospitalDAO;

    

    @Override
    public List<OutstandingHospitalDTO> getOutstandingHospitals(int status) {
        return hospitalDAO.getOutstandingHospitals(status);
    }

}
