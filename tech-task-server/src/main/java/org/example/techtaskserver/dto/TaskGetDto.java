package org.example.techtaskserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Task read model returned by API")
public class TaskGetDto {
    @Schema(description = "Task identifier", example = "1")
    private Long id;

    @Schema(description = "Title of the task", example = "Buy groceries")
    private String title;

    @Schema(description = "Detailed description", example = "Buy milk and eggs")
    private String description;

    @Schema(description = "Whether the task is completed", example = "false")
    private boolean completed;
}
