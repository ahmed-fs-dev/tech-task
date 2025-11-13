package org.example.techtaskserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskGet {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
}
