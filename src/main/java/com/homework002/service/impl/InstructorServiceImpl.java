package com.homework002.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.homework002.exception.NotFoundException;
import com.homework002.model.entity.Instructor;
import com.homework002.model.request.InstructorRequest;
import com.homework002.repository.InstructorRepository;
import com.homework002.service.InstructorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService{

  private final InstructorRepository instructorRepository;

  @Override
  public List<Instructor> getAllInstructors(Integer offset, Integer page) {

    List<Instructor> instructors = instructorRepository.getAllInstructors(offset, page);
    if (instructors.isEmpty()) {
      throw new NotFoundException("No instructors found");
    }
    return instructors;
  }

  @Override
  public Instructor getInstructorById(Long id) {
    Instructor instructor = instructorRepository.getInstructorById(id);
    if (instructor == null) {
      throw new NotFoundException("Instructor not found with id: " + id);
    }
    return instructor;
  }

  @Override
  public Instructor createInstructor(InstructorRequest instructorRequest) {
    return instructorRepository.addInstructor(instructorRequest);
    
  }

  @Override
  public Instructor updateInstructorById(Long id, InstructorRequest instructorRequest) {
    getInstructorById(id);
  return instructorRepository.updateInstructorById(id, instructorRequest);
  }

  @Override
  public void deleteInstructorbYiD(Long id) {
    getInstructorById(id);
    instructorRepository.removeInstructorByID(id);
  }




}
