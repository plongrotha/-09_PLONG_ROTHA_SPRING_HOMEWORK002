package com.learningonlinecourse.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learningonlinecourse.model.entity.Course;
import com.learningonlinecourse.model.request.CourseRequest;
import com.learningonlinecourse.model.response.APIResponse;
import com.learningonlinecourse.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

  private CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  // get all courses
  @Operation(summary = "Get all courses")
  @GetMapping
  public APIResponse<List<Course>> getAllCourses(@RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "10") Integer size) {
    List<Course> courses = courseService.getAllCourses(page, size);

    if (courses.isEmpty()) {
      return new APIResponse<>("No courses found", null, HttpStatus.NOT_FOUND, LocalDate.now());
    }
    return new APIResponse<>("Courses retrieved successfully", courses, HttpStatus.OK, LocalDate.now());
  }

  // create a new course
  @Operation(summary = "Create a new course")
  @PostMapping
  public APIResponse<Course> saveCourse(@RequestBody CourseRequest courseRequest) {
    Course course = courseService.saveCourse(courseRequest);

    if (course == null) {
      return new APIResponse<>("Failed to save course", null, HttpStatus.BAD_REQUEST, LocalDate.now());
    }
    return new APIResponse<>("Course saved successfully", course, HttpStatus.CREATED, LocalDate.now());
  }

  // get a course by id
  @Operation(summary = "Get a course by id")
  @GetMapping("/{course-id}")
  public APIResponse<Course> getCourseById(@RequestParam Long courseId) {
    Course course = courseService.getCourseById(courseId);
    if (course == null) {
      return new APIResponse<>("Course not found", null, HttpStatus.NOT_FOUND, LocalDate.now());
    }
    return new APIResponse<>("Course retrieved successfully", course, HttpStatus.OK, LocalDate.now());
  }

  // update a course
  @Operation(summary = "Update a course")
  @PutMapping("/{course-id}")
  public APIResponse<Course> updateCourse(@RequestParam Long courseId, @RequestBody CourseRequest courseRequest) {
    Course course = courseService.updateCourse(courseId, courseRequest);
    if (course == null) {
      return new APIResponse<>("Failed to update course", null, HttpStatus.BAD_REQUEST, LocalDate.now());
    }
    return new APIResponse<>("Course updated successfully", course, HttpStatus.OK, LocalDate.now());
  }

  // delete a course
  @Operation(summary = "Delete a course")
  @DeleteMapping("/{course-id}")
  public APIResponse<String> deleteCourse(@RequestParam Long courseId) {
    courseService.deleteCourse(courseId);
    return new APIResponse<>("Course deleted successfully", null, HttpStatus.OK, LocalDate.now());
  }

}
