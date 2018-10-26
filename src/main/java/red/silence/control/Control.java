package red.silence.control;

/**
 * 任务控制中心
 * @author Quiet
 * @date 2018-10-26
 */
public class Control {

    private Long systemStartTime;
    private Long currentTaskTime;

    //线程计数
    private int threadCount = 0;

    private int taskCount = 0;

    private boolean hasTask = true;

    private TaskControlInterface taskControl;

    private ThreadPam threadPam;

    private static Control control = new Control();

    private Control() {
    }

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
                taskControl.exit();

                System.out.println("第" + taskCount + "任务结束; 耗时："
                        + (System.currentTimeMillis()-control.currentTaskTime) + "毫秒");
                run();
            }
        }
    };

    private boolean hasTask() {
        if(hasTask) {
            hasTask = taskControl.hasTask();
        }

        return hasTask;
    }

    /***
     * 获取事件通知器
     * @return ControlEventInterface
     */
    private ControlEventInterface getControl() {
        //任务线程计数
        threadCount++;
        return controlEvent;
    }

    public static void run(ThreadPam threadPam) {
        control.systemStartTime = System.currentTimeMillis();

        control.threadPam = threadPam;
        run();
    }

    private static void run() {
        control.taskCount ++;
        control.hasTask = true;
        control.currentTaskTime = System.currentTimeMillis();
        if(null != control.taskControl) {
            control.taskControl = null;
        }

        control.taskControl = control.threadPam.getTaskControl();

        if(null == control.taskControl) {
            System.out.println("任务完成，系统退出！");
            System.out.println("所有任务共耗时：" + (System.currentTimeMillis()-control.systemStartTime) + "毫秒");
            return;
        }

        for(int i=0; i<=control.threadPam.getThreadNum() && control.hasTask(); i++) {
            Thread thread = new Thread(new TaskTread(control.getControl()));
            thread.start();
        }
    }
}
