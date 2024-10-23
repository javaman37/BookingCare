package com.fx.asm3.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fx.asm3.dto.DoctorDTO;
import com.fx.asm3.entity.Doctor;

@Repository
public interface DoctorDAO extends JpaRepository<Doctor, Integer> {
	
	@Query("SELECT d FROM Doctor d WHERE d.name LIKE :keyword OR d.specialization LIKE :keyword")
	List<Doctor> searchDoctorsByKeyword(@Param("keyword") String keyword);
	
	@Query("SELECT new com.fx.asm3.dto.DoctorDTO(d) FROM Doctor d WHERE d.specialization LIKE %:keyword%")
    List<DoctorDTO> findDoctorsBySpecialization(@Param("keyword") String keyword);


}
