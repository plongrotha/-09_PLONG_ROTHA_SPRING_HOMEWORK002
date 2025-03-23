package com.learningonlinecourse.service;

import java.util.List;

import com.learningonlinecourse.model.entity.Course;
import com.learningonlinecourse.model.request.CourseRequest;

public interface CourseService {

  List<Course> getAllCourses(Integer page, Integer size);

  Course createCourse(CourseRequest courseRequest);

  Course getCourseById(Long courseId);

  Course updateCourse(Long courseId, CourseRequest courseRequest);

  void deleteCourse(Long courseId);
}
