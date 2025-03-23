package com.learningonlinecourse.service;

import java.util.List;

import com.learningonlinecourse.model.entity.Instructor;
import com.learningonlinecourse.model.request.InstructorRequest;

public interface InstructorService {

  List<Instructor> getAllInstructors(Integer page, Integer size);

  Instructor createInstructor(InstructorRequest instructorRequest);

  Instructor getInstructorById(Long id);

  Instructor deleteInstructorById(Long id);

  Instructor updateInstructorById(Long id, InstructorRequest instructorRequest);
}
