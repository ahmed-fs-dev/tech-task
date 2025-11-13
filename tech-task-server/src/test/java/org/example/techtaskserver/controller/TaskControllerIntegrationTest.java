package org.example.techtaskserver.controller;

import org.example.techtaskserver.model.Task;
import org.example.techtaskserver.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        taskRepository.deleteAll();
    }

    @Test
    void testCreateAndGetTask() throws Exception {
        Task task = Task.builder().title("Integration").description("Test").completed(false).build();
        String json = objectMapper.writeValueAsString(task);
        ResultActions result = mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));
        result.andExpect(status().isCreated())
              .andExpect(jsonPath("$.title").value("Integration"));
    }

    @Test
    void testGetAllTasks() throws Exception {
        Task task = Task.builder().title("Integration").description("Test").completed(false).build();
        taskRepository.save(task);
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tasks[0].title").value("Integration"));
    }

    @Test
    void testUpdateTask() throws Exception {
        Task task = taskRepository.save(Task.builder().title("Old").description("Test").completed(false).build());
        task.setTitle("Updated");
        String json = objectMapper.writeValueAsString(task);
        mockMvc.perform(put("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated"));
    }

    @Test
    void testDeleteTask() throws Exception {
        Task task = taskRepository.save(Task.builder().title("Delete").description("Test").completed(false).build());
        mockMvc.perform(delete("/tasks/" + task.getId()))
                .andExpect(status().isNoContent());
    }
}

