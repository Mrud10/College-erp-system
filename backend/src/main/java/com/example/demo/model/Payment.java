package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "fee_structure_id")
    private FeeStructure feeStructure;

    private Double amount;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    private String status; // PENDING / CONFIRMED

    @ManyToOne
    @JoinColumn(name = "verified_by")
    private User verifiedBy;

    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;
}