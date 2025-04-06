package com.homework002.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homework002.model.entity.Course;
import com.homework002.model.request.CourseRequest;
import com.homework002.model.response.ApiResponse;
import com.homework002.service.CousreService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

  private final CousreService courseService;

  @Operation(summary = "Get all courses", description = "Get all courses with pagination")
  @GetMapping
  public ResponseEntity<?> getAllCourses(@RequestParam(defaultValue = "1") Integer offset,@RequestParam(defaultValue = "10")  Integer page) {
    List<Course> courses = courseService.getAllCourses( offset, page);
    return ResponseEntity.ok(
      ApiResponse.builder()
        .status(HttpStatus.OK)
        .message("Courses retrieved successfully")
        .payload(courses)
        .timestamp(LocalDateTime.now())
        .build()
    );
  }

  @Operation(summary = "Create a course", description = "Create a new course")
  @PostMapping
  public ResponseEntity<?> createCourse(@RequestBody CourseRequest courseRequest) {
    Course course = courseService.createCourse(courseRequest);
    return ResponseEntity.ok(
      ApiResponse.builder()
        .status(HttpStatus.CREATED)
        .message("Course created successfully")
        .payload(course)
        .timestamp(LocalDateTime.now())
        .build()
    );
  }

  @Operation(summary = "Get a course by ID", description = "Get a course by its ID")
  @GetMapping("{course-id}")
  public ResponseEntity<?> getCourseById(@PathVariable("course-id") Long courseId) {
    Course course = courseService.getCourseById(courseId);
    return ResponseEntity.ok(
      ApiResponse.builder()
        .status(HttpStatus.OK)
        .message("Course is geted successfully")
        .payload(course)
        .timestamp(LocalDateTime.now())
        .build()
    );
  }

  @Operation(summary = "Update a course", description = "Update a course by its ID")
  @PutMapping("{course-id}")
  public ResponseEntity<?> updateCourse(@PathVariable("course-id") Long courseId, @RequestBody CourseRequest courseRequest) {
    Course course = courseService.updateCourseById(courseId, courseRequest);
    return ResponseEntity.ok(
      ApiResponse.builder()
        .status(HttpStatus.OK)
        .message("Course updated successfully")
        .payload(course)
        .timestamp(LocalDateTime.now())
        .build()
    );
  }

  @Operation(summary = "Delete a course", description = "Delete a course by its ID")
  @DeleteMapping("{course-id}")
  public ResponseEntity<?> deleteCourse(@PathParam("course-id") Long courseId) {
    courseService.deleteCourse(courseId);
    return ResponseEntity.ok(
      ApiResponse.builder()
        .status(HttpStatus.OK)
        .message("Course deleted successfully")
        .payload(courseId)  
        .timestamp(LocalDateTime.now())
        .build()
    );
  }

}
