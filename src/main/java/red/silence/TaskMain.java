package red.silence;

import red.silence.control.Control;
import red.silence.control.TaskControlInterface;
import red.silence.control.ThreadPam;
import red.silence.reference.TaskControlImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class TaskMain
{
    public static void main(String[] args) {
        List<TaskControlInterface> taskControls = new ArrayList<>();
        taskControls.add(new TaskControlImpl());
        taskControls.add(new TaskControlImpl(new Random()));

        ThreadPam threadPam = new ThreadPam(10, taskControls);

        Control.run(threadPam);
    }
}
