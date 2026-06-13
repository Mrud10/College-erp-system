package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.model.User;
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
public class StudentController {

    private final StudentService studentService;
    private final UserService userService;

    // STUDENT - view own profile
    @GetMapping("/student/profile")
    public ResponseEntity<Student> getMyProfile(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Student student = studentService.getStudentByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return ResponseEntity.ok(student);
    }

    // ADMIN - view all students
    @GetMapping("/admin/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // ADMIN - delete student
    @DeleteMapping("/admin/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
}