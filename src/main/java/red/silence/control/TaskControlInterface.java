package red.silence.control;

import java.util.List;

/**
 * 任务接口
 * @author Quiet
 * @date 2018-10-26
 */
public interface TaskControlInterface {
    /**
     * 结果集合并处理
     * @param results 结果集
     * @param taskEventInterface //任务线程事件控制器
     */
    void resultMmerge(List<Object> results, TaskEventInterface taskEventInterface);

    /**
     * 任务分发
     * @param tasks 任务集
     */
    void allocatingTask(List<Object> tasks);

    /**
     * 开始任务
     */
    void run();
}
