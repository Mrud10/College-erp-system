//jpa
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Data
@Entity
@Table(name = "users")
public class User {
    @JsonIgnore
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String role; // ADMIN, STUDENT, TEACHER

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}