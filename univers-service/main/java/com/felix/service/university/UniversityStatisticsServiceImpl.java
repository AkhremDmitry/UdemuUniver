package com.felix.service.university;

import com.felix.repository.university.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UniversityStatisticsServiceImpl implements UniversityStatisticsService {

    @Autowired
    private UniversityRepository universityRepository;

    public Integer getNumberOfStudentsForUniversity(Integer integerId) {
        return universityRepository.getNumOfStudentsForUniversity(integerId);
    }

}
