package pjwstk.edu.pl.s27619.clients;

import pjwstk.edu.pl.s27619.tasks.TaskQueue;

public abstract class Client implements Runnable{

    private static int currentId = 0;
    private final int id;
    private TaskQueue taskQueue;

    public Client(TaskQueue taskQueue){
        id = getCurrentId();
        this.taskQueue = taskQueue;
    }

    private static int getCurrentId(){
        currentId++;
        return currentId;
    }

    private int getId(){
        return id;
    }

    public TaskQueue getTaskQueue() {
        return taskQueue;
    }

    @Override
    public String toString() {
        return "Client ID: " + getId();
    }
}
