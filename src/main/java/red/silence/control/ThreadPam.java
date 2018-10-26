package red.silence.control;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Quiet
 * @date 2018-10-27
 */
public class ThreadPam {
    private int threadNum;

    private List<TaskControlInterface> tempTaskControls;
    private List<TaskControlInterface> taskControls;

    public ThreadPam(int threadNum, List<TaskControlInterface> taskControls){
        this.threadNum = threadNum;
        this.taskControls = taskControls;
        tempTaskControls = new ArrayList<>(taskControls.size());
        tempTaskControls.addAll(taskControls);
    }

    public int getThreadNum() {
        return this.threadNum;
    }

    public TaskControlInterface getTaskControl() {
        if(null != taskControls && taskControls.size() > 0) {
            TaskControlInterface taskC = taskControls.get(0);
            tempTaskControls.add(taskC);

            taskControls.remove(0);

            return taskC;
        }

        return null;
    }
}
