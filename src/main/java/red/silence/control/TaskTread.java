package red.silence.control;

/**
 * @author Quiet
 * @date 2018-10-26
 */
public class TaskTread implements Runnable {

    //线程结束标志
    private boolean exitFlag = false;
    //任务控制中心事件通知器
    private final ControlEventInterface cEInterface;

    private TaskInterface taskInterface;

    private final TaskEventInterface taskEventInterface = new TaskEventInterface() {
        @Override
        public void allocatingTask(TaskInterface taskInterface) {
            TaskTread.this.taskInterface = taskInterface;
        }

        @Override
        public void exit() {
            exitFlag = true;
        }
    };

    /**
     * 构造器
     * @param cEInterface 任务中心事件通知器
     */
    public TaskTread(ControlEventInterface cEInterface) {
        this.cEInterface = cEInterface;
    }

    @Override
    public void run() {
        while (!exitFlag) {
            //获取任务
            if(cEInterface.allocatingTask(taskEventInterface)) {
                taskInterface.run();
                cEInterface.resultMmerge(taskInterface);
            }
        }

        cEInterface.threadExit();
    }
}
