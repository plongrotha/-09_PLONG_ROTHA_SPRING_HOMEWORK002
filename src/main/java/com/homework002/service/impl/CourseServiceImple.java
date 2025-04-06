package com.homework002.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.homework002.exception.NotFoundException;
import com.homework002.model.entity.Course;
import com.homework002.model.request.CourseRequest;
import com.homework002.repository.CourseRepostory;
import com.homework002.service.CousreService;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class CourseServiceImple implements CousreService {
  private final CourseRepostory courseRepostory;
    @Override
    public List<Course> getAllCourses(Integer offset, Integer page) {

      offset = ( offset - 1) * page;
      List<Course> courses = courseRepostory.getAllCourses( offset, page);
        if (courses.isEmpty()) {
          throw new NotFoundException( "No courses found");
        }
        return courses;
    }

    @Override
    public Course getCourseById(Long id) {
      Course course = courseRepostory.getCourseById(id);
        if (course == null) {
          throw new NotFoundException("Course with id " + id + " not found");
        }
        return course;
    }

    @Override
    public Course createCourse(CourseRequest courseRequest) {
        return courseRepostory.createCourse(courseRequest);
    }

    @Override
    public Course updateCourseById(Long id, CourseRequest courseRequest) {
      getCourseById(id); 
        return courseRepostory.updateCourse(id, courseRequest);
    }

    @Override
    public void deleteCourse(Long id) {
      getCourseById(id);
        courseRepostory.deleteCourse(id);
    }

}
