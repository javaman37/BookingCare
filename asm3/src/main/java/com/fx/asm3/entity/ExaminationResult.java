package com.fx.asm3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
@Data
@Entity
@Table(name = "examination_result")
public class ExaminationResult {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "file_name", length = 250)
    private String fileName;

    @Column(name = "email_doctor", length = 250)
    private String emailDoctor;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "time")
    private LocalDateTime time;

    public ExaminationResult(String fileName, String emailDoctor, User user, LocalDateTime time) {
        this.fileName = fileName;
        this.emailDoctor = emailDoctor;
        this.user = user;
        this.time = time;
    }

    public ExaminationResult() {

    }
}
