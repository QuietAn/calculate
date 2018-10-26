package red.silence.reference;

import red.silence.control.TaskControlInterface;
import red.silence.control.TaskEventInterface;
import red.silence.control.TaskInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 测试
 * @author Quiet
 * @date 2018-10-26
 */
public class TaskControlImpl implements TaskControlInterface {
    //计算集过
    private int count;

    private Integer[] nums;
    //数据集和
    private List<Integer> tasks;

    //构造器
    public TaskControlImpl() {
        tasks = new ArrayList<>();
        Random random = new Random(47);

        for(int i=0; i<=10; i++) {
            //tasks.add(random.nextInt(1000));
            tasks.add(i);
        }
        nums = new Integer[tasks.size()];
        tasks.toArray(nums);
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
        for(Integer i : nums) {
            System.out.print("i:" + i + " ");

            if((t++)%20 == 0) {
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("计算结果为:" + count);
    }
}
