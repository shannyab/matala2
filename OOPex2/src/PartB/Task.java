package PartB;

import java.util.concurrent.*;

public class Task<T> implements Callable<T> {

    private final Callable<T> callable;
    private int prio;

    /**
     * Task to be executed by the thread pool.
     * We are using factory design pattern.
     * @param callable - the callable to be executed
     * @param taskType - the type of the task, aka the priority of the task itself.
     */
    private Task(Callable<T> callable, TaskType taskType) {
        this.callable = callable;
        this.prio = taskType.getPriorityValue();
    }

    /**
     * Task to be executed by the thread pool.
     * We are using factory design pattern.
     * @param callable - the callable to be executed
     * @param taskType - the type of the task, aka the priority of the task itself.
     * @return a task object.
     */
    public static <T> Task<T> createTask(Callable<T> callable, TaskType taskType) {
        return new Task<>(callable, taskType);
    }

    /**
     * Task to be executed by the thread pool, with default priority.
     * We are using factory design pattern.
     * @param callable - the callable to be executed
     * @return a task object
     */
    public static <T> Task<T> createTask(Callable<T> callable) {
        return new Task<>(callable, TaskType.IO);
    }

    /**
     * get the priority of the task
     * @return task's pritority
     */
    public int getPrio() {
        return prio;
    }

    /**
     * Callable method.
     * @return task's result
     * @throws Exception in case of an error
     */
    @Override
    public T call() throws Exception {
        return this.callable.call();
    }

    /**
     * Hashcode the object.
     * @return a hashcode of the task
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * from object class, return if the object is equal to this object.
     * @param obj - the object to be compared
     * @return true if the object is equal to this task, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
