package red.silence.control;

/**
 * 任务控制中心
 * @author Quiet
 * @date 2018-10-26
 */
public class Control {

    //线程计数
    private int threadCount = 0;
    //结果集
    private int result = 0;

    private boolean hasTask = true;

    private TaskControlInterface taskControl;

    //事件控制器--任务结果的合并，任务分发
    private final ControlEventInterface controlEvent = new ControlEventInterface(){
        @Override
        public synchronized void resultMmerge(TaskInterface taskInterface) {
            taskControl.resultMmerge(taskInterface);
        }

        @Override
        public synchronized boolean allocatingTask(TaskEventInterface taskEventInterface) {
            if(hasTask()) {
                taskEventInterface.allocatingTask(taskControl.allocatingTask());
                return true;
            } {
                taskEventInterface.exit();
                return false;
            }
        }

        @Override
        public synchronized void threadExit() {
            if(--threadCount == 0) {
                System.out.println("任务结束");
                taskControl.exit();
            }
        }
    };

    public boolean hasTask() {
        if(hasTask) {
            hasTask = taskControl.hasTask();
        }

        return hasTask;
    }

    public Control(TaskControlInterface taskControl) {
        this.taskControl = taskControl;
    }

    /***
     * 获取事件通知器
     * @return ControlEventInterface
     */
    public ControlEventInterface getControl() {
        //任务线程计数
        threadCount++;
        return controlEvent;
    }
}
