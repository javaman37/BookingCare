package com.fx.asm3.dto;

import lombok.Data;

@Data
public class OutstandingHospitalDTO {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private String description;
    private String workingHours; // add working hours if necessary
    private String importantNotes; // notes like key specialties, cost of consultation, etc.

    // Constructor to match the query output
    public OutstandingHospitalDTO(Integer id, String name, String address, String phone, String description, String workingHours, String importantNotes) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.description = description;
        this.workingHours = workingHours;
        this.importantNotes = importantNotes;
    }
}
