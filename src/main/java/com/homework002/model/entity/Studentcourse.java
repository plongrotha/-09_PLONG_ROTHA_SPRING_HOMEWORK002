package com.homework002.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Studentcourse {
  private Long id;
  private Long studentId;
  private Long courseId;
}
