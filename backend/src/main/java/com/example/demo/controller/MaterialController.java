package com.example.demo.controller;

import com.example.demo.model.Material;
import com.example.demo.model.Teacher;
import com.example.demo.model.User;
import com.example.demo.service.MaterialService;
import com.example.demo.service.TeacherService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;
    private final TeacherService teacherService;
    private final UserService userService;

    // TEACHER - upload material
    @PostMapping("/teacher/materials")
    public ResponseEntity<Material> uploadMaterial(
            @RequestBody Material material,
            Authentication authentication) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Teacher teacher = teacherService.getTeacherByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        material.setTeacher(teacher);
        material.setUploadedAt(LocalDateTime.now());
        return ResponseEntity.ok(materialService.saveMaterial(material));
    }

    // TEACHER - view own uploaded materials
    @GetMapping("/teacher/materials")
    public List<Material> getMyMaterials(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Teacher teacher = teacherService.getTeacherByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        return materialService.getMaterialsByTeacher(teacher.getId());
    }

    // STUDENT - view materials for a course
    @GetMapping("/student/materials/{courseId}")
    public List<Material> getMaterialsByCourse(@PathVariable Long courseId) {
        return materialService.getMaterialsByCourse(courseId);
    }

    // TEACHER - delete material
    @DeleteMapping("/teacher/materials/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
        materialService.deleteMaterial(id);
        return ResponseEntity.ok().build();
    }
}