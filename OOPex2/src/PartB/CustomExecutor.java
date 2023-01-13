package PartB;

import java.util.concurrent.*;

public class CustomExecutor extends ThreadPoolExecutor {

    private int maxPrio;

    /**
     * a constructor for the CustomExecutor class
     */
    public CustomExecutor() {
        super(Runtime.getRuntime().availableProcessors() / 2,
                Runtime.getRuntime().availableProcessors() - 1,
                300,
                TimeUnit.MILLISECONDS,
                new PriorityBlockingQueue<>());

        this.maxPrio = 0;
    }

    /**
     * This method is called just right before the execution of the task
     * @param t the thread that will run task {@code r}
     * @param r the task that will be executed
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        AdptToFut<?> adptToFut = (AdptToFut<?>) r;
        if (adptToFut.getPrio() > this.maxPrio) {
            this.maxPrio = adptToFut.getPrio();
        }
    }

    /**
     * Sumbits a task to the threadpool
     * @param task the task
     * @return a Future object
     */
    public <T> Future<T> submit(Task<T> task) {
        if (task.getPrio() < this.maxPrio) {
            this.maxPrio = task.getPrio();
        }

        AdptToFut<T> adpt = new AdptToFut<>(task, task.getPrio());
        execute(adpt);
        return adpt;
    }

    /**
     * Sumbits a task to the threadpool
     * @param callable the task to submit
     * @param taskType task's priority
     * @return a Future object
     */
    public <T> Future<T> submit(Callable<T> callable, TaskType taskType) {
        return this.submit(Task.createTask(callable, taskType));
    }

    /**
     * Sumbits a task to the threadpool
     * @param callable the task to submit
     * @return a Future object
     */
    public <T> Future<T> submit(Callable<T> callable) {
        return this.submit(Task.createTask(callable));
    }

    /**
     * Return the max priority.
     * @return max priority of the current task in the pool.
     */
    public int getCurrentMax() {
        return this.maxPrio;
    }

    /**
     * Shutdown the pool & wait for all tasks to be completed.
     */
    public void gracefullyTerminate() {
        super.shutdown();

        while (!super.isShutdown());
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
