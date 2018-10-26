package red.silence.control;

import java.util.List;

/**
 * 事件控制器--任务结果的合并，任务分发
 * @author Quiet
 * @date 2018-10-26
 */
public interface ControlEventInterface {
    /**
     * 结果集合并处理
     * @param results 结果集
     * @param taskEventInterface //任务线程事件控制器
     */
    void resultMmerge(List<Object> results, TaskEventInterface taskEventInterface);

    /**
     * 任务分发控制器
     * @param taskEventInterface //任务线程事件控制器
     */
    void allocatingTask(TaskEventInterface taskEventInterface);
}
