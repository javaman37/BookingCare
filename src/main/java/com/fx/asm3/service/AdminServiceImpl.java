package com.fx.asm3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.asm3.dao.UserDAO;
import com.fx.asm3.entity.AccountLockInformation;
import com.fx.asm3.entity.User;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
    private UserDAO userDAO;
    @Autowired
    private AccountLockInformationDAO lockInfoDAO;

	@Override
	public boolean changeStatusAccount(String email, int status) {
		User user = userDAO.findByEmail(email).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        user.setActive(status == 1);
        userDAO.save(user);
        return true;
	}

	@Override
	public void saveLockInfo(AccountLockInformation lockInfo) {
		lockInfoDAO.save(lockInfo);

	}

}
