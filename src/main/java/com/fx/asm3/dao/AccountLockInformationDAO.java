package com.fx.asm3.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fx.asm3.entity.AccountLockInformation;

@Repository
public interface AccountLockInformationDAO extends JpaRepository<AccountLockInformation, Integer> {

}
