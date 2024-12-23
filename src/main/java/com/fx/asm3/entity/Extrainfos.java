package com.fx.asm3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
@Data
@Entity
@Table(name = "extrainfos")
public class Extrainfos {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @ManyToOne
	    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
	    private Appointment appointment;

	    @ManyToOne
	    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
	    private Doctor doctor;

	    @Column(name = "description")
	    private String description;

	    @Column(name = "status")
	    private Integer status;

		public Extrainfos(Appointment appointment, Doctor doctor, String description, Integer status) {
			super();
			this.appointment = appointment;
			this.doctor = doctor;
			this.description = description;
			this.status = status;
		}
		public Extrainfos() {}

}

