package pjwstk.edu.pl.s27619.clients;

import pjwstk.edu.pl.s27619.tasks.TaskQueue;
import pjwstk.edu.pl.s27619.tasks.TaskRequest;
import pjwstk.edu.pl.s27619.tasks.TaskResponse;

public final class ClientService extends Client {

    public ClientService(TaskQueue taskQueue) {
        super(taskQueue);
    }

    /**
     * Method starts thread of client service
     */
    @Override
    public void run() {

        while (true) {

            TaskRequest taskRequest = getTaskQueue().pollTaskRequest(); // Contains task request from the queue of tasks

            // Check if task request is not empty and call method to add this request to the queue of tasks, if
            // task request is empty we just wait 1 sec and try to take request once more
            if (taskRequest != null) {
                clientServiceRequest(taskRequest);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted exception in thread of client service");
            }
        }
    }

    /**
     * Method handles client service requests
     *
     * @param taskRequest given task which should be handled
     */
    public void clientServiceRequest(TaskRequest taskRequest) {
        System.out.println("Request" + taskRequest + " is running");
        TaskResponse taskResponse = calculateTask(taskRequest);
        getTaskQueue().addTask(taskResponse);
    }

    /**
     * Method to calculate sum of two values given in task request
     *
     * @param taskRequest task which should be calculated
     * @return new task response
     */
    public TaskResponse calculateTask(TaskRequest taskRequest) {
        return new TaskResponse(taskRequest);
    }

}
