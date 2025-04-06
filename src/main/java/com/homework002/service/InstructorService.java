package com.homework002.service;

import java.util.List;

import com.homework002.model.entity.Instructor;
import com.homework002.model.request.InstructorRequest;

public interface InstructorService {


  List<Instructor> getAllInstructors(Integer offset, Integer page);
  Instructor getInstructorById(Long id);
  Instructor createInstructor(InstructorRequest instructorRequest);
  Instructor updateInstructorById(Long id, InstructorRequest instructorRequest);
  void deleteInstructorbYiD(Long id);

}
