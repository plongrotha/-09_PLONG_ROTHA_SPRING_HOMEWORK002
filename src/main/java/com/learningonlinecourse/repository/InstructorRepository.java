package com.learningonlinecourse.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.learningonlinecourse.model.entity.Instructor;
import com.learningonlinecourse.model.request.InstructorRequest;

@Mapper
public interface InstructorRepository {

  @Results(id = "instructorMapper", value = {
      @Result(property = "id", column = "instructor_id"),
      @Result(property = "name", column = "instructor_name"),
      @Result(property = "email", column = "email")
  })

  @Select("""
      SELECT * FROM instructor OFFSET #{offset} LIMIT #{size};
      """)
  List<Instructor> getAllInstructors(int offset, Integer size);

  @ResultMap("instructorMapper")
  @Select("""
      INSERT INTO instructor (instructor_id,instructor_name, email) VALUES (default, #{req.name},#{req.email}) RETURNING *;
      """)
  Instructor createInstructor(@Param("req") InstructorRequest instructorRequest);

  //
  @ResultMap("instructorMapper")
  @Select("""
      SELECT * FROM instructor WHERE instructor_id = #{id};
      """)
  Instructor getInstructorById(Long id);

  @ResultMap("instructorMapper")
  @Select("""
      DELETE FROM instructor WHERE instructor_id = #{id} RETURNING *;
      """)
  Instructor deleteInstructorById(Long id);

  @ResultMap("instructorMapper")
  @Select("""
      UPDATE instructor SET instructor_name = #{req.name}, email = #{req.email} WHERE instructor_id = #{id} RETURNING *;
      """)
  Instructor updateInstructorById(Long id, @Param("req") InstructorRequest instructorRequest);

}
