package com.homework002.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.homework002.model.entity.Instructor;
import com.homework002.model.request.InstructorRequest;

@Mapper
public interface InstructorRepository {
 @Results(id = "instructorMapper", value = {
            @Result(property = "instructorId", column = "instructor_id"),
            @Result(property = "instructorName", column = "instructor_name"),
            @Result(property = "email", column = "email"),
    })
    @Select("""
      SELECT * FROM instructors OFFSET #{offset} LIMIT #{size};
""")
List<Instructor> getAllInstructors(Integer offset, Integer size);


    @ResultMap("instructorMapper")
    @Select("""
      SELECT * FROM instructors WHERE instructor_id = #{instructorId};
""")
Instructor getInstructorById(Long instructorId);


    @ResultMap("instructorMapper")
    @Select("""
                    INSERT INTO instructors VALUES(default,#{req.instructorName},#{req.email});
            """)
    Instructor addInstructor(@Param("req") InstructorRequest instructorRequest);

    @ResultMap("instructorMapper")
    @Select("""
  UPDATE instructors SET instructor_name = #{req.instructorName}, email=#{req.email} WHERE instructor_id = #{instructorId} returning *;
            """)
    Instructor updateInstructorById(Long instructorId,@Param("req") InstructorRequest instructorRequest);

@Delete("""
    DELETE FROM instructors WHERE instructor_id=#{instructorId};
""")
    void removeInstructorByID(Long instructorId);
}
