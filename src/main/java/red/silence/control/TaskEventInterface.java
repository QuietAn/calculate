package red.silence.control;

/**
 * 任务线程事件控制器
 * @author Quiet
 * @date 2018-10-26
 */
public interface TaskEventInterface {
    /**
     * 任务分发
     * @param taskInterface 任务
     */
    void allocatingTask(TaskInterface taskInterface);

    /**
     * 任务计算完成
     * 线程退出
     */
    void exit();
}
