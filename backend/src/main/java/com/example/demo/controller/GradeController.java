package com.example.demo.controller;

import com.example.demo.model.Grade;
import com.example.demo.model.Student;
import com.example.demo.model.User;
import com.example.demo.service.GradeService;
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
public class GradeController {

    private final GradeService gradeService;
    private final StudentService studentService;
    private final UserService userService;

    // TEACHER - enter marks (auto calculates grade)
    @PostMapping("/teacher/grades")
    public ResponseEntity<Grade> enterMarks(@RequestBody Grade grade) {
        return ResponseEntity.ok(gradeService.saveGrade(grade));
    }

    // STUDENT - view own grades
    @GetMapping("/student/grades")
    public List<Grade> getMyGrades(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Student student = studentService.getStudentByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return gradeService.getGradesByStudent(student.getId());
    }

    // STUDENT - view grades for specific course
    @GetMapping("/student/grades/{courseId}")
    public List<Grade> getMyGradesByCourse(
            @PathVariable Long courseId,
            Authentication authentication) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Student student = studentService.getStudentByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return gradeService.getGradesByStudentAndCourse(student.getId(), courseId);
    }
}