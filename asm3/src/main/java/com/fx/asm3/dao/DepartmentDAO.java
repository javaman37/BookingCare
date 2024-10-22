package com.fx.asm3.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fx.asm3.dto.OutstandingDepartmentDTO;
import com.fx.asm3.entity.Department;

@Repository
public interface DepartmentDAO extends JpaRepository<Department ,Integer> {
 
	
	@Query("SELECT new com.fx.asm3.dto.OutstandingDepartmentDTO(d.id, d.name, d.phone, d.description, d.status, h.name, COUNT(appt.id)) " +
		       "FROM Department d " +
		       "LEFT JOIN d.doctors doc " +
		       "LEFT JOIN doc.appointments appt " +
		       "LEFT JOIN d.hospital h " +
		       "WHERE d.status = :status " +
		       "GROUP BY d.id " +
		       "ORDER BY COUNT(appt.id) DESC")
		List<OutstandingDepartmentDTO> getOutstandingMedicalDepartment(@Param("status") int status);
}
