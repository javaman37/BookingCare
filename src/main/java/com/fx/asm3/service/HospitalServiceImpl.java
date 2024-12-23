package com.fx.asm3.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.asm3.dao.HospitalDAO;
import com.fx.asm3.dto.HospitalDTO;
import com.fx.asm3.dto.OutstandingHospitalDTO;

@Service
public class HospitalServiceImpl implements HospitalService {
	
	@Autowired
	private HospitalDAO hospitalDAO;

    

    @Override
    public List<OutstandingHospitalDTO> getOutstandingHospitals(int status) {
        return hospitalDAO.getOutstandingHospitals(status);
    }



	@Override
	public List<HospitalDTO> searchHospitalsByKeyword(String keyword) {
		 return hospitalDAO.searchHospitalsByKeyword("%" + keyword + "%")
                 .stream()
                 .map(hospital -> new HospitalDTO(hospital))
                 .collect(Collectors.toList());
	}

}
