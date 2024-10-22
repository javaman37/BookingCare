package com.fx.asm3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx.asm3.dao.DepartmentDAO;
import com.fx.asm3.dto.OutstandingDepartmentDTO;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDAO departmentDAO;

	@Override
	@Transactional
	public List<OutstandingDepartmentDTO> getOutstandingDepartments(int status) {
		return departmentDAO.getOutstandingMedicalDepartment(status);
	}

}
