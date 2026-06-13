package com.example.demo.service;

import com.example.demo.model.Material;
import com.example.demo.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;

    public List<Material> getMaterialsByCourse(Long courseId) {
        return materialRepository.findByCourseId(courseId);
    }

    public List<Material> getMaterialsByTeacher(Long teacherId) {
        return materialRepository.findByTeacherId(teacherId);
    }

    public Material saveMaterial(Material material) {
        return materialRepository.save(material);
    }

    public void deleteMaterial(Long id) {
        materialRepository.deleteById(id);
    }
}