package com.homework002.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.homework002.exception.NotFoundException;
import com.homework002.model.entity.Student;
import com.homework002.model.request.StudentRequest;
import com.homework002.repository.StudentRepsitory;
import com.homework002.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
  
  private final StudentRepsitory studentRepsitory;
  @Override
  public List<Student> getAllStudents(Integer offet, Integer page) {
   
    List<Student> students = studentRepsitory.getAllStudents(offet, page);
    if (students.isEmpty()) {
      throw new NotFoundException("No students found");
    }
    return students;
  }

  @Override
  public Student getStudentById(Long id) {

    Student student = studentRepsitory.getStudentById(id);
    if (student == null) {
      throw new NotFoundException("Student not found with id: " + id);
    }
    return student; 
  }

  @Override
  public Student createStudent(StudentRequest studentRequest) {
    return studentRepsitory.createStudent(studentRequest);
  }

  @Override
  public Student updateStudentById(Long id, StudentRequest studentRequest) {
    Student student = getStudentById(id);
    if (student == null) {
      throw new NotFoundException("Student not found with id: " + id);
    }
  return studentRepsitory.updateStudentById(id, studentRequest);
  }

  @Override
  public void deleteStudent(Long id) {

    Student student = getStudentById(id);
    if (student == null) {
      throw new NotFoundException("Student not found with id: " + id);
    }
    studentRepsitory.deleteStudent(id);
  }
  

}
