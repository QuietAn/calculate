package red.silence.control;

/**
 * 事件控制器--任务结果的合并，任务分发
 * @author Quiet
 * @date 2018-10-26
 */
public interface ControlEventInterface {
    /**
     * 任务计算结果合并
     * @param taskInterface 任务
     */
    void resultMmerge(TaskInterface taskInterface);

    /**
     * 任务分发控制器
     * @param taskEventInterface //任务线程事件控制器
     */
    boolean allocatingTask(TaskEventInterface taskEventInterface);

    /**
     * 任务线程退出
     */
    void threadExit();
}
