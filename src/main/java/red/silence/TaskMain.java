package red.silence;

import red.silence.control.Control;
import red.silence.control.TaskTread;
import red.silence.reference.TaskControlImpl;

/**
 * Hello world!
 *
 */
public class TaskMain
{
    public static void main(String[] args) {
        Control control = new Control(new TaskControlImpl());
        for(int i=0; i<10 && control.hasTask(); i++) {
            Thread thread = new Thread(new TaskTread(control.getControl()));

            thread.start();
        }
    }
}
