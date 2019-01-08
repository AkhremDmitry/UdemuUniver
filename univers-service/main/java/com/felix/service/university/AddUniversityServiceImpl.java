package com.felix.service.university;

import com.felix.model.entity.University;
import com.felix.repository.university.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AddUniversityServiceImpl implements AddUniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    @Transactional
    public void addUniversity(University universityDAO) {

        University university = new University();
        university.setUniversityName(universityDAO.getUniversityName());
        university.setUniversityCountry(universityDAO.getUniversityCountry());
        university.setUniversityCity(universityDAO.getUniversityCity());

        universityRepository.save(university);
    }
}
