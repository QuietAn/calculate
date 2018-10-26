package red.silence.control;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Quiet
 * @date 2018-10-26
 */
public class TaskTread implements Runnable {

    //线程结束标志
    private boolean exitFlag = false;
    //任务控制中心事件通知器
    private final ControlEventInterface cEInterface;
    //当前任务集
    private List<Object> tasks;
    //计算结果
    private List<Object> results = new ArrayList<>();

    private final TaskEventInterface taskEventInterface = new TaskEventInterface() {
        @Override
        public void allocatingTask(List<Object> tasks) {
            Print.println("TaskTread.allocatingTask");
            TaskTread.this.allocatingTask(tasks);
        }

        @Override
        public void exit() {
            exitFlag = true;
        }
    };

    //接收任务
    private void allocatingTask(List<Object> tasks){
        Print.println("TaskTread.allocatingTask");
        this.tasks = tasks;
    }

    //计算任务
    private void count() {
        int count = 0;
        for(int i=0; i<100; i++) {
            count += i;
        }

        results.add(count);

        Print.println("cout call count:" + count);
        cEInterface.resultMmerge(results, taskEventInterface);
    }

    /**
     * 构造器
     * @param cEInterface 任务中心事件通知器
     */
    public TaskTread(ControlEventInterface cEInterface) {
        this.cEInterface = cEInterface;
    }

    @Override
    public void run() {
        //获取任务
        cEInterface.allocatingTask(taskEventInterface);

        while (!exitFlag) {
            //任务计算
            count();
        }
    }

    public static void main(String[] args) {
        Control control = new Control();
        for(int i=0; i<10; i++) {
            Thread thread = new Thread(new TaskTread(control.getControl()));

            thread.start();
        }
    }
}
