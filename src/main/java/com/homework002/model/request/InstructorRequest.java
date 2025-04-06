package com.homework002.model.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorRequest {
    @Size(max = 50)
    private String instructorName;
    @Size(max = 50)
    private String email;
}
