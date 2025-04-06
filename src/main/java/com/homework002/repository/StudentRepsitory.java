package com.homework002.repository;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.homework002.model.entity.Student;
import com.homework002.model.request.StudentRequest;

@Mapper
public interface StudentRepsitory {

    // private Long id;
    // private String name;
    // private String email;
    // private String phonenumber;
    // private List<Course> course;

  @Results(id = "studentMapper", value = {
      @Result(property = "id", column = "student_id"),
      @Result(property = "name", column = "student_name"),
      @Result(property = "phonenumber", column = "phone_number"),
      @Result(property = "email", column = "email"),
      @Result(property = "course", column = "student_id", many = @Many(select = "com.homework002.repository.StudentcourseRepository.getCoursesByStudentId"))
  })

  @Select("""
      SELECT * FROM students OFFSET #{offset} LIMIT #{size};
  """)
  List<Student> getAllStudents(Integer offset, Integer size); 

  @ResultMap("studentMapper")
  @Select("""
      SELECT * FROM students WHERE student_id = #{studentId};
  """)
  Student getStudentById(Long studentId);

  @ResultMap("studentMapper")
  @Select("""
      INSERT INTO students VALUES (DEFAULT, #{req.name}, #{req.email}, #{req.phonenumber} , #{req.course}) RETURNING *;
  """)
  Student createStudent(StudentRequest studentRequest);

  @ResultMap("studentMapper")
  @Select("""
      UPDATE students SET student_name = #{req.name}, email = #{req.email}, phone_number = #{req.phonenumber} WHERE student_id = #{studentId} RETURNING *;
  """)
  Student updateStudentById(Long studentId, StudentRequest studentRequest);

  @Select("""
      DELETE FROM students WHERE student_id = #{studentId};
  """)
  void deleteStudent(Long studentId);


}
