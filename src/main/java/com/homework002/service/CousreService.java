package com.homework002.service;

import java.util.List;

import com.homework002.model.entity.Course;
import com.homework002.model.request.CourseRequest;

public interface CousreService {
  List<Course> getAllCourses(Integer offset, Integer page);
  Course getCourseById(Long id); 
  Course createCourse(CourseRequest courseRequest);
  Course updateCourseById(Long id, CourseRequest courseRequest);  
  void deleteCourse(Long id);
}
