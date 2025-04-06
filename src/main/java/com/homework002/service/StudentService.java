package com.homework002.service;

import java.util.List;

import com.homework002.model.entity.Student;
import com.homework002.model.request.StudentRequest;

public interface StudentService {

  List<Student> getAllStudents(Integer offet, Integer page);
  Student getStudentById(Long id);
  Student createStudent(StudentRequest studentRequest);
  Student updateStudentById(Long id, StudentRequest studentRequest);
  void deleteStudent(Long id);

}
