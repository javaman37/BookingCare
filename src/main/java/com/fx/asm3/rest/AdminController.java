package com.fx.asm3.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fx.asm3.dto.AccountStatusRequest;
import com.fx.asm3.entity.AccountLockInformation;
import com.fx.asm3.service.AdminService;


@RestController
@RequestMapping("/admin")
public class AdminController {
	 @Autowired
	    private AdminService adminService;
	
	
	@PostMapping("/toggleAccountStatus")
	public String toggleAccountStatus(@RequestBody AccountStatusRequest request) {
	    try {
	        // Kiểm tra trạng thái và mô tả lý do (nếu có)
	        if (request.getStatus() == 1) {
	            if (adminService.changeStatusAccount(request.getEmail(), 1)) {
	                return "Đã mở khóa tài khoản có email: " + request.getEmail();
	            }
	        } else if (request.getStatus() == 0 && request.getDescription() != null && !request.getDescription().isEmpty()) {
	            if (adminService.changeStatusAccount(request.getEmail(), 0)) {
	                AccountLockInformation lockInfo = new AccountLockInformation(request.getEmail(), request.getDescription());
	                adminService.saveLockInfo(lockInfo);
	                return "Đã khóa tài khoản có email: " + request.getEmail();
	            }
	        }
	        return "Lỗi trong quá trình khóa/mở khóa tài khoản";
	    } catch (Exception e) {
	        return "Lỗi: " + e.getMessage();
	    }
	}


}
