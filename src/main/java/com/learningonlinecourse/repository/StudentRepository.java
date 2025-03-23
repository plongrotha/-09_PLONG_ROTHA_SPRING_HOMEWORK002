package com.learningonlinecourse.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.learningonlinecourse.model.entity.Student;
import com.learningonlinecourse.model.request.StudentRequest;

@Mapper
public interface StudentRepository {

  @Results(id = "studentMapper", value = {
      @Result(property = "id", column = "student_id"),
      @Result(property = "name", column = "student_name"),
      @Result(property = "email", column = "student_email"),
      @Result(property = "phone", column = "student_phone"),
  })

  @Select("""
      SELECT * FROM student OFFSET #{offset} LIMIT #{size};
      """)
  List<Student> getAllStudents(Integer offset, Integer size);
  // SELECT * FROM student OFFSET #{offset} LIMIT #{size};

  @ResultMap("studentMapper")
  @Select("""
      INSERT INTO student (student_id, student_name, student_email, student_phone) VALUES (default, #{req.name}, #{req.email}, #{req.phone}) RETURNING *;
          """)
  Student createStudent(@Param("req") StudentRequest studentreRequest);

  @ResultMap("studentMapper")
  @Select("""
      SELECT * FROM student WHERE student_id = #{id};
      """)
  Student getStudentById(Long id);

  @ResultMap("studentMapper")
  @Select("""
      DELETE FROM student WHERE student_id = #{id} RETURNING *;
      """)
  Student deleteStudent(Long id);

  @ResultMap("studentMapper")
  @Select("""
      UPDATE student SET student_name = #{req.name}, student_email = #{req.email}, student_phone = #{req.phone} WHERE student_id = #{id} RETURNING *;
      """)
  Student updateStudent(Long id, @Param("req") StudentRequest studentRequest);
}
