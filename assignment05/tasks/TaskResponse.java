package pjwstk.edu.pl.s27619.tasks;

public class TaskResponse extends Task {
    private int result;

    public TaskResponse(TaskRequest taskRequest) {
        super(taskRequest.getRequestor(), taskRequest.getTaskPriority());
        result = taskRequest.getValue1() + taskRequest.getValue2(); // Calculate sum of two given values
    }

    @Override
    public String toString() {
        return "[" + result + "]";
    }

}
