package com.fx.asm3.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fx.asm3.entity.Appointment;

@Repository
public interface AppointmentDAO extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByUserId(Integer userId);
}

