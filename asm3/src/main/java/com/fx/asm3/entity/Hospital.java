package com.fx.asm3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import static lombok.AccessLevel.PRIVATE;


@FieldDefaults(level = PRIVATE)
@Data
@Entity
@Table(name = "hospital")
public class Hospital {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "address", length = 250)
    private String address;

    @Column(name = "phone", length = 250)
    private String phone;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    // One-to-Many relationship with Doctor
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Doctor> doctors;

    // One-to-Many relationship with Appointment
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Appointment> appointments;
    
    

	public Hospital(String name, String address, String phone, String description, Integer status, List<Doctor> doctors,
		List<Appointment> appointments) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.description = description;
		this.status = status;
		this.doctors = doctors;
		this.appointments = appointments;
	}
	public Hospital() {}
	
}
