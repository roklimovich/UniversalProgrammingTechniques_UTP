package pjwstk.edu.pl.s27619.tasks;

import pjwstk.edu.pl.s27619.clients.ClientRequestor;

import java.util.Random;

public final class TaskRequest extends Task {
    private Random random = new Random();
    private final int value1;
    private final int value2;

    public TaskRequest(ClientRequestor clientRequestor) {
        super(clientRequestor, TaskPriority.generateTaskPriority());
        value1 = random.nextInt(100); // generate any random int value between [0, 100)
        value2 = random.nextInt(100); // generate any random int value between [0, 100)
    }

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    @Override
    public String toString() {
        return super.toString() + "[" + value1 + ", " + value2 + "]";
    }

}
