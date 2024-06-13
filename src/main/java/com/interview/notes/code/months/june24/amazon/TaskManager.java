package com.interview.notes.code.months.june24.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {

    private final Map<String, Task> tasks; // Stores tasks by their IDs
    private final Map<String, List<String>> subtasks; // Stores parent-child relationships

    public TaskManager() {
        tasks = new HashMap<>();
        subtasks = new HashMap<>();
    }

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        manager.addTask("a", "Task A", "Description A");
        manager.addTask("b", "Task B", "Description B");
        manager.addTask("c", "Task C", "Description C");
        manager.addTask("d", "Task D", "Description D");

        manager.addSubtask("a", "b");
        manager.addSubtask("a", "c");
        manager.addSubtask("b", "d");

        System.out.println("Before marking complete:");
        System.out.println(manager.tasks);

        manager.markComplete("a");

        System.out.println("After marking complete:");
        System.out.println(manager.tasks);
    }

    // Adds a new task to the manager
    public void addTask(String id, String title, String description) {
        tasks.put(id, new Task(id, title, description));
    }

    // Adds a subtask relationship between a parent task and a subtask
    public void addSubtask(String parentId, String subtaskId) {
        if (!tasks.containsKey(parentId) || !tasks.containsKey(subtaskId)) {
            System.err.println("Invalid task or subtask ID.");
            return;
        }
        subtasks.computeIfAbsent(parentId, k -> new ArrayList<>()).add(subtaskId);
        tasks.get(subtaskId).setParentId(parentId);
    }

    // Marks a task and all its subtasks as COMPLETED
    public void markComplete(String taskId) {
        if (!tasks.containsKey(taskId)) {
            System.err.println("Task ID not found: " + taskId);
            return;
        }
        markCompleteRecursive(taskId);
    }

    // Recursively marks a task and its subtasks as COMPLETED
    private void markCompleteRecursive(String taskId) {
        Task task = tasks.get(taskId);
        task.setStatus(TaskStatus.COMPLETED);
        if (subtasks.containsKey(taskId)) {
            for (String subtaskId : subtasks.get(taskId)) {
                markCompleteRecursive(subtaskId);
            }
        }
    }

    // Enum representing task statuses
    public enum TaskStatus {
        COMPLETED,
        NOT_COMPLETED
    }

    // Task class representing a task with ID, title, description, and status
    public static class Task {
        private final String id;
        private final String title;
        private final String description;
        private TaskStatus status;
        private String parentId;

        public Task(String id, String title, String description) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.status = TaskStatus.NOT_COMPLETED;
            this.parentId = null;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public TaskStatus getStatus() {
            return status;
        }

        public void setStatus(TaskStatus status) {
            this.status = status;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", status=" + status +
                    ", parentId='" + parentId + '\'' +
                    '}';
        }
    }
}
