package red.silence.reference;

import red.silence.control.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 测试
 * @author Quiet
 * @date 2018-10-26
 */
public class TaskControlImpl implements TaskControlInterface {
    //计算结果
    private int count;

    //数据集和
    private List<Integer> tasks;

    //构造器
    public TaskControlImpl() {
        tasks = new ArrayList<>();
        for(int i=0; i<=100; i++) {
            tasks.add(i);
        }
    }

    public TaskControlImpl(Random random) {
        tasks = new ArrayList<>();

        for(int i=0; i<=100; i++) {
            tasks.add(random.nextInt(10000));
        }
    }

    @Override
    public void resultMmerge(TaskInterface taskInterface) {
        Integer tR = Integer.parseInt(taskInterface.getResult());
        count += tR;
    }

    @Override
    public TaskInterface allocatingTask() {
        List<Integer> list = new ArrayList<>();
        for(int i=tasks.size()-1; i>=0 && tasks.size()>0; i--) {
            list.add(tasks.get(i));
            tasks.remove(i);
        }

        TaskImpl task = new TaskImpl();
        TaskImpl.setTasks(list);

        return task;
    }

    @Override
    public boolean hasTask() {
        return tasks.size() != 0;
    }

    @Override
    public void exit() {
        int t = 0;
        System.out.println("计算结果为:" + count);
    }
}
