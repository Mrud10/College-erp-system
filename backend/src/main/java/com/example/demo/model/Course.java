package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_code", unique = true)
    private String courseCode;

    private Integer credits;

    private Integer semester;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}