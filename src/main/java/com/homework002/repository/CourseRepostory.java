package com.homework002.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.homework002.model.entity.Course;
import com.homework002.model.request.CourseRequest;

@Mapper
public interface CourseRepostory {
  // private Long courseId;
  // private String courseName;
  // private String courseDescription;
  // private Instructor instructor;
  @Results(id = "courseMapper", value = {
      @org.apache.ibatis.annotations.Result(property = "courseId", column = "course_id"),
      @org.apache.ibatis.annotations.Result(property = "courseName", column = "course_name"),
      @org.apache.ibatis.annotations.Result(property = "courseDescription", column = "description"),
      @org.apache.ibatis.annotations.Result(property = "instructor", column = "instructor_id",
        one = @One(select = "com.homework002.repository.InstructorRepository.getInstructorById")),
  })
  @Select("""
      SELECT * FROM courses OFFSET #{offset} LIMIT #{page};
      """)
  List<Course> getAllCourses(Integer offset, Integer page);

  @ResultMap("courseMapper")
  @Select("SELECT * FROM courses WHERE course_id = #{courseId}")
  Course getCourseById(Long courseId);

  @ResultMap("courseMapper")
  @Select("""
         INSERT INTO courses VALUES (default,#{req.courseName},#{req.description},#{req.instructorId}) RETURNING *;
   """)
  Course createCourse(@Param("req") CourseRequest coursRequest);


  @ResultMap("courseMapper")
  @Select("""
         UPDATE courses SET course_name = #{req.courseName}, description = #{req.description}, instructor_id = #{req.instructorId} WHERE course_id = #{courseId} RETURNING *;
   """)
  Course updateCourse( Long courseId, @Param("req") CourseRequest courseRequest);

  @ResultMap("courseMapper")
  @Select("""
         DELETE FROM courses WHERE course_id = #{courseId} RETURNING *;
   """)
  Course deleteCourse(Long courseId);
}
