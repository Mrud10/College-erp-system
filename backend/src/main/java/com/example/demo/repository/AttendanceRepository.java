package com.example.demo.repository;

import com.example.demo.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // get all attendance for a student in a course
    List<Attendance> findByStudentIdAndCourseId(Long studentId, Long courseId);

    // get all attendance for a student
    List<Attendance> findByStudentId(Long studentId);

    // get all attendance for a course (teacher uses this)
    List<Attendance> findByCourseId(Long courseId);

    // count present days for a student in a course
    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.student.id = ?1 AND a.course.id = ?2 AND a.status = 'PRESENT'")
    Long countPresentDays(Long studentId, Long courseId);

    // count total days for a student in a course
    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.student.id = ?1 AND a.course.id = ?2")
    Long countTotalDays(Long studentId, Long courseId);
}