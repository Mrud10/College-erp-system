package com.example.demo.controller;

import com.example.demo.model.Teacher;
import com.example.demo.model.User;
import com.example.demo.service.TeacherService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final UserService userService;

    // TEACHER - view own profile
    @GetMapping("/teacher/profile")
    public ResponseEntity<Teacher> getMyProfile(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Teacher teacher = teacherService.getTeacherByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        return ResponseEntity.ok(teacher);
    }

    // ADMIN - view all teachers
    @GetMapping("/admin/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    // ADMIN - delete teacher
    @DeleteMapping("/admin/teachers/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok().build();
    }
}