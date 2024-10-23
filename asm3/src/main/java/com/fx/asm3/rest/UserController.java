package com.fx.asm3.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fx.asm3.dto.DepartmentDTO;
import com.fx.asm3.dto.DoctorDTO;
import com.fx.asm3.dto.HospitalDTO;
import com.fx.asm3.dto.OutstandingDepartmentDTO;
import com.fx.asm3.dto.OutstandingHospitalDTO;
import com.fx.asm3.dto.PersonalInfoDTO;
import com.fx.asm3.dto.SearchResultDTO;
import com.fx.asm3.service.DepartmentService;
import com.fx.asm3.service.DoctorService;
import com.fx.asm3.service.HospitalService;
import com.fx.asm3.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private DoctorService doctorService;

	@Autowired
	private HospitalService hospitalService;

	@Autowired
	private DepartmentService departmentService;

	// Danh sách khoa nổi bật có lịch đặt nhiều
	@GetMapping("/outstanding-department")
	public ResponseEntity<?> getOutstandingDepartment(@RequestParam("status") int status) {
		try {
			List<OutstandingDepartmentDTO> departments = departmentService.getOutstandingDepartments(status);
			if (departments.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No highlighted Department found.");
			}
			return ResponseEntity.ok(departments);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while fetching Departments.");
		}

	}

	@GetMapping("/outstanding-hospitals")
	public ResponseEntity<?> getOutstandingHospitals(@RequestParam("status") int status) {
		try {
			List<OutstandingHospitalDTO> hospitals = hospitalService.getOutstandingHospitals(status);
			if (hospitals.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No highlighted hospitals found.");
			}
			return ResponseEntity.ok(hospitals);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while fetching hospital data.");
		}
	}

	@GetMapping("/personal-info")
	public ResponseEntity<PersonalInfoDTO> getPersonalInfo(Authentication authentication) {
		String email = authentication.getName(); // Extract email from authenticated user
		PersonalInfoDTO personalInfo = userService.getPersonalInfo(email);
		return ResponseEntity.ok(personalInfo);
	}
	
	@GetMapping("/search")
    public ResponseEntity<SearchResultDTO> search(@RequestParam("keyword") String keyword) {
        List<DoctorDTO> doctors = doctorService.searchDoctorsByKeyword(keyword);
        List<DepartmentDTO> departments = departmentService.searchDepartmentsByKeyword(keyword);
        List<HospitalDTO> hospitals = hospitalService.searchHospitalsByKeyword(keyword);

        SearchResultDTO searchResult = new SearchResultDTO(doctors, departments, hospitals);
        return ResponseEntity.ok(searchResult);
    }
	
	@GetMapping("/search-by-specialization")
    public ResponseEntity<List<DoctorDTO>> searchDoctorsBySpecialization(@RequestParam("keyword") String keyword) {
        List<DoctorDTO> doctors = doctorService.searchDoctorsBySpecialization(keyword);
        return ResponseEntity.ok(doctors);
    }

}
