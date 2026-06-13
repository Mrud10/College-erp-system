package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.Teacher;
import com.example.demo.model.User;
import com.example.demo.service.CourseService;
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
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;
    private final UserService userService;

    // ADMIN - get all courses
    @GetMapping("/admin/courses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // ADMIN - create course
    @PostMapping("/admin/courses")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.saveCourse(course));
    }

    // ADMIN - delete course
    @DeleteMapping("/admin/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }

    // TEACHER - view own courses
    @GetMapping("/teacher/courses")
    public List<Course> getMyCourses(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Teacher teacher = teacherService.getTeacherByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        return courseService.getCoursesByTeacherId(teacher.getId());
    }

    // STUDENT - view courses by semester
    @GetMapping("/student/courses")
    public List<Course> getCoursesBySemester(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return courseService.getCoursesBySemester(
                userService.getStudentSemester(user.getId())
        );
    }
}