package com.felix.service.showallstudents;

import com.felix.model.entity.Student;
import com.felix.repository.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowAllStudentsServiceImpl implements ShowAllStudentsService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }
}
