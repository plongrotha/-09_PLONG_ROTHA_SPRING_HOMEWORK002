package com.learningonlinecourse.service.servicimplement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learningonlinecourse.model.entity.Student;
import com.learningonlinecourse.model.request.StudentRequest;
import com.learningonlinecourse.repository.StudentRepository;
import com.learningonlinecourse.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

  private StudentRepository studentRepository;

  public StudentServiceImpl(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public List<Student> getAllStudents(Integer page, Integer size) {
    int offset = (page - 1) * size;
    return studentRepository.getAllStudents(offset, size);
  }

  @Override
  public Student createStudent(StudentRequest studentRequest) {
    return studentRepository.createStudent(studentRequest);
  }

  @Override
  public Student getStudentById(Long id) {
    return studentRepository.getStudentById(id);
  }

  @Override
  public Student deleteStudentById(Long id) {
    return studentRepository.deleteStudent(id);
  }
}
