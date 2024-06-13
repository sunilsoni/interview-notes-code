package com.interview.notes.code.months.june24.test4;

import java.util.*;

public class TaskManager {

    private final Map<String, Task> tasks; // Stores tasks by their IDs
    private final Map<String, List<String>> subtasks; // Stores parent-child relationships

    public TaskManager() {
        tasks = new HashMap<>();
        subtasks = new HashMap<>();
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

    // Adds a dependency relationship between a task and its dependency
    public void addDependency(String taskId, String dependencyId) {
        if (!tasks.containsKey(taskId) || !tasks.containsKey(dependencyId)) {
            System.err.println("Invalid task or dependency ID.");
            return;
        }
        tasks.get(taskId).addDependency(dependencyId);
    }

    // Marks a task and all its subtasks as COMPLETED
    public void markComplete(String taskId) throws Exception {
        if (!tasks.containsKey(taskId)) {
            throw new Exception("Task ID not found: " + taskId);
        }
        if (!areDependenciesComplete(taskId)) {
            throw new Exception("Not all dependencies are complete for task: " + taskId);
        }
        markCompleteRecursive(taskId);
    }

    // Checks if all dependencies of a task are complete
    private boolean areDependenciesComplete(String taskId) {
        Task task = tasks.get(taskId);
        for (String dependencyId : task.getDependencies()) {
            if (tasks.get(dependencyId).getStatus() != TaskStatus.COMPLETED) {
                return false;
            }
        }
        return true;
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

    // Task class representing a task with ID, title, description, status, and dependencies
    public static class Task {
        private final String id;
        private final String title;
        private final String description;
        private TaskStatus status;
        private String parentId;
        private final Set<String> dependencies;

        public Task(String id, String title, String description) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.status = TaskStatus.NOT_COMPLETED;
            this.parentId = null;
            this.dependencies = new HashSet<>();
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

        public Set<String> getDependencies() {
            return dependencies;
        }

        public void addDependency(String dependencyId) {
            dependencies.add(dependencyId);
        }

        @Override
        public String toString() {
            return "Task{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", status=" + status +
                    ", parentId='" + parentId + '\'' +
                    ", dependencies=" + dependencies +
                    '}';
        }
    }

    // Enum representing task statuses
    public enum TaskStatus {
        COMPLETED,
        NOT_COMPLETED
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

        manager.addDependency("b", "c");
        manager.addDependency("d", "c");

        System.out.println("Before marking complete:");
        System.out.println(manager.tasks);

        try {
            manager.markComplete("a");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.println("After attempting to mark complete:");
        System.out.println(manager.tasks);

        // Marking dependencies as complete
        try {
            manager.markComplete("c");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.println("After marking dependencies complete:");
        System.out.println(manager.tasks);

        // Now attempting to mark "a" complete again
        try {
            manager.markComplete("a");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.println("After marking 'a' complete:");
        System.out.println(manager.tasks);
    }
}
