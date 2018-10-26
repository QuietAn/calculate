package red.silence.control;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务控制中心
 * @author Quiet
 * @date 2018-10-26
 */
public class Control {

    //任务计数
    private int count = 0;
    //线程计数
    private int threadCount = 0;
    //结果集
    private List<Object> results;
    //事件控制器--任务结果的合并，任务分发
    private final ControlEventInterface controlEvent = new ControlEventInterface(){
        @Override
        public void resultMmerge(List<Object> results, TaskEventInterface taskEventInterface) {
            //Print.println("ControlEventInterface.resultMmerge");
            Control.this.resultMmerge(results, taskEventInterface);
        }

        @Override
        public void allocatingTask(TaskEventInterface taskEventInterface) {
            Print.println("ControlEventInterface.allocatingTask");
            Control.this.allocatingTask(taskEventInterface);
        }
    };

    //任务合并
    private synchronized void resultMmerge(List<Object> results, TaskEventInterface taskEventInterface){
        //任务计数--
        if(++count > 1000) {
            //任务线程退出
            taskEventInterface.exit();
            //任务线程计数
            if(--threadCount == 0) {
                System.out.println("任务结束");
            }
        }

        //合并结果
        this.results.addAll(results);
        //分配新任务
        this.allocatingTask(taskEventInterface);
        Print.println("Control.resultMmerge()");
    }

    //任务分发
    private synchronized void allocatingTask(TaskEventInterface taskEventInterface){
        //Print.println("Control.allocatingTask()");
        taskEventInterface.allocatingTask(new ArrayList<>());
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
