package pjwstk.edu.pl.s27619.clients;

import pjwstk.edu.pl.s27619.tasks.TaskQueue;
import pjwstk.edu.pl.s27619.tasks.TaskRequest;
import pjwstk.edu.pl.s27619.tasks.TaskResponse;

public class ClientRequestor extends Client {

    public ClientRequestor(TaskQueue taskQueue) {
        super(taskQueue);
    }

    /**
     * Method which starts client requestor thread
     */
    @Override
    public void run() {

        System.out.println("Client Requestor is running " + this);

        while (true) {
            addTaskRequest();

            boolean responseFinished = false; // Boolean flag which contains current info about current response

            while (!responseFinished) {

                TaskResponse taskResponse = pollTaskResponse();

                // If task response will be null, then we can assume that current response is completed and can go for
                // the next response
                if (taskResponse != null) {
                    System.out.println("Response = " + taskResponse);
                    responseFinished = true;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted exception in thread client requestor was thrown");
                }

            }
        }
    }

    /**
     * Method adds task request to the queue of tasks
     */
    public void addTaskRequest() {
        getTaskQueue().addTask(new TaskRequest(this));
    }

    /**
     * Method polls task response in the queue of tasks
     *
     * @return polled task response from the task of queue
     */
    public TaskResponse pollTaskResponse() {
        return getTaskQueue().pollTaskResponse(this);
    }

}
