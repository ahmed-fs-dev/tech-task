package org.example.techtaskserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdate {
    @NotBlank(message = "Title cannot be empty")
    @Size(min = 3, max = 50, message = "Title must have 2 to 50 characters")
    private String title;
    private String description;
    private boolean completed;
}
