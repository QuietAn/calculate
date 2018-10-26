package red.silence.control;

import java.util.List;

/**
 * 任务线程事件控制器
 * @author Quiet
 * @date 2018-10-26
 */
public interface TaskEventInterface {
    /**
     * 任务分发
     * @param tasks 任务集
     */
    void allocatingTask(List<Object> tasks);

    /**
     * 任务计算完成
     * 线程退出
     */
    void exit();
}
