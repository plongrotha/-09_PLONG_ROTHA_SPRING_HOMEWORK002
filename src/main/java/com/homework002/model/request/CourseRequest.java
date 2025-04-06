package com.homework002.model.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
  @Size(min = 5, max = 50)
  private String courseName;
  @Size(min = 0, max = 255, message = " must be between 0-255")
  private String description;
  private Long instructorId;
}
