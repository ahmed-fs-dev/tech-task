import { Task } from "../types/task";
import axiosApiClient from "../utils/axios-api-client";

export function getTasks(): Promise<{tasks: Task[]}> {
    return axiosApiClient.get<{tasks: Task[]}>("/api/tasks").then(response => response.data);
}

export function createTask(payload: Omit<Task, "id">): Promise<Task> {
    return axiosApiClient.post<Task>("/api/tasks", payload).then(response => response.data);
}

export function updateTask(id: number, payload: Partial<Omit<Task, "id">>): Promise<Task> {
    return axiosApiClient.put<Task>(`/api/tasks/${id}`, payload).then(response => response.data);
}

export function deleteTask(id: number): Promise<void> {
    return axiosApiClient.delete<void>(`/api/tasks/${id}`).then(response => response.data);
}

export function getTaskById(id: number): Promise<Task> {
    return axiosApiClient.get<Task>(`/api/tasks/${id}`).then(response => response.data);
}

const TaskService = {
    getTasks,
    createTask,
    updateTask,
    deleteTask,
    getTaskById,
}

export default TaskService;