package com.fx.asm3.service;

import com.fx.asm3.entity.AccountLockInformation;

public interface AdminService {
	boolean changeStatusAccount(String email, int status);
    void saveLockInfo(AccountLockInformation lockInfo);

}
