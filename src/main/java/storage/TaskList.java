package storage;

import java.util.ArrayList;

import exception.DudeException;
import task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a task list with an empty list of tasks by default.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list with the given list of tasks.
     *
     * @param tasks The list of tasks to be added to the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates a task list with the given tasks.
     *
     * @param tasks The tasks to be added to the task list.
     */
    public TaskList(Task ... tasks) {
        this.tasks = new ArrayList<>();
        for (Task task : tasks) {
            this.tasks.add(task);
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was deleted.
     */
    public Task deleteTask(int index) throws DudeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DudeException("There is no such task!");
        }
        return this.tasks.remove(index);
    }

    /**
     * Gets a task from the task list.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the given index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markDone(int index) throws DudeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DudeException("There is no such task!");
        }
        if (tasks.get(index).isDone()) {
            throw new DudeException("This task is already marked as done!");
        }
        Task task = this.tasks.get(index);
        task.markAsDone();
    }

    /**
     * Marks a task as undone.
     *
     * @param index The index of the task to be marked as undone.
     */
    public void markUndone(int index) throws DudeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DudeException("There is no such task!");
        }
        if (!tasks.get(index).isDone()) {
            throw new DudeException("This task is already marked as undone!");
        }
        Task task = this.tasks.get(index);
        task.markAsUndone();
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets the list of tasks that match a keyword.
     *
     * @param keyword The keyword to match with.
     * @return The list of tasks that match the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
