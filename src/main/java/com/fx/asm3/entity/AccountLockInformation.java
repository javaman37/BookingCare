package com.fx.asm3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
@Data
@Entity
@Table(name = "account_lock_information")
public class AccountLockInformation {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", length = 250)
    private String email;

    @Column(name = "description")
    private String description;

    @Column(name = "create_at")
    private LocalDateTime createAt;
    
    
    
    public AccountLockInformation() {}
    
    
    public AccountLockInformation(String email, String description, LocalDateTime createAt) {
		this.email = email;
		this.description = description;
		this.createAt = createAt;
	}


}
