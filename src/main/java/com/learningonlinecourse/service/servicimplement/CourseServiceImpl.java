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

  public CourseServiceImpl(CourseRepositoty courseRepositoty) {
    this.courseRepositoty = courseRepositoty;
  }

  @Override
  public Course saveCourse(CourseRequest courseRequest) {
    return courseRepositoty.savCourse(courseRequest);
  }

  @Override
  public List<Course> getAllCourses(Integer page, Integer size) {
    int offset = (page - 1) * size;
    return courseRepositoty.getAllCourses(offset, size);
  }

  @Override
  public Course getCourseById(Long courseId) {
    return courseRepositoty.getCourseById(courseId);
  }

  @Override
  public Course updateCourse(Long courseId, CourseRequest courseRequest) {
    return courseRepositoty.updateCourse(courseId, courseRequest);
  }

  @Override
  public void deleteCourse(Long courseId) {
    courseRepositoty.deleteCourse(courseId);
  }
}
