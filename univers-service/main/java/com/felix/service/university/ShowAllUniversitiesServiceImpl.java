package com.felix.service.university;

import com.felix.model.entity.University;
import com.felix.repository.university.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowAllUniversitiesServiceImpl implements ShowAllUniversitiesService {

    @Autowired
    private UniversityRepository universityRepository;

    public List<University> getAllUniversities() {
        return universityRepository.getAllUniversities();
    }
}
