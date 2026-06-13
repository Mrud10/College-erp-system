package com.example.demo.service;

import com.example.demo.model.Payment;
import com.example.demo.model.User;
import com.example.demo.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public List<Payment> getPaymentsByStudent(Long studentId) {
        return paymentRepository.findByStudentId(studentId);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> getPendingPayments() {
        return paymentRepository.findByStatus("PENDING");
    }

    public Payment confirmPayment(Long paymentId, User admin) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus("CONFIRMED");
        payment.setVerifiedBy(admin);
        payment.setConfirmedAt(LocalDateTime.now());
        return paymentRepository.save(payment);
    }
}