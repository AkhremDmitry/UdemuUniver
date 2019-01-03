package com.felix.service.student;

import com.felix.model.entity.Student;
import com.felix.repository.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveStudentServiceImpl implements RemoveStudentService{

    @Autowired
    private StudentRepository studentRepository;

    public void removeStudent(Student student) {
        studentRepository.delete(student);
    }
}
