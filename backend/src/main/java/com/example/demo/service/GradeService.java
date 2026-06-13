package com.example.demo.service;

import com.example.demo.model.Grade;
import com.example.demo.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;

    public List<Grade> getGradesByStudent(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    public List<Grade> getGradesByStudentAndCourse(Long studentId, Long courseId) {
        return gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
    }

    public Grade saveGrade(Grade grade) {
        // auto calculate percentage and grade
        double percentage = (grade.getMarksObtained() * 100.0) / grade.getMaxMarks();
        grade.setPercentage(percentage);
        grade.setGrade(calculateGrade(percentage));
        return gradeRepository.save(grade);
    }

    private String calculateGrade(double percentage) {
        if (percentage >= 90) return "A+";
        if (percentage >= 80) return "A";
        if (percentage >= 70) return "B+";
        if (percentage >= 60) return "B";
        if (percentage >= 50) return "C";
        return "F";
    }
}