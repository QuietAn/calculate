package red.silence.control;

/**
 * @author Quiet
 * @date 2018-10-26
 */
public class Print {
    public static void println(Object obj){
        System.out.println(Thread.currentThread().getName() + " :" + obj.toString());
    }
}
