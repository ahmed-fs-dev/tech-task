package org.example.techtaskserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Container for a list of tasks")
public class TaskListGet {
    @Schema(description = "Tasks returned by the list endpoint")
    private List<TaskGet> tasks;
}
