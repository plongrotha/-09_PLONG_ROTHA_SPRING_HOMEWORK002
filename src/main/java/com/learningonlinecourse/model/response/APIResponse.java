package com.learningonlinecourse.model.response;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> {
  private String message;
  private T payload;
  private HttpStatus status;
  private LocalDate timestamp;

}
