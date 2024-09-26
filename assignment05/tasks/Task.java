package pjwstk.edu.pl.s27619.tasks;

import pjwstk.edu.pl.s27619.clients.ClientRequestor;

import java.util.Comparator;

public abstract class Task implements Comparable<Task> {
    private ClientRequestor clientRequestor;
    private TaskPriority taskPriority;
    private static final Comparator<Task> TASK_COMPARATOR = Comparator
            .comparing(Task::getTaskPriority); // Comparator to compare each task with their priority


    protected Task(ClientRequestor clientRequestor, TaskPriority taskPriority) {
        this.clientRequestor = clientRequestor;
        this.taskPriority = taskPriority;
    }

    /**
     * Method to compare priority of current and given task
     *
     * @param anotherTask the object to be compared.
     * @return 0 if it is equals, 1 if first param greater, and -1 if the sec param is greater
     */
    @Override
    public int compareTo(Task anotherTask) {
        return TASK_COMPARATOR.compare(this, anotherTask);
    }

    public ClientRequestor getRequestor() {
        return clientRequestor;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    @Override
    public String toString() {
        return " priority: " + taskPriority + " ";
    }

}
