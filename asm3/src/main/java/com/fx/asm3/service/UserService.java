package com.fx.asm3.service;

import com.fx.asm3.dto.PersonalInfoDTO;

public interface UserService {
	
	public PersonalInfoDTO getPersonalInfo(String email);

}
