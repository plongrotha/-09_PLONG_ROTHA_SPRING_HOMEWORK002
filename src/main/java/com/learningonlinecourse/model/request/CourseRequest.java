package com.learningonlinecourse.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {

  private String courseName;
  private String courseDescription;
  private Long instructorId;
}
