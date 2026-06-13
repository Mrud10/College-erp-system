package com.example.demo.repository;

import com.example.demo.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByCourseId(Long courseId);
    List<Material> findByTeacherId(Long teacherId);
}