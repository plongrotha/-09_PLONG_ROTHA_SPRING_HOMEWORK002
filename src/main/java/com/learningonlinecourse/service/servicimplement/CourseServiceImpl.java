package com.learningonlinecourse.service.servicimplement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learningonlinecourse.model.entity.Course;
import com.learningonlinecourse.model.request.CourseRequest;
import com.learningonlinecourse.repository.CourseRepositoty;
import com.learningonlinecourse.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

  private CourseRepositoty courseRepositoty;

  @Override
  public List<Course> getAllCourses(Integer page, Integer size) {
    return courseRepositoty.getAllCourses();
  }

  @Override
  public Course createCourse(CourseRequest courseRequest) {
    return courseRepositoty.savCourse(courseRequest);
  }

  @Override
  public Course getCourseById(Long courseId) {

    return null;
  }

  @Override
  public Course updateCourse(Long courseId, CourseRequest courseRequest) {

    return null;
  }

  @Override
  public void deleteCourse(Long courseId) {

  }
}
