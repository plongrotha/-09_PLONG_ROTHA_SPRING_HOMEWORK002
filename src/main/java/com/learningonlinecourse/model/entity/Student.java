package com.learningonlinecourse.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
  private Long id;
  private String name;
  private String email;
  private String phone;
}
