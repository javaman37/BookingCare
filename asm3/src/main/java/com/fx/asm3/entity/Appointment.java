package com.fx.asm3.entity;
import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = PRIVATE)
@Data
@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "email_doctor", length = 250)
    private String emailDoctor;

    @Column(name = "title", length = 250)
    private String title;

    @Column(name = "description")
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;


    @Column(name = "appointment_date")
    private LocalDate appointmentDate;

    @Column(name = "registration_time", length = 250)
    private String registrationTime;

    @Column(name = "create_at")
    private LocalDateTime createdAt;

    @Column(name = "delete_at")
    private LocalDateTime deletedAt;

    @Column(name = "status")
    private Integer status;

    
    
   
}

