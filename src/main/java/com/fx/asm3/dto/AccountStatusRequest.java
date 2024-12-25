package com.fx.asm3.dto;



import lombok.Data;

@Data
public class AccountStatusRequest {
	private String email;
    private int status;
    private String description;

}
