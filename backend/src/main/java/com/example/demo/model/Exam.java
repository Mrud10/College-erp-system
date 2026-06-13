package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "exam_type")
    private String examType; // INTERNAL / MID / FINAL

    @Column(name = "max_marks")
    private Integer maxMarks;

    private LocalDate date;
}