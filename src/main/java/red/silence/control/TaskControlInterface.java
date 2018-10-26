package red.silence.control;

/**
 * 任务接口
 * @author Quiet
 * @date 2018-10-26
 */
public interface TaskControlInterface {
    /**
     * 结果集合并处理
     * @param taskInterface 结果
     */
    void resultMmerge(TaskInterface taskInterface);

    /**
     * 分配任务
     * @return
     */
    TaskInterface allocatingTask();

    /**
     * 查询是否还有任务
     * @return
     */
    boolean hasTask();
    /**
     * 任务完成退出任务
     */
    void exit();
}
