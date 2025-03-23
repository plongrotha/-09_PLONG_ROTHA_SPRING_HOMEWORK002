package com.learningonlinecourse.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.learningonlinecourse.model.entity.Course;
import com.learningonlinecourse.model.request.CourseRequest;

@Mapper
public interface CourseRepositoty {

  @Results(id = "courseMapper", value = {
      @Result(property = "courseId", column = "course_id"),
      @Result(property = "courseName", column = "course_name"),
      @Result(property = "courseDescription", column = "course_description"),
      @Result(property = "instructorId", column = "instructor_id") })

  @Select("""
      SELECT * FROM course OFFSET #{offset} LIMIT #{size};
      """)
  List<Course> getAllCourses(int offset, Integer size);

  @ResultMap("courseMapper")
  @Select("SELECT * FROM course WHERE course_id = #{courseId}")
  Course getCourseById(Long courseId);

  @ResultMap("courseMapper")
  @Select("INSERT INTO course (course_name, course_description, instructor_id) VALUES (#{req.courseName}, #{req.courseDescription}, #{req.instructorId})")
  Course savCourse(@Param("req") CourseRequest courseRequest);

  @ResultMap("courseMapper")
  @Select("UPDATE course SET course_name = #{req.courseName}, course_description = #{req.courseDescription}, instructor_id = #{req.instructorId} WHERE course_id = #{courseId}")
  Course updateCourse(@Param("courseId") Long courseId, @Param("req") CourseRequest courseRequest);

  @Select("DELETE FROM course WHERE course_id = #{courseId}")
  void deleteCourse(Long courseId);
}
