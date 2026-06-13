package com.example.demo.controller;

import com.example.demo.model.Attendance;
import com.example.demo.model.Student;
import com.example.demo.model.User;
import com.example.demo.service.AttendanceService;
import com.example.demo.service.StudentService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final StudentService studentService;
    private final UserService userService;

    // TEACHER - mark attendance
    @PostMapping("/teacher/attendance")
    public ResponseEntity<Attendance> markAttendance(@RequestBody Attendance attendance) {
        return ResponseEntity.ok(attendanceService.markAttendance(attendance));
    }

    // TEACHER - view attendance for a course
    @GetMapping("/teacher/attendance/course/{courseId}")
    public List<Attendance> getAttendanceByCourse(@PathVariable Long courseId) {
        return attendanceService.getAttendanceByCourse(courseId);
    }

    // STUDENT - view own attendance
    @GetMapping("/student/attendance")
    public List<Attendance> getMyAttendance(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Student student = studentService.getStudentByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return attendanceService.getAttendanceByStudent(student.getId());
    }

    // STUDENT - view attendance percentage for a course
    @GetMapping("/student/attendance/percentage/{courseId}")
    public ResponseEntity<Map<String, Object>> getAttendancePercentage(
            @PathVariable Long courseId,
            Authentication authentication) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Student student = studentService.getStudentByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        double percentage = attendanceService.getAttendancePercentage(student.getId(), courseId);

        Map<String, Object> response = new HashMap<>();
        response.put("studentId", student.getId());
        response.put("courseId", courseId);
        response.put("attendancePercentage", percentage);

        return ResponseEntity.ok(response);
    }
}