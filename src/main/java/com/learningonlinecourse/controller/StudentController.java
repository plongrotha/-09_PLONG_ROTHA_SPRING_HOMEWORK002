package com.learningonlinecourse.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learningonlinecourse.model.entity.Student;
import com.learningonlinecourse.model.request.StudentRequest;
import com.learningonlinecourse.model.response.APIResponse;
import com.learningonlinecourse.service.StudentService;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

  private StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  // get all students
  @GetMapping
  public APIResponse<List<Student>> getAllStudents(@RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "10") Integer size) {
    List<Student> students = studentService.getAllStudents(page, size);

    if (students.isEmpty()) {
      return new APIResponse<>("there is no student in database",
          null, HttpStatus.NOT_FOUND,
          LocalDate.now());
    }
    return new APIResponse<>("all student retrieved successfully", students, HttpStatus.OK,
        LocalDate.now());
  }

  // public APIResponse<List<Instructor>>
  // getAllInstructor(@RequestParam(defaultValue = "1") Integer page,
  // @RequestParam(defaultValue = "10") Integer size) {
  // List<Instructor> instructors = instructorService.getAllInstructors(page,
  // size);

  // if (instructors.isEmpty()) {
  // return new APIResponse<>("No instructors found", null, HttpStatus.NOT_FOUND,
  // LocalDate.now());
  // }
  // return new APIResponse<>("Instructors retrieved successfully", instructors,
  // HttpStatus.OK, LocalDate.now());
  // }

  // save a student to the database
  @PostMapping
  public APIResponse<Student> saveStudent(@RequestBody StudentRequest studentRequest) {
    Student student = studentService.createStudent(studentRequest);
    if (student == null) {
      return new APIResponse<>("Failed to save student", null,
          HttpStatus.BAD_REQUEST, LocalDate.now());
    }
    // return new APIResponse<>("Failed to save student", null,
    // HttpStatus.BAD_REQUEST, LocalDate.now());
    return new APIResponse<>("Student saved successfully", student,
        HttpStatus.CREATED, LocalDate.now());
  }

  // get a student by id

  @GetMapping("{student-id}")
  public APIResponse<Student> getStudentById(@PathVariable("student-id") Long id) {
    Optional<Student> student = Optional.ofNullable(studentService.getStudentById(id));
    if (student.isEmpty()) {
      return new APIResponse<>("Student id " + id + " is not found", null, HttpStatus.NOT_FOUND, LocalDate.now());
    }
    return new APIResponse<>("Student founded successfully", student.get(), HttpStatus.OK, LocalDate.now());
  }

  @DeleteMapping("{student-id}")
  public APIResponse<Student> deleteStudentById(@PathVariable("student-id") Long id) {
    Student student = studentService.deleteStudentById(id);
    if (student == null) {
      return new APIResponse<>("Student id " + id + " is not found", null, HttpStatus.NOT_FOUND, LocalDate.now());
    }
    return new APIResponse<>("Student deleted successfully", student, HttpStatus.OK, LocalDate.now());
  }

  // update student by id
  @PutMapping("{student-id}")
  public APIResponse<Student> updateStudent(@PathVariable("student-id") Long id,
      @RequestBody StudentRequest studentRequest) {
    Student student = studentService.updateStudent(id, studentRequest);
    if (student == null) {
      return new APIResponse<>("Student id " + id + " is not found", null, HttpStatus.NOT_FOUND, LocalDate.now());
    }
    return new APIResponse<>("Student updated successfully", student, HttpStatus.OK, LocalDate.now());
  }
}
