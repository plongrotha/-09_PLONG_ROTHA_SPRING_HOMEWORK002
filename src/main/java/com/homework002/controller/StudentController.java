package com.homework002.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homework002.model.entity.Student;
import com.homework002.model.request.StudentRequest;
import com.homework002.model.response.ApiResponse;
import com.homework002.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {


  private final StudentService studentService;

  @Operation(summary = "Get all students", description = "Get all students with pagination")
  @GetMapping
  public ResponseEntity<?> getAllStudents(@RequestParam(defaultValue = "1") Integer offset, @RequestParam(defaultValue = "10") Integer page) {
    List<Student> students = studentService.getAllStudents(offset, page);
    return ResponseEntity.ok(
      ApiResponse.builder()
        .status(HttpStatus.OK)
        .message(" all Students get successfully")
        .payload(students)
        .timestamp(LocalDateTime.now())
        .build()
    );
  }

  @Operation(summary = "Get student by id", description = "Get student by id")
  @GetMapping("{student-id}")
  public ResponseEntity<?> getStudentById(@PathVariable("student-id") Long studentId) {
    Student student = studentService.getStudentById(studentId);
    return ResponseEntity.ok(
      ApiResponse.builder()
        .status(HttpStatus.OK)
        .message("Student retrieved successfully")
        .payload(student)
        .timestamp(LocalDateTime.now())
        .build()
    );

  }

  @Operation(summary = "Create student", description = "Create student")
  @PostMapping
  public ResponseEntity<?> createStudent(@RequestBody StudentRequest studentRequest) {
    Student createdStudent = studentService.createStudent(studentRequest);
    return ResponseEntity.ok(
      ApiResponse.builder()
        .status(HttpStatus.CREATED)
        .message("Student created successfully")
        .payload(createdStudent)
        .timestamp(LocalDateTime.now())
        .build()
    );
  }

  @Operation(summary = "Update student", description = "Update student")
  @PostMapping("{student-id}")
  public ResponseEntity<?> updateStudent(@PathVariable("student-id") Long studentId, @RequestBody StudentRequest studentRequest) {
    Student updatedStudent = studentService.updateStudentById(studentId, studentRequest);
    return ResponseEntity.ok(
      ApiResponse.builder()
        .status(HttpStatus.OK)
        .message("Student updated successfully")
        .payload(updatedStudent)
        .timestamp(LocalDateTime.now())
        .build()
    );
  }

  @Operation(summary = "Delete student", description = "Delete student")
  @DeleteMapping("{student-id}")
  public ResponseEntity<?> deleteStudent(@PathVariable("student-id") Long studentId) {
    studentService.deleteStudent(studentId);
    return ResponseEntity.ok(
      ApiResponse.builder()
        .status(HttpStatus.OK)
        .message("Student deleted successfully")
        .timestamp(LocalDateTime.now())
        .build()
    );
  }

}
