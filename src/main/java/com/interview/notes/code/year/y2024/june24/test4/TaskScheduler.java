package com.interview.notes.code.year.y2024.june24.test4;

import java.util.PriorityQueue;

public class TaskScheduler {

    private final PriorityQueue<Task> taskQueue;

    public TaskScheduler() {
        // Initialize the priority queue with a comparator that orders tasks by their time to complete
        taskQueue = new PriorityQueue<>((t1, t2) -> Long.compare(t1.getTimeToComplete(), t2.getTimeToComplete()));
    }

    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();

        Task task1 = new Task(5000, "Task 1");
        Task task2 = new Task(3000, "Task 2");
        Task task3 = new Task(1000, "Task 3");

        scheduler.addTask(task1);
        scheduler.addTask(task2);
        scheduler.addTask(task3);

        System.out.println("Next Task: " + scheduler.getNextTask());
        System.out.println("Next Task: " + scheduler.getNextTask());
        System.out.println("Next Task: " + scheduler.getNextTask());

        scheduler.completeTask(task1);
        scheduler.completeTask(task2);
        scheduler.completeTask(task3);
    }

    // Adds a task to the scheduler
    public void addTask(Task task) {
        task.setStatus(TaskStatus.PENDING);
        taskQueue.offer(task);
    }

    // Retrieves the next task to work on
    public Task getNextTask() {
        Task nextTask = taskQueue.poll(); // Retrieves and removes the head of the queue
        if (nextTask != null) {
            nextTask.setStatus(TaskStatus.IN_PROGRESS);
        }
        return nextTask;
    }

    // Marks a task as completed
    public void completeTask(Task task) {
        task.setStatus(TaskStatus.COMPLETED);
        // Additional logic to mark the task as complete can be added here
        System.out.println("Task completed: " + task);
    }

    // Enum representing task statuses
    public enum TaskStatus {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }

    // Task class representing a task with an ID, time to complete, and description
    public static class Task {
        private final long timeToComplete;
        private final String description;
        private TaskStatus status;

        public Task(long timeToComplete, String description) {
            this.timeToComplete = timeToComplete;
            this.description = description;
            this.status = TaskStatus.PENDING;
        }

        public long getTimeToComplete() {
            return timeToComplete;
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

        @Override
        public String toString() {
            return "Task{" +
                    "timeToComplete=" + timeToComplete +
                    ", description='" + description + '\'' +
                    ", status=" + status +
                    '}';
        }
    }
}
