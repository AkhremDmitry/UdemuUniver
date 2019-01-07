package com.felix.repository.university;

import com.felix.model.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {

    @Query("Select u from University u order by u.universityName")
    List<University> getAllUniversities();

    @Query("select count(s) from Student s where s.university.id =:universityId")
    Integer getNumOfStudentsForUniversity(@Param("universityId") Integer universityId);
}
