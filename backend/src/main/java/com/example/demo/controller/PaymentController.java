package com.example.demo.controller;

import com.example.demo.model.Payment;
import com.example.demo.model.Student;
import com.example.demo.model.User;
import com.example.demo.service.PaymentService;
import com.example.demo.service.StudentService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final StudentService studentService;
    private final UserService userService;

    // STUDENT - view own payments
    @GetMapping("/student/fees")
    public List<Payment> getMyPayments(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Student student = studentService.getStudentByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return paymentService.getPaymentsByStudent(student.getId());
    }

    // ADMIN - view all payments
    @GetMapping("/admin/fees")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    // ADMIN - view pending payments
    @GetMapping("/admin/fees/pending")
    public List<Payment> getPendingPayments() {
        return paymentService.getPendingPayments();
    }

    // ADMIN - confirm a payment
    @PutMapping("/admin/fees/{id}/confirm")
    public ResponseEntity<Payment> confirmPayment(
            @PathVariable Long id,
            Authentication authentication) {
        String email = authentication.getName();
        User admin = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(paymentService.confirmPayment(id, admin));
    }
}