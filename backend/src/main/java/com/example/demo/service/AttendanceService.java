package com.example.demo.service;

import com.example.demo.model.Attendance;
import com.example.demo.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public List<Attendance> getAttendanceByStudent(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    public List<Attendance> getAttendanceByCourse(Long courseId) {
        return attendanceRepository.findByCourseId(courseId);
    }

    public List<Attendance> getAttendanceByStudentAndCourse(Long studentId, Long courseId) {
        return attendanceRepository.findByStudentIdAndCourseId(studentId, courseId);
    }

    public Attendance markAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public double getAttendancePercentage(Long studentId, Long courseId) {
        Long present = attendanceRepository.countPresentDays(studentId, courseId);
        Long total = attendanceRepository.countTotalDays(studentId, courseId);
        if (total == 0) return 0.0;
        return (present * 100.0) / total;
    }
}