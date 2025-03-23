package com.learningonlinecourse.service;

import java.util.List;

import com.learningonlinecourse.model.entity.Student;
import com.learningonlinecourse.model.request.StudentRequest;

public interface StudentService {

  List<Student> getAllStudents(Integer page, Integer size);

  Student createStudent(StudentRequest studentreRequest);

  Student getStudentById(Long id);

  Student deleteStudentById(Long id);

  Student updateStudent(Long id, StudentRequest studentRequest);
}
