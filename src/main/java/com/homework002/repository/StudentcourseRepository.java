package com.homework002.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.homework002.model.entity.Course;

@Mapper
public interface StudentcourseRepository {

    @Results(id = "studentCourseMapper", value = {
      @Result(property = "courseId", column = "course_id"), 
      @Result(property = "courseName", column = "course_name"), 
      @Result(property = "courseDescription", column = "description"), 
      @Result(property = "instructor", column = "instructor_id", 
      one = @One(select = "com.homework002.repository.InstructorRepository.getInstructorById"))
    })
    @Select("""
                   SELECT * FROM student_course st inner join courses c on st.course_id = c.course_id where student_id 
                   = #{studentId};
            """)
    List<Course> getCoursesByStudentId(Long studentId);

    @ResultMap("studentCourseMapper") 
    @Select("""
                    INSERT INTO student_course VALUES (DEFAULT, #{studentId} ,#{courseId});
            """)
    void addStudentCourse( Long studentId, Long courseId);

    @ResultMap("studentCourseMapper")
    @Delete("""
            DELETE FROM student_course where student_id = #{studentId} AND course_id = #{courseId};
            """)
    void deleteStudentCourse(Long studentId, Long courseId);

}
