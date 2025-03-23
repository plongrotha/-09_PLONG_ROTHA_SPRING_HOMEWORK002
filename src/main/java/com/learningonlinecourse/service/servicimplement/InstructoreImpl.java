package com.learningonlinecourse.service.servicimplement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learningonlinecourse.model.entity.Instructor;
import com.learningonlinecourse.model.request.InstructorRequest;
import com.learningonlinecourse.repository.InstructorRepository;
import com.learningonlinecourse.service.InstructorService;

@Service
public class InstructoreImpl implements InstructorService {

  private InstructorRepository instructorRepository;

  public InstructoreImpl(InstructorRepository instructorRepository) {
    this.instructorRepository = instructorRepository;
  }

  @Override
  public List<Instructor> getAllInstructors(Integer page, Integer size) {
    int offet = (page - 1) * size;
    return instructorRepository.getAllInstructors(offet, size);
  }

  @Override
  public Instructor createInstructor(InstructorRequest instructorRequest) {
    return instructorRepository.createInstructor(instructorRequest);
  }

  @Override
  public Instructor getInstructorById(Long id) {
    return instructorRepository.getInstructorById(id);
  }

  @Override
  public Instructor deleteInstructorById(Long id) {
    return instructorRepository.deleteInstructorById(id);
  }

  @Override
  public Instructor updateInstructorById(Long id, InstructorRequest instructorRequest) {
    return instructorRepository.updateInstructorById(id, instructorRequest);
  }
}
