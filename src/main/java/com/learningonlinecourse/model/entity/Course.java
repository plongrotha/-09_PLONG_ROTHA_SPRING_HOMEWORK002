package com.learningonlinecourse.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
  private Long courseId;
  private String courseNname;
  private String courseDescription;
  private Instructor instructor;
}
