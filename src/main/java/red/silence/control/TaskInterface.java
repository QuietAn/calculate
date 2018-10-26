package red.silence.control;

/**
 * @author Quiet
 * @date 2018-10-27
 */
public interface TaskInterface {

    /**
     * 执行计算
     */
    public void run();

    /**
     * 获取结果集
     * @return
     */
    String getResult();
}
