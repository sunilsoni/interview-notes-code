package com.interview.notes.code.months.june24.amazon;

import java.util.PriorityQueue;

/**
 * Implement a task scheduler class that supports 3 methods such as addask(), which takes in a task that takes a specific amount of time to complete and getNextTask() which is called by workers to get the next task to work on,
 *
 * and finally completeTask() that is used to complete a scheduled task.
 * stores it for scheduling,
 */
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

        // Test edge cases
        scheduler.addTask(null);  // Should print an error message

        Task nextTask = scheduler.getNextTask();
        scheduler.completeTask(nextTask);

        nextTask = scheduler.getNextTask();
        scheduler.completeTask(nextTask);

        nextTask = scheduler.getNextTask();
        scheduler.completeTask(nextTask);

        // Attempt to get a task from an empty queue
        nextTask = scheduler.getNextTask();  // Should print an error message

        // Attempt to complete a null task
        scheduler.completeTask(null);  // Should print an error message
    }

    // Adds a task to the scheduler
    public void addTask(Task task) {
        if (task == null) {
            System.err.println("Cannot add null task.");
            return;
        }
        task.setStatus(TaskStatus.PENDING);
        taskQueue.offer(task);
        System.out.println("Added task: " + task);
    }

    // Retrieves the next task to work on
    public Task getNextTask() {
        if (taskQueue.isEmpty()) {
            System.err.println("No tasks available.");
            return null;
        }
        Task nextTask = taskQueue.poll(); // Retrieves and removes the head of the queue
        if (nextTask != null) {
            nextTask.setStatus(TaskStatus.IN_PROGRESS);
            System.out.println("Started task: " + nextTask);
        }
        return nextTask;
    }

    // Marks a task as completed
    public void completeTask(Task task) {
        if (task == null) {
            System.err.println("Cannot complete null task.");
            return;
        }
        task.setStatus(TaskStatus.COMPLETED);
        // Additional logic to mark the task as complete can be added here
        System.out.println("Completed task: " + task);
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
