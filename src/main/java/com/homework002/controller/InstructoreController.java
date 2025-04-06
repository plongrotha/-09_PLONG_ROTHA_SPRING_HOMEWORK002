package com.homework002.controller;

import java.time.LocalDateTime;
import java.util.List;

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

import com.homework002.model.entity.Instructor;
import com.homework002.model.request.InstructorRequest;
import com.homework002.model.response.ApiResponse;
import com.homework002.service.InstructorService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/instructors")
@RequiredArgsConstructor
public class InstructoreController {

  private final InstructorService instructorService;

  @Operation(summary = "Get all instructors", description = "Get all instructors with pagination")
  @GetMapping
  public ResponseEntity<?> getAllInstructors(@RequestParam(defaultValue = "1") Integer offset, @RequestParam(defaultValue = "10") Integer page) {
    List<Instructor> instructors = instructorService.getAllInstructors(offset, page);
    return ResponseEntity.ok(
      ApiResponse.builder()
        .status(HttpStatus.OK)
        .message(" all Instructors get successfully")
        .payload(instructors)
        .timestamp(LocalDateTime.now())
        .build()
    );
  }

  @Operation(summary = "Get instructor by id", description = "Get instructor by id")
  @GetMapping("{instructor-id}")
  public ResponseEntity<?> getInstructorById(@PathVariable("instructor-id") Long instructorId) {
    Instructor instructor = instructorService.getInstructorById(instructorId);
    return ResponseEntity.ok(
      ApiResponse.builder()
        .status(HttpStatus.OK)
        .message("Instructor retrieved successfully")
        .payload(instructor)
        .timestamp(LocalDateTime.now())
        .build()
    );
  }

  @Operation(summary = " crete instructor", description = "create instructor")
  @PostMapping
  public ResponseEntity<?> createInstructor(@RequestBody InstructorRequest instructorRequest) {
    Instructor instructor = instructorService.createInstructor(instructorRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(
      ApiResponse.builder()
        .status(HttpStatus.CREATED)
        .message("Instructor created successfully")
        .payload(instructor)
        .timestamp(LocalDateTime.now())
        .build()
    );
  }
  @Operation(summary = "update instructor", description = "update instructor")
  @PutMapping("{instructor-id}")
  public ResponseEntity<?> updateInstructor(@PathVariable("instructor-id") Long instructorId, @RequestBody InstructorRequest instructorRequest) {
    Instructor instructor = instructorService.updateInstructorById(instructorId, instructorRequest);
    return ResponseEntity.ok(
      ApiResponse.builder()
        .status(HttpStatus.OK)
        .message("Instructor updated successfully")
        .payload(instructor)
        .timestamp(LocalDateTime.now())
        .build()
    );
  }

  @Operation(summary = "delete instructor", description = "delete instructor")
  @DeleteMapping("{instructor-id}")
  public ResponseEntity<?> deleteInstructor(@PathVariable("instructor-id") Long instructorId) {
    instructorService.deleteInstructorbYiD(instructorId);
    return ResponseEntity.ok(
      ApiResponse.builder()
        .status(HttpStatus.OK)
        .message("Instructor deleted successfully")
        .timestamp(LocalDateTime.now())
        .build()
    );
  }
}
