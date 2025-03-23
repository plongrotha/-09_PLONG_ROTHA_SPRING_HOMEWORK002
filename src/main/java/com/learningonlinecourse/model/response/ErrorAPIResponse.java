package com.learningonlinecourse.model.response;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorAPIResponse {

  private LocalDate timestamp;
  private HttpStatus status;
  private String error;
  private String path;
}
