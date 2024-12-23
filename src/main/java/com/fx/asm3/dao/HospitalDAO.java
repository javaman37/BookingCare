package com.fx.asm3.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fx.asm3.dto.OutstandingHospitalDTO;
import com.fx.asm3.entity.Hospital;

@Repository
public interface HospitalDAO extends JpaRepository<Hospital, Integer> {

	@Query("SELECT new com.fx.asm3.dto.OutstandingHospitalDTO(h.id, h.name, h.address, h.phone, h.description) "
			+ "FROM Hospital h WHERE h.status = :status")
	List<OutstandingHospitalDTO> getOutstandingHospitals(@Param("status") int status);

	@Query("SELECT h FROM Hospital h WHERE h.name LIKE :keyword OR h.address LIKE :keyword")
	List<Hospital> searchHospitalsByKeyword(@Param("keyword") String keyword);

}
