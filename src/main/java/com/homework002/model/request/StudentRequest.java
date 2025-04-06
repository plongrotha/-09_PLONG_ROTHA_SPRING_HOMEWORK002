package com.homework002.model.request;

import java.util.List;

import com.homework002.model.entity.Course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
  private String name;
    private String email;
    private String phonenumber;
    private List<Course> course;
}
