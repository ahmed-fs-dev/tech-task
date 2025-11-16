package org.example.techtaskserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Payload to create a new task")
public class TaskInsert {
    @NotBlank(message = "Title cannot be empty")
    @Size(min = 3, max = 50, message = "Title must have 2 to 50 characters")
    @Schema(description = "Title of the task", example = "Buy groceries", required = true)
    private String title;
    @Schema(description = "Detailed description", example = "Buy milk and eggs")
    private String description;

    @Schema(description = "Whether the task is completed", example = "false")
    private boolean completed;
}
