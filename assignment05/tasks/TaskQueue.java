package pjwstk.edu.pl.s27619.tasks;

import pjwstk.edu.pl.s27619.clients.ClientRequestor;

import java.util.PriorityQueue;
import java.util.Queue;

public class TaskQueue {

    private Queue<Task> tasksQueue; // Contains queue of given tasks

    public TaskQueue() {
        tasksQueue = new PriorityQueue<>();
    }

    /**
     * Method adds task to the queue of tasks
     *
     * @param task given object to add in queue
     */
    public synchronized void addTask(Task task) {
        if (task != null) {
            tasksQueue.offer(task);
        }
    }

    /**
     * Method polls task response from the queue of tasks
     *
     * @param clientRequestor contains info about client requestor
     * @return polled task response or null
     */
    public synchronized TaskResponse pollTaskResponse(ClientRequestor clientRequestor) {
        Task task = tasksQueue.peek();
        // We should check head of our queue of tasks and compare given сlient requestor with requestor of task response
        // If all conditions done and also true, we remove from head of task queue our task response, else return null
        if (task instanceof TaskResponse && tasksQueue.peek().getRequestor() == clientRequestor) {
            return (TaskResponse) tasksQueue.poll();
        } else {
            return null;
        }
    }

    /**
     * Method polls task request from the queue of tasks
     *
     * @return polled task request or null
     */
    public synchronized TaskRequest pollTaskRequest() {
        Task task = tasksQueue.peek();
        if (task instanceof TaskRequest) {
            return (TaskRequest) tasksQueue.poll();
        } else {
            return null;
        }
    }

}
