package pjwstk.edu.pl.s27619.tasks;

import java.util.Random;

public enum TaskPriority {
    HIGH,
    NORMAL,
    LOW;

    /**
     * Method generates random task priority
     *
     * @return one of the given priorities
     */
    public static TaskPriority generateTaskPriority() {

        // Assign all given priorities into array and generate random index from length of that array, then return
        // priority with generated index
        TaskPriority[] taskPriorities = TaskPriority.values();
        TaskPriority randomPriority = taskPriorities[new Random().nextInt(taskPriorities.length)];

        return randomPriority;
    }

}
