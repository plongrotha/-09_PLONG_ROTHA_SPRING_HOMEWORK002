package com.learningonlinecourse.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learningonlinecourse.model.entity.Instructor;
import com.learningonlinecourse.model.request.InstructorRequest;
import com.learningonlinecourse.model.response.APIResponse;
import com.learningonlinecourse.service.InstructorService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/instructors")
public class InstructorController {

  private InstructorService instructorService;

  public InstructorController(InstructorService instructorService) {
    this.instructorService = instructorService;
  }

  // get all instructors
  @Operation(summary = "Get all instructors")
  @GetMapping
  public APIResponse<List<Instructor>> getAllInstructor(@RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "10") Integer size) {
    List<Instructor> instructors = instructorService.getAllInstructors(page, size);

    if (instructors.isEmpty()) {
      return new APIResponse<>("No instructors found", null, HttpStatus.NOT_FOUND, LocalDate.now());
    }
    return new APIResponse<>("Instructors retrieved successfully", instructors, HttpStatus.OK, LocalDate.now());
  }

  // save a instructor to the database
  @Operation(summary = "Create a new instructor")
  @PostMapping
  // public ResponseEntity<?> saveInstructor(@RequestBody InstructorRequest
  // instructorRequest) {
  // Instructor instructor =
  // instructorService.createInstructor(instructorRequest);
  // if (instructor == null) {
  // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save
  // instructor");
  // }
  // return ResponseEntity.status(HttpStatus.CREATED).body("Instructor saved
  // successfully");
  // }
  public APIResponse<Instructor> saveInstructor(@RequestBody InstructorRequest instructorRequest) {
    Instructor instructor = instructorService.createInstructor(instructorRequest);
    if (instructor == null) {
      return new APIResponse<>("Failed to save instructor", null,
          HttpStatus.BAD_REQUEST, LocalDate.now());
    }
    return new APIResponse<>("Instructor saved successfully", instructor,
        HttpStatus.CREATED, LocalDate.now());
  }

  // get instructor by id
  @Operation(summary = "Get instructor by id")
  @GetMapping("/{instructor-id}")
  public APIResponse<Optional<Instructor>> getInstructorById(@PathVariable("instructor-id") Long id) {
    Optional<Instructor> instructor = Optional.ofNullable(instructorService.getInstructorById(id));
    if (instructor.isEmpty()) {
      return new APIResponse<>("Instrctor id " + id + " is not found", null, HttpStatus.NOT_FOUND, LocalDate.now());
    }
    return new APIResponse<>(" Instructor retrieved successfully ", instructor, HttpStatus.OK, LocalDate.now());
  }

  // delete instructor by id
  @Operation(summary = "Delete instructor by id")
  @DeleteMapping("/{instructor-id}")
  public APIResponse<Instructor> deleteInstructorById(@PathVariable("instructor-id") Long id) {
    Instructor instructor = instructorService.deleteInstructorById(id);
    if (instructor == null) {
      return new APIResponse<>("Failed to delete instructor", null, HttpStatus.BAD_REQUEST, LocalDate.now());
    }
    return new APIResponse<>("Instructor deleted successfully", instructor, HttpStatus.OK, LocalDate.now());

  }

  // update instructor by id
  @Operation(summary = "Update instructor by id")
  @PutMapping("/{instructor-id}")
  public APIResponse<Instructor> updateInstructorById(@PathVariable("instructor-id") Long id,
      @RequestBody InstructorRequest instructorRequest) {
    Instructor updatedInstructor = instructorService.updateInstructorById(id, instructorRequest);
    if (updatedInstructor == null) {
      return new APIResponse<>("Failed to update instructor", null, HttpStatus.BAD_REQUEST, LocalDate.now());
    }
    return new APIResponse<>("Instructor updated successfully", updatedInstructor, HttpStatus.OK, LocalDate.now());
  }

}
