package com.fx.asm3.service;

import java.util.List;

import com.fx.asm3.dto.DepartmentDTO;
import com.fx.asm3.dto.OutstandingDepartmentDTO;

public interface DepartmentService {

	List<OutstandingDepartmentDTO> getOutstandingDepartments(int status);

	List<DepartmentDTO> searchDepartmentsByKeyword(String keyword);

}
