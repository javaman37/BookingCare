package com.fx.asm3.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fx.asm3.dto.OutstandingHospitalDTO;
import com.fx.asm3.entity.Hospital;

public interface HospitalDAO extends JpaRepository<Hospital, Integer> {

	@Query("SELECT new com.fx.asm3.dto.OutstandingHospitalDTO(h.id, h.name, h.address, h.phone, h.description, h.workingHours, h.importantNotes) "
			+ "FROM Hospital h WHERE h.status = :status")
	List<OutstandingHospitalDTO> getOutstandingHospitals(@Param("status") int status);

}
