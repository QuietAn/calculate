package red.silence.reference;

import red.silence.control.TaskInterface;

import java.util.List;

/**
 * @author Quiet
 * @date 2018-10-27
 */
public class TaskImpl implements TaskInterface {
    private static List<Integer> tasks;

    private int count = 0;
    @Override
    public void run() {
        for(int i : tasks) {
            count += i;
        }
    }

    @Override
    public String getResult() {
        return count+"";
    }

    /**
     * Gets the value of tasks.
     *
     * @return the value of tasks
     */
    public static List<Integer> getTasks() {
        return tasks;
    }

    /**
     * Sets the tasks.
     *
     * <p>You can use getTasks() to get the value of tasks</p>
     *
     * @param tasks tasks
     */
    public static void setTasks(List<Integer> tasks) {
        TaskImpl.tasks = tasks;
    }
}
